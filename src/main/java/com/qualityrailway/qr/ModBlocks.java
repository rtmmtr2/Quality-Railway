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
import com.qualityrailway.qr.blocks.signs.*;
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
            () -> new c70_right_end_board(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> c70_front_board = BLOCKS.register("c70_front_board",
            () -> new c70_front_board(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> c70_left_board = BLOCKS.register("c70_left_board",
            () -> new c70_left_board(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> c70_door = BLOCKS.register("c70_door",
            () -> new c70_door(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> c70_floor = BLOCKS.register("c70_floor",
            () -> new c70_floor(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> gq70_end_tank = BLOCKS.register("gq70_end_tank",
            () -> new gq70_end_tank(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> gq70_tank_a = BLOCKS.register("gq70_tank_a",
            () -> new gq70_tank_a(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> gq70_tank_b = BLOCKS.register("gq70_tank_b",
            () -> new gq70_tank_b(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> gq70_tank_c = BLOCKS.register("gq70_tank_c",
            () -> new gq70_tank_c(Block.Properties.copy(c70_left_end_board.get())));

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
                    BlockBehaviour.Properties.copy(gravel.get())));

    //signs

    public static final RegistryObject<Block> ticket_check = BLOCKS.register("ticket_check",
            () -> new ticket_check(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> police = BLOCKS.register("police",
            () -> new police(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> information = BLOCKS.register("information",
            () -> new information(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> toilet = BLOCKS.register("toilet",
            () -> new toilet(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> sign_post = BLOCKS.register("sign_post",
            () -> new sign_post(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> first_approach_section_sign = BLOCKS.register("first_approach_section_sign",
            () -> new first_approach_section_sign(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> second_approach_section_sign = BLOCKS.register("second_approach_section_sign",
            () -> new second_approach_section_sign(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> third_approach_section_sign = BLOCKS.register("third_approach_section_sign",
            () -> new third_approach_section_sign(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> dual_pantograph_prohibited_sign = BLOCKS.register("dual_pantograph_prohibited_sign",
            () -> new dual_pantograph_prohibited_sign(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));



}