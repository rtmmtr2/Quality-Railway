package com.qualityrailway.qr;

import com.qualityrailway.qr.blocks.gates.ArriveGateBlockRight;
import com.qualityrailway.qr.blocks.gates.DepartGateBlockRight;
import com.qualityrailway.qr.blocks.gates.GateBlockLeft;
import com.qualityrailway.qr.blocks.gates.TicketMachineBlock;
import net.minecraft.core.registries.*;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredHolder;
import com.qualityrailway.qr.blocks.tools.*;
import com.qualityrailway.qr.blocks.signs.*;
import com.qualityrailway.qr.blocks.c70.*;
import com.qualityrailway.qr.blocks.gq70.*;
import com.qualityrailway.qr.blocks.df7g.*;
import com.qualityrailway.qr.blocks.df4d.*;

import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.SoundType;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(BuiltInRegistries.BLOCK, qr.MODID);

    // trains
    public static final DeferredHolder<Block, Block> c70_left_end_board = BLOCKS.register("c70_left_end_board",
            () -> new c70_left_end_board(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> c70_right_end_board = BLOCKS.register("c70_right_end_board",
            () -> new c70_right_end_board(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> c70_front_board = BLOCKS.register("c70_front_board",
            () -> new c70_front_board(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> c70_left_board = BLOCKS.register("c70_left_board",
            () -> new c70_left_board(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> c70_door = BLOCKS.register("c70_door",
            () -> new c70_door(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> c70_floor = BLOCKS.register("c70_floor",
            () -> new c70_floor(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> gq70_end_tank = BLOCKS.register("gq70_end_tank",
            () -> new gq70_end_tank(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> gq70_tank_a = BLOCKS.register("gq70_tank_a",
            () -> new gq70_tank_a(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> gq70_tank_b = BLOCKS.register("gq70_tank_b",
            () -> new gq70_tank_b(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> gq70_tank_c = BLOCKS.register("gq70_tank_c",
            () -> new gq70_tank_c(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    // df7g

    public static final DeferredHolder<Block, Block> df7g_cab = BLOCKS.register("df7g_cab",
            () -> new df7g_cab(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_cowcatcher_a = BLOCKS.register("df7g_cowcatcher_a",
            () -> new df7g_cowcatcher_a(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_cowcatcher_b = BLOCKS.register("df7g_cowcatcher_b",
            () -> new df7g_cowcatcher_b(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_floor_a = BLOCKS.register("df7g_floor_a",
            () -> new df7g_floor_a(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_floor_b = BLOCKS.register("df7g_floor_b",
            () -> new df7g_floor_b(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_floor_c = BLOCKS.register("df7g_floor_c",
            () -> new df7g_floor_c(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_floor_d = BLOCKS.register("df7g_floor_d",
            () -> new df7g_floor_d(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_floor_e = BLOCKS.register("df7g_floor_e",
            () -> new df7g_floor_e(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_floor_f = BLOCKS.register("df7g_floor_f",
            () -> new df7g_floor_f(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_enclosure_a = BLOCKS.register("df7g_enclosure_a",
            () -> new df7g_enclosure_a(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_enclosure_b = BLOCKS.register("df7g_enclosure_b",
            () -> new df7g_enclosure_b(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_enclosure_c = BLOCKS.register("df7g_enclosure_c",
            () -> new df7g_enclosure_c(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_enclosure_d = BLOCKS.register("df7g_enclosure_d",
            () -> new df7g_enclosure_d(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_enclosure_e = BLOCKS.register("df7g_enclosure_e",
            () -> new df7g_enclosure_e(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_enclosure_f = BLOCKS.register("df7g_enclosure_f",
            () -> new df7g_enclosure_f(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df7g_enclosure_end = BLOCKS.register("df7g_enclosure_end",
            () -> new df7g_enclosure_end(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    // df4d

    public static final DeferredHolder<Block, Block> df4d_cab = BLOCKS.register("df4d_cab",
            () -> new df4d_cab(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_chimney_a = BLOCKS.register("df4d_chimney_a",
            () -> new df4d_chimney_a(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_floor_a = BLOCKS.register("df4d_floor_a",
            () -> new df4d_floor_a(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_floor_b = BLOCKS.register("df4d_floor_b",
            () -> new df4d_floor_b(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_floor_c = BLOCKS.register("df4d_floor_c",
            () -> new df4d_floor_c(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_left_board_a = BLOCKS.register("df4d_left_board_a",
            () -> new df4d_left_board_a(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_left_board_b = BLOCKS.register("df4d_left_board_b",
            () -> new df4d_left_board_b(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_left_board_c = BLOCKS.register("df4d_left_board_c",
            () -> new df4d_left_board_c(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_left_board_d = BLOCKS.register("df4d_left_board_d",
            () -> new df4d_left_board_d(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_left_board_e = BLOCKS.register("df4d_left_board_e",
            () -> new df4d_left_board_e(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_left_board_f = BLOCKS.register("df4d_left_board_f",
            () -> new df4d_left_board_f(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_left_board_g = BLOCKS.register("df4d_left_board_g",
            () -> new df4d_left_board_g(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_radiator_a = BLOCKS.register("df4d_radiator_a",
            () -> new df4d_radiator_a(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_radiator_b = BLOCKS.register("df4d_radiator_b",
            () -> new df4d_radiator_b(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_right_board_a = BLOCKS.register("df4d_right_board_a",
            () -> new df4d_right_board_a(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_right_board_b = BLOCKS.register("df4d_right_board_b",
            () -> new df4d_right_board_b(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_right_board_c = BLOCKS.register("df4d_right_board_c",
            () -> new df4d_right_board_c(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_right_board_d = BLOCKS.register("df4d_right_board_d",
            () -> new df4d_right_board_d(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_right_board_e = BLOCKS.register("df4d_right_board_e",
            () -> new df4d_right_board_e(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_right_board_f = BLOCKS.register("df4d_right_board_f",
            () -> new df4d_right_board_f(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_right_board_g = BLOCKS.register("df4d_right_board_g",
            () -> new df4d_right_board_g(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_roof_a = BLOCKS.register("df4d_roof_a",
            () -> new df4d_roof_a(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_roof_b = BLOCKS.register("df4d_roof_b",
            () -> new df4d_roof_b(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_roof_c = BLOCKS.register("df4d_roof_c",
            () -> new df4d_roof_c(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_roof_d = BLOCKS.register("df4d_roof_d",
            () -> new df4d_roof_d(Block.Properties.ofFullCopy(c70_left_end_board.get())));

    public static final DeferredHolder<Block, Block> df4d_roof_e = BLOCKS.register("df4d_roof_e",
            () -> new df4d_roof_e(Block.Properties.ofFullCopy(c70_left_end_board.get())));


    public static final DeferredHolder<Block,DoorBlock> df4d_cab_door_a = BLOCKS.register("df4d_cab_door_a",
            () -> new df4d_cab_door_a(Block.Properties.of()));

    public static final DeferredHolder<Block,DoorBlock> df4d_cab_door_b = BLOCKS.register("df4d_cab_door_b",
            () -> new df4d_cab_door_b(Block.Properties.of()));

    public static final DeferredHolder<Block,DoorBlock> df4d_middle_door = BLOCKS.register("df4d_middle_door",
            () -> new df4d_middle_door(Block.Properties.of()));

    public static final DeferredHolder<Block, Block> df4d_floor_d = BLOCKS.register("df4d_floor_d",
            () -> new df4d_floor_d(Block.Properties.ofFullCopy(c70_left_end_board.get())));




    //tools

    public static final DeferredHolder<Block, Block> bell = BLOCKS.register(
            "bell",
            () -> new bell(Block.Properties.of()
                    .strength(2.0f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    //

    public static final DeferredHolder<Block, Block> railway_ballast = BLOCKS.register("railway_ballast",
            () -> new railway_ballast(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block,SlabBlock> railway_ballast_slab =
            BLOCKS.register("railway_ballast_slab", () -> new railway_ballast_slab(
                    BlockBehaviour.Properties.ofFullCopy(railway_ballast.get())
            ));

    public static final DeferredHolder<Block, Block> gravel = BLOCKS.register("gravel",
            () -> new gravel(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block,SlabBlock> gravel_slab =
            BLOCKS.register("gravel_slab", () -> new gravel_slab(
                    BlockBehaviour.Properties.ofFullCopy(gravel.get())));

    //signs

    public static final DeferredHolder<Block, Block> ticket_check = BLOCKS.register("ticket_check",
            () -> new ticket_check(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> police = BLOCKS.register("police",
            () -> new police(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> information = BLOCKS.register("information",
            () -> new information(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> toilet = BLOCKS.register("toilet",
            () -> new toilet(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> sign_post = BLOCKS.register("sign_post",
            () -> new sign_post(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> first_approach_section_sign = BLOCKS.register("first_approach_section_sign",
            () -> new first_approach_section_sign(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> second_approach_section_sign = BLOCKS.register("second_approach_section_sign",
            () -> new first_approach_section_sign(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> third_approach_section_sign = BLOCKS.register("third_approach_section_sign",
            () -> new first_approach_section_sign(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> dual_pantograph_prohibited_sign = BLOCKS.register("dual_pantograph_prohibited_sign",
            () -> new first_approach_section_sign(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> automatic_tickets = BLOCKS.register("automatic_tickets",
            () -> new automatic_tickets(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> entrance = BLOCKS.register("entrance",
            () -> new automatic_tickets(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> exit = BLOCKS.register("exit",
            () -> new automatic_tickets(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> railway_tickets = BLOCKS.register("railway_tickets",
            () -> new automatic_tickets(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> shopping = BLOCKS.register("shopping",
            () -> new automatic_tickets(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> vip = BLOCKS.register("vip",
            () -> new automatic_tickets(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> passengers_stopped = BLOCKS.register("passengers_stopped",
            () -> new automatic_tickets(Block.Properties.of()
                    .strength(2.0f)));
    public static final DeferredHolder<Block, Block> waiting_room = BLOCKS.register("waiting_room",
            () -> new automatic_tickets(Block.Properties.of()
                    .strength(2.0f)));



    public static final DeferredHolder<Block, Block> china_railway_freight_a = BLOCKS.register("china_railway_freight_a",
            () -> new china_railway_freight_a(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> china_railway_freight_b = BLOCKS.register("china_railway_freight_b",
            () -> new china_railway_freight_a(Block.Properties.ofFullCopy(china_railway_freight_a.get())));


    public static final DeferredHolder<Block, Block> catenary_end = BLOCKS.register("catenary_end",
            () -> new catenary_end(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> communication_switching = BLOCKS.register("communication_switching",
            () -> new catenary_end(Block.Properties.ofFullCopy(catenary_end.get())));

    public static final DeferredHolder<Block, Block> derailer = BLOCKS.register("derailer",
            () -> new catenary_end(Block.Properties.ofFullCopy(catenary_end.get())));

    public static final DeferredHolder<Block, Block> electrified_zone = BLOCKS.register("electrified_zone",
            () -> new catenary_end(Block.Properties.ofFullCopy(catenary_end.get())));

    public static final DeferredHolder<Block, Block> fifty_meters_to_buffer_stop = BLOCKS.register("fifty_meters_to_buffer_stop",
            () -> new catenary_end(Block.Properties.ofFullCopy(catenary_end.get())));

    public static final DeferredHolder<Block, Block> locomotive_stopping_position = BLOCKS.register("locomotive_stopping_position",
            () -> new catenary_end(Block.Properties.ofFullCopy(catenary_end.get())));

    public static final DeferredHolder<Block, Block> no_entry = BLOCKS.register("no_entry",
            () -> new catenary_end(Block.Properties.ofFullCopy(catenary_end.get())));

    public static final DeferredHolder<Block, Block> no_thoroughfare = BLOCKS.register("no_thoroughfare",
            () -> new catenary_end(Block.Properties.ofFullCopy(catenary_end.get())));

    public static final DeferredHolder<Block, Block> non_electrified_zone = BLOCKS.register("non_electrified_zone",
            () -> new catenary_end(Block.Properties.ofFullCopy(catenary_end.get())));

    public static final DeferredHolder<Block, Block> prepare_to_lower_pantograph = BLOCKS.register("prepare_to_lower_pantograph",
            () -> new catenary_end(Block.Properties.ofFullCopy(catenary_end.get())));

    public static final DeferredHolder<Block, Block> road_rail_vehicle_coordination = BLOCKS.register("road_rail_vehicle_coordination",
            () -> new catenary_end(Block.Properties.ofFullCopy(catenary_end.get())));

    public static final DeferredHolder<Block, Block> track_scale = BLOCKS.register("track_scale",
            () -> new catenary_end(Block.Properties.ofFullCopy(catenary_end.get())));

    public static final DeferredHolder<Block, Block> platform_1 = BLOCKS.register("platform_1",
            () -> new platform_1(Block.Properties.of()
                    .strength(2.0f)));

    public static final DeferredHolder<Block, Block> platform_1_and_2 = BLOCKS.register("platform_1_and_2",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_1_and_2_left = BLOCKS.register("platform_1_and_2_left",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_1_and_2_right = BLOCKS.register("platform_1_and_2_right",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_1_left = BLOCKS.register("platform_1_left",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_1_right = BLOCKS.register("platform_1_right",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_2 = BLOCKS.register("platform_2",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_2_and_3 = BLOCKS.register("platform_2_and_3",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_2_and_3_left = BLOCKS.register("platform_2_and_3_left",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_2_and_3_right = BLOCKS.register("platform_2_and_3_right",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_3 = BLOCKS.register("platform_3",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_3_and_4 = BLOCKS.register("platform_3_and_4",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_3_and_4_left = BLOCKS.register("platform_3_and_4_left",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_3_and_4_right = BLOCKS.register("platform_3_and_4_right",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_4 = BLOCKS.register("platform_4",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_4_and_5 = BLOCKS.register("platform_4_and_5",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_4_and_5_left = BLOCKS.register("platform_4_and_5_left",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_4_and_5_right = BLOCKS.register("platform_4_and_5_right",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_5 = BLOCKS.register("platform_5",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_5_and_6 = BLOCKS.register("platform_5_and_6",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_5_and_6_left = BLOCKS.register("platform_5_and_6_left",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_5_and_6_right = BLOCKS.register("platform_5_and_6_right",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_6 = BLOCKS.register("platform_6",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_6_and_7 = BLOCKS.register("platform_6_and_7",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_6_and_7_left = BLOCKS.register("platform_6_and_7_left",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_6_and_7_right = BLOCKS.register("platform_6_and_7_right",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_7 = BLOCKS.register("platform_7",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_7_and_8 = BLOCKS.register("platform_7_and_8",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_7_and_8_left = BLOCKS.register("platform_7_and_8_left",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_7_and_8_right = BLOCKS.register("platform_7_and_8_right",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> platform_8 = BLOCKS.register("platform_8",
            () -> new platform_1(Block.Properties.ofFullCopy(platform_1.get())));

    public static final DeferredHolder<Block, Block> drinking_water = BLOCKS.register("drinking_water",
            () -> new china_railway_freight_a(Block.Properties.ofFullCopy(china_railway_freight_a.get())));

    public static final DeferredHolder<Block, Block> crh_emu_stop = BLOCKS.register("crh_emu_stop",
            () -> new sign_post(Block.Properties.ofFullCopy(sign_post.get())));

    public static final DeferredHolder<Block, Block> crh_fight_location = BLOCKS.register("crh_fight_location",
            () -> new sign_post(Block.Properties.ofFullCopy(sign_post.get())));

    //Gate blocks
    public static final DeferredHolder<Block, Block> ArriveGateBlockRight = BLOCKS.register("arrive_gate_block_right",
            () -> new ArriveGateBlockRight(Block.Properties.of()
                    .strength(3.0f, 6.0f)
                    .noOcclusion())); //半透明渲染

    public static final DeferredHolder<Block, Block> DepartGateBlockRight = BLOCKS.register("depart_gate_block_right",
            () -> new DepartGateBlockRight(Block.Properties.ofFullCopy(ArriveGateBlockRight.get())));

    public static final DeferredHolder<Block, Block> GateBlockLeft = BLOCKS.register("gate_block_left",
            () -> new GateBlockLeft(Block.Properties.ofFullCopy(ArriveGateBlockRight.get())));

    public static final DeferredHolder<Block, Block> TicketMachineBlock  = BLOCKS.register("ticket_machine_block",
            () -> new TicketMachineBlock(Block.Properties.ofFullCopy(ArriveGateBlockRight.get())));



}