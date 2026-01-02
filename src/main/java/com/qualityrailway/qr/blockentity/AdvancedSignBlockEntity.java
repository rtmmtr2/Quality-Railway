package com.qualityrailway.qr.blockentity;

import com.qualityrailway.qr.ModBlockEntities;
import com.qualityrailway.qr.screen.AdvancedSignMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class AdvancedSignBlockEntity extends BlockEntity implements MenuProvider {
    
    // 默认值
    private String text = "Hello, World!";
    private int textX = 0;  // 水平偏移 (-100 到 100)
    private int textY = 0;  // 垂直偏移 (-100 到 100)
    private int textSize = 50;  // 字体大小 (1 到 100)
    private String colorHex = "#FFFFFF";  // 16进制颜色
    
    public AdvancedSignBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ADVANCED_SIGN.get(), pos, state);
    }
    
    public void setText(String text) {
        this.text = text;
        setChanged();
        if (level != null) {
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }
    
    public String getText() {
        return text;
    }
    
    public void setTextX(int textX) {
        this.textX = Math.max(-100, Math.min(100, textX));
        setChanged();
    }
    
    public int getTextX() {
        return textX;
    }
    
    public void setTextY(int textY) {
        this.textY = Math.max(-100, Math.min(100, textY));
        setChanged();
    }
    
    public int getTextY() {
        return textY;
    }
    
    public void setTextSize(int textSize) {
        this.textSize = Math.max(1, Math.min(100, textSize));
        setChanged();
    }
    
    public int getTextSize() {
        return textSize;
    }
    
    public void setColorHex(String colorHex) {
        // 简单的16进制颜色验证
        if (colorHex.matches("^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$")) {
            this.colorHex = colorHex;
            setChanged();
        }
    }
    
    public String getColorHex() {
        return colorHex;
    }
    
    // 将16进制颜色转换为ARGB整数
    public int getColorARGB() {
        try {
            String hex = colorHex.replace("#", "");
            if (hex.length() == 3) {
                hex = String.valueOf(hex.charAt(0)) + hex.charAt(0) +
                      hex.charAt(1) + hex.charAt(1) +
                      hex.charAt(2) + hex.charAt(2);
            }
            int rgb = Integer.parseInt(hex, 16);
            return 0xFF000000 | rgb; // Alpha = 255
        } catch (NumberFormatException e) {
            return 0xFFFFFFFF; // 白色作为默认
        }
    }
    
    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putString("Text", text);
        tag.putInt("TextX", textX);
        tag.putInt("TextY", textY);
        tag.putInt("TextSize", textSize);
        tag.putString("ColorHex", colorHex);
    }
    
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        text = tag.getString("Text");
        textX = tag.getInt("TextX");
        textY = tag.getInt("TextY");
        textSize = tag.getInt("TextSize");
        colorHex = tag.getString("ColorHex");
    }
    
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putString("Text", text);
        tag.putInt("TextX", textX);
        tag.putInt("TextY", textY);
        tag.putInt("TextSize", textSize);
        tag.putString("ColorHex", colorHex);
        return tag;
    }
    
    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
    
    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("qr.text.advanced_sign.title");
    }
    
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new AdvancedSignMenu(containerId, playerInventory, this);
    }
}