package com.qualityrailway.qr;

import com.qualityrailway.qr.items.*;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, qr.MODID);

    // 注册方块对应的物品
    public static final RegistryObject<Item> C70_LEFT_END_BOARD = ITEMS.register("c70_left_end_board",
            () -> new BlockItem(ModBlocks.C70_LEFT_END_BOARD.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> C70_RIGHT_END_BOARD = ITEMS.register("c70_right_end_board",
            () -> new BlockItem(ModBlocks.C70_RIGHT_END_BOARD.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> C70_FRONT_BOARD = ITEMS.register("c70_front_board",
            () -> new BlockItem(ModBlocks.C70_FRONT_BOARD.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> C70_LEFT_BOARD = ITEMS.register("c70_left_board",
            () -> new BlockItem(ModBlocks.C70_LEFT_BOARD.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> C70_DOOR = ITEMS.register("c70_door",
            () -> new BlockItem(ModBlocks.C70_DOOR.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> C70_FLOOR = ITEMS.register("c70_floor",
            () -> new BlockItem(ModBlocks.C70_FLOOR.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> GQ70_END_TANK = ITEMS.register("gq70_end_tank",
            () -> new BlockItem(ModBlocks.GQ70_END_TANK.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> GQ70_TANK_A = ITEMS.register("gq70_tank_a",
            () -> new BlockItem(ModBlocks.GQ70_TANK_A.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> GQ70_TANK_B = ITEMS.register("gq70_tank_b",
            () -> new BlockItem(ModBlocks.GQ70_TANK_B.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> GQ70_TANK_C = ITEMS.register("gq70_tank_c",
            () -> new BlockItem(ModBlocks.GQ70_TANK_C.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    //df7g

    public static final RegistryObject<Item> DF7G_CAB = ITEMS.register("df7g_cab",
            () -> new BlockItem(ModBlocks.DF7G_CAB.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_COWCATCHER_A = ITEMS.register("df7g_cowcatcher_a",
            () -> new BlockItem(ModBlocks.DF7G_COWCATCHER_A.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_COWCATCHER_B = ITEMS.register("df7g_cowcatcher_b",
            () -> new BlockItem(ModBlocks.DF7G_COWCATCHER_B.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_FLOOR_A = ITEMS.register("df7g_floor_a",
            () -> new BlockItem(ModBlocks.DF7G_FLOOR_A.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_FLOOR_B = ITEMS.register("df7g_floor_b",
            () -> new BlockItem(ModBlocks.DF7G_FLOOR_B.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_FLOOR_C = ITEMS.register("df7g_floor_c",
            () -> new BlockItem(ModBlocks.DF7G_FLOOR_C.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_FLOOR_D = ITEMS.register("df7g_floor_d",
            () -> new BlockItem(ModBlocks.DF7G_FLOOR_D.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_FLOOR_E = ITEMS.register("df7g_floor_e",
            () -> new BlockItem(ModBlocks.DF7G_FLOOR_E.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_FLOOR_F = ITEMS.register("df7g_floor_f",
            () -> new BlockItem(ModBlocks.DF7G_FLOOR_F.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_ENCLOSURE_A = ITEMS.register("df7g_enclosure_a",
            () -> new BlockItem(ModBlocks.DF7G_ENCLOSURE_A.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_ENCLOSURE_B = ITEMS.register("df7g_enclosure_b",
            () -> new BlockItem(ModBlocks.DF7G_ENCLOSURE_B.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_ENCLOSURE_C = ITEMS.register("df7g_enclosure_c",
            () -> new BlockItem(ModBlocks.DF7G_ENCLOSURE_C.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_ENCLOSURE_D = ITEMS.register("df7g_enclosure_d",
            () -> new BlockItem(ModBlocks.DF7G_ENCLOSURE_D.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_ENCLOSURE_E = ITEMS.register("df7g_enclosure_e",
            () -> new BlockItem(ModBlocks.DF7G_ENCLOSURE_E.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_ENCLOSURE_F = ITEMS.register("df7g_enclosure_f",
            () -> new BlockItem(ModBlocks.DF7G_ENCLOSURE_F.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF7G_ENCLOSURE_END = ITEMS.register("df7g_enclosure_end",
            () -> new BlockItem(ModBlocks.DF7G_ENCLOSURE_END.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    //df4d

    public static final RegistryObject<Item> DF4D_CAB = ITEMS.register("df4d_cab",
            () -> new BlockItem(ModBlocks.DF4D_CAB.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_CHIMNEY_A = ITEMS.register("df4d_chimney_a",
            () -> new BlockItem(ModBlocks.DF4D_CHIMNEY_A.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_FLOOR_A = ITEMS.register("df4d_floor_a",
            () -> new BlockItem(ModBlocks.DF4D_FLOOR_A.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_FLOOR_B = ITEMS.register("df4d_floor_b",
            () -> new BlockItem(ModBlocks.DF4D_FLOOR_B.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_FLOOR_C = ITEMS.register("df4d_floor_c",
            () -> new BlockItem(ModBlocks.DF4D_FLOOR_C.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_LEFT_BOARD_A = ITEMS.register("df4d_left_board_a",
            () -> new BlockItem(ModBlocks.DF4D_LEFT_BOARD_A.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_LEFT_BOARD_B = ITEMS.register("df4d_left_board_b",
            () -> new BlockItem(ModBlocks.DF4D_LEFT_BOARD_B.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_LEFT_BOARD_C = ITEMS.register("df4d_left_board_c",
            () -> new BlockItem(ModBlocks.DF4D_LEFT_BOARD_C.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_LEFT_BOARD_D = ITEMS.register("df4d_left_board_d",
            () -> new BlockItem(ModBlocks.DF4D_LEFT_BOARD_D.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_LEFT_BOARD_E = ITEMS.register("df4d_left_board_e",
            () -> new BlockItem(ModBlocks.DF4D_LEFT_BOARD_E.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_LEFT_BOARD_F = ITEMS.register("df4d_left_board_f",
            () -> new BlockItem(ModBlocks.DF4D_LEFT_BOARD_F.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_LEFT_BOARD_G = ITEMS.register("df4d_left_board_g",
            () -> new BlockItem(ModBlocks.DF4D_LEFT_BOARD_G.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_RADIATOR_A = ITEMS.register("df4d_radiator_a",
            () -> new BlockItem(ModBlocks.DF4D_RADIATOR_A.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_RADIATOR_B = ITEMS.register("df4d_radiator_b",
            () -> new BlockItem(ModBlocks.DF4D_RADIATOR_B.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_RIGHT_BOARD_A = ITEMS.register("df4d_right_board_a",
            () -> new BlockItem(ModBlocks.DF4D_RIGHT_BOARD_A.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_RIGHT_BOARD_B = ITEMS.register("df4d_right_board_b",
            () -> new BlockItem(ModBlocks.DF4D_RIGHT_BOARD_B.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_RIGHT_BOARD_C = ITEMS.register("df4d_right_board_c",
            () -> new BlockItem(ModBlocks.DF4D_RIGHT_BOARD_C.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_RIGHT_BOARD_D = ITEMS.register("df4d_right_board_d",
            () -> new BlockItem(ModBlocks.DF4D_RIGHT_BOARD_D.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_RIGHT_BOARD_E = ITEMS.register("df4d_right_board_e",
            () -> new BlockItem(ModBlocks.DF4D_RIGHT_BOARD_E.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_RIGHT_BOARD_F = ITEMS.register("df4d_right_board_f",
            () -> new BlockItem(ModBlocks.DF4D_RIGHT_BOARD_F.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_RIGHT_BOARD_G = ITEMS.register("df4d_right_board_g",
            () -> new BlockItem(ModBlocks.DF4D_RIGHT_BOARD_G.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_ROOF_A = ITEMS.register("df4d_roof_a",
            () -> new BlockItem(ModBlocks.DF4D_ROOF_A.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_ROOF_B = ITEMS.register("df4d_roof_b",
            () -> new BlockItem(ModBlocks.DF4D_ROOF_B.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_ROOF_C = ITEMS.register("df4d_roof_c",
            () -> new BlockItem(ModBlocks.DF4D_ROOF_C.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_ROOF_D = ITEMS.register("df4d_roof_d",
            () -> new BlockItem(ModBlocks.DF4D_ROOF_D.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_ROOF_E = ITEMS.register("df4d_roof_e",
            () -> new BlockItem(ModBlocks.DF4D_ROOF_E.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_CAB_DOOR_A = ITEMS.register("df4d_cab_door_a",
            () -> new BlockItem(ModBlocks.DF4D_CAB_DOOR_A.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_CAB_DOOR_B = ITEMS.register("df4d_cab_door_b",
            () -> new BlockItem(ModBlocks.DF4D_CAB_DOOR_B.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_MIDDLE_DOOR = ITEMS.register("df4d_middle_door",
            () -> new BlockItem(ModBlocks.DF4D_MIDDLE_DOOR.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> DF4D_FLOOR_D = ITEMS.register("df4d_floor_d",
            () -> new BlockItem(ModBlocks.DF4D_FLOOR_D.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));



    // 注册图标物品
    public static final RegistryObject<Item> QR_ITEM = ITEMS.register("qr_item",
            () -> new qr_item(new Item.Properties()));

    public static final RegistryObject<Item> SPANNER = ITEMS.register("spanner",
            () -> new spanner(new Item.Properties()));

    public static final RegistryObject<Item> SIGN = ITEMS.register("sign",
            () -> new sign(new Item.Properties()));

    //railway_tools


    public static final RegistryObject<Item> STEEL_PLATE = ITEMS.register("steel_plate",
            () -> new steel_plate(new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));

    public static final RegistryObject<Item> BELL = ITEMS.register("bell",
            () -> new BlockItem(ModBlocks.BELL.get(), new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));

    public static final RegistryObject<Item> RAILWAY_BALLAST = ITEMS.register("railway_ballast",
            () -> new BlockItem(ModBlocks.RAILWAY_BALLAST.get(), new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));

    public static final RegistryObject<Item> RAILWAY_BALLAST_SLAB = ITEMS.register("railway_ballast_slab",
            () -> new BlockItem(ModBlocks.RAILWAY_BALLAST_SLAB.get(), new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));

    public static final RegistryObject<Item> GRAVEL = ITEMS.register("gravel",
            () -> new BlockItem(ModBlocks.GRAVEL.get(), new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));

    public static final RegistryObject<Item> GRAVEL_SLAB = ITEMS.register("gravel_slab",
            () -> new BlockItem(ModBlocks.GRAVEL_SLAB.get(), new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));

    //signs

    public static final RegistryObject<Item> TICKET_CHECK = ITEMS.register("ticket_check",
            () -> new BlockItem(ModBlocks.TICKET_CHECK.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> POLICE = ITEMS.register("police",
            () -> new BlockItem(ModBlocks.POLICE.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> INFORMATION = ITEMS.register("information",
            () -> new BlockItem(ModBlocks.INFORMATION.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> TOILET = ITEMS.register("toilet",
            () -> new BlockItem(ModBlocks.TOILET.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> SIGN_POST = ITEMS.register("sign_post",
            () -> new BlockItem(ModBlocks.SIGN_POST.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> FIRST_APPROACH_SECTION_SIGN = ITEMS.register("first_approach_section_sign",
            () -> new BlockItem(ModBlocks.FIRST_APPROACH_SECTION_SIGN.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> SECOND_APPROACH_SECTION_SIGN = ITEMS.register("second_approach_section_sign",
            () -> new BlockItem(ModBlocks.SECOND_APPROACH_SECTION_SIGN.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> THIRD_APPROACH_SECTION_SIGN = ITEMS.register("third_approach_section_sign",
            () -> new BlockItem(ModBlocks.THIRD_APPROACH_SECTION_SIGN.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> DUAL_PANTOGRAPH_PROHIBITED_SIGN = ITEMS.register("dual_pantograph_prohibited_sign",
            () -> new BlockItem(ModBlocks.DUAL_PANTOGRAPH_PROHIBITED_SIGN.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> AUTOMATIC_TICKETS = ITEMS.register("automatic_tickets",
            () -> new BlockItem(ModBlocks.AUTOMATIC_TICKETS.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> ENTRANCE = ITEMS.register("entrance",
            () -> new BlockItem(ModBlocks.ENTRANCE.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> EXIT = ITEMS.register("exit",
            () -> new BlockItem(ModBlocks.EXIT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> RAILWAY_TICKETS = ITEMS.register("railway_tickets",
            () -> new BlockItem(ModBlocks.RAILWAY_TICKETS.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> SHOPPING = ITEMS.register("shopping",
            () -> new BlockItem(ModBlocks.SHOPPING.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> VIP = ITEMS.register("vip",
            () -> new BlockItem(ModBlocks.VIP.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PASSENGERS_STOPPED = ITEMS.register("passengers_stopped",
            () -> new BlockItem(ModBlocks.PASSENGERS_STOPPED.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> WAITING_ROOM = ITEMS.register("waiting_room",
            () -> new BlockItem(ModBlocks.WAITING_ROOM.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));



    public static final RegistryObject<Item> CHINA_RAILWAY_FREIGHT_A = ITEMS.register("china_railway_freight_a",
            () -> new BlockItem(ModBlocks.CHINA_RAILWAY_FREIGHT_A.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> CHINA_RAILWAY_FREIGHT_B = ITEMS.register("china_railway_freight_b",
            () -> new BlockItem(ModBlocks.CHINA_RAILWAY_FREIGHT_B.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> CATENARY_END = ITEMS.register("catenary_end",
            () -> new BlockItem(ModBlocks.CATENARY_END.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> COMMUNICATION_SWITCHING = ITEMS.register("communication_switching",
            () -> new BlockItem(ModBlocks.COMMUNICATION_SWITCHING.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> DERAILER = ITEMS.register("derailer",
            () -> new BlockItem(ModBlocks.DERAILER.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> ELECTRIFIED_ZONE = ITEMS.register("electrified_zone",
            () -> new BlockItem(ModBlocks.ELECTRIFIED_ZONE.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> FIFTY_METERS_TO_BUFFER_STOP = ITEMS.register("fifty_meters_to_buffer_stop",
            () -> new BlockItem(ModBlocks.FIFTY_METERS_TO_BUFFER_STOP.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> LOCOMOTIVE_STOPPING_POSITION = ITEMS.register("locomotive_stopping_position",
            () -> new BlockItem(ModBlocks.LOCOMOTIVE_STOPPING_POSITION.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> NO_ENTRY = ITEMS.register("no_entry",
            () -> new BlockItem(ModBlocks.NO_ENTRY.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> NO_THOROUGHFARE = ITEMS.register("no_thoroughfare",
            () -> new BlockItem(ModBlocks.NO_THOROUGHFARE.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> NON_ELECTRIFIED_ZONE = ITEMS.register("non_electrified_zone",
            () -> new BlockItem(ModBlocks.NON_ELECTRIFIED_ZONE.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PREPARE_TO_LOWER_PANTOGRAPH = ITEMS.register("prepare_to_lower_pantograph",
            () -> new BlockItem(ModBlocks.PREPARE_TO_LOWER_PANTOGRAPH.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> ROAD_RAIL_VEHICLE_COORDINATION = ITEMS.register("road_rail_vehicle_coordination",
            () -> new BlockItem(ModBlocks.ROAD_RAIL_VEHICLE_COORDINATION.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> TRACK_SCALE = ITEMS.register("track_scale",
            () -> new BlockItem(ModBlocks.TRACK_SCALE.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_1 = ITEMS.register("platform_1",
            () -> new BlockItem(ModBlocks.PLATFORM_1.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_1_AND_2 = ITEMS.register("platform_1_and_2",
            () -> new BlockItem(ModBlocks.PLATFORM_1_AND_2.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_1_AND_2_LEFT = ITEMS.register("platform_1_and_2_left",
            () -> new BlockItem(ModBlocks.PLATFORM_1_AND_2_LEFT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_1_AND_2_RIGHT = ITEMS.register("platform_1_and_2_right",
            () -> new BlockItem(ModBlocks.PLATFORM_1_AND_2_RIGHT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_1_LEFT = ITEMS.register("platform_1_left",
            () -> new BlockItem(ModBlocks.PLATFORM_1_LEFT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_1_RIGHT = ITEMS.register("platform_1_right",
            () -> new BlockItem(ModBlocks.PLATFORM_1_RIGHT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_2 = ITEMS.register("platform_2",
            () -> new BlockItem(ModBlocks.PLATFORM_2.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_2_AND_3 = ITEMS.register("platform_2_and_3",
            () -> new BlockItem(ModBlocks.PLATFORM_2_AND_3.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_2_AND_3_LEFT = ITEMS.register("platform_2_and_3_left",
            () -> new BlockItem(ModBlocks.PLATFORM_2_AND_3_LEFT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_2_AND_3_RIGHT = ITEMS.register("platform_2_and_3_right",
            () -> new BlockItem(ModBlocks.PLATFORM_2_AND_3_RIGHT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_3 = ITEMS.register("platform_3",
            () -> new BlockItem(ModBlocks.PLATFORM_3.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_3_AND_4 = ITEMS.register("platform_3_and_4",
            () -> new BlockItem(ModBlocks.PLATFORM_3_AND_4.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_3_AND_4_LEFT = ITEMS.register("platform_3_and_4_left",
            () -> new BlockItem(ModBlocks.PLATFORM_3_AND_4_LEFT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_3_AND_4_RIGHT = ITEMS.register("platform_3_and_4_right",
            () -> new BlockItem(ModBlocks.PLATFORM_3_AND_4_RIGHT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_4 = ITEMS.register("platform_4",
            () -> new BlockItem(ModBlocks.PLATFORM_4.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_4_AND_5 = ITEMS.register("platform_4_and_5",
            () -> new BlockItem(ModBlocks.PLATFORM_4_AND_5.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_4_AND_5_LEFT = ITEMS.register("platform_4_and_5_left",
            () -> new BlockItem(ModBlocks.PLATFORM_4_AND_5_LEFT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_4_AND_5_RIGHT = ITEMS.register("platform_4_and_5_right",
            () -> new BlockItem(ModBlocks.PLATFORM_4_AND_5_RIGHT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_5 = ITEMS.register("platform_5",
            () -> new BlockItem(ModBlocks.PLATFORM_5.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_5_AND_6 = ITEMS.register("platform_5_and_6",
            () -> new BlockItem(ModBlocks.PLATFORM_5_AND_6.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_5_AND_6_LEFT = ITEMS.register("platform_5_and_6_left",
            () -> new BlockItem(ModBlocks.PLATFORM_5_AND_6_LEFT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_5_AND_6_RIGHT = ITEMS.register("platform_5_and_6_right",
            () -> new BlockItem(ModBlocks.PLATFORM_5_AND_6_RIGHT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_6 = ITEMS.register("platform_6",
            () -> new BlockItem(ModBlocks.PLATFORM_6.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_6_AND_7 = ITEMS.register("platform_6_and_7",
            () -> new BlockItem(ModBlocks.PLATFORM_6_AND_7.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_6_AND_7_LEFT = ITEMS.register("platform_6_and_7_left",
            () -> new BlockItem(ModBlocks.PLATFORM_6_AND_7_LEFT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_6_AND_7_RIGHT = ITEMS.register("platform_6_and_7_right",
            () -> new BlockItem(ModBlocks.PLATFORM_6_AND_7_RIGHT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_7 = ITEMS.register("platform_7",
            () -> new BlockItem(ModBlocks.PLATFORM_7.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_7_AND_8 = ITEMS.register("platform_7_and_8",
            () -> new BlockItem(ModBlocks.PLATFORM_7_AND_8.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_7_AND_8_LEFT = ITEMS.register("platform_7_and_8_left",
            () -> new BlockItem(ModBlocks.PLATFORM_7_AND_8_LEFT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_7_AND_8_RIGHT = ITEMS.register("platform_7_and_8_right",
            () -> new BlockItem(ModBlocks.PLATFORM_7_AND_8_RIGHT.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> PLATFORM_8 = ITEMS.register("platform_8",
            () -> new BlockItem(ModBlocks.PLATFORM_8.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> DRINKING_WATER = ITEMS.register("drinking_water",
            () -> new BlockItem(ModBlocks.DRINKING_WATER.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> CRH_EMU_STOP = ITEMS.register("crh_emu_stop",
            () -> new BlockItem(ModBlocks.CRH_EMU_STOP.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    public static final RegistryObject<Item> CRH_FIGHT_LOCATION = ITEMS.register("crh_fight_location",
            () -> new BlockItem(ModBlocks.CRH_FIGHT_LOCATION.get(), new Item.Properties().tab(ModCreativeTab.SIGNS)));

    //tickets
    public static final RegistryObject<Item> ARRIVE_GATE_BLOCK_RIGHT = ITEMS.register("arrive_gate_block_right",
            () -> new BlockItem(ModBlocks.ARRIVE_GATE_BLOCK_RIGHT.get(), new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));

    public static final RegistryObject<Item> DEPART_GATE_BLOCK_RIGHT = ITEMS.register("depart_gate_block_right",
            () -> new BlockItem(ModBlocks.DEPART_GATE_BLOCK_RIGHT.get(), new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));

    public static final RegistryObject<Item> GATE_BLOCK_LEFT = ITEMS.register("gate_block_left",
            () -> new BlockItem(ModBlocks.GATE_BLOCK_LEFT.get(), new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));

    public static final RegistryObject<Item> TICKET_MACHINE_BLOCK = ITEMS.register("ticket_machine_block",
            () -> new BlockItem(ModBlocks.TICKET_MACHINE_BLOCK.get(), new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));


    // 注册车票物品，都要添加ticket标签
    public static final RegistryObject<Item> RED_TICKET = ITEMS.register("red_ticket",
            () -> new RedTicket(new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));

    public static final RegistryObject<Item> BLUE_TICKET = ITEMS.register("blue_ticket",
            () -> new RedTicket(new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));
    //door
    public static final RegistryObject<Item> TRAIN_DOOR = ITEMS.register("train_door",
            () -> new BlockItem(ModBlocks.TRAIN_DOOR.get(), new Item.Properties().tab(ModCreativeTab.TRAINS)));

    public static final RegistryObject<Item> TRAIN_SEAT = ITEMS.register("train_seat",
            () -> new BlockItem(ModBlocks.TRAIN_SEAT.get(),
                    new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));

    public static final RegistryObject<Item> ADVANCED_SIGN = ITEMS.register("advanced_sign",
            () -> new BlockItem(ModBlocks.ADVANCED_SIGN.get(),
                    new Item.Properties().tab(ModCreativeTab.RAILWAY_TOOLS)));
}