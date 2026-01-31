package com.qualityrailway.qr.items;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.lang.reflect.Method;
import java.util.function.Predicate;

public class TrackTargetingBlockItem extends BlockItem {
    private static final Logger LOGGER = LogManager.getLogger(TrackTargetingBlockItem.class);

    // NBT键名
    public static final String TRACK_TARGETING_DATA = "CreateTrackTargeting";
    private static final String TARGET_TRACK = "TargetTrack";
    private static final String TARGET_DIRECTION = "TargetDirection";

    private final Predicate<BlockState> trackValidator;

    public TrackTargetingBlockItem(Block block, Item.Properties properties, Predicate<BlockState> trackValidator) {
        super(block, properties);
        this.trackValidator = trackValidator;
    }

    public TrackTargetingBlockItem(Block block, Item.Properties properties) {
        this(block, properties, TrackTargetingBlockItem::defaultTrackValidator);
    }

    private static boolean defaultTrackValidator(BlockState state) {
        String registryName = state.getBlock().getRegistryName().toString();
        return registryName.contains("create:track");
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        if (level.isClientSide) {
            // 客户端：处理渲染预览
            return InteractionResult.SUCCESS;
        }

        // 服务器端：验证点击的方块
        BlockState clickedState = level.getBlockState(pos);
        if (!trackValidator.test(clickedState)) {
            // 不是轨道，使用默认行为
            return super.useOn(context);
        }

        LOGGER.debug("点击了轨道方块，开始轨道目标选择");

        // 获取命中结果
        BlockHitResult hit = getHitResultFromContext(context);
        if (hit == null) {
            LOGGER.error("无法获取命中结果");
            return InteractionResult.FAIL;
        }

        Vec3 hitLoc = hit.getLocation();
        Vec3 relativeHit = hitLoc.subtract(Vec3.atCenterOf(pos));

        // 确定目标方向（简化版本）
        Direction.AxisDirection targetDirection = calculateTargetDirection(relativeHit, hit.getDirection());

        // 保存轨道目标数据到物品
        ItemStack stack = context.getItemInHand();
        CompoundTag tag = stack.getOrCreateTag();
        CompoundTag targetingData = new CompoundTag();

        targetingData.put(TARGET_TRACK, NbtUtils.writeBlockPos(pos));
        targetingData.putBoolean(TARGET_DIRECTION, targetDirection == Direction.AxisDirection.POSITIVE);

        tag.put(TRACK_TARGETING_DATA, targetingData);

        // 通知玩家
        if (player != null) {
            player.displayClientMessage(new TextComponent("已选择轨道目标"), true);
        }

        return InteractionResult.CONSUME;
    }

    @Nullable
    private BlockHitResult getHitResultFromContext(UseOnContext context) {
        try {
            // 尝试使用反射访问protected方法
            Method getHitResultMethod = UseOnContext.class.getDeclaredMethod("getHitResult");
            getHitResultMethod.setAccessible(true);
            return (BlockHitResult) getHitResultMethod.invoke(context);
        } catch (Exception e) {
            LOGGER.error("无法通过反射获取命中结果: {}", e.getMessage());

            // 尝试替代方案：使用公开的方法重建BlockHitResult
            try {
                // UseOnContext有getClickLocation()和getClickedFace()等公开方法
                Vec3 clickLocation = context.getClickLocation();
                Direction clickedFace = context.getClickedFace();
                BlockPos clickedPos = context.getClickedPos();

                return new BlockHitResult(
                    clickLocation,
                    clickedFace,
                    clickedPos,
                    false
                );
            } catch (Exception e2) {
                LOGGER.error("创建BlockHitResult失败: {}", e2.getMessage());
                return null;
            }
        }
    }

    @Nullable
    @Override
    public BlockPlaceContext updatePlacementContext(BlockPlaceContext context) {
        // 如果有轨道目标数据，调整放置位置
        ItemStack stack = context.getItemInHand();
        CompoundTag tag = stack.getTag();

        if (tag != null && tag.contains(TRACK_TARGETING_DATA)) {
            CompoundTag targetingData = tag.getCompound(TRACK_TARGETING_DATA);

            if (targetingData.contains(TARGET_TRACK)) {
                BlockPos targetTrack = NbtUtils.readBlockPos(targetingData.getCompound(TARGET_TRACK));
                BlockPos signalPos = calculateSignalPosition(targetTrack, context);

                if (signalPos != null && context.getLevel().getBlockState(signalPos).getMaterial().isReplaceable()) {
                    // 创建新的放置上下文
                    BlockHitResult hitResult = new BlockHitResult(
                        Vec3.atCenterOf(signalPos),
                        Direction.UP,
                        signalPos,
                        false
                    );

                    return new BlockPlaceContext(
                        context.getPlayer(),
                        context.getHand(),
                        stack,
                        hitResult
                    ) {
                        @Override
                        public boolean canPlace() {
                            return true;
                        }

                        @Override
                        public boolean replacingClickedOnBlock() {
                            return false;
                        }
                    };
                }
            }
        }

        return super.updatePlacementContext(context);
    }

    @Nullable
    private BlockPos calculateSignalPosition(BlockPos trackPos, BlockPlaceContext context) {
        // 信号机通常放在轨道侧面或旁边
        // 这里使用简化逻辑：放在轨道旁边
        Direction playerFacing = context.getHorizontalDirection();
        return trackPos.relative(playerFacing);
    }

    private Direction.AxisDirection calculateTargetDirection(Vec3 relativeHit, Direction face) {
        // 简化计算方向
        if (face.getAxis() == Direction.Axis.X) {
            return relativeHit.z > 0 ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE;
        } else {
            return relativeHit.x > 0 ? Direction.AxisDirection.POSITIVE : Direction.AxisDirection.NEGATIVE;
        }
    }

    @Override
    protected boolean placeBlock(BlockPlaceContext context, BlockState state) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();

        // 先放置方块
        if (!super.placeBlock(context, state)) {
            return false;
        }

        // 传递轨道目标数据给方块实体
        if (level.getBlockEntity(pos) instanceof TrackTargetingBlockEntity trackTargetingBE) {
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains(TRACK_TARGETING_DATA)) {
                CompoundTag targetingData = tag.getCompound(TRACK_TARGETING_DATA);

                // 传递给方块实体
                trackTargetingBE.setTrackTargetingData(targetingData);
                LOGGER.debug("已将轨道目标数据传递给方块实体: {}", pos);
            }
        }

        // 清除物品中的轨道目标数据
        stack.removeTagKey(TRACK_TARGETING_DATA);

        return true;
    }

    // 轨道目标方块实体接口
    public interface TrackTargetingBlockEntity {
        void setTrackTargetingData(CompoundTag data);
    }
}