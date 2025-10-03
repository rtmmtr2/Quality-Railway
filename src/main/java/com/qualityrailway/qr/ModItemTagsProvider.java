package com.qualityrailway.qr;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import com.qualityrailway.qr.qr;
import com.qualityrailway.qr.ModItems;

//用于为车票物品添加ticket标签
public class ModItemTagsProvider extends ItemTagsProvider {
    // 创建自定义ticket标签
    @SuppressWarnings("removal")
    public static final TagKey<Item> TicketItems = ItemTags.create(new ResourceLocation(qr.MODID, "ticket"));

    public ModItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTags,
                               ExistingFileHelper helper) {
        super(generator, blockTags, qr.MODID, helper);
    }

    @Override
    protected void addTags() {
        // 将车票添加到ticket标签
        this.tag(TicketItems)
                .add(ModItems.SingleTicket.get());
    }
}