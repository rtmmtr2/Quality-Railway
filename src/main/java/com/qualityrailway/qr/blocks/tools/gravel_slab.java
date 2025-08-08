package com.qualityrailway.qr.blocks.tools;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class gravel_slab extends SlabBlock {

    private static final VoxelShape BOTTOM_SHAPE = Block.box(0, 0, 0, 16, 8, 16);
    private static final VoxelShape TOP_SHAPE = Block.box(0, 8, 0, 16, 16, 16);
    private static final VoxelShape DOUBLE_SHAPE = Block.box(0, 0, 0, 16, 16, 16);

    public gravel_slab(Properties properties) {
        super(properties);
    }


    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        SlabType type = state.getValue(SlabBlock.TYPE);

        return switch (type) {
            case BOTTOM -> BOTTOM_SHAPE;
            case TOP -> TOP_SHAPE;
            case DOUBLE -> DOUBLE_SHAPE;
        };
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return getCollisionShape(state, world, pos, context);
    }
}