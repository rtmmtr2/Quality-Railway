package com.qualityrailway.qr;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper helper) {
        super(generator, blockTags, qr.MODID, helper);
    }

    @Override
    protected void addTags() {
        // 将两种车票添加到ticket标签中
        tag(ModTags.Items.TICKETS)
                .add(ModItems.RedTicket.get())
                .add(ModItems.BlueTicket.get());
    }
}