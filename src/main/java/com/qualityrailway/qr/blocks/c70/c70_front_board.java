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

/**
 * 自定义方块类，支持：
 * 1. OBJ 模型和贴图
 * 2. 碰撞箱
 * 3. 根据玩家放置方向旋转
 */
public class c70_front_board extends Block {
    // 方向属性（水平方向）
    public static final DirectionProperty FACING = HORIZONTAL_FACING;

    // 定义碰撞箱
    private static final VoxelShape SHAPE_NORTH = Shapes.box(-1, -0.5+0.125, 0.75, 1, 1.5+0.125, 1);
    // 朝北时的碰撞箱
    private static final VoxelShape SHAPE_EAST = Shapes.box(0, -0.5+0.125, -1, 0.25, 1.5+0.125, 1);
    // 朝东时的碰撞箱
    private static final VoxelShape SHAPE_SOUTH = Shapes.box(0, -0.5+0.125, 0, 2, 1.5+0.125, 0.25);
    // 朝南时的碰撞箱
    private static final VoxelShape SHAPE_WEST = Shapes.box(0.75, -0.5+0.125, 0, 1, 1.5+0.125, 2);
    // 朝西时的碰撞箱

    /**
     * 构造函数
     * @param properties 方块属性
     */
    public c70_front_board(Properties properties) {
        super(properties);
        // 设置默认方块状态（朝北）
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH));
    }

    /**
     * 获取方块的碰撞箱形状
     * @param state 方块状态
     * @param world 世界对象
     * @param pos 方块位置
     * @param context 碰撞上下文
     * @return 对应方向的碰撞箱形状
     */
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        // 根据方块方向返回对应的碰撞箱形状
        return switch (state.getValue(FACING)) {
            case NORTH -> SHAPE_NORTH;
            case EAST -> SHAPE_EAST;
            case SOUTH -> SHAPE_SOUTH;
            case WEST -> SHAPE_WEST;
            default -> SHAPE_NORTH; // 默认返回完整方块形状
        };
    }

    /**
     * 获取方块放置时的状态
     * @param context 放置上下文
     * @return 方块状态
     */
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // 获取玩家水平朝向的逆方向，使方块正面朝向玩家
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    /**
     * 注册方块状态属性
     * @param builder 状态定义构建器
     */
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}