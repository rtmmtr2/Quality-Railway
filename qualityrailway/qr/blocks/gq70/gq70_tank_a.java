package com.qualityrailway.qr.blocks.gq70;

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


public class gq70_tank_a extends Block {

    public static final DirectionProperty FACING = HORIZONTAL_FACING;


    private static final VoxelShape SHAPE_NORTH = Shapes.box(-1, 0, 0, 2, 2, 1);

    private static final VoxelShape SHAPE_EAST = Shapes.box(0, 0, -1, 1, 2, 2);

    private static final VoxelShape SHAPE_SOUTH = Shapes.box(-1, 0, 0, 2, 2, 1);

    private static final VoxelShape SHAPE_WEST = Shapes.box(0, 0, -1, 1, 2, 2);



    public gq70_tank_a(Properties properties) {
        super(properties);
        // 设置默认方块状态（朝北）
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