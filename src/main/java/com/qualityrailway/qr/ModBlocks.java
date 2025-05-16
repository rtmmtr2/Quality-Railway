package com.qualityrailway.qr;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
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

    // trains
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

    public static final RegistryObject<Block> c70_floor = BLOCKS.register("c70_floor",
            () -> new c70_floor(Block.Properties.of(Material.STONE)
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

    //tools

    public static final RegistryObject<Block> bell = BLOCKS.register(
            "bell",
            () -> new bell(Block.Properties.of(Material.STONE)
                    .strength(2.0f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    //

    public static final RegistryObject<Block> railway_ballast = BLOCKS.register("railway_ballast",
            () -> new railway_ballast(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<SlabBlock> railway_ballast_slab =
            BLOCKS.register("railway_ballast_slab", () -> new railway_ballast_slab(
                    BlockBehaviour.Properties.copy(railway_ballast.get())
            ));

    public static final RegistryObject<Block> gravel = BLOCKS.register("gravel",
            () -> new gravel(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<SlabBlock> gravel_slab =
            BLOCKS.register("gravel_slab", () -> new gravel_slab(
                    BlockBehaviour.Properties.copy(gravel.get())
            ));

}