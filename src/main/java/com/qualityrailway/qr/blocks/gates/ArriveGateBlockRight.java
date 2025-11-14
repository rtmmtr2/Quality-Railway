package com.qualityrailway.qr.blocks.gates;

import com.qualityrailway.qr.ModTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;

import java.util.logging.Logger;

public class ArriveGateBlockRight extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    public static final BooleanProperty OPEN = BooleanProperty.create("open");

    private static final VoxelShape SHAPE_SOUTH = Shapes.or(Shapes.box(0.75, 0, -1, 1, 1.3125, 2),Shapes.box(0, 0.3125, 0.5, 0.75, 1.3125, 0.5625));
    private static final VoxelShape SHAPE_NORTH = Shapes.or(Shapes.box(0, 0, -1, 0.25, 1.3125, 2),Shapes.box(0.25, 0.3125, 0.5, 1, 1.3125, 0.5625));
    private static final VoxelShape SHAPE_WEST = Shapes.or(Shapes.box(-1, 0, 0.75, 2, 1.3125, 1),Shapes.box(0.4375, 0.3125, 0, 0.5, 1.3125, 0.75));
    private static final VoxelShape SHAPE_EAST = Shapes.or(Shapes.box(-1, 0, 0, 2, 1.3125, 0.25),Shapes.box(0.45, 0.3125, 0.25, 0.5125, 1.3125, 1));

    private static final VoxelShape SHAPE_OPEN_SOUTH = Shapes.box(0.75, 0, -1, 1, 1.3125, 2);
    private static final VoxelShape SHAPE_OPEN_NORTH = Shapes.box(0, 0, -1, 0.25, 1.3125, 2);
    private static final VoxelShape SHAPE_OPEN_WEST = Shapes.box(-1, 0, 0.75, 2, 1.3125, 1);
    private static final VoxelShape SHAPE_OPEN_EAST = Shapes.box(-1, 0, 0, 2, 1.3125, 0.25);

    public ArriveGateBlockRight(Properties properties) {
        super(properties);

        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(OPEN, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        boolean isOpen = state.getValue(OPEN);

        if (isOpen) {
            switch (direction) {
                case NORTH:
                    return SHAPE_OPEN_NORTH;
                case SOUTH:
                    return SHAPE_OPEN_SOUTH;
                case EAST:
                    return SHAPE_OPEN_EAST;
                case WEST:
                    return SHAPE_OPEN_WEST;
                default:
                    return SHAPE_OPEN_NORTH;
            }
        } else {
            switch (direction) {
                case NORTH:
                    return SHAPE_NORTH;
                case SOUTH:
                    return SHAPE_SOUTH;
                case EAST:
                    return SHAPE_EAST;
                case WEST:
                    return SHAPE_WEST;
                default:
                    return SHAPE_NORTH;
            }
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        // 检查玩家手持物品是否有ticket标签
        ItemStack heldItem = player.getItemInHand(hand);
        if (heldItem.is(ModTags.Items.TICKETS)) {
            System.out.println("Player is holding a ticket.");
            // 检查是否有红石信号输入
            if (world.hasNeighborSignal(pos)) {
                // 检查闸机是否已经开启
                if (!state.getValue(OPEN)) {
                    // 扣除一个车票
                    if (!player.isCreative()) {
                        heldItem.shrink(1); // 减少物品数量

                        // 扣票音效
                        world.playSound(null, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.5F, 1.0F);
                    }

                    // 切换闸机状态
                    world.setBlock(pos, state.setValue(OPEN, true), 3);

                    // 播放音效
                    world.playSound(null, pos, SoundEvents.IRON_DOOR_OPEN, SoundSource.BLOCKS, 1.0F, 1.0F);

                    return InteractionResult.SUCCESS;
                } else {
                    // 闸机已开启，不扣票
                    return InteractionResult.SUCCESS;
                }
            } else {
                // 无红石信号，闸机无法开启
                world.playSound(null, pos, SoundEvents.NOTE_BLOCK_BASS, SoundSource.BLOCKS, 1.0F, 0.5F);
                return InteractionResult.FAIL;
            }
        }
        return InteractionResult.PASS;
    }

    // 用于检测红石信号变化
    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        // 当没有红石信号时，关闭闸机
        if (!world.hasNeighborSignal(pos)) {
            if (state.getValue(OPEN)) {
                world.setBlock(pos, state.setValue(OPEN, false), 3);
                // 播放关音效
                world.playSound(null, pos, SoundEvents.IRON_DOOR_CLOSE, SoundSource.BLOCKS, 1.0F, 1.0F);
            }
        }
        super.neighborChanged(state, world, pos, block, fromPos, isMoving);
    }

    // 获取渲染形状
    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
        return this.getShape(state, world, pos, CollisionContext.empty());
    }
}