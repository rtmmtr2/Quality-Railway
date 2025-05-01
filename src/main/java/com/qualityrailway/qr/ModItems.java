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

    public static final RegistryObject<Item> c70_fool = ITEMS.register("c70_fool",
            () -> new BlockItem(ModBlocks.c70_fool.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> gq70_end_tank = ITEMS.register("gq70_end_tank",
            () -> new BlockItem(ModBlocks.gq70_end_tank.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> gq70_tank_a = ITEMS.register("gq70_tank_a",
            () -> new BlockItem(ModBlocks.gq70_tank_a.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> gq70_tank_b = ITEMS.register("gq70_tank_b",
            () -> new BlockItem(ModBlocks.gq70_tank_b.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> gq70_tank_c = ITEMS.register("gq70_tank_c",
            () -> new BlockItem(ModBlocks.gq70_tank_c.get(), new Item.Properties().tab(ModCreativeTab.trains)));


    // 注册物品
    public static final RegistryObject<Item> qr_item = ITEMS.register("qr_item",
            () -> new qr_item(new Item.Properties()));

    public static final RegistryObject<Item> spanner = ITEMS.register("spanner",
            () -> new spanner(new Item.Properties()));
}