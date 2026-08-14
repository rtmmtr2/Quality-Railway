package com.qualityrailway.qr.blocks.track;

import com.simibubi.create.content.trains.track.TrackBlock;
import com.simibubi.create.content.trains.track.TrackMaterial;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public class GaugeTrackMaterial extends TrackMaterial {
    private final double gaugeOffset;

    public GaugeTrackMaterial(ResourceLocation id, String langName,
                              NonNullSupplier<NonNullSupplier<? extends TrackBlock>> trackBlock,
                              ResourceLocation particle, Ingredient sleeperIngredient,
                              Ingredient railsIngredient, TrackType trackType,
                              Supplier<Supplier<TrackModelHolder>> modelHolder,
                              TrackType.TrackBlockFactory customFactory,
                              double gaugeOffset) {
        super(id, langName, trackBlock, particle, sleeperIngredient, railsIngredient,
                trackType, modelHolder, customFactory);
        this.gaugeOffset = gaugeOffset;
    }

    public double getGaugeOffset() {
        return gaugeOffset;
    }
}