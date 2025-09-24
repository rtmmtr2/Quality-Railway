package com.qualityrailway.qr;

import com.qualityrailway.qr.items.*;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, qr.MODID);

    // 注册方块对应的物品
    public static final RegistryObject<Item> c70_left_end_board = ITEMS.register("c70_left_end_board",
            () -> new BlockItem(ModBlocks.c70_left_end_board.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> c70_right_end_board = ITEMS.register("c70_right_end_board",
            () -> new BlockItem(ModBlocks.c70_right_end_board.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> c70_front_board = ITEMS.register("c70_front_board",
            () -> new BlockItem(ModBlocks.c70_front_board.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> c70_left_board = ITEMS.register("c70_left_board",
            () -> new BlockItem(ModBlocks.c70_left_board.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> c70_door = ITEMS.register("c70_door",
            () -> new BlockItem(ModBlocks.c70_door.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> c70_floor = ITEMS.register("c70_floor",
            () -> new BlockItem(ModBlocks.c70_floor.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> gq70_end_tank = ITEMS.register("gq70_end_tank",
            () -> new BlockItem(ModBlocks.gq70_end_tank.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> gq70_tank_a = ITEMS.register("gq70_tank_a",
            () -> new BlockItem(ModBlocks.gq70_tank_a.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> gq70_tank_b = ITEMS.register("gq70_tank_b",
            () -> new BlockItem(ModBlocks.gq70_tank_b.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> gq70_tank_c = ITEMS.register("gq70_tank_c",
            () -> new BlockItem(ModBlocks.gq70_tank_c.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    //df7g

    public static final RegistryObject<Item> df7g_cab = ITEMS.register("df7g_cab",
            () -> new BlockItem(ModBlocks.df7g_cab.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_cowcatcher_a = ITEMS.register("df7g_cowcatcher_a",
            () -> new BlockItem(ModBlocks.df7g_cowcatcher_a.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_cowcatcher_b = ITEMS.register("df7g_cowcatcher_b",
            () -> new BlockItem(ModBlocks.df7g_cowcatcher_b.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_floor_a = ITEMS.register("df7g_floor_a",
            () -> new BlockItem(ModBlocks.df7g_floor_a.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_floor_b = ITEMS.register("df7g_floor_b",
            () -> new BlockItem(ModBlocks.df7g_floor_b.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_floor_c = ITEMS.register("df7g_floor_c",
            () -> new BlockItem(ModBlocks.df7g_floor_c.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_floor_d = ITEMS.register("df7g_floor_d",
            () -> new BlockItem(ModBlocks.df7g_floor_d.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_floor_e = ITEMS.register("df7g_floor_e",
            () -> new BlockItem(ModBlocks.df7g_floor_e.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_floor_f = ITEMS.register("df7g_floor_f",
            () -> new BlockItem(ModBlocks.df7g_floor_f.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_enclosure_a = ITEMS.register("df7g_enclosure_a",
            () -> new BlockItem(ModBlocks.df7g_enclosure_a.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_enclosure_b = ITEMS.register("df7g_enclosure_b",
            () -> new BlockItem(ModBlocks.df7g_enclosure_b.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_enclosure_c = ITEMS.register("df7g_enclosure_c",
            () -> new BlockItem(ModBlocks.df7g_enclosure_c.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_enclosure_d = ITEMS.register("df7g_enclosure_d",
            () -> new BlockItem(ModBlocks.df7g_enclosure_d.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_enclosure_e = ITEMS.register("df7g_enclosure_e",
            () -> new BlockItem(ModBlocks.df7g_enclosure_e.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_enclosure_f = ITEMS.register("df7g_enclosure_f",
            () -> new BlockItem(ModBlocks.df7g_enclosure_f.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df7g_enclosure_end = ITEMS.register("df7g_enclosure_end",
            () -> new BlockItem(ModBlocks.df7g_enclosure_end.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    //df4d

    public static final RegistryObject<Item> df4d_cab = ITEMS.register("df4d_cab",
            () -> new BlockItem(ModBlocks.df4d_cab.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_chimney_a = ITEMS.register("df4d_chimney_a",
            () -> new BlockItem(ModBlocks.df4d_chimney_a.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_floor_a = ITEMS.register("df4d_floor_a",
            () -> new BlockItem(ModBlocks.df4d_floor_a.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_floor_b = ITEMS.register("df4d_floor_b",
            () -> new BlockItem(ModBlocks.df4d_floor_b.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_floor_c = ITEMS.register("df4d_floor_c",
            () -> new BlockItem(ModBlocks.df4d_floor_c.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_left_board_a = ITEMS.register("df4d_left_board_a",
            () -> new BlockItem(ModBlocks.df4d_left_board_a.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_left_board_b = ITEMS.register("df4d_left_board_b",
            () -> new BlockItem(ModBlocks.df4d_left_board_b.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_left_board_c = ITEMS.register("df4d_left_board_c",
            () -> new BlockItem(ModBlocks.df4d_left_board_c.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_left_board_d = ITEMS.register("df4d_left_board_d",
            () -> new BlockItem(ModBlocks.df4d_left_board_d.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_left_board_e = ITEMS.register("df4d_left_board_e",
            () -> new BlockItem(ModBlocks.df4d_left_board_e.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_left_board_f = ITEMS.register("df4d_left_board_f",
            () -> new BlockItem(ModBlocks.df4d_left_board_f.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_left_board_g = ITEMS.register("df4d_left_board_g",
            () -> new BlockItem(ModBlocks.df4d_left_board_g.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_radiator_a = ITEMS.register("df4d_radiator_a",
            () -> new BlockItem(ModBlocks.df4d_radiator_a.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_radiator_b = ITEMS.register("df4d_radiator_b",
            () -> new BlockItem(ModBlocks.df4d_radiator_b.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_right_board_a = ITEMS.register("df4d_right_board_a",
            () -> new BlockItem(ModBlocks.df4d_right_board_a.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_right_board_b = ITEMS.register("df4d_right_board_b",
            () -> new BlockItem(ModBlocks.df4d_right_board_b.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_right_board_c = ITEMS.register("df4d_right_board_c",
            () -> new BlockItem(ModBlocks.df4d_right_board_c.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_right_board_d = ITEMS.register("df4d_right_board_d",
            () -> new BlockItem(ModBlocks.df4d_right_board_d.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_right_board_e = ITEMS.register("df4d_right_board_e",
            () -> new BlockItem(ModBlocks.df4d_right_board_e.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_right_board_f = ITEMS.register("df4d_right_board_f",
            () -> new BlockItem(ModBlocks.df4d_right_board_f.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_right_board_g = ITEMS.register("df4d_right_board_g",
            () -> new BlockItem(ModBlocks.df4d_right_board_g.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_roof_a = ITEMS.register("df4d_roof_a",
            () -> new BlockItem(ModBlocks.df4d_roof_a.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_roof_b = ITEMS.register("df4d_roof_b",
            () -> new BlockItem(ModBlocks.df4d_roof_b.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_roof_c = ITEMS.register("df4d_roof_c",
            () -> new BlockItem(ModBlocks.df4d_roof_c.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_roof_d = ITEMS.register("df4d_roof_d",
            () -> new BlockItem(ModBlocks.df4d_roof_d.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_roof_e = ITEMS.register("df4d_roof_e",
            () -> new BlockItem(ModBlocks.df4d_roof_e.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_cab_door_a = ITEMS.register("df4d_cab_door_a",
            () -> new BlockItem(ModBlocks.df4d_cab_door_a.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_cab_door_b = ITEMS.register("df4d_cab_door_b",
            () -> new BlockItem(ModBlocks.df4d_cab_door_b.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_middle_door = ITEMS.register("df4d_middle_door",
            () -> new BlockItem(ModBlocks.df4d_middle_door.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> df4d_floor_d = ITEMS.register("df4d_floor_d",
            () -> new BlockItem(ModBlocks.df4d_floor_d.get(), new Item.Properties().tab(ModCreativeTab.trains)));



    // 注册图标物品
    public static final RegistryObject<Item> qr_item = ITEMS.register("qr_item",
            () -> new qr_item(new Item.Properties()));

    public static final RegistryObject<Item> spanner = ITEMS.register("spanner",
            () -> new spanner(new Item.Properties()));

    public static final RegistryObject<Item> sign = ITEMS.register("sign",
            () -> new sign(new Item.Properties()));

    //railway_tools


    public static final RegistryObject<Item> steel_plate = ITEMS.register("steel_plate",
            () -> new steel_plate(new Item.Properties().tab(ModCreativeTab.railway_tools)));

    public static final RegistryObject<Item> bell = ITEMS.register("bell",
            () -> new BlockItem(ModBlocks.bell.get(), new Item.Properties().tab(ModCreativeTab.railway_tools)));

    public static final RegistryObject<Item> railway_ballast = ITEMS.register("railway_ballast",
            () -> new BlockItem(ModBlocks.railway_ballast.get(), new Item.Properties().tab(ModCreativeTab.railway_tools)));

    public static final RegistryObject<Item> railway_ballast_slab = ITEMS.register("railway_ballast_slab",
            () -> new BlockItem(ModBlocks.railway_ballast_slab.get(), new Item.Properties().tab(ModCreativeTab.railway_tools)));

    public static final RegistryObject<Item> gravel = ITEMS.register("gravel",
            () -> new BlockItem(ModBlocks.gravel.get(), new Item.Properties().tab(ModCreativeTab.railway_tools)));

    public static final RegistryObject<Item> gravel_slab = ITEMS.register("gravel_slab",
            () -> new BlockItem(ModBlocks.gravel_slab.get(), new Item.Properties().tab(ModCreativeTab.railway_tools)));

    //signs

    public static final RegistryObject<Item> ticket_check = ITEMS.register("ticket_check",
            () -> new BlockItem(ModBlocks.ticket_check.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> police = ITEMS.register("police",
            () -> new BlockItem(ModBlocks.police.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> information = ITEMS.register("information",
            () -> new BlockItem(ModBlocks.information.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> toilet = ITEMS.register("toilet",
            () -> new BlockItem(ModBlocks.toilet.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> sign_post = ITEMS.register("sign_post",
            () -> new BlockItem(ModBlocks.sign_post.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> first_approach_section_sign = ITEMS.register("first_approach_section_sign",
            () -> new BlockItem(ModBlocks.first_approach_section_sign.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> second_approach_section_sign = ITEMS.register("second_approach_section_sign",
            () -> new BlockItem(ModBlocks.second_approach_section_sign.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> third_approach_section_sign = ITEMS.register("third_approach_section_sign",
            () -> new BlockItem(ModBlocks.third_approach_section_sign.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> dual_pantograph_prohibited_sign = ITEMS.register("dual_pantograph_prohibited_sign",
            () -> new BlockItem(ModBlocks.dual_pantograph_prohibited_sign.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> automatic_tickets = ITEMS.register("automatic_tickets",
            () -> new BlockItem(ModBlocks.automatic_tickets.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> entrance = ITEMS.register("entrance",
            () -> new BlockItem(ModBlocks.entrance.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> exit = ITEMS.register("exit",
            () -> new BlockItem(ModBlocks.exit.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> railway_tickets = ITEMS.register("railway_tickets",
            () -> new BlockItem(ModBlocks.railway_tickets.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> shopping = ITEMS.register("shopping",
            () -> new BlockItem(ModBlocks.shopping.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> vip = ITEMS.register("vip",
            () -> new BlockItem(ModBlocks.vip.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> passengers_stopped = ITEMS.register("passengers_stopped",
            () -> new BlockItem(ModBlocks.passengers_stopped.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    public static final RegistryObject<Item> waiting_room = ITEMS.register("waiting_room",
            () -> new BlockItem(ModBlocks.waiting_room.get(), new Item.Properties().tab(ModCreativeTab.signs)));

    //doors


}