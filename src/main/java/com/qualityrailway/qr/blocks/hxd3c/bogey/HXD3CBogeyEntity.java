package com.qualityrailway.qr.blocks.hxd3c.bogey;

import com.simibubi.create.content.trains.bogey.AbstractBogeyBlockEntity;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class HXD3CBogeyEntity extends AbstractBogeyBlockEntity {

    public HXD3CBogeyEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public BogeyStyle getDefaultStyle() {
        return AllCustomBogeyStyles.HXD3C_STYLE;
    }
}