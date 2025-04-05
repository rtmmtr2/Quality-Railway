package com.qualityrailway.qr;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
//import path
import com.qualityrailway.qr.blocks.c70.*;


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

    public static final RegistryObject<Block> c70_front = BLOCKS.register("c70_front",
            () -> new c70_front(Block.Properties.of(net.minecraft.world.level.material.Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> c70_door = BLOCKS.register("c70_door",
            () -> new c70_door(Block.Properties.of(net.minecraft.world.level.material.Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> c70_fool = BLOCKS.register("c70_fool",
            () -> new c70_fool(Block.Properties.of(net.minecraft.world.level.material.Material.STONE)
                    .strength(2.0f)));


}