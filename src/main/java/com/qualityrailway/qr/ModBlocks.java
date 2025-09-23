package com.qualityrailway.qr;
//import com.qualityrailway.qr.blocks.doors.CustomDoorBlock;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlock;
import com.simibubi.create.foundation.data.BuilderTransformers;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.material.Material;
import com.qualityrailway.qr.blocks.tools.*;
import com.qualityrailway.qr.blocks.signs.*;
import com.qualityrailway.qr.blocks.c70.*;
import com.qualityrailway.qr.blocks.gq70.*;
import com.qualityrailway.qr.blocks.df7g.*;
import com.qualityrailway.qr.blocks.df4d.*;
import static com.simibubi.create.Create.REGISTRATE;
import com.qualityrailway.qr.blocks.doors.CustomDoorBlock;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.SoundType;





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

    // df7g

    public static final RegistryObject<Block> df7g_cab = BLOCKS.register("df7g_cab",
            () -> new df7g_cab(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_cowcatcher_a = BLOCKS.register("df7g_cowcatcher_a",
            () -> new df7g_cowcatcher_a(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_cowcatcher_b = BLOCKS.register("df7g_cowcatcher_b",
            () -> new df7g_cowcatcher_b(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_floor_a = BLOCKS.register("df7g_floor_a",
            () -> new df7g_floor_a(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_floor_b = BLOCKS.register("df7g_floor_b",
            () -> new df7g_floor_b(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_floor_c = BLOCKS.register("df7g_floor_c",
            () -> new df7g_floor_c(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_floor_d = BLOCKS.register("df7g_floor_d",
            () -> new df7g_floor_d(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_floor_e = BLOCKS.register("df7g_floor_e",
            () -> new df7g_floor_e(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_floor_f = BLOCKS.register("df7g_floor_f",
            () -> new df7g_floor_f(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_enclosure_a = BLOCKS.register("df7g_enclosure_a",
            () -> new df7g_enclosure_a(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_enclosure_b = BLOCKS.register("df7g_enclosure_b",
            () -> new df7g_enclosure_b(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_enclosure_c = BLOCKS.register("df7g_enclosure_c",
            () -> new df7g_enclosure_c(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_enclosure_d = BLOCKS.register("df7g_enclosure_d",
            () -> new df7g_enclosure_d(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_enclosure_e = BLOCKS.register("df7g_enclosure_e",
            () -> new df7g_enclosure_e(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_enclosure_f = BLOCKS.register("df7g_enclosure_f",
            () -> new df7g_enclosure_f(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df7g_enclosure_end = BLOCKS.register("df7g_enclosure_end",
            () -> new df7g_enclosure_end(Block.Properties.copy(c70_left_end_board.get())));

    // df4d

    public static final RegistryObject<Block> df4d_cab = BLOCKS.register("df4d_cab",
            () -> new df4d_cab(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_chimney_a = BLOCKS.register("df4d_chimney_a",
            () -> new df4d_chimney_a(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_floor_a = BLOCKS.register("df4d_floor_a",
            () -> new df4d_floor_a(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_floor_b = BLOCKS.register("df4d_floor_b",
            () -> new df4d_floor_b(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_floor_c = BLOCKS.register("df4d_floor_c",
            () -> new df4d_floor_c(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_left_board_a = BLOCKS.register("df4d_left_board_a",
            () -> new df4d_left_board_a(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_left_board_b = BLOCKS.register("df4d_left_board_b",
            () -> new df4d_left_board_b(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_left_board_c = BLOCKS.register("df4d_left_board_c",
            () -> new df4d_left_board_c(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_left_board_d = BLOCKS.register("df4d_left_board_d",
            () -> new df4d_left_board_d(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_left_board_e = BLOCKS.register("df4d_left_board_e",
            () -> new df4d_left_board_e(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_left_board_f = BLOCKS.register("df4d_left_board_f",
            () -> new df4d_left_board_f(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_left_board_g = BLOCKS.register("df4d_left_board_g",
            () -> new df4d_left_board_g(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_radiator_a = BLOCKS.register("df4d_radiator_a",
            () -> new df4d_radiator_a(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_radiator_b = BLOCKS.register("df4d_radiator_b",
            () -> new df4d_radiator_b(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_right_board_a = BLOCKS.register("df4d_right_board_a",
            () -> new df4d_right_board_a(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_right_board_b = BLOCKS.register("df4d_right_board_b",
            () -> new df4d_right_board_b(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_right_board_c = BLOCKS.register("df4d_right_board_c",
            () -> new df4d_right_board_c(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_right_board_d = BLOCKS.register("df4d_right_board_d",
            () -> new df4d_right_board_d(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_right_board_e = BLOCKS.register("df4d_right_board_e",
            () -> new df4d_right_board_e(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_right_board_f = BLOCKS.register("df4d_right_board_f",
            () -> new df4d_right_board_f(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_right_board_g = BLOCKS.register("df4d_right_board_g",
            () -> new df4d_right_board_g(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_roof_a = BLOCKS.register("df4d_roof_a",
            () -> new df4d_roof_a(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_roof_b = BLOCKS.register("df4d_roof_b",
            () -> new df4d_roof_b(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_roof_c = BLOCKS.register("df4d_roof_c",
            () -> new df4d_roof_c(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_roof_d = BLOCKS.register("df4d_roof_d",
            () -> new df4d_roof_d(Block.Properties.copy(c70_left_end_board.get())));

    public static final RegistryObject<Block> df4d_roof_e = BLOCKS.register("df4d_roof_e",
            () -> new df4d_roof_e(Block.Properties.copy(c70_left_end_board.get())));


    public static final RegistryObject<DoorBlock> df4d_cab_door_a = BLOCKS.register("df4d_cab_door_a",
            () -> new df4d_cab_door_a(Block.Properties.of(Material.WOOD)));

    public static final RegistryObject<DoorBlock> df4d_cab_door_b = BLOCKS.register("df4d_cab_door_b",
            () -> new df4d_cab_door_b(Block.Properties.of(Material.WOOD)));

    public static final RegistryObject<DoorBlock> df4d_middle_door = BLOCKS.register("df4d_middle_door",
            () -> new df4d_middle_door(Block.Properties.of(Material.WOOD)));

    public static final RegistryObject<Block> df4d_floor_d = BLOCKS.register("df4d_floor_d",
            () -> new df4d_floor_d(Block.Properties.copy(c70_left_end_board.get())));




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

    public static final RegistryObject<Block> automatic_tickets = BLOCKS.register("automatic_tickets",
            () -> new automatic_tickets(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> entrance = BLOCKS.register("entrance",
            () -> new entrance(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> exit = BLOCKS.register("exit",
            () -> new exit(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> railway_tickets = BLOCKS.register("railway_tickets",
            () -> new railway_tickets(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> shopping = BLOCKS.register("shopping",
            () -> new shopping(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> vip = BLOCKS.register("vip",
            () -> new vip(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> passengers_stopped = BLOCKS.register("passengers_stopped",
            () -> new passengers_stopped(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));
    public static final RegistryObject<Block> waiting_room = BLOCKS.register("waiting_room",
            () -> new waiting_room(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    //doors
    // 注册示例木门方块
    public static final RegistryObject<CustomDoorBlock> EXAMPLE_DOOR_1 = BLOCKS.register("example_door_1",
            () -> new CustomDoorBlock(Block.Properties.of(Material.WOOD, MaterialColor.WOOD)
                    .strength(3.0F)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()));

}