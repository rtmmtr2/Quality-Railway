package com.qualityrailway.qr.blocks.doors;

import com.qualityrailway.qr.ModBlockEntities;
import com.qualityrailway.qr.blockentity.TrainDoorBlockEntity;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.contraptions.behaviour.MovementContext;
import com.simibubi.create.content.contraptions.behaviour.SimpleBlockMovingInteraction;
import com.simibubi.create.foundation.block.IBE;
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
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class TrainDoorBlock extends BaseEntityBlock implements IBE<TrainDoorBlockEntity> {
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);
    public static final BooleanProperty OPEN = BooleanProperty.create("open");

    protected static final VoxelShape NORTH_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape SOUTH_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape EAST_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape WEST_SHAPE = Block.box(0.0, 0.0, 0.0, 16.0, 16.0, 16.0);
    protected static final VoxelShape OPEN_NORTH_SHAPE = Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0);
    protected static final VoxelShape OPEN_SOUTH_SHAPE = Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0);
    protected static final VoxelShape OPEN_EAST_SHAPE = Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0);
    protected static final VoxelShape OPEN_WEST_SHAPE = Block.box(0.0, 0.0, 0.0, 8.0, 8.0, 8.0);

    public TrainDoorBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(OPEN, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING, OPEN);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(OPEN, false);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        Direction facing = state.getValue(FACING);
        boolean open = state.getValue(OPEN);

        if (open) {
            return switch (facing) {
                case NORTH -> OPEN_NORTH_SHAPE;
                case SOUTH -> OPEN_SOUTH_SHAPE;
                case EAST -> OPEN_EAST_SHAPE;
                case WEST -> OPEN_WEST_SHAPE;
                default -> OPEN_NORTH_SHAPE;
            };
        } else {
            return switch (facing) {
                case NORTH -> NORTH_SHAPE;
                case SOUTH -> SOUTH_SHAPE;
                case EAST -> EAST_SHAPE;
                case WEST -> WEST_SHAPE;
                default -> NORTH_SHAPE;
            };
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player,
                                 InteractionHand hand, BlockHitResult hit) {
        return toggleDoor(state, level, pos);
    }

    private InteractionResult toggleDoor(BlockState state, Level level, BlockPos pos) {
        if (!level.isClientSide) {
            boolean newOpenState = !state.getValue(OPEN);
            BlockState newState = state.setValue(OPEN, newOpenState);
            level.setBlock(pos, newState, 3);

            // 播放开关门声音
            level.playSound(null, pos,
                    newOpenState ? net.minecraft.sounds.SoundEvents.IRON_DOOR_OPEN :
                            net.minecraft.sounds.SoundEvents.IRON_DOOR_CLOSE,
                    net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);

            return InteractionResult.CONSUME;
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public net.minecraft.world.level.block.RenderShape getRenderShape(BlockState state) {
        return net.minecraft.world.level.block.RenderShape.MODEL;
    }

    @Override
    public Class<TrainDoorBlockEntity> getBlockEntityClass() {
        return TrainDoorBlockEntity.class;
    }

    @Override
    public BlockEntityType<? extends TrainDoorBlockEntity> getBlockEntityType() {
        return ModBlockEntities.TRAIN_DOOR.get();
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new TrainDoorBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                  BlockEntityType<T> type) {
        return createTickerHelper(type, ModBlockEntities.TRAIN_DOOR.get(),
                TrainDoorBlockEntity::tick);
    }
}
