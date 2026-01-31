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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChinaHighspeedSignalRenderer extends SignalRenderer {

    private static final Logger LOGGER = LogManager.getLogger(ChinaHighspeedSignalRenderer.class);

    public ChinaHighspeedSignalRenderer(BlockEntityRendererProvider.Context context) {
        super(context);
        LOGGER.debug("ChinaHighspeedSignalRenderer 已创建");
    }

    @Override
    protected void renderSafe(com.simibubi.create.content.trains.signal.SignalBlockEntity be, float partialTicks, PoseStack ms,
                             MultiBufferSource buffer, int light, int overlay) {
        // 先调用父类渲染基座
        super.renderSafe(be, partialTicks, ms, buffer, light, overlay);

        // 添加类型安全检查
        if (be == null) {
            LOGGER.warn("ChinaHighspeedSignalRenderer 收到 null 方块实体");
            return;
        }

        if (!(be instanceof ChinaHighspeedSignalBlockEntity)) {
            LOGGER.error("ChinaHighspeedSignalRenderer 收到错误的方块实体类型: {}，期望: ChinaHighspeedSignalBlockEntity",
                    be.getClass().getName());
            return;
        }

        // 注意：灯光现在通过BlockState模型渲染，这里只处理动态效果（如闪烁）
        // 如果需要动态效果，可以在这里添加
    }
}
