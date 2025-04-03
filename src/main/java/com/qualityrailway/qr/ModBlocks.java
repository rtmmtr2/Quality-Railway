package com.qualityrailway.qr;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
//import block.java
import com.qualityrailway.qr.blocks.c70.c70_left_face;
import com.qualityrailway.qr.blocks.c70.c70_right_face;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, qr.MODID);

    // 注册三个示例方块
    public static final RegistryObject<Block> c70_left_face = BLOCKS.register("c70_left_face",
            () -> new c70_left_face(Block.Properties.of(net.minecraft.world.level.material.Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> c70_right_face = BLOCKS.register("c70_right_face",
            () -> new c70_right_face(Block.Properties.of(net.minecraft.world.level.material.Material.STONE)
                    .strength(2.0f)));


}