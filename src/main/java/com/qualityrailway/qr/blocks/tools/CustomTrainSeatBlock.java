package com.qualityrailway.qr.blocks.tools;

import com.simibubi.create.content.contraptions.actors.seat.SeatBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class CustomTrainSeatBlock extends SeatBlock {

    // 方向属性（水平方向）
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    // 基础碰撞箱（朝北时的碰撞箱） - 非对称，可以测试旋转效果
    private static final VoxelShape SHAPE_NORTH = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    private static final VoxelShape SHAPE_EAST = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.00D);
    private static final VoxelShape SHAPE_SOUTH = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);
    private static final VoxelShape SHAPE_WEST = Block.box(0.0, 0.0, 0.0, 16.0, 8.0, 16.0);

    public CustomTrainSeatBlock(Properties properties) {
        super(properties, null, true); // color为null，inCreativeTab为true
        // 注册默认状态，面朝北
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // 根据玩家朝向确定座椅方向
        // 默认：玩家面对的方向就是座椅的正方向
        Direction direction = context.getHorizontalDirection();

        // 调用父类获取基础状态（包括WATERLOGGED）
        BlockState baseState = super.getStateForPlacement(context);
        if (baseState == null) {
            baseState = this.defaultBlockState();
        }

        return baseState.setValue(FACING, direction);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        // 根据方向返回对应的碰撞箱形状
        Direction direction = state.getValue(FACING);

        switch (direction) {
            case EAST:
                return SHAPE_EAST;
            case SOUTH:
                return SHAPE_SOUTH;
            case WEST:
                return SHAPE_WEST;
            case NORTH:
            default:
                return SHAPE_NORTH;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        // 碰撞箱与形状相同
        return getShape(state, world, pos, context);
    }
}