package com.qualityrailway.qr.blocks.c70;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;


public class c70_front_board extends Block {
    public static final DirectionProperty FACING = HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_NORTH = Shapes.box(-1, -0.5+0.125, 0.75, 1, 1.5+0.125, 1);
    private static final VoxelShape SHAPE_EAST = Shapes.box(0, -0.5+0.125, -1, 0.25, 1.5+0.125, 1);
    private static final VoxelShape SHAPE_SOUTH = Shapes.box(0, -0.5+0.125, 0, 2, 1.5+0.125, 0.25);
    private static final VoxelShape SHAPE_WEST = Shapes.box(0.75, -0.5+0.125, 0, 1, 1.5+0.125, 2);


    public c70_front_board(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH));
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH;
        };
    }


    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // 获取玩家水平朝向的逆方向，使方块正面朝向玩家
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }


    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}