package com.qualityrailway.qr;

import com.qualityrailway.qr.blocks.doors.CustomDoorBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, qr.MODID);

    public static final RegistryObject<BlockEntityType<CustomDoorBlockEntity>> CUSTOM_DOOR_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("custom_door_block_entity",
                    () -> BlockEntityType.Builder.of(CustomDoorBlockEntity::new,
                            ModBlocks.EXAMPLE_DOOR_1.get()).build(null));
}
