package com.qualityrailway.qr;

import com.qualityrailway.qr.blockentity.TrainDoorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, qr.MODID);

    public static final RegistryObject<BlockEntityType<TrainDoorBlockEntity>> TRAIN_DOOR =
            BLOCK_ENTITIES.register("train_door",
                    () -> BlockEntityType.Builder.of(
                            TrainDoorBlockEntity::new,
                            ModBlocks.TRAIN_DOOR.get()
                    ).build(null));
}
