package com.qualityrailway.qr;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.qualityrailway.qr.qr;
import com.qualityrailway.qr.blocks.tickets.TurnstileBlock;
import com.qualityrailway.qr.blocks.tickets.TurnstileBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, qr.MODID);

    // 注册闸机方块实体类型
    public static final RegistryObject<BlockEntityType<TurnstileBlockEntity>> TurnstileBlockEntity =
            BLOCK_ENTITIES.register("turnstile_block_entity",
                    () -> BlockEntityType.Builder.of(TurnstileBlockEntity::new,
                            ModBlocks.TurnstileBlock.get()).build(null));
}