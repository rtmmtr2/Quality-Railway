package com.qualityrailway.qr;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.material.Material;
import com.qualityrailway.qr.blocks.tools.*;
import com.qualityrailway.qr.blocks.c70.*;
import com.qualityrailway.qr.blocks.gq70.*;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, qr.MODID);

    // 注册方块
    public static final RegistryObject<Block> c70_left_end_board = BLOCKS.register("c70_left_end_board",
            () -> new c70_left_end_board(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> c70_right_end_board = BLOCKS.register("c70_right_end_board",
            () -> new c70_right_end_board(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> c70_front_board = BLOCKS.register("c70_front_board",
            () -> new c70_front_board(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> c70_left_board = BLOCKS.register("c70_left_board",
            () -> new c70_left_board(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> c70_door = BLOCKS.register("c70_door",
            () -> new c70_door(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> c70_fool = BLOCKS.register("c70_fool",
            () -> new c70_fool(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> gq70_end_tank = BLOCKS.register("gq70_end_tank",
            () -> new gq70_end_tank(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> gq70_tank_a = BLOCKS.register("gq70_tank_a",
            () -> new gq70_tank_a(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> gq70_tank_b = BLOCKS.register("gq70_tank_b",
            () -> new gq70_tank_b(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> gq70_tank_c = BLOCKS.register("gq70_tank_c",
            () -> new gq70_tank_c(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    //other blocks

    public static final RegistryObject<Block> bell = BLOCKS.register(
            "bell",
            () -> new bell(Block.Properties.of(Material.STONE)
                    .strength(2.0f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

}