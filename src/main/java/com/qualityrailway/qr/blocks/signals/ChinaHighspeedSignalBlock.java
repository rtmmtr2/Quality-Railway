package com.qualityrailway.qr.blocks.signals;

import com.qualityrailway.qr.signals.SignalTypes;
import com.qualityrailway.qr.blockentity.signals.ChinaHighspeedSignalBlockEntity;
import com.simibubi.create.AllItems;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.content.trains.track.TrackTargetingBehaviour;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.blockEntity.SmartBlockEntity;
import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.util.Random;

public class ChinaHighspeedSignalBlock extends Block implements IBE<ChinaHighspeedSignalBlockEntity>, IWrenchable {

    private static final Logger LOGGER = LogManager.getLogger(ChinaHighspeedSignalBlock.class);

    public static final EnumProperty<SignalTypes> TYPE = EnumProperty.create("type", SignalTypes.class);
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final IntegerProperty LIGHT_STATE = IntegerProperty.create("light_state", 0, 5);

    public ChinaHighspeedSignalBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(TYPE, SignalTypes.CHINA_HIGHSPEED_SIGNAL)
                .setValue(POWERED, false)
                .setValue(LIT, true)
                .setValue(LIGHT_STATE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TYPE, POWERED, LIT, LIGHT_STATE);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Direction facing = context.getHorizontalDirection().getOpposite();

        LOGGER.debug("放置高铁信号机，位置: {}, 朝向: {}", pos, facing);

        // 检查是否有轨道可以绑定
        if (canConnectToTrack(level, pos, facing)) {
            LOGGER.debug("找到可连接的轨道");
        }

        return this.defaultBlockState()
                .setValue(POWERED, level.hasNeighborSignal(pos))
                .setValue(LIT, true)
                .setValue(LIGHT_STATE, 0);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        ItemStack item = player.getItemInHand(hand);

        // 如果手持扳手（Create的wrench），使用扳手功能
        if (item.is(AllItems.WRENCH.get())) {
            return onWrenched(state, new UseOnContext(player, hand, hit));
        }

        // 检查是否可以连接到轨道
        withBlockEntityDo(level, pos, be -> {
            if (be != null) {
                TrackTargetingBehaviour<?> edgePoint = be.edgePoint;
                if (edgePoint != null) {
                    boolean hasTrack = edgePoint.hasValidTrack();
                    boolean hasEdgePoint = edgePoint.getEdgePoint() != null;

                    LOGGER.info("信号机状态 - 位置: {}, 有轨道: {}, 已连接: {}",
                        pos, hasTrack, hasEdgePoint);

                    if (!hasEdgePoint) {
                        player.displayClientMessage(
                            Lang.translateDirect("track_signal.not_attached").append(" "),
                            true
                        );
                    }
                }
            }
        });

        return InteractionResult.SUCCESS;
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();

        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }

        withBlockEntityDo(level, pos, be -> {
            if (be != null) {
                SignalTypes currentType = state.getValue(TYPE);
                SignalTypes[] values = SignalTypes.values();
                int nextIndex = (currentType.ordinal() + 1) % values.length;
                SignalTypes newType = values[nextIndex];

                level.setBlock(pos, state.setValue(TYPE, newType), 3);

                if (player != null) {
                    player.displayClientMessage(
                        Lang.translateDirect("track_signal.mode_change." + newType.getSerializedName()),
                        true
                    );
                }
            }
        });

        return InteractionResult.SUCCESS;
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block,
                               BlockPos fromPos, boolean isMoving) {
        if (level.isClientSide) {
            return;
        }
        boolean powered = state.getValue(POWERED);
        if (powered == level.hasNeighborSignal(pos)) {
            return;
        }
        if (powered) {
            level.scheduleTick(pos, this, 4);
        } else {
            level.setBlock(pos, state.cycle(POWERED), 2);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        if (state.getValue(POWERED) && !level.hasNeighborSignal(pos)) {
            level.setBlock(pos, state.cycle(POWERED), 2);
        }
    }

    @Override
    public Class<ChinaHighspeedSignalBlockEntity> getBlockEntityClass() {
        return ChinaHighspeedSignalBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends ChinaHighspeedSignalBlockEntity> getBlockEntityType() {
        return com.qualityrailway.qr.ModBlockEntities.CHINA_HIGHSPEED_SIGNAL.get();
    }

    @Override
    public boolean shouldCheckWeakPower(BlockState state, LevelReader world, BlockPos pos, Direction side) {
        return false;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return getBlockEntityOptional(level, pos)
                .map(ChinaHighspeedSignalBlockEntity::isPowered)
                .map(powered -> powered ? 15 : 0)
                .orElse(0);
    }

    private boolean canConnectToTrack(Level level, BlockPos pos, Direction facing) {
        // 检查前方是否有轨道
        BlockPos trackPos = pos.relative(facing);
        return isCreateTrack(level, trackPos);
    }

    private boolean isCreateTrack(Level level, BlockPos pos) {
        BlockState state = level.getBlockState(pos);
        // 检查是否是Create的轨道
        return state.getBlock().getRegistryName().toString().contains("create:track");
    }

    // 根据SignalStates获取对应的BlockState值
    public static int getLightStateFromSignalState(com.qualityrailway.qr.signals.SignalStates state) {
        switch (state) {
            case RED:
                return 0; // 红灯
            case YELLOW:
                return 1; // 黄灯
            case GREEN:
                return 2; // 绿灯
            case GREEN_YELLOW:
                return 3; // 绿黄灯
            case YELLOW_YELLOW:
                return 4; // 双黄灯
            case WHITE:
                return 5; // 白灯
            case INVALID:
            default:
                return 0; // 无效时显示红灯
        }
    }

    // 更新BlockState中的灯光状态（可以被SignalBoundary调用）
    public static void updateLightState(Level level, BlockPos pos, com.qualityrailway.qr.signals.SignalStates state) {
        if (level == null || level.isClientSide) {
            return;
        }

        BlockState currentState = level.getBlockState(pos);
        if (currentState.hasProperty(LIGHT_STATE)) {
            int newLightState = getLightStateFromSignalState(state);
            int currentLightState = currentState.getValue(LIGHT_STATE);

            if (newLightState != currentLightState) {
                level.setBlock(pos, currentState.setValue(LIGHT_STATE, newLightState), 3);
                LOGGER.debug("更新信号机灯光状态: {} -> {} 在位置 {}",
                    currentLightState, newLightState, pos);
            }
        }
    }
}