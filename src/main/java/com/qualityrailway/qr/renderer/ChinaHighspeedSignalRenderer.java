package com.qualityrailway.qr.renderer;

import com.qualityrailway.qr.blockentity.signals.ChinaHighspeedSignalBlockEntity;
import com.qualityrailway.qr.signals.SignalStates;
import com.mojang.blaze3d.vertex.PoseStack;
import com.simibubi.create.AllPartialModels;
import com.simibubi.create.content.trains.signal.SignalRenderer;
import com.simibubi.create.foundation.render.CachedBufferer;
import com.simibubi.create.foundation.utility.AnimationTickHolder;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ChinaHighspeedSignalRenderer extends SignalRenderer {

    public ChinaHighspeedSignalRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    protected void renderSafe(com.simibubi.create.content.trains.signal.SignalBlockEntity be, float partialTicks,
                              PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
        // 先渲染父类的基座
        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);

        // 如果是我们的自定义信号机，渲染自定义灯光
        if (be instanceof ChinaHighspeedSignalBlockEntity customBe) {
            renderCustomLights(customBe, ms, buffer, light, overlay);
        }
    }

    private void renderCustomLights(ChinaHighspeedSignalBlockEntity be, PoseStack ms,
                                    MultiBufferSource buffer, int light, int overlay) {
        SignalStates state = be.getCustomState();
        String modelType = be.getSignalModel();
        float renderTime = AnimationTickHolder.getRenderTime(be.getLevel());

        // 根据模型类型渲染不同的灯光
        BlockState blockState = be.getBlockState();
        Level level = be.getLevel();
        BlockPos pos = be.getBlockPos();

        if (level == null) return;

        // 渲染自定义灯光效果
        renderSignalLights(be, ms, buffer, state, modelType, renderTime, light, overlay);
    }

    private void renderSignalLights(ChinaHighspeedSignalBlockEntity be, PoseStack ms, MultiBufferSource buffer,
                                    SignalStates state, String modelType, float renderTime, int light, int overlay) {

        switch (modelType) {
            case "highspeed_standard":
                renderHighspeedStandardSignal(be, ms, buffer, state, renderTime, light, overlay);
                break;
            case "highspeed_compact":
                renderHighspeedCompactSignal(be, ms, buffer, state, renderTime, light, overlay);
                break;
            case "traditional_china":
                renderTraditionalChinaSignal(be, ms, buffer, state, renderTime, light, overlay);
                break;
            default:
                renderDefaultSignal(be, ms, buffer, state, renderTime, light, overlay);
                break;
        }
    }

    private void renderHighspeedStandardSignal(ChinaHighspeedSignalBlockEntity be, PoseStack ms, MultiBufferSource buffer,
                                               SignalStates state, float renderTime, int light, int overlay) {
        ms.pushPose();
        ms.translate(0.5, 1.2, 0.5);
        ms.scale(0.15f, 0.15f, 0.15f);

        // 渲染各种灯光
        if (state.isRedLight(renderTime)) {
            renderLight(ms, buffer, 1.0f, 0.0f, 0.0f, be.getBlockState(), 0, 0.2f, light, overlay);
        }
        if (state.isYellowLight(renderTime) && (state == SignalStates.YELLOW || state == SignalStates.GREEN_YELLOW)) {
            renderLight(ms, buffer, 1.0f, 1.0f, 0.0f, be.getBlockState(), 0, 0.1f, light, overlay);
        }
        if (state.isGreenLight(renderTime)) {
            renderLight(ms, buffer, 0.0f, 1.0f, 0.0f, be.getBlockState(), 0, 0, light, overlay);
        }
        if (state.isGreenYellowLight(renderTime)) {
            renderLight(ms, buffer, 1.0f, 1.0f, 0.0f, be.getBlockState(), 0, -0.1f, light, overlay);
        }
        if (state.isWhiteLight(renderTime)) {
            renderLight(ms, buffer, 1.0f, 1.0f, 1.0f, be.getBlockState(), 0, -0.2f, light, overlay);
        }

        ms.popPose();
    }

    private void renderHighspeedCompactSignal(ChinaHighspeedSignalBlockEntity be, PoseStack ms, MultiBufferSource buffer,
                                              SignalStates state, float renderTime, int light, int overlay) {
        ms.pushPose();
        ms.translate(0.5, 1.1, 0.5);
        ms.scale(0.12f, 0.12f, 0.12f);

        if (state.isRedLight(renderTime)) {
            renderLight(ms, buffer, 1.0f, 0.0f, 0.0f, be.getBlockState(), 0, 0.1f, light, overlay);
        }
        if (state.isYellowLight(renderTime)) {
            renderLight(ms, buffer, 1.0f, 1.0f, 0.0f, be.getBlockState(), 0, 0, light, overlay);
        }
        if (state.isGreenLight(renderTime) && !state.isGreenYellowLight(renderTime)) {
            renderLight(ms, buffer, 0.0f, 1.0f, 0.0f, be.getBlockState(), 0, -0.1f, light, overlay);
        }

        ms.popPose();
    }

    private void renderTraditionalChinaSignal(ChinaHighspeedSignalBlockEntity be, PoseStack ms, MultiBufferSource buffer,
                                              SignalStates state, float renderTime, int light, int overlay) {
        ms.pushPose();
        ms.translate(0.5, 1.3, 0.5);
        ms.scale(0.18f, 0.18f, 0.18f);

        if (state.isRedLight(renderTime)) {
            renderLight(ms, buffer, 1.0f, 0.0f, 0.0f, be.getBlockState(), 0, 0.3f, light, overlay);
        }
        if (state.isYellowLight(renderTime)) {
            renderLight(ms, buffer, 1.0f, 1.0f, 0.0f, be.getBlockState(), 0, 0.15f, light, overlay);
        }
        if (state.isGreenLight(renderTime)) {
            renderLight(ms, buffer, 0.0f, 1.0f, 0.0f, be.getBlockState(), 0, 0, light, overlay);
        }
        if (state.isGreenYellowLight(renderTime)) {
            renderLight(ms, buffer, 0.0f, 1.0f, 0.0f, be.getBlockState(), -0.15f, -0.15f, light, overlay);
            renderLight(ms, buffer, 1.0f, 1.0f, 0.0f, be.getBlockState(), 0.15f, -0.15f, light, overlay);
        }

        ms.popPose();
    }

    private void renderDefaultSignal(ChinaHighspeedSignalBlockEntity be, PoseStack ms, MultiBufferSource buffer,
                                     SignalStates state, float renderTime, int light, int overlay) {
        renderHighspeedStandardSignal(be, ms, buffer, state, renderTime, light, overlay);
    }

    private void renderLight(PoseStack ms, MultiBufferSource buffer, float r, float g, float b,
                             BlockState blockState, float xOffset, float yOffset, int light, int overlay) {
        ms.pushPose();
        ms.translate(xOffset, yOffset, 0);

        // 使用Create的CachedBufferer渲染灯光
        CachedBufferer.partial(AllPartialModels.SIGNAL_ON, blockState)
                .light(light)
                .color(r, g, b, 1.0f)
                .renderInto(ms, buffer.getBuffer(RenderType.solid()));

        ms.popPose();
    }
}
