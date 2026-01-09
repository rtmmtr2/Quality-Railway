package com.qualityrailway.qr.ctcs;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.qualityrailway.qr.config.CTCSCLientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class CTCSOverlay {
    @SuppressWarnings("removal")
    private static final ResourceLocation DIAL_TEXTURE = new ResourceLocation("qr", "textures/ctcs/ctcs.png");
    @SuppressWarnings("removal")
    private static final ResourceLocation NEEDLE_TEXTURE = new ResourceLocation("qr", "textures/ctcs/ctcs_hand.png");

    // 表盘和指针的尺寸（根据您的纹理）
    private static final int DIAL_WIDTH = 535;  // 表盘宽度
    private static final int DIAL_HEIGHT = 394; // 表盘高度
    private static final int NEEDLE_WIDTH = 49; // 指针宽度
    private static final int NEEDLE_HEIGHT = 119; // 指针高度

    // 表盘中心点（从纹理左上角计算）
    private static final int DIAL_CENTER_X = 158; // 从左往右第158像素
    private static final int DIAL_CENTER_Y = 155; // 从上往下第155像素

    // 指针轴心点（从指针纹理左上角计算）
    private static final int NEEDLE_PIVOT_X = 24; // 从左往右第24像素
    private static final int NEEDLE_PIVOT_Y = 94; // 从上往下第94像素

    private boolean isVisible = false;
    private boolean isEnabled = true;

    /**
     * 切换CTCS界面显示状态
     */
    public void toggleVisibility() {
        isVisible = !isVisible;
    }

    /**
     * 设置CTCS界面显示状态
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * 设置CTCS界面启用状态
     */
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    /**
     * 获取CTCS界面启用状态
     */
    public boolean isEnabled() {
        return isEnabled;
    }

    /**
     * 获取CTCS界面可见状态
     */
    public boolean isVisible() {
        return isVisible;
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }

        if (!CTCSCLientConfig.ENABLED.get() || !isEnabled) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.options.hideGui) {
            return;
        }

        // 检查自动显示逻辑
        boolean isDriving = CTCSUtils.isPlayerDrivingTrain();
        if (CTCSCLientConfig.AUTO_SHOW.get()) {
            if (isDriving) {
                isVisible = true;
            } else {
                isVisible = false;
                return;
            }
        }

        if (!isVisible) {
            return;
        }

        PoseStack poseStack = event.getMatrixStack();
        int screenWidth = mc.getWindow().getGuiScaledWidth();
        int screenHeight = mc.getWindow().getGuiScaledHeight();

        // 计算GUI位置（右上角）
        int guiX = CTCSCLientConfig.GUI_X.get();
        int guiY = CTCSCLientConfig.GUI_Y.get();
        // 修复类型转换：使用floatValue()将Double转换为float
        float scale = CTCSCLientConfig.SCALE.get().floatValue();

        // 如果使用默认位置（-1），则计算右上角位置
        if (guiX == -1) {
            guiX = screenWidth - (int)(DIAL_WIDTH * scale) - 10; // 右边留10像素边距
        }
        if (guiY == -1) {
            guiX = screenHeight - (int)(DIAL_HEIGHT * scale) - 10;guiY = 10; // 顶部留10像素边距
        }

        // 获取当前速度
        float speedKmh = CTCSUtils.getTrainSpeedKmh();

        // 计算旋转角度
        float rotationDegrees = CTCSUtils.calculateNeedleRotation(speedKmh);

        // 渲染表盘
        renderDial(poseStack, guiX, guiY, scale);

        // 渲染指针
        renderNeedle(poseStack, guiX, guiY, scale, rotationDegrees);

        // 渲染速度文本
        renderSpeedText(poseStack, guiX, guiY, scale, speedKmh);
    }

    /**
     * 渲染表盘
     */
    private void renderDial(PoseStack poseStack, int x, int y, float scale) {
        poseStack.pushPose();
        poseStack.translate(x, y, 0);
        poseStack.scale(scale, scale, 1.0f);

        RenderSystem.setShaderTexture(0, DIAL_TEXTURE);
        GuiComponent.blit(poseStack, 0, 0, 0, 0, DIAL_WIDTH, DIAL_HEIGHT, DIAL_WIDTH, DIAL_HEIGHT);

        poseStack.popPose();
    }

    /**
     * 渲染指针 - 使用精确的坐标计算
     */
    private void renderNeedle(PoseStack poseStack, int x, int y, float scale, float rotationDegrees) {
        // 计算缩放后的值
        float scaledWidth = NEEDLE_WIDTH * scale;
        float scaledHeight = NEEDLE_HEIGHT * scale;

        // 确保最小尺寸
        if (scaledWidth < 1.0f) scaledWidth = 1.0f;
        if (scaledHeight < 1.0f) scaledHeight = 1.0f;

        // 计算表盘中心在屏幕上的位置
        float dialCenterX = x + (DIAL_CENTER_X * scale);
        float dialCenterY = y + (DIAL_CENTER_Y * scale);

        // 计算指针轴心点在指针纹理中的相对位置
        float pivotRelX = NEEDLE_PIVOT_X / (float)NEEDLE_WIDTH;
        float pivotRelY = NEEDLE_PIVOT_Y / (float)NEEDLE_HEIGHT;

        // 计算指针应该渲染的位置（使轴心点与表盘中心对齐）
        // 指针左上角位置 = 表盘中心 - (轴心点相对位置 * 指针缩放后尺寸)
        float needleLeftX = dialCenterX - (pivotRelX * scaledWidth);
        float needleTopY = dialCenterY - (pivotRelY * scaledHeight);

        poseStack.pushPose();

        // 保存当前的渲染状态
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        // 禁用深度测试，确保指针在表盘上方
        RenderSystem.disableDepthTest();

        // 移动到指针位置
        poseStack.translate(needleLeftX, needleTopY, 200);

        // 计算旋转中心（相对于指针左上角）
        float rotationCenterX = pivotRelX * scaledWidth;
        float rotationCenterY = pivotRelY * scaledHeight;

        // 如果需要旋转，执行旋转操作
        if (Math.abs(rotationDegrees) > 0.001f) {
            // 移动到旋转中心
            poseStack.translate(rotationCenterX, rotationCenterY, 0);
            // 旋转
            poseStack.mulPose(Vector3f.ZP.rotationDegrees(rotationDegrees));
            // 移回
            poseStack.translate(-rotationCenterX, -rotationCenterY, 0);
        }

        // 渲染指针
        RenderSystem.setShaderTexture(0, NEEDLE_TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        // 确保渲染尺寸至少为1像素
        int renderWidth = Math.max(1, Math.round(scaledWidth));
        int renderHeight = Math.max(1, Math.round(scaledHeight));

        GuiComponent.blit(poseStack, 0, 0,
                        0, 0,
                        renderWidth, renderHeight,
                        NEEDLE_WIDTH, NEEDLE_HEIGHT);

        // 恢复渲染状态
        RenderSystem.enableDepthTest();
        poseStack.popPose();
    }

    /**
     * 渲染速度文本
     */
    private void renderSpeedText(PoseStack poseStack, int x, int y, float scale, float speedKmh) {
        Minecraft mc = Minecraft.getInstance();
        String speedText = String.format("%.1f km/h", speedKmh);

        // 计算文本位置（表盘下方居中）
        int textWidth = mc.font.width(speedText);
        int textX = x + (int)((DIAL_WIDTH * scale) / 2) - (textWidth / 2);
        int textY = y + (int)(DIAL_HEIGHT * scale) + 5;

        // 渲染带阴影的文本
        poseStack.pushPose();
        poseStack.translate(0, 0, 300); // 使用更高的Z值确保文本在最上层
        mc.font.drawShadow(poseStack, speedText, textX, textY, 0xFFFFFF);
        poseStack.popPose();
    }
}