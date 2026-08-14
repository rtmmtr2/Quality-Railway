package com.qualityrailway.qr.blocks.track;

import com.simibubi.create.content.trains.track.TrackMaterial.TrackType;
import com.simibubi.create.content.trains.track.TrackMaterial.TrackModelHolder;
import com.jozufozu.flywheel.core.PartialModel;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.Items;

import static com.qualityrailway.qr.register.QRRegistrate.MODID;

public class AllTrackMaterial_30KG {

    // 自定义 30kg 轨道，使用 1435mm 轨距 (半轨距 0.7175)
    public static final GaugeTrackMaterial TRACK_30KG =
            new GaugeTrackMaterial(
                    new ResourceLocation(MODID, "track_standard_30kg"),
                    "30kg Standard Track",
                    NonNullSupplier.lazy(() -> NonNullSupplier.lazy(() -> AllTracks.TRACK_STANDARD_30KG.get())),
                    new ResourceLocation(MODID, "block/track/standard/30kg/particle"),
                    Ingredient.of(Items.IRON_INGOT),
                    Ingredient.of(Items.IRON_INGOT),
                    TrackType.STANDARD,
                    () -> () -> new TrackModelHolder(
                            new PartialModel(new ResourceLocation(MODID, "block/track/standard/30kg/tie_formal/tie")),
                            new PartialModel(new ResourceLocation(MODID, "block/track/standard/30kg/tie_formal/segment_left")),
                            new PartialModel(new ResourceLocation(MODID, "block/track/standard/30kg/tie_formal/segment_right"))
                    ),
                    null,
                    0.7515  // 半轨距 = 1.435 / 2
            );
}