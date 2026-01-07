package com.qualityrailway.qr.screen;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.player.Inventory;
import org.lwjgl.glfw.GLFW;
import com.qualityrailway.qr.network.ModNetwork;
import com.qualityrailway.qr.network.UpdateAdvancedSignPacket;

public class AdvancedSignScreen extends AbstractContainerScreen<AdvancedSignMenu> {
    
    private EditBox textInput;
    private EditBox posXInput;
    private EditBox posYInput;
    private EditBox sizeInput;
    private EditBox colorInput;
    private Button saveButton;
    private Button cancelButton;
    
    // 调节按钮
    private Button leftButton;
    private Button rightButton;
    private Button upButton;
    private Button downButton;
    private Button sizeUpButton;
    private Button sizeDownButton;
    
    public AdvancedSignScreen(AdvancedSignMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.imageWidth = 256;
        this.imageHeight = 220;
    }
    
    @Override
    protected void init() {
        super.init();
        
        int centerX = this.width / 2;
        int centerY = this.height / 2;
        
        // 文本输入框
        textInput = new EditBox(this.font, centerX - 120, topPos + 20, 240, 20, 
            new TranslatableComponent("qr.screen.advanced_sign.text"));
        textInput.setMaxLength(256);
        textInput.setValue(menu.getBlockEntity().getText());
        this.addRenderableWidget(textInput);
        
        // 位置X输入 - 支持小数
        posXInput = new EditBox(this.font, centerX - 120, topPos + 50, 80, 20,
            new TranslatableComponent("qr.screen.advanced_sign.pos_x"));
        posXInput.setValue(String.format("%.1f", menu.getBlockEntity().getTextX())); // 格式化为1位小数
        posXInput.setFilter(s -> s.matches("-?\\d*(\\.\\d*)?")); // 允许小数
        this.addRenderableWidget(posXInput);
        
        // 位置Y输入 - 支持小数
        posYInput = new EditBox(this.font, centerX - 30, topPos + 50, 80, 20,
            new TranslatableComponent("qr.screen.advanced_sign.pos_y"));
        posYInput.setValue(String.format("%.1f", menu.getBlockEntity().getTextY())); // 格式化为1位小数
        posYInput.setFilter(s -> s.matches("-?\\d*(\\.\\d*)?"));
        this.addRenderableWidget(posYInput);
        
        // 大小输入
        sizeInput = new EditBox(this.font, centerX + 60, topPos + 50, 60, 20,
            new TranslatableComponent("qr.screen.advanced_sign.size"));
        sizeInput.setValue(String.valueOf(menu.getBlockEntity().getTextSize()));
        sizeInput.setFilter(s -> s.matches("\\d*"));
        this.addRenderableWidget(sizeInput);
        
        // 颜色输入
        colorInput = new EditBox(this.font, centerX - 120, topPos + 80, 120, 20,
            new TranslatableComponent("qr.screen.advanced_sign.color"));
        colorInput.setValue(menu.getBlockEntity().getColorHex());
        this.addRenderableWidget(colorInput);
        
        // 位置调节按钮 - 改为小数步长
        leftButton = new Button(centerX - 120, topPos + 110, 40, 20, 
            new TextComponent("←"), button -> adjustPosX(-0.5f));
        rightButton = new Button(centerX - 70, topPos + 110, 40, 20,
            new TextComponent("→"), button -> adjustPosX(0.5f));
        upButton = new Button(centerX - 20, topPos + 110, 40, 20,
            new TextComponent("↑"), button -> adjustPosY(0.5f));
        downButton = new Button(centerX + 30, topPos + 110, 40, 20,
                new TextComponent("↓"), button -> adjustPosY(-0.5f));
        
        // 大小调节按钮
        sizeUpButton = new Button(centerX + 80, topPos + 110, 40, 20,
            new TextComponent("+"), button -> adjustSize(1));
        sizeDownButton = new Button(centerX + 130, topPos + 110, 40, 20,
            new TextComponent("-"), button -> adjustSize(-1));
        
        this.addRenderableWidget(leftButton);
        this.addRenderableWidget(rightButton);
        this.addRenderableWidget(upButton);
        this.addRenderableWidget(downButton);
        this.addRenderableWidget(sizeUpButton);
        this.addRenderableWidget(sizeDownButton);
        
        // 保存和取消按钮
        saveButton = new Button(centerX - 90, topPos + 150, 85, 20,
            new TranslatableComponent("qr.screen.advanced_sign.save"), button -> save());
        cancelButton = new Button(centerX + 5, topPos + 150, 85, 20,
            new TranslatableComponent("qr.screen.advanced_sign.cancel"), button -> cancel());
        
        this.addRenderableWidget(saveButton);
        this.addRenderableWidget(cancelButton);
        
        // 设置初始焦点
        setInitialFocus(textInput);
    }
    
    private void adjustPosX(float delta) {
        try {
            float current = Float.parseFloat(posXInput.getValue());
            float newValue = Math.max(-100.0f, Math.min(100.0f, current + delta));
            posXInput.setValue(String.format("%.1f", newValue));
        } catch (NumberFormatException e) {
            posXInput.setValue("0.0");
        }
    }
    
    private void adjustPosY(float delta) {
        try {
            float current = Float.parseFloat(posYInput.getValue());
            float newValue = Math.max(-100.0f, Math.min(100.0f, current + delta));
            posYInput.setValue(String.format("%.1f", newValue));
        } catch (NumberFormatException e) {
            posYInput.setValue("0.0");
        }
    }
    
    private void adjustSize(int delta) {
        try {
            int current = Integer.parseInt(sizeInput.getValue());
            int newValue = Math.max(1, Math.min(100, current + delta));
            sizeInput.setValue(String.valueOf(newValue));
        } catch (NumberFormatException e) {
            sizeInput.setValue("60");
        }
    }
    
    private void save() {
        String text = textInput.getValue();
        if (!text.isEmpty()) {
            try {
                float posX = Float.parseFloat(posXInput.getValue());
                float posY = Float.parseFloat(posYInput.getValue());
                int size = Integer.parseInt(sizeInput.getValue());
                String color = colorInput.getValue();
                
                // 发送网络数据包到服务器
                ModNetwork.CHANNEL.sendToServer(
                    new UpdateAdvancedSignPacket(
                        menu.getBlockEntity().getBlockPos(),
                        text, posX, posY, size, color
                    )
                );
                
                // 同时更新客户端方块实体，以便立即显示
                menu.getBlockEntity().setText(text);
                menu.getBlockEntity().setTextX(posX);
                menu.getBlockEntity().setTextY(posY);
                menu.getBlockEntity().setTextSize(size);
                menu.getBlockEntity().setColorHex(color);
                
                this.minecraft.player.closeContainer();
            } catch (NumberFormatException e) {
                // 输入无效
            }
        }
    }
    
    private void cancel() {
        this.minecraft.player.closeContainer();
    }
    
    @Override
    protected void renderBg(PoseStack poseStack, float partialTick, int mouseX, int mouseY) {
        this.renderBackground(poseStack);
    }
    
    @Override
    protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
        // 绘制标题
        this.font.draw(poseStack, this.title, (float)this.titleLabelX, (float)this.titleLabelY, 0x404040);
        
        // 绘制标签
        this.font.draw(poseStack, new TranslatableComponent("qr.screen.advanced_sign.text_label"), 10, 25, 0x404040);
        this.font.draw(poseStack, new TranslatableComponent("qr.screen.advanced_sign.position"), 10, 55, 0x404040);
        this.font.draw(poseStack, new TranslatableComponent("qr.screen.advanced_sign.size_label"), 190, 55, 0x404040);
        this.font.draw(poseStack, new TranslatableComponent("qr.screen.advanced_sign.color_label"), 10, 85, 0x404040);
        this.font.draw(poseStack, new TranslatableComponent("qr.screen.advanced_sign.adjust"), 10, 115, 0x404040);
    }
    
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.minecraft.player.closeContainer();
            return true;
        }
        
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}