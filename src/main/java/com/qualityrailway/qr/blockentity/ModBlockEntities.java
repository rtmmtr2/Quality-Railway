package com.qualityrailway.qr.blockentity;

import com.qualityrailway.qr.blocks.ModBlocks;
import com.qualityrailway.qr.qr;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, qr.MODID);

    public static final RegistryObject<BlockEntityType<TrainDoorBlockEntity>> TRAIN_DOOR =
            BLOCK_ENTITIES.register("train_door",
                    () -> BlockEntityType.Builder.of(
                            TrainDoorBlockEntity::new,
                            ModBlocks.TRAIN_DOOR.get()
                    ).build(null));
}
