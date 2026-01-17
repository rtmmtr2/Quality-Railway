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
import com.qualityrailway.qr.qr;

public class CTCSOverlay {
    @SuppressWarnings("removal")
    private static final ResourceLocation DIAL_TEXTURE = new ResourceLocation("qr", "textures/ctcs/ctcs.png");
    @SuppressWarnings("removal")
    private static final ResourceLocation NEEDLE_TEXTURE = new ResourceLocation("qr", "textures/ctcs/ctcs_hand.png");
    //get scale
    static int scale = CTCSCLientConfig.SCALE.get().intValue();

    // 表盘和指针的尺寸，除以scale缩放
    private static final int DIAL_WIDTH = 535/scale;  // 表盘宽度
    private static final int DIAL_HEIGHT = 394/scale; // 表盘高度
    private static final int NEEDLE_WIDTH = 49/scale; // 指针宽度
    private static final int NEEDLE_HEIGHT = 119/scale; // 指针高度

    // 表盘中心点
    private static final int DIAL_CENTER_X = 158/scale; // 从左往右第158像素
    private static final int DIAL_CENTER_Y = 155/scale; // 从上往下第155像素

    // 指针轴心点
    private static final int NEEDLE_PIVOT_X = 24/scale; // 从左往右第24像素
    private static final int NEEDLE_PIVOT_Y = 94/scale; // 从上往下第94像素

    private boolean isVisible = false;
    private boolean isEnabled = true;

    private static CTCSOverlay instance;

    /**
     * 获取CTCSOverlay的单例实例
     */
    public static CTCSOverlay getInstance() {
        if (instance == null) {
            instance = new CTCSOverlay();
        }
        return instance;
    }

    /**
     * 私有构造函数，确保单例模式
     */
    private CTCSOverlay() {
    }

    /**
     * 设置CTCS界面启用状态
     */
    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
        qr.LOGGER.debug("CTCS enabled set to: " + isEnabled);
    }

    /**
     * 获取CTCS界面启用状态
     */
    public boolean isEnabled() {
        return isEnabled;
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

        // 计算GUI位置（右上角）
        int guiX = CTCSCLientConfig.GUI_X.get();
        int guiY = CTCSCLientConfig.GUI_Y.get();


        // 如果使用默认位置（-1），则计算右上角位置
        if (guiX == -1) {
            guiX = screenWidth - DIAL_WIDTH - 10; // 右边留10像素边距
        }
        if (guiY == -1) {
            guiY = 10; // 顶部留10像素边距
        }

        // 获取当前速度
        float speedKmh = CTCSUtils.getTrainSpeedKmh();

        // 计算旋转角度
        float rotationDegrees = CTCSUtils.calculateNeedleRotation(speedKmh);

        // 渲染表盘
        renderDial(poseStack, guiX, guiY);

        // 渲染指针
        renderNeedle(poseStack, guiX, guiY, rotationDegrees);

        // 渲染速度文本
        renderSpeedText(poseStack, guiX, guiY, speedKmh);
    }

    /**
     * 渲染表盘
     */
    private void renderDial(PoseStack poseStack, int x, int y) {
        poseStack.pushPose();
        poseStack.translate(x, y, 0);

        RenderSystem.setShaderTexture(0, DIAL_TEXTURE);
        GuiComponent.blit(poseStack, 0, 0, 0, 0, DIAL_WIDTH, DIAL_HEIGHT, DIAL_WIDTH, DIAL_HEIGHT);

        poseStack.popPose();
    }

    /**
     * 渲染指针 - 移除缩放计算，简化坐标计算
     */
    private void renderNeedle(PoseStack poseStack, int x, int y, float rotationDegrees) {
        // 表盘中心在屏幕上的绝对位置
        int dialCenterX = x + DIAL_CENTER_X;
        int dialCenterY = y + DIAL_CENTER_Y;

        poseStack.pushPose();

        // 保存当前的渲染状态
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        // 禁用深度测试，确保指针在表盘上方
        RenderSystem.disableDepthTest();

        // 移动到表盘中心
        poseStack.translate(dialCenterX, dialCenterY, 200);

        // 旋转指针
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(rotationDegrees));

        // 调整指针轴心点偏移（将指针的轴心点移动到原点）
        poseStack.translate(-NEEDLE_PIVOT_X, -NEEDLE_PIVOT_Y, 0);

        // 渲染指针
        RenderSystem.setShaderTexture(0, NEEDLE_TEXTURE);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        GuiComponent.blit(poseStack, 0, 0,
                        0, 0,
                        NEEDLE_WIDTH, NEEDLE_HEIGHT,
                        NEEDLE_WIDTH, NEEDLE_HEIGHT);

        // 恢复渲染状态
        RenderSystem.enableDepthTest();
        poseStack.popPose();
    }

    /**
     * 渲染速度文本
     */
    private void renderSpeedText(PoseStack poseStack, int x, int y, float speedKmh) {
        Minecraft mc = Minecraft.getInstance();
        String speedText = String.format("%.1f km/h", speedKmh);

        // 计算文本位置（表盘下方居中）
        int textWidth = mc.font.width(speedText);
        int textX = x + (DIAL_WIDTH / 2) - (textWidth / 2);
        int textY = y + DIAL_HEIGHT + 5;

        // 渲染带阴影的文本
        poseStack.pushPose();
        poseStack.translate(0, 0, 300); // 使用更高的Z值确保文本在最上层
        mc.font.drawShadow(poseStack, speedText, textX, textY, 0xFFFFFF);
        poseStack.popPose();
    }
}