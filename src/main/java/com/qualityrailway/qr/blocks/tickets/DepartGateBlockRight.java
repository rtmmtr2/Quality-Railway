package com.qualityrailway.qr.blocks.tickets;

import javax.annotation.Nullable;
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
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import com.qualityrailway.qr.ModBlockEntities;

// 继承BaseEntityBlock
public class DepartGateBlockRight extends BaseEntityBlock {
    // 定义方块状态
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BooleanProperty.create("open");

    // 定义碰撞箱形状
    private static final VoxelShape CLOSED_SHAPE_SOUTH = Shapes.or(Shapes.box(0.75, 0, -1, 1, 1.3125, 2),Shapes.box(0, 0.3125, 0.5, 0.75, 1.3125, 0.5625));
    private static final VoxelShape CLOSED_SHAPE_NORTH = Shapes.or(Shapes.box(0, 0, -1, 0.25, 1.3125, 2),Shapes.box(0.25, 0.3125, 0.5, 1, 1.3125, 0.5625));
    private static final VoxelShape CLOSED_SHAPE_WEST = Shapes.or(Shapes.box(-1, 0, 0.75, 2, 1.3125, 1),Shapes.box(0.4375, 0.3125, 0, 0.5, 1.3125, 0.75));
    private static final VoxelShape CLOSED_SHAPE_EAST = Shapes.or(Shapes.box(-1, 0, 0, 2, 1.3125, 0.25),Shapes.box(0.75, 0.3125, 0.25, 0.8125, 1.3125, 1));

    private static final VoxelShape OPEN_SHAPE_SOUTH = Shapes.box(0.75, 0, -1, 1, 1.3125, 2);
    private static final VoxelShape OPEN_SHAPE_NORTH = Shapes.box(0, 0, -1, 0.25, 1.3125, 2);
    private static final VoxelShape OPEN_SHAPE_WEST = Shapes.box(-1, 0, 0.75, 2, 1.3125, 1);
    private static final VoxelShape OPEN_SHAPE_EAST = Shapes.box(-1, 0, 0, 2, 1.3125, 0.25);

    public DepartGateBlockRight(Properties properties) {
        super(properties);
        // 默认方块状态
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(OPEN, false));
    }

    // 创建方块状态定义
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN);
    }

    // 获取放置方块时的状态（设置朝向）
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    // 获取渲染形状
    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    // 获取碰撞箱形状，根据朝向和开门状态返回不同的形状
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Direction direction = state.getValue(FACING);
        boolean isOpen = state.getValue(OPEN);

        if (isOpen) {
            switch (direction) {
                case NORTH: return OPEN_SHAPE_NORTH;
                case SOUTH: return OPEN_SHAPE_SOUTH;
                case EAST: return OPEN_SHAPE_EAST;
                case WEST: return OPEN_SHAPE_WEST;
                default: return CLOSED_SHAPE_NORTH;
            }
        } else {
            switch (direction) {
                case NORTH: return CLOSED_SHAPE_NORTH;
                case SOUTH: return CLOSED_SHAPE_SOUTH;
                case EAST: return CLOSED_SHAPE_EAST;
                case WEST: return CLOSED_SHAPE_WEST;
                default: return CLOSED_SHAPE_NORTH;
            }
        }
    }

    // 获取 occlusion 形状
    @Override
    public VoxelShape getOcclusionShape(BlockState state, BlockGetter world, BlockPos pos) {
        return this.getShape(state, world, pos, CollisionContext.empty());
    }

    // 方块交互方法（右键调用）
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof DepartGateBlockRightEntity) {
                DepartGateBlockRightEntity turnstile = (DepartGateBlockRightEntity) blockEntity;
                // 检查物品是否含有ticket标签
                if (hasTicketTag(player.getItemInHand(hand))) {
                    return turnstile.activate(player, hand);
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    // 检查物品是否含有ticket标签
    private boolean hasTicketTag(net.minecraft.world.item.ItemStack stack) {
        // 检查物品的标签中是否包含"ticket"
        return stack.getTags().anyMatch(tag -> tag.location().getPath().contains("ticket"));
    }

    // 创建方块实体
    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new DepartGateBlockRightEntity(pos, state);
    }

    // 获取方块实体的Ticker
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state,
                                                                  BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.DepartGateBlockRightEntity.get(),
                DepartGateBlockRightEntity::tick);
    }
}