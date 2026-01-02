package com.qualityrailway.qr.blocks.tools;

import com.qualityrailway.qr.blockentity.AdvancedSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class AdvancedSignBlock extends BaseEntityBlock {
    
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    
    // 为每个方向定义不同的碰撞箱（2像素厚，位于方块后方）
    private static final VoxelShape NORTH_SHAPE = Block.box(0.0, 0.0, 14.0, 16.0, 16.0, 16.0);    // 北面：Z轴从14到16（后方）
    private static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 2.0);      // 南面：Z轴从0到2（后方）
    private static final VoxelShape EAST_SHAPE = Block.box(0.0, 0.0, 0.0, 2.0, 16.0, 16.0);       // 东面：X轴从0到2（后方）
    private static final VoxelShape WEST_SHAPE = Block.box(14.0, 0.0, 0.0, 16.0, 16.0, 16.0);     // 西面：X轴从14到16（后方）
    
    public AdvancedSignBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }
    
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    
    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }
    
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        switch (direction) {
            case NORTH:
                return NORTH_SHAPE;  // 北面朝外，碰撞箱在方块南侧（后方）
            case SOUTH:
                return SOUTH_SHAPE;  // 南面朝外，碰撞箱在方块北侧（后方）
            case EAST:
                return EAST_SHAPE;   // 东面朝外，碰撞箱在方块西侧（后方）
            case WEST:
                return WEST_SHAPE;   // 西面朝外，碰撞箱在方块东侧（后方）
            default:
                return NORTH_SHAPE;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return getShape(state, level, pos, context);
    }
    
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }
    
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new AdvancedSignBlockEntity(pos, state);
    }
    
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            BlockEntity blockEntity = level.getBlockEntity(pos);
            if (blockEntity instanceof AdvancedSignBlockEntity) {
                NetworkHooks.openGui(serverPlayer, (AdvancedSignBlockEntity) blockEntity, pos);
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.SUCCESS;
    }
    
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, 
                         BlockState newState, boolean isMoving) {
        super.onRemove(state, level, pos, newState, isMoving);
    }
}