package com.qualityrailway.qr;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component; // 使用 Component 替代 TranslatableComponent

public class ModCreativeTab {
    public static final CreativeModeTab trains = new CreativeModeTab("trains") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.qr_item.get());
        }

        // 使用 Component.literal 替代 TranslatableComponent
        @Override
        public Component getDisplayName() {
            return Component.translatable("itemGroup.trains");
        }
    };

    public static final CreativeModeTab railway_tools = new CreativeModeTab("railway_tools") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.spanner.get());
        }

        @Override
        public Component getDisplayName() {
            return Component.translatable("itemGroup.railway_tools");
        }
    };

    public static final CreativeModeTab signs = new CreativeModeTab("signs") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.sign.get());
        }

        @Override
        public Component getDisplayName() {
            return Component.translatable("itemGroup.signs");
        }
    };
}