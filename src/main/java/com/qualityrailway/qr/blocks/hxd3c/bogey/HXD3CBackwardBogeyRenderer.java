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

public class HXD3CBackwardBogeyRenderer extends BogeyRenderer {

    private static final PartialModel FRAME_MODEL =
            new PartialModel(new ResourceLocation("qr", "block/hxd3c/common/bogey/hxd3c_bogey_frame_backward"));
    private static final PartialModel WHEEL_MODEL =
            new PartialModel(new ResourceLocation("qr", "block/hxd3c/common/bogey/hxd3c_bogey_wheel"));

    // 轮对 Z 轴位置（反向：前后顺序相反）
    private static final double[] WHEEL_Z = { -2.0, 0.0, 2.25 };
    private static final float WHEEL_Y_OFFSET = 0.96875f;

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

        frame.render(ms, light, vb);

        // 反向轮对：Z 轴位置是正向的镜像，且轮子反转（旋转角度取反）
        for (int i = 0; i < 3; i++) {
            wheels[i]
                    .translate(0, 0, -0.125)
                    .translate(0, WHEEL_Y_OFFSET, -WHEEL_Z[i])
                    .rotateX(wheelAngle)
                    .render(ms, light, vb);
        }
    }

    @Override
    public BogeySizes.BogeySize getSize() {
        return AllCustomBogeyStyles.HXD3C_BACKWARD;
    }
}