package com.qualityrailway.qr.blocks.doors;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class CustomDoorBlockEntity extends BlockEntity {
    private String placerName = "未知";
    private String customData = "";
    private int openCount = 0;

    public CustomDoorBlockEntity(BlockPos pos, BlockState state) {
        super(com.qualityrailway.qr.ModBlockEntities.CUSTOM_DOOR_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString("placerName", placerName);
        tag.putString("customData", customData);
        tag.putInt("openCount", openCount);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        placerName = tag.getString("placerName");
        customData = tag.getString("customData");
        openCount = tag.getInt("openCount");
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        load(pkt.getTag());
    }

    public void incrementOpenCount() {
        openCount++;
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 2);
        }
    }

    public String getPlacerName() { return placerName; }
    public void setPlacerName(String placerName) { this.placerName = placerName; setChanged(); }
    public String getCustomData() { return customData; }
    public void setCustomData(String customData) { this.customData = customData; setChanged(); }
    public int getOpenCount() { return openCount; }
}
