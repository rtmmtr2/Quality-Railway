package com.qualityrailway.qr.blocks.track;

import com.simibubi.create.AllTags;
import com.simibubi.create.content.trains.track.TrackBlock;
import com.simibubi.create.content.trains.track.TrackBlockItem;
import com.simibubi.create.content.trains.track.TrackBlockStateGenerator;
import com.simibubi.create.content.trains.track.TrackModel;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.data.TagGen;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.SoundType;

import static com.qualityrailway.qr.register.QRRegistrate.MODID;
import static com.qualityrailway.qr.register.QRRegistrate.REGISTRATE;

public class AllTracks {

    public static final BlockEntry<TrackBlock> TRACK_STANDARD_30KG =
            REGISTRATE.block("track_standard_30kg", p -> new TrackBlock(p, AllTrackMaterial_30KG.TRACK_30KG))
                    .initialProperties(SharedProperties::stone)
                    .properties(p -> p.mapColor(MapColor.METAL)
                            .strength(0.8F)
                            .sound(SoundType.METAL)
                            .noOcclusion()
                            .forceSolidOn())
                    .addLayer(() -> RenderType::cutoutMipped)
                    .transform(TagGen.pickaxeOnly())
                    .onRegister(CreateRegistrate.blockModel(() -> TrackModel::new))
                    .blockstate(new TrackBlockStateGenerator()::generate)
                    .tag(AllTags.AllBlockTags.RELOCATION_NOT_SUPPORTED.tag)
                    .tag(AllTags.AllBlockTags.TRACKS.tag)
                    .tag(AllTags.AllBlockTags.GIRDABLE_TRACKS.tag)
                    .lang("30kg Standard Track")
                    .item(TrackBlockItem::new)  // ✅ 使用自定义物品类
                    .model((c, p) -> p.generated(c, new ResourceLocation(MODID, "item/track_standard_30kg")))
                    .tag(AllTags.AllItemTags.TRACKS.tag)
                    .build()
                    .register();

    public static void register() {}
}