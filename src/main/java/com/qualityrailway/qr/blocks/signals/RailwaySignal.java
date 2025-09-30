package com.qualityrailway.qr.blocks.signals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

// 自定义信号方块类，继承Block并实现EntityBlock接口
public class RailwaySignal extends Block implements EntityBlock {
    // 定义信号强度属性，范围0-15
    public static final IntegerProperty SIGNAL_STRENGTH = IntegerProperty.create("signal", 0, 15);
    // 定义模型状态属性，对应6种不同的模型
    public static final IntegerProperty MODEL_STATE = IntegerProperty.create("model_state", 0, 5);
    public static final DirectionProperty FACING = HORIZONTAL_FACING;
    // 定义不同方向的碰撞箱

    private static final VoxelShape SHAPE_NORTH = Block.box(0, 0, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_SOUTH = Block.box(0, 0, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_WEST = Block.box(0, 0, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_EAST = Block.box(0, 0, 0, 16, 16, 16);

    public RailwaySignal() {
        super(Block.Properties.of(Material.STONE)
                .strength(2.0f).noOcclusion());//半透明渲染


        // 注册默认方块状态
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(SIGNAL_STRENGTH, 0)
                .setValue(FACING, Direction.NORTH)
                .setValue(MODEL_STATE, 0));
    }

    // 创建方块实体
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RailwaySignalEntity(pos, state);
    }

    // 当方块被放置时调用，设置面向方向
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getNearestLookingDirection().getOpposite());
    }

    // 添加方块状态属性
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(SIGNAL_STRENGTH, MODEL_STATE, FACING);
    }

    // 获取碰撞箱形状，根据放置方向变化
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);

        switch (direction) {
            case NORTH: return SHAPE_NORTH;
            case SOUTH: return SHAPE_SOUTH;
            case WEST: return SHAPE_WEST;
            case EAST: return SHAPE_EAST;
            default: return SHAPE_NORTH;
        }
    }

    // 当相邻方块红石信号变化时调用
    @Override
    public void neighborChanged(BlockState state, Level world, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (!world.isClientSide) {
            int currentPower = world.getBestNeighborSignal(pos);
            BlockEntity tileEntity = world.getBlockEntity(pos);

            if (tileEntity instanceof RailwaySignalEntity) {
                ((RailwaySignalEntity) tileEntity).updateSignalStrength(currentPower);
            }

            // 强制加载周围区块（半径为2）
            forceLoadChunks(world, pos);
        }
    }

    // 强制加载周围区块的方法
    private void forceLoadChunks(Level world, BlockPos pos) {
        int chunkX = pos.getX() >> 4;
        int chunkZ = pos.getZ() >> 4;

        // 加载周围5x5区块区域（半径为2）
        for (int x = chunkX - 5; x <= chunkX + 5; x++) {
            for (int z = chunkZ - 5; z <= chunkZ + 5; z++) {
                // 修复：移除第三个参数，只传递区块坐标
                world.getChunkSource().updateChunkForced(world.getChunk(x, z).getPos(), true);
            }
        }
    }

    // 当方块被破坏时调用，取消强制加载
    @Override
    public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!world.isClientSide && !state.is(newState.getBlock())) {
            int chunkX = pos.getX() >> 4;
            int chunkZ = pos.getZ() >> 4;

            // 取消强制加载周围区块
            for (int x = chunkX - 5; x <= chunkX + 5; x++) {
                for (int z = chunkZ - 5; z <= chunkZ + 5; z++) {
                    // 修复：移除第三个参数，只传递区块坐标
                    world.getChunkSource().updateChunkForced(world.getChunk(x, z).getPos(), false);
                }
            }
        }
        super.onRemove(state, world, pos, newState, isMoving);
    }
}