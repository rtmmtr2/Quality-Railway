package com.qualityrailway.qr;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import com.qualityrailway.qr.blocks.tickets.GateBlockEntity;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, qr.MODID);

    // 注册闸机方块实体类型
    public static final RegistryObject<BlockEntityType<GateBlockEntity>> GateBlockEntity =
            BLOCK_ENTITIES.register("gate_block_entity",
                    () -> BlockEntityType.Builder.of(GateBlockEntity::new,
                            ModBlocks.GateBlock.get()).build(null));
}