package com.qualityrailway.qr.blocks.tickets;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import javax.annotation.Nullable;

public class TicketMachineBlock extends BaseEntityBlock {
    // 定义方向属性
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;

    // 定义碰撞箱形状
    private static final VoxelShape SHAPE_NORTH = Block.box(2, 0, 2, 14, 16, 14);
    private static final VoxelShape SHAPE_SOUTH = Block.box(2, 0, 2, 14, 16, 14);
    private static final VoxelShape SHAPE_EAST = Block.box(2, 0, 2, 14, 16, 14);
    private static final VoxelShape SHAPE_WEST = Block.box(2, 0, 2, 14, 16, 14);

    public TicketMachineBlock(Properties properties) {
        super(properties);
        // 设置默认朝向
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    // 获取碰撞箱形状
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);

        // 根据朝向返回不同的碰撞箱
        switch(direction) {
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

    // 获取渲染类型
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    // 当玩家放置方块时设置朝向
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    // 创建方块状态定义
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    // 创建方块实体
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TicketMachineBlockEntity(pos, state);
    }

    // 当玩家右击方块时触发
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            // 获取方块实体
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof TicketMachineBlockEntity) {
                TicketMachineBlockEntity ticketMachine = (TicketMachineBlockEntity) blockEntity;
                // 执行交互逻辑
                ticketMachine.interact(player);
            }
        }
        return InteractionResult.sidedSuccess(world.isClientSide);
    }
}