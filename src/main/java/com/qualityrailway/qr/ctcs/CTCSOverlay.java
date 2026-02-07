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

import javax.annotation.Nullable;

public class CTCSOverlay {
    @SuppressWarnings("removal")
    private static final ResourceLocation DIAL_TEXTURE = new ResourceLocation("qr", "textures/ctcs/ctcs.png");
    @SuppressWarnings("removal")
    private static final ResourceLocation NEEDLE_TEXTURE = new ResourceLocation("qr", "textures/ctcs/ctcs_hand.png");
    // 信号灯纹理
    @SuppressWarnings("removal")
    private static final ResourceLocation SIGNAL_GREEN_TEXTURE = new ResourceLocation("qr", "textures/ctcs/ctcs_signal_green.png");
    @SuppressWarnings("removal")
    private static final ResourceLocation SIGNAL_YELLOW_TEXTURE = new ResourceLocation("qr", "textures/ctcs/ctcs_signal_yellow.png");
    @SuppressWarnings("removal")
    private static final ResourceLocation SIGNAL_RED_YELLOW_TEXTURE = new ResourceLocation("qr", "textures/ctcs/ctcs_signal_red_yellow.png");
    @SuppressWarnings("removal")
    private static final ResourceLocation SIGNAL_WHITE_TEXTURE = new ResourceLocation("qr", "textures/ctcs/ctcs_signal_white.png");


    //get scale
    static int scale = CTCSCLientConfig.SCALE.get().intValue();

    // 表盘和指针的尺寸，除以scale缩放
    private static final int DIAL_WIDTH = 535/scale;  // 表盘宽度
    private static final int DIAL_HEIGHT = 394/scale; // 表盘高度
    private static final int NEEDLE_WIDTH = 49/scale; // 指针宽度
    private static final int NEEDLE_HEIGHT = 119/scale; // 指针高度

    // 信号灯尺寸，除以scale缩放
    private static final int SIGNAL_WIDTH = 80/scale;  // 信号灯宽度
    private static final int SIGNAL_HEIGHT = 80/scale; // 信号灯高度

    // 表盘中心点
    private static final int DIAL_CENTER_X = 158/scale; // 从左往右第158像素
    private static final int DIAL_CENTER_Y = 155/scale; // 从上往下第155像素

    // 信号灯中心点在表盘上的位置（相对于表盘左上角）
    // 要求：中心对齐到CTCS从左往右第80个像素，从上往下第323个像素
    private static final int SIGNAL_CENTER_X_IN_DIAL = 80/scale; // 从左往右第80像素
    private static final int SIGNAL_CENTER_Y_IN_DIAL = 323/scale; // 从上往下第323像素

    // 指针轴心点
    private static final int NEEDLE_PIVOT_X = 24/scale; // 从左往右第24像素
    private static final int NEEDLE_PIVOT_Y = 94/scale; // 从上往下第94像素

    private boolean isVisible = false;
    private boolean isEnabled = true;

    // 信号状态
    @Nullable
    private SignalState signalState = null;
    private double signalDistance = 0.0;

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

    /**
     * 更新信号状态
     */
    public void updateSignalState(@Nullable SignalState state, double distance) {
        qr.LOGGER.info("CTCSOverlay: 更新信号状态 - state=" + state + ", distance=" + distance);
        this.signalState = state;
        this.signalDistance = distance;
    }

    /**
     * 清除信号状态
     */
    public void clearSignalState() {
        qr.LOGGER.info("CTCSOverlay: 清除信号状态");
        this.signalState = null;
        this.signalDistance = 0.0;
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

        qr.LOGGER.info("CTCSOverlay: 渲染CTCS界面...");

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

        qr.LOGGER.info("CTCSOverlay: 表盘位置 - guiX=" + guiX + ", guiY=" + guiY +
                      ", DIAL_WIDTH=" + DIAL_WIDTH + ", DIAL_HEIGHT=" + DIAL_HEIGHT);

        // 获取当前速度
        float speedKmh = CTCSUtils.getTrainSpeedKmh();
        qr.LOGGER.info("CTCSOverlay: 当前速度 - " + speedKmh + " km/h");

        // 渲染表盘
        renderDial(poseStack, guiX, guiY);

        // 渲染指针
        float rotationDegrees = CTCSUtils.calculateNeedleRotation(speedKmh);
        renderNeedle(poseStack, guiX, guiY, rotationDegrees);

        // 渲染速度文本
        renderSpeedText(poseStack, guiX, guiY, speedKmh);

        // 渲染信号灯
        renderSignalLight(poseStack, guiX, guiY);
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

    /**
     * 渲染信号灯
     */
    private void renderSignalLight(PoseStack poseStack, int x, int y) {
        qr.LOGGER.info("CTCSOverlay.renderSignalLight: 开始渲染信号灯");
        qr.LOGGER.info("CTCSOverlay.renderSignalLight: 当前信号状态=" + signalState);

        // 如果信号状态为NONE或null，不渲染
        if (signalState == null || signalState == SignalState.NONE) {
            qr.LOGGER.info("CTCSOverlay.renderSignalLight: 信号状态为null或NONE，不渲染");
            return;
        }

        // 计算信号灯在屏幕上的位置
        // 信号灯中心在表盘上的位置 + 表盘位置 = 屏幕上的绝对位置
        int signalCenterX = x + SIGNAL_CENTER_X_IN_DIAL;
        int signalCenterY = y + SIGNAL_CENTER_Y_IN_DIAL;

        // 计算信号灯左上角位置（从中心点减去一半的宽度/高度）
        int signalX = signalCenterX - (SIGNAL_WIDTH / 2);
        int signalY = signalCenterY - (SIGNAL_HEIGHT / 2);

        qr.LOGGER.info("CTCSOverlay.renderSignalLight: 信号灯位置计算 - " +
                      "signalCenterX=" + signalCenterX + ", signalCenterY=" + signalCenterY + ", " +
                      "signalX=" + signalX + ", signalY=" + signalY + ", " +
                      "SIGNAL_WIDTH=" + SIGNAL_WIDTH + ", SIGNAL_HEIGHT=" + SIGNAL_HEIGHT);

        // 获取对应的纹理
        ResourceLocation signalTexture = getSignalTexture();
        if (signalTexture == null) {
            qr.LOGGER.info("CTCSOverlay.renderSignalLight: 未找到对应的纹理");
            return;
        }

        qr.LOGGER.info("CTCSOverlay.renderSignalLight: 使用纹理 - " + signalTexture);

        poseStack.pushPose();

        // 移动到信号灯位置
        poseStack.translate(signalX, signalY, 250); // Z值250，在指针下方但在表盘上方

        // 保存当前的渲染状态
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();

        // 渲染信号灯
        RenderSystem.setShaderTexture(0, signalTexture);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

        GuiComponent.blit(poseStack, 0, 0,
                        0, 0,
                        SIGNAL_WIDTH, SIGNAL_HEIGHT,
                        SIGNAL_WIDTH, SIGNAL_HEIGHT);

        qr.LOGGER.info("CTCSOverlay.renderSignalLight: 信号灯渲染完成");

        // 渲染距离文本（如果距离大于0）
        if (signalDistance > 0) {
            renderSignalDistance(poseStack, signalX, signalY, signalDistance);
        }

        poseStack.popPose();
    }

    /**
     * 根据信号状态获取对应的纹理
     */
    @Nullable
    private ResourceLocation getSignalTexture() {
        if (signalState == null) {
            qr.LOGGER.info("CTCSOverlay.getSignalTexture: signalState为null");
            return null;
        }

        qr.LOGGER.info("CTCSOverlay.getSignalTexture: 当前信号状态=" + signalState);

        switch (signalState) {
            case GREEN:
                qr.LOGGER.info("CTCSOverlay.getSignalTexture: 返回绿灯纹理");
                return SIGNAL_GREEN_TEXTURE;
            case YELLOW:
                qr.LOGGER.info("CTCSOverlay.getSignalTexture: 返回黄灯纹理");
                return SIGNAL_YELLOW_TEXTURE;
            case REDYELLOW:
                qr.LOGGER.info("CTCSOverlay.getSignalTexture: 返回红黄灯纹理");
                return SIGNAL_RED_YELLOW_TEXTURE;
            default:
                qr.LOGGER.info("CTCSOverlay.getSignalTexture: 返回白灯纹理");
                return SIGNAL_WHITE_TEXTURE;
        }
    }

    /**
     * 渲染信号距离文本
     */
    private void renderSignalDistance(PoseStack poseStack, int x, int y, double distance) {
        Minecraft mc = Minecraft.getInstance();

        // 格式化距离文本
        String distanceText;
        if (distance < 1000) {
            distanceText = String.format("%.0f m", distance);
        } else {
            distanceText = String.format("%.1f km", distance / 1000.0);
        }

        // 计算文本位置（信号灯下方居中）
        int textWidth = mc.font.width(distanceText);
        int textX = x + (SIGNAL_WIDTH / 2) - (textWidth / 2);
        int textY = y + SIGNAL_HEIGHT + 2;

        // 渲染带阴影的文本
        poseStack.pushPose();
        poseStack.translate(0, 0, 300); // 更高的Z值确保文本在最上层
        mc.font.drawShadow(poseStack, distanceText, textX, textY, 0xFFFFFF);
        poseStack.popPose();
    }
}