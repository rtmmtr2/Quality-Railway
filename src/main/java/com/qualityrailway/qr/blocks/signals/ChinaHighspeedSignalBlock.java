package com.qualityrailway.qr.blocks.signals;

import com.qualityrailway.qr.boundary.ChinaHighspeedSignalBoundary;
import com.qualityrailway.qr.signals.SignalTypes;
import com.qualityrailway.qr.blockentity.signals.ChinaHighspeedSignalBlockEntity;
import com.simibubi.create.content.equipment.wrench.IWrenchable;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.utility.Lang;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;

import javax.annotation.Nullable;
import java.util.Random;

public class ChinaHighspeedSignalBlock extends Block implements IBE<ChinaHighspeedSignalBlockEntity>, IWrenchable {

    public static final EnumProperty<SignalTypes> TYPE = EnumProperty.create("type", SignalTypes.class);
    public static final BooleanProperty POWERED = BlockStateProperties.POWERED;
    public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public ChinaHighspeedSignalBlock(Properties properties) {
        super(properties);
        registerDefaultState(defaultBlockState()
                .setValue(TYPE, SignalTypes.CHINA_HIGHSPEED_SIGNAL)
                .setValue(POWERED, false)
                .setValue(LIT, true));
    }

    @Override
    public Class<ChinaHighspeedSignalBlockEntity> getBlockEntityClass() {
        return ChinaHighspeedSignalBlockEntity.class;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(TYPE, POWERED, LIT));
    }

    @Override
    public boolean shouldCheckWeakPower(BlockState state, LevelReader world, BlockPos pos, Direction side) {
        return false;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(POWERED, context.getLevel().hasNeighborSignal(context.getClickedPos()))
                .setValue(LIT, true);
    }

    @Override
    public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, BlockPos fromPos, boolean isMoving) {
        if (level.isClientSide)
            return;
        boolean powered = state.getValue(POWERED);
        if (powered == level.hasNeighborSignal(pos))
            return;
        if (powered) {
            level.scheduleTick(pos, this, 4);
        } else {
            level.setBlock(pos, state.cycle(POWERED), 2);
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, Random random) {
        if (state.getValue(POWERED) && !level.hasNeighborSignal(pos))
            level.setBlock(pos, state.cycle(POWERED), 2);
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        IBE.onRemove(state, worldIn, pos, newState);
    }

    @Override
    public BlockEntityType<? extends ChinaHighspeedSignalBlockEntity> getBlockEntityType() {
        return com.qualityrailway.qr.ModBlockEntities.CHINA_HIGHSPEED_SIGNAL.get();
    }

    @Override
    public InteractionResult onWrenched(BlockState state, UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        if (level.isClientSide)
            return InteractionResult.SUCCESS;

        withBlockEntityDo(level, pos, ste -> {
            ChinaHighspeedSignalBoundary signal = ste.getSignal();
            Player player = context.getPlayer();
            if (signal != null) {
                signal.cycleSignalType(pos);
                if (player != null)
                    player.displayClientMessage(Lang.translateDirect("track_signal.mode_change." +
                            signal.getTypeFor(pos).getSerializedName()), true);
            } else if (player != null)
                player.displayClientMessage(Lang.translateDirect("track_signal.cannot_change_mode"), true);
        });
        return InteractionResult.SUCCESS;
    }

    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos) {
        return getBlockEntityOptional(level, pos)
                .filter(ChinaHighspeedSignalBlockEntity::isPowered)
                .map($ -> 15)
                .orElse(0);
    }
}