package com.qualityrailway.qr;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.TranslatableComponent; // 导入 TranslatableComponent

public class ModCreativeTab {
    public static final CreativeModeTab TRAINS = new CreativeModeTab("trains") {
        @Override
        public ItemStack makeIcon() {
            // 使用自定义物品作为图标
            return new ItemStack(ModItems.QR_ITEM.get());
        }

        @Override
        public TranslatableComponent getDisplayName() {

            return new TranslatableComponent("itemGroup.trains");
        }
    };
    public static final CreativeModeTab RAILWAY_TOOLS = new CreativeModeTab("railway_tools") {
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(ModItems.SPANNER.get());
        }

        @Override
        public TranslatableComponent getDisplayName() {

            return new TranslatableComponent("itemGroup.railway_tools");
        }
    };
    public static final CreativeModeTab SIGNS = new CreativeModeTab("signs") {
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(ModItems.SIGN.get());
        }

        @Override
        public TranslatableComponent getDisplayName() {

            return new TranslatableComponent("itemGroup.signs");
        }
    };

}