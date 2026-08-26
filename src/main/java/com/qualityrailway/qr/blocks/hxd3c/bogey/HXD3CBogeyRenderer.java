package com.qualityrailway.qr.blocks.hxd3c.bogey;

import com.jozufozu.flywheel.api.MaterialManager;
import com.jozufozu.flywheel.core.PartialModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.simibubi.create.content.trains.bogey.BogeyRenderer;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.entity.CarriageBogey;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;

public class HXD3CBogeyRenderer extends BogeyRenderer {

    private static final PartialModel FRAME_MODEL =
            new PartialModel(new ResourceLocation("qr", "block/hxd3c/common/bogey/hxd3c_bogey_frame"));
    private static final PartialModel WHEEL_MODEL =
            new PartialModel(new ResourceLocation("qr", "block/hxd3c/common/bogey/hxd3c_bogey_wheel"));

    // 轮对 Z 轴位置（正向：前→后）
    private static final double[] WHEEL_Z = { -2.25, 0.0, 2.0 };
    private static final float WHEEL_Y_OFFSET = 0.96875f; // 轮径 1250mm → 半径 0.625 格

    @Override
    public void initialiseContraptionModelData(MaterialManager mm, CarriageBogey bogey) {
        createModelInstance(mm, FRAME_MODEL);
        createModelInstance(mm, WHEEL_MODEL, 3);
    }

    @Override
    public void render(CompoundTag data, float wheelAngle, PoseStack ms, int light,
                       VertexConsumer vb, boolean inContraption) {
        boolean inInstanced = vb == null;

        BogeyModelData frame = getTransform(FRAME_MODEL, ms, inInstanced);
        BogeyModelData[] wheels = getTransform(WHEEL_MODEL, ms, inInstanced, 3);

        // 框架和顶部直接渲染在原点
        frame.render(ms, light, vb);

        // 轮对：Z 轴位置 + Y 轴向上抬半径 + 旋转
        for (int i = 0; i < 3; i++) {
            wheels[i]
                    .translate(0, 0, -0.125)
                    .translate(0, WHEEL_Y_OFFSET, WHEEL_Z[i])
                    .rotateX(wheelAngle)
                    .render(ms, light, vb);
        }
    }

    @Override
    public BogeySizes.BogeySize getSize() {
        return AllCustomBogeyStyles.HXD3C_TOWARD;
    }
}