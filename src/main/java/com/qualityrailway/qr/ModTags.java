package com.qualityrailway.qr;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTags {
    @SuppressWarnings("removal")
    public static class Items {
        public static final TagKey<Item> TICKETS = tag("ticket");

        private static TagKey<Item> tag(String name) {
            return ForgeRegistries.ITEMS.tags().createTagKey(new ResourceLocation(qr.MODID, name));
        }
    }
}