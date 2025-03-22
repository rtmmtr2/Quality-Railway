package com.example.qr.blocks;
import net.minecraft.world.level.block.state.BlockState; // 导入 BlockState
import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.level.BlockGetter; // 导入 BlockGetter
import net.minecraft.world.phys.shapes.CollisionContext; // 导入 CollisionContext
import net.minecraft.core.BlockPos; // 导入 BlockPos
public class c70_left_face extends Block {
    // 自定义碰撞箱（1x1x1）
    private static final VoxelShape SHAPE = Shapes.or(
            // 底部平板（占满XZ平面，高度0-0.25）
            Shapes.box(0.0, 0.0, 0.0, 1.0, 0.25, 1.0),

            // 中部立方体（XZ方向0.25-0.75，高度0.25-0.75）
            Shapes.box(0.25, 0.25, 0.25, 0.75, 0.75, 0.75),

            // 顶部平板（占满XZ平面，高度0.75-1.0）
            Shapes.box(0.0, 0.75, 0.0, 1.0, 1.0, 1.0)
    );

    public c70_left_face(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}