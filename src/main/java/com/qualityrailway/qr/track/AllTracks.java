package com.qualityrailway.qr.track;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.trains.track.TrackBlock;
import com.simibubi.create.content.trains.track.TrackBlockItem;
import com.simibubi.create.content.trains.track.TrackModel;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MaterialColor;

import static com.qualityrailway.qr.register.QRRegistrate.REGISTRATE;

public class AllTracks {

    public static final BlockEntry<TrackBlock> TRACK_STANDARD_38KG =
            REGISTRATE.block("track_standard_38kg", p -> new TrackBlock(p, AllTrackMaterial.TRACK_38KG))
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.color(MaterialColor.METAL)
                            .strength(0.8F)
                            .sound(SoundType.METAL)
                            .noOcclusion())
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(TagGen.pickaxeOnly())
                    .onRegister(CreateRegistrate.blockModel(() -> TrackModel::new))
                    .tag(AllTags.AllBlockTags.RELOCATION_NOT_SUPPORTED.tag)
                    .tag(AllTags.AllBlockTags.TRACKS.tag)
                    .tag(AllTags.AllBlockTags.GIRDABLE_TRACKS.tag)
                    .lang("38kg Standard Track")
                    .blockstate((ctx, prov) -> {})
                    .item(TrackBlockItem::new)  // ✅ 使用自定义物品类
                    .model((c, p) -> {})
                    .tag(AllTags.AllItemTags.TRACKS.tag)
                    .build()
                    .register();

    public static final BlockEntry<TrackBlock> TRACK_STANDARD_43KG =
            REGISTRATE.block("track_standard_43kg", p -> new TrackBlock(p, AllTrackMaterial.TRACK_43KG))
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.color(MaterialColor.METAL)
                            .strength(0.8F)
                            .sound(SoundType.METAL)
                            .noOcclusion())
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(TagGen.pickaxeOnly())
                    .onRegister(CreateRegistrate.blockModel(() -> TrackModel::new))
                    .blockstate((ctx, prov) -> {})
                    .tag(AllTags.AllBlockTags.RELOCATION_NOT_SUPPORTED.tag)
                    .tag(AllTags.AllBlockTags.TRACKS.tag)
                    .tag(AllTags.AllBlockTags.GIRDABLE_TRACKS.tag)
                    .lang("43kg Standard Track")
                    .item(TrackBlockItem::new)  // ✅ 使用自定义物品类
                    .model((c, p) -> {})
                    .tag(AllTags.AllItemTags.TRACKS.tag)
                    .build()
                    .register();

    public static void register() {}
}