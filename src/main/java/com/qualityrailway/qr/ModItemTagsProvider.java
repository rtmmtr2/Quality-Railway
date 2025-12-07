package com.qualityrailway.qr;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(
            PackOutput packOutput,
            CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagsProvider.TagLookup<Item>> itemTagLookup,
            CompletableFuture<TagsProvider.TagLookup<Block>> blockTagLookup,
            ExistingFileHelper existingFileHelper) {
        super(packOutput, lookupProvider, itemTagLookup, blockTagLookup, qr.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.TICKETS)
                .add(ModItems.RedTicket.get())
                .add(ModItems.BlueTicket.get());
    }
}
