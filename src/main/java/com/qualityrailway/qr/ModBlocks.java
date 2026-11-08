package com.qualityrailway.qr;

import com.qualityrailway.qr.blocks.tools.AdvancedSignBlock;
import com.simibubi.create.AllInteractionBehaviours;
import com.simibubi.create.content.contraptions.actors.seat.SeatInteractionBehaviour;
import com.simibubi.create.content.contraptions.behaviour.MovingInteractionBehaviour;
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
import com.qualityrailway.qr.blocks.gates.*;
import com.qualityrailway.qr.blocks.doors.*;
import com.qualityrailway.qr.interaction.*;
import net.minecraft.world.level.block.Block;

import net.minecraft.world.level.block.SoundType;
import org.lwjgl.system.CallbackI;


public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, qr.MODID);

    // trains
    public static final RegistryObject<Block> C70_LEFT_END_BOARD = BLOCKS.register("c70_left_end_board",
            () -> new c70_left_end_board(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> C70_RIGHT_END_BOARD = BLOCKS.register("c70_right_end_board",
            () -> new c70_right_end_board(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> C70_FRONT_BOARD = BLOCKS.register("c70_front_board",
            () -> new c70_front_board(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> C70_LEFT_BOARD = BLOCKS.register("c70_left_board",
            () -> new c70_left_board(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> C70_DOOR = BLOCKS.register("c70_door",
            () -> new c70_door(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> C70_FLOOR = BLOCKS.register("c70_floor",
            () -> new c70_floor(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> GQ70_END_TANK = BLOCKS.register("gq70_end_tank",
            () -> new gq70_end_tank(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> GQ70_TANK_A = BLOCKS.register("gq70_tank_a",
            () -> new gq70_tank_a(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> GQ70_TANK_B = BLOCKS.register("gq70_tank_b",
            () -> new gq70_tank_b(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> GQ70_TANK_C = BLOCKS.register("gq70_tank_c",
            () -> new gq70_tank_c(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    // df7g

    public static final RegistryObject<Block> DF7G_CAB = BLOCKS.register("df7g_cab",
            () -> new df7g_cab(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_COWCATCHER_A = BLOCKS.register("df7g_cowcatcher_a",
            () -> new df7g_cowcatcher_a(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_COWCATCHER_B = BLOCKS.register("df7g_cowcatcher_b",
            () -> new df7g_cowcatcher_b(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_FLOOR_A = BLOCKS.register("df7g_floor_a",
            () -> new df7g_floor_a(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_FLOOR_B = BLOCKS.register("df7g_floor_b",
            () -> new df7g_floor_b(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_FLOOR_C = BLOCKS.register("df7g_floor_c",
            () -> new df7g_floor_c(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_FLOOR_D = BLOCKS.register("df7g_floor_d",
            () -> new df7g_floor_d(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_FLOOR_E = BLOCKS.register("df7g_floor_e",
            () -> new df7g_floor_e(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_FLOOR_F = BLOCKS.register("df7g_floor_f",
            () -> new df7g_floor_f(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_ENCLOSURE_A = BLOCKS.register("df7g_enclosure_a",
            () -> new df7g_enclosure_a(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_ENCLOSURE_B = BLOCKS.register("df7g_enclosure_b",
            () -> new df7g_enclosure_b(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_ENCLOSURE_C = BLOCKS.register("df7g_enclosure_c",
            () -> new df7g_enclosure_c(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_ENCLOSURE_D = BLOCKS.register("df7g_enclosure_d",
            () -> new df7g_enclosure_d(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_ENCLOSURE_E = BLOCKS.register("df7g_enclosure_e",
            () -> new df7g_enclosure_e(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_ENCLOSURE_F = BLOCKS.register("df7g_enclosure_f",
            () -> new df7g_enclosure_f(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF7G_ENCLOSURE_END = BLOCKS.register("df7g_enclosure_end",
            () -> new df7g_enclosure_end(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    // df4d

    public static final RegistryObject<Block> DF4D_CAB = BLOCKS.register("df4d_cab",
            () -> new df4d_cab(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_CHIMNEY_A = BLOCKS.register("df4d_chimney_a",
            () -> new df4d_chimney_a(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_FLOOR_A = BLOCKS.register("df4d_floor_a",
            () -> new df4d_floor_a(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_FLOOR_B = BLOCKS.register("df4d_floor_b",
            () -> new df4d_floor_b(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_FLOOR_C = BLOCKS.register("df4d_floor_c",
            () -> new df4d_floor_c(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_LEFT_BOARD_A = BLOCKS.register("df4d_left_board_a",
            () -> new df4d_left_board_a(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_LEFT_BOARD_B = BLOCKS.register("df4d_left_board_b",
            () -> new df4d_left_board_b(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_LEFT_BOARD_C = BLOCKS.register("df4d_left_board_c",
            () -> new df4d_left_board_c(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_LEFT_BOARD_D = BLOCKS.register("df4d_left_board_d",
            () -> new df4d_left_board_d(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_LEFT_BOARD_E = BLOCKS.register("df4d_left_board_e",
            () -> new df4d_left_board_e(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_LEFT_BOARD_F = BLOCKS.register("df4d_left_board_f",
            () -> new df4d_left_board_f(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_LEFT_BOARD_G = BLOCKS.register("df4d_left_board_g",
            () -> new df4d_left_board_g(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_RADIATOR_A = BLOCKS.register("df4d_radiator_a",
            () -> new df4d_radiator_a(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_RADIATOR_B = BLOCKS.register("df4d_radiator_b",
            () -> new df4d_radiator_b(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_RIGHT_BOARD_A = BLOCKS.register("df4d_right_board_a",
            () -> new df4d_right_board_a(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_RIGHT_BOARD_B = BLOCKS.register("df4d_right_board_b",
            () -> new df4d_right_board_b(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_RIGHT_BOARD_C = BLOCKS.register("df4d_right_board_c",
            () -> new df4d_right_board_c(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_RIGHT_BOARD_D = BLOCKS.register("df4d_right_board_d",
            () -> new df4d_right_board_d(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_RIGHT_BOARD_E = BLOCKS.register("df4d_right_board_e",
            () -> new df4d_right_board_e(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_RIGHT_BOARD_F = BLOCKS.register("df4d_right_board_f",
            () -> new df4d_right_board_f(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_RIGHT_BOARD_G = BLOCKS.register("df4d_right_board_g",
            () -> new df4d_right_board_g(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_ROOF_A = BLOCKS.register("df4d_roof_a",
            () -> new df4d_roof_a(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_ROOF_B = BLOCKS.register("df4d_roof_b",
            () -> new df4d_roof_b(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_ROOF_C = BLOCKS.register("df4d_roof_c",
            () -> new df4d_roof_c(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_ROOF_D = BLOCKS.register("df4d_roof_d",
            () -> new df4d_roof_d(Block.Properties.copy(C70_LEFT_END_BOARD.get())));

    public static final RegistryObject<Block> DF4D_ROOF_E = BLOCKS.register("df4d_roof_e",
            () -> new df4d_roof_e(Block.Properties.copy(C70_LEFT_END_BOARD.get())));


    public static final RegistryObject<Block> DF4D_CAB_DOOR_A = BLOCKS.register("df4d_cab_door_a",
            () -> new TrainDoorBlock(Block.Properties.of(Material.WOOD)));

    public static final RegistryObject<DoorBlock> DF4D_CAB_DOOR_B = BLOCKS.register("df4d_cab_door_b",
            () -> new df4d_cab_door_b(Block.Properties.of(Material.WOOD)));

    public static final RegistryObject<DoorBlock> DF4D_MIDDLE_DOOR = BLOCKS.register("df4d_middle_door",
            () -> new df4d_middle_door(Block.Properties.of(Material.WOOD)));

    public static final RegistryObject<Block> DF4D_FLOOR_D = BLOCKS.register("df4d_floor_d",
            () -> new df4d_floor_d(Block.Properties.of(Material.STONE).noOcclusion()));




    //tools

    public static final RegistryObject<Block> BELL = BLOCKS.register(
            "bell",
            () -> new bell(Block.Properties.of(Material.STONE)
                    .strength(2.0f).requiresCorrectToolForDrops().sound(SoundType.STONE)));

    //

    public static final RegistryObject<Block> RAILWAY_BALLAST = BLOCKS.register("railway_ballast",
            () -> new railway_ballast(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<SlabBlock> RAILWAY_BALLAST_SLAB =
            BLOCKS.register("railway_ballast_slab", () -> new railway_ballast_slab(
                    BlockBehaviour.Properties.copy(RAILWAY_BALLAST.get())
            ));

    public static final RegistryObject<Block> GRAVEL = BLOCKS.register("gravel",
            () -> new gravel(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<SlabBlock> GRAVEL_SLAB =
            BLOCKS.register("gravel_slab", () -> new gravel_slab(
                    BlockBehaviour.Properties.copy(GRAVEL.get())));

    //signs

    public static final RegistryObject<Block> TICKET_CHECK = BLOCKS.register("ticket_check",
            () -> new ticket_check(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> POLICE = BLOCKS.register("police",
            () -> new police(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> INFORMATION = BLOCKS.register("information",
            () -> new information(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> TOILET = BLOCKS.register("toilet",
            () -> new toilet(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> SIGN_POST = BLOCKS.register("sign_post",
            () -> new sign_post(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> FIRST_APPROACH_SECTION_SIGN = BLOCKS.register("first_approach_section_sign",
            () -> new first_approach_section_sign(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> SECOND_APPROACH_SECTION_SIGN = BLOCKS.register("second_approach_section_sign",
            () -> new first_approach_section_sign(Block.Properties.copy(FIRST_APPROACH_SECTION_SIGN.get())));

    public static final RegistryObject<Block> THIRD_APPROACH_SECTION_SIGN = BLOCKS.register("third_approach_section_sign",
            () -> new first_approach_section_sign(Block.Properties.copy(FIRST_APPROACH_SECTION_SIGN.get())));

    public static final RegistryObject<Block> DUAL_PANTOGRAPH_PROHIBITED_SIGN = BLOCKS.register("dual_pantograph_prohibited_sign",
            () -> new first_approach_section_sign(Block.Properties.copy(FIRST_APPROACH_SECTION_SIGN.get())));

    public static final RegistryObject<Block> AUTOMATIC_TICKETS = BLOCKS.register("automatic_tickets",
            () -> new automatic_tickets(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> ENTRANCE = BLOCKS.register("entrance",
            () -> new automatic_tickets(Block.Properties.copy(AUTOMATIC_TICKETS.get())));

    public static final RegistryObject<Block> EXIT = BLOCKS.register("exit",
            () -> new automatic_tickets(Block.Properties.copy(AUTOMATIC_TICKETS.get())));

    public static final RegistryObject<Block> RAILWAY_TICKETS = BLOCKS.register("railway_tickets",
            () -> new automatic_tickets(Block.Properties.copy(AUTOMATIC_TICKETS.get())));

    public static final RegistryObject<Block> SHOPPING = BLOCKS.register("shopping",
            () -> new automatic_tickets(Block.Properties.copy(AUTOMATIC_TICKETS.get())));

    public static final RegistryObject<Block> VIP = BLOCKS.register("vip",
            () -> new automatic_tickets(Block.Properties.copy(AUTOMATIC_TICKETS.get())));

    public static final RegistryObject<Block> PASSENGERS_STOPPED = BLOCKS.register("passengers_stopped",
            () -> new automatic_tickets(Block.Properties.copy(AUTOMATIC_TICKETS.get())));

    public static final RegistryObject<Block> WAITING_ROOM = BLOCKS.register("waiting_room",
            () -> new automatic_tickets(Block.Properties.copy(AUTOMATIC_TICKETS.get())));



    public static final RegistryObject<Block> CHINA_RAILWAY_FREIGHT_A = BLOCKS.register("china_railway_freight_a",
            () -> new china_railway_freight_a(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> CHINA_RAILWAY_FREIGHT_B = BLOCKS.register("china_railway_freight_b",
            () -> new china_railway_freight_a(Block.Properties.copy(CHINA_RAILWAY_FREIGHT_A.get())));


    public static final RegistryObject<Block> CATENARY_END = BLOCKS.register("catenary_end",
            () -> new catenary_end(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> COMMUNICATION_SWITCHING = BLOCKS.register("communication_switching",
            () -> new catenary_end(Block.Properties.copy(CATENARY_END.get())));

    public static final RegistryObject<Block> DERAILER = BLOCKS.register("derailer",
            () -> new catenary_end(Block.Properties.copy(CATENARY_END.get())));

    public static final RegistryObject<Block> ELECTRIFIED_ZONE = BLOCKS.register("electrified_zone",
            () -> new catenary_end(Block.Properties.copy(CATENARY_END.get())));

    public static final RegistryObject<Block> FIFTY_METERS_TO_BUFFER_STOP = BLOCKS.register("fifty_meters_to_buffer_stop",
            () -> new catenary_end(Block.Properties.copy(CATENARY_END.get())));

    public static final RegistryObject<Block> LOCOMOTIVE_STOPPING_POSITION = BLOCKS.register("locomotive_stopping_position",
            () -> new catenary_end(Block.Properties.copy(CATENARY_END.get())));

    public static final RegistryObject<Block> NO_ENTRY = BLOCKS.register("no_entry",
            () -> new catenary_end(Block.Properties.copy(CATENARY_END.get())));

    public static final RegistryObject<Block> NO_THOROUGHFARE = BLOCKS.register("no_thoroughfare",
            () -> new catenary_end(Block.Properties.copy(CATENARY_END.get())));

    public static final RegistryObject<Block> NON_ELECTRIFIED_ZONE = BLOCKS.register("non_electrified_zone",
            () -> new catenary_end(Block.Properties.copy(CATENARY_END.get())));

    public static final RegistryObject<Block> PREPARE_TO_LOWER_PANTOGRAPH = BLOCKS.register("prepare_to_lower_pantograph",
            () -> new catenary_end(Block.Properties.copy(CATENARY_END.get())));

    public static final RegistryObject<Block> ROAD_RAIL_VEHICLE_COORDINATION = BLOCKS.register("road_rail_vehicle_coordination",
            () -> new catenary_end(Block.Properties.copy(CATENARY_END.get())));

    public static final RegistryObject<Block> TRACK_SCALE = BLOCKS.register("track_scale",
            () -> new catenary_end(Block.Properties.copy(CATENARY_END.get())));

    public static final RegistryObject<Block> PLATFORM_1 = BLOCKS.register("platform_1",
            () -> new platform_1(Block.Properties.of(Material.STONE)
                    .strength(2.0f)));

    public static final RegistryObject<Block> PLATFORM_1_AND_2 = BLOCKS.register("platform_1_and_2",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_1_AND_2_LEFT = BLOCKS.register("platform_1_and_2_left",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_1_AND_2_RIGHT = BLOCKS.register("platform_1_and_2_right",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_1_LEFT = BLOCKS.register("platform_1_left",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_1_RIGHT = BLOCKS.register("platform_1_right",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_2 = BLOCKS.register("platform_2",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_2_AND_3 = BLOCKS.register("platform_2_and_3",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_2_AND_3_LEFT = BLOCKS.register("platform_2_and_3_left",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_2_AND_3_RIGHT = BLOCKS.register("platform_2_and_3_right",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_3 = BLOCKS.register("platform_3",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_3_AND_4 = BLOCKS.register("platform_3_and_4",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_3_AND_4_LEFT = BLOCKS.register("platform_3_and_4_left",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_3_AND_4_RIGHT = BLOCKS.register("platform_3_and_4_right",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_4 = BLOCKS.register("platform_4",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_4_AND_5 = BLOCKS.register("platform_4_and_5",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_4_AND_5_LEFT = BLOCKS.register("platform_4_and_5_left",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_4_AND_5_RIGHT = BLOCKS.register("platform_4_and_5_right",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_5 = BLOCKS.register("platform_5",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_5_AND_6 = BLOCKS.register("platform_5_and_6",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_5_AND_6_LEFT = BLOCKS.register("platform_5_and_6_left",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_5_AND_6_RIGHT = BLOCKS.register("platform_5_and_6_right",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_6 = BLOCKS.register("platform_6",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_6_AND_7 = BLOCKS.register("platform_6_and_7",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_6_AND_7_LEFT = BLOCKS.register("platform_6_and_7_left",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_6_AND_7_RIGHT = BLOCKS.register("platform_6_and_7_right",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_7 = BLOCKS.register("platform_7",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_7_AND_8 = BLOCKS.register("platform_7_and_8",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_7_AND_8_LEFT = BLOCKS.register("platform_7_and_8_left",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_7_AND_8_RIGHT = BLOCKS.register("platform_7_and_8_right",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> PLATFORM_8 = BLOCKS.register("platform_8",
            () -> new platform_1(Block.Properties.copy(PLATFORM_1.get())));

    public static final RegistryObject<Block> DRINKING_WATER = BLOCKS.register("drinking_water",
            () -> new china_railway_freight_a(Block.Properties.copy(CHINA_RAILWAY_FREIGHT_A.get())));

    public static final RegistryObject<Block> CRH_EMU_STOP = BLOCKS.register("crh_emu_stop",
            () -> new sign_post(Block.Properties.copy(SIGN_POST.get())));

    public static final RegistryObject<Block> CRH_FIGHT_LOCATION = BLOCKS.register("crh_fight_location",
            () -> new sign_post(Block.Properties.copy(SIGN_POST.get())));
    //Gate blocks
    public static final RegistryObject<Block> ARRIVE_GATE_BLOCK_RIGHT = BLOCKS.register("arrive_gate_block_right",
            () -> new ArriveGateBlockRight(Block.Properties.of(Material.METAL)
                    .strength(3.0f, 6.0f)
                    .noOcclusion())); //半透明渲染

    public static final RegistryObject<Block> DEPART_GATE_BLOCK_RIGHT = BLOCKS.register("depart_gate_block_right",
            () -> new DepartGateBlockRight(Block.Properties.copy(ARRIVE_GATE_BLOCK_RIGHT.get())));

    public static final RegistryObject<Block> GATE_BLOCK_LEFT = BLOCKS.register("gate_block_left",
            () -> new GateBlockLeft(Block.Properties.copy(ARRIVE_GATE_BLOCK_RIGHT.get())));

    public static final RegistryObject<Block> TICKET_MACHINE_BLOCK = BLOCKS.register("ticket_machine_block",
            () -> new TicketMachineBlock(Block.Properties.copy(ARRIVE_GATE_BLOCK_RIGHT.get())));
    //door
    public static final RegistryObject<Block> TRAIN_DOOR =
            BLOCKS.register("train_door",
                    () -> new TrainDoorBlock(Block.Properties.of(Material.METAL)
                            .strength(2.0f, 6.0f)
                            .noOcclusion()));

    public static final RegistryObject<Block> TRAIN_SEAT = BLOCKS.register("train_seat",
            () -> new CustomTrainSeatBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(1.5f)
                    .noOcclusion()));

    public static final RegistryObject<Block> ADVANCED_SIGN =
            BLOCKS.register("advanced_sign",
                    () -> new AdvancedSignBlock(BlockBehaviour.Properties.of(Material.WOOD)
                            .strength(1.0f, 3.0f)
                            .noOcclusion()));



    public static void registerInteractionBehaviours() {
        MovingInteractionBehaviour trainDoorInteraction = new TrainDoorInteraction();
        AllInteractionBehaviours.registerBehaviour(TRAIN_DOOR.get(), trainDoorInteraction);

        MovingInteractionBehaviour seatInteraction = new SeatInteractionBehaviour();
        AllInteractionBehaviours.registerBehaviour(TRAIN_SEAT.get(), seatInteraction);
    }
}