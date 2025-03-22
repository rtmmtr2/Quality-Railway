package com.example.qr;

import com.example.qr.blocks.c70_left_face;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, qr.MODID);

    // 注册三个示例方块
    public static final RegistryObject<Block> c70_left_face = BLOCKS.register("c70_left_face",
            () -> new c70_left_face(Block.Properties.of(net.minecraft.world.level.material.Material.STONE)
                    .strength(2.0f)));


}