package com.qualityrailway.qr;

import com.qualityrailway.qr.items.qr_item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, qr.MODID);

    // 注册方块对应的物品
    public static final RegistryObject<Item> c70_left_face = ITEMS.register("c70_left_face",
            () -> new BlockItem(ModBlocks.c70_left_face.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> c70_right_face = ITEMS.register("c70_right_face",
            () -> new BlockItem(ModBlocks.c70_right_face.get(), new Item.Properties().tab(ModCreativeTab.trains)));

    public static final RegistryObject<Item> c70_front = ITEMS.register("c70_front",
            () -> new BlockItem(ModBlocks.c70_front.get(), new Item.Properties().tab(ModCreativeTab.trains)));


    // 注册自定义物品
    public static final RegistryObject<Item> qr_item = ITEMS.register("qr_item",
            () -> new qr_item(new Item.Properties()));
}