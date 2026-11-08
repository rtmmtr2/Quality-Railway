package com.qualityrailway.qr.blocks.hxd3c.bogey;

import com.simibubi.create.AllBlocks;
import com.simibubi.create.content.trains.bogey.AbstractBogeyBlock;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import com.simibubi.create.content.trains.track.TrackMaterial;
import com.simibubi.create.foundation.block.IBE;
import com.simibubi.create.foundation.block.ProperWaterloggedBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class HXD3CBogeyBlock extends AbstractBogeyBlock<HXD3CBogeyEntity>
        implements IBE<HXD3CBogeyEntity>, ProperWaterloggedBlock {

    private final BogeyStyle style;
    private final boolean isBackward;

    // ✅ 构造函数接受自定义尺寸
    public HXD3CBogeyBlock(Properties props, BogeyStyle style, BogeySizes.BogeySize size, boolean isBackward) {
        super(props, size);
        this.style = style;
        this.isBackward = isBackward;
        registerDefaultState(defaultBlockState().setValue(WATERLOGGED, false));
    }

    @Override
    public TrackMaterial.TrackType getTrackType(BogeyStyle style) {
        return TrackMaterial.TrackType.STANDARD;
    }

    @Override
    public double getWheelPointSpacing() {
        return 2.25;
    }

    @Override
    public double getWheelRadius() {
        return 12.5 / 16d;
    }

    @Override
    protected Vec3 getConnectorAnchorOffset() {
        return new Vec3(0, 7 / 32f, 1.125);
    }

    @Override
    public BogeyStyle getDefaultStyle() {
        return style;
    }

    @Override
    public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
        return AllBlocks.RAILWAY_CASING.asStack();
    }

    @Override
    public Class<HXD3CBogeyEntity> getBlockEntityClass() {
        return HXD3CBogeyEntity.class;
    }

    @Override
    public BlockEntityType<? extends HXD3CBogeyEntity> getBlockEntityType() {
        return AllBlockEntities.HXD3C_BOGEY_ENTITY.get();
    }

    public boolean isBackward() {
        return isBackward;
    }
}