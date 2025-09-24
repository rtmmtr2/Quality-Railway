package com.qualityrailway.qr;

import com.qualityrailway.qr.ModBlocks;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import com.qualityrailway.qr.blocks.Doors.SlidingDoor.TrainSlidingDoorBlockEntity;
import com.qualityrailway.qr.blocks.Doors.SlidingDoor.TrainSlidingDoorBlockRenderer;

import static com.qualityrailway.qr.qr.REGISTRATE;

public class ModBlockEntities {

    public static final BlockEntityEntry<TrainSlidingDoorBlockEntity> SLIDING_DOOR =
            REGISTRATE.blockEntity("sliding_door", TrainSlidingDoorBlockEntity::new)
                    .renderer(() -> TrainSlidingDoorBlockRenderer::new)
                    .validBlocks(ModBlocks.door_custom)
                    .register();

    public static void register() {

    }
}
