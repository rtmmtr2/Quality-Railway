package com.qualityrailway.qr;

import com.qualityrailway.qr.qr;
import com.qualityrailway.qr.blocks.signals.SignalBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// 方块实体注册类
public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, qr.MODID);

    public static final RegistryObject<BlockEntityType<SignalBlockEntity>> SIGNAL_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("signal_block_entity",
                    () -> BlockEntityType.Builder.of(SignalBlockEntity::new, ModBlocks.SIGNAL_BLOCK.get()).build(null));
}