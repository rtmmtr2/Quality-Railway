package com.qualityrailway.qr;

import com.qualityrailway.qr.blocks.signals.RailwaySignalEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// 方块实体注册类
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, qr.MODID);

    public static final RegistryObject<BlockEntityType<RailwaySignalEntity>> RAILWAY_SIGNAL_ENTITY =
            BLOCK_ENTITIES.register("railway_signal_entity",
                    () -> BlockEntityType.Builder.of(RailwaySignalEntity::new, ModBlocks.RAILWAY_SIGNAL.get()).build(null));
}