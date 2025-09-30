package com.qualityrailway.qr.blocks.signs;

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


public class raise_sign extends Block {
    public static final DirectionProperty FACING = HORIZONTAL_FACING;

    private static final VoxelShape SHAPE_SOUTH = Shapes.or(Shapes.box(0.25, 0, -0.03125, 0.75, 1.5, 0),Shapes.box(0.4375, 0, 0, 0.5625, 1, 0.125));
    private static final VoxelShape SHAPE_WEST = Shapes.or(Shapes.box(1, 0, 0.25, 1.03125, 1.5, 0.75),Shapes.box(0.875, 0, 0.4375, 1, 1, 0.5625));
    private static final VoxelShape SHAPE_NORTH = Shapes.or(Shapes.box(0.25, 0, 1, 0.75, 1.5, 1.03125),Shapes.box(0.4375, 0, 0.875, 0.5625, 1, 1));
    private static final VoxelShape SHAPE_EAST = Shapes.or(Shapes.box(-0.03125, 0, 0.25, 0, 1.5, 0.75),Shapes.box(0, 0, 0.4375, 0.125, 1, 0.5625));


    public raise_sign(Properties properties) {
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
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}