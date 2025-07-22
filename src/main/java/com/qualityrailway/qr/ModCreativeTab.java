package com.qualityrailway.qr;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.TranslatableComponent; // 导入 TranslatableComponent

public class ModCreativeTab {
    public static final CreativeModeTab trains = new CreativeModeTab("trains") {
        @Override
        public ItemStack makeIcon() {
            // 使用自定义物品作为图标（需要先注册图标物品）
            return new ItemStack(ModItems.qr_item.get());
        }

        @Override
        public TranslatableComponent getDisplayName() {

            return new TranslatableComponent("itemGroup.trains");
        }
    };
    public static final CreativeModeTab tools = new CreativeModeTab("tools") {
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(ModItems.spanner.get());
        }

        @Override
        public TranslatableComponent getDisplayName() {

            return new TranslatableComponent("itemGroup.tools");
        }
    };
    public static final CreativeModeTab signs = new CreativeModeTab("signs") {
        @Override
        public ItemStack makeIcon() {

            return new ItemStack(ModItems.sign.get());
        }

        @Override
        public TranslatableComponent getDisplayName() {

            return new TranslatableComponent("itemGroup.signs");
        }
    };
}