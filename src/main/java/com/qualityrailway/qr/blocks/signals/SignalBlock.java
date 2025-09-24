package com.qualityrailway.qr.blocks.signals;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SignalBlock extends Block {
    // 定义方向属性，表示方块的朝向:cite[9]
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    // 定义红石信号强度属性，范围0-15:cite[5]
    public static final IntegerProperty REDSTONE_SIGNAL = IntegerProperty.create("redstone_signal", 0, 15);

    // 定义模型状态属性，0-5对应不同的模型:cite[9]
    public static final IntegerProperty MODEL_STATE = IntegerProperty.create("model_state", 0, 5);

    // 为每个方向定义碰撞箱形状:cite[6]
    private static final VoxelShape SHAPE_NORTH = Block.box(0, 0, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_SOUTH = Block.box(0, 0, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_EAST = Block.box(0, 0, 0, 16, 16, 16);
    private static final VoxelShape SHAPE_WEST = Block.box(0, 0, 0, 16, 16, 16);

    public SignalBlock(Properties properties) {
        super(properties);
        // 设置默认状态：朝北、红石信号0、模型状态0:cite[9]
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(REDSTONE_SIGNAL, 0)
                .setValue(MODEL_STATE, 0));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        // 注册方块状态属性:cite[9]
        builder.add(FACING, REDSTONE_SIGNAL, MODEL_STATE);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        // 当玩家放置方块时，设置朝向为玩家面对的方向的反方向:cite[9]
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(REDSTONE_SIGNAL, 0)
                .setValue(MODEL_STATE, 0);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        // 当邻近方块更新时检测红石信号:cite[4]
        if (!level.isClientSide) {
            int currentPower = state.getValue(REDSTONE_SIGNAL);
            int newPower = level.getBestNeighborSignal(pos);

            // 只有红石信号变化时才更新
            if (currentPower != newPower) {
                // 更新红石信号强度
                BlockState newState = state.setValue(REDSTONE_SIGNAL, newPower);

                // 根据红石信号强度计算模型状态:cite[5]
                int modelState = calculateModelState(newPower);
                newState = newState.setValue(MODEL_STATE, modelState);

                level.setBlock(pos, newState, 3); // 更新方块状态
            }
        }
    }

    private int calculateModelState(int redstonePower) {
        // 根据红石信号强度返回对应的模型状态:cite[5]
        switch (redstonePower) {
            case 0: return 0;  // green
            case 3: return 1;  // green (重复，但按用户要求)
            case 6: return 2;  // green_yellow
            case 9: return 3;  // yellow
            case 12: return 4; // red_yellow
            case 15: return 5; // red
            default:
                // 对于非指定强度，保持最近的有效状态或使用默认
                if (redstonePower < 3) return 0;
                else if (redstonePower < 6) return 1;
                else if (redstonePower < 9) return 2;
                else if (redstonePower < 12) return 3;
                else if (redstonePower < 15) return 4;
                else return 5;
        }
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        // 根据方块朝向返回对应的碰撞箱形状:cite[6]
        Direction direction = state.getValue(FACING);

        switch (direction) {
            case NORTH: return SHAPE_NORTH;
            case SOUTH: return SHAPE_SOUTH;
            case EAST: return SHAPE_EAST;
            case WEST: return SHAPE_WEST;
            default: return SHAPE_NORTH;
        }
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        // 实现方块旋转功能:cite[9]
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        // 实现方块镜像功能
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }
}