package com.qualityrailway.qr.blockentity;

import com.qualityrailway.qr.ModBlockEntities;
import com.qualityrailway.qr.screen.AdvancedSignMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.FloatTag;
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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

public class AdvancedSignBlockEntity extends BlockEntity implements MenuProvider {
    
    private static final Logger LOGGER = LogManager.getLogger();

    // 默认值
    private String text = "文";
    private float textX = 0.0f;  // 水平偏移 (-100 到 100)，支持小数
    private float textY = 0.0f;  // 垂直偏移 (-100 到 100)，支持小数
    private int textSize = 60;  // 字体大小 (1 到 100)
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
    
    public void setTextX(float textX) {
        this.textX = Math.max(-100.0f, Math.min(100.0f, textX));
        setChanged();
    }
    
    public float getTextX() {
        return textX;
    }
    
    public void setTextY(float textY) {
        this.textY = Math.max(-100.0f, Math.min(100.0f, textY));
        setChanged();
    }
    
    public float getTextY() {
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
        tag.putFloat("TextX", textX);  // 改为putFloat
        tag.putFloat("TextY", textY);  // 改为putFloat
        tag.putInt("TextSize", textSize);
        tag.putString("ColorHex", colorHex);

        // 调试日志
        LOGGER.info("高级告示牌保存数据: text={}, X={}, Y={}, size={}, color={}",
            text, textX, textY, textSize, colorHex);
    }
    
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        text = tag.getString("Text");
        textX = tag.getFloat("TextX");  // 改为getFloat
        textY = tag.getFloat("TextY");  // 改为getFloat
        textSize = tag.getInt("TextSize");
        colorHex = tag.getString("ColorHex");

        // 调试日志
        LOGGER.info("高级告示牌加载数据: text={}, X={}, Y={}, size={}, color={}",
            text, textX, textY, textSize, colorHex);
    }
    
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putString("Text", text);
        tag.putFloat("TextX", textX);  // 改为putFloat
        tag.putFloat("TextY", textY);  // 改为putFloat
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
        return new TranslatableComponent("qr.screen.advanced_sign.title");
    }
    
    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new AdvancedSignMenu(containerId, playerInventory, this);
    }
}