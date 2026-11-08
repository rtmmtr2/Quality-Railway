package com.qualityrailway.qr.blocks.hxd3c.bogey;

import com.simibubi.create.content.trains.bogey.BogeyBlockEntityRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

import static com.qualityrailway.qr.register.QRRegistrate.REGISTRATE;

public class AllBlockEntities {

    public static final BlockEntityEntry<HXD3CBogeyEntity> HXD3C_BOGEY_ENTITY =
            REGISTRATE.blockEntity("hxd3c_bogey_entity", HXD3CBogeyEntity::new)
                    .renderer(() -> BogeyBlockEntityRenderer::new)
                    .validBlocks(
                            AllCustomBogeyStyles.HXD3C_BOGEY,
                            AllCustomBogeyStyles.HXD3C_BACKWARD_BOGEY
                    )
                    .register();

    public static void register() {
        System.out.println("[QR] HXD3C 实体已注册");
    }
}