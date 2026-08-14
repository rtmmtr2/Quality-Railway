package com.qualityrailway.qr.mixin;

import com.jozufozu.flywheel.util.transform.TransformStack;
import com.mojang.blaze3d.vertex.PoseStack;
import com.qualityrailway.qr.blocks.track.GaugeTrackMaterial;
import com.simibubi.create.content.trains.track.BezierConnection;
import com.simibubi.create.content.trains.track.TrackMaterial;
import com.simibubi.create.content.trains.track.TrackRenderer;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.foundation.utility.Iterate;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value = BezierConnection.class, remap = false)
public class MixinBezierConnection {

    @Shadow
    private BezierConnection.SegmentAngles[] bakedSegments;

    @Shadow
    private int getSegmentCount() { return 0; }

    /**
     * @reason 替换硬编码的轨距偏移量，根据材质类型决定轨距
     * @author QualityRailway
     */
    @Overwrite
    public BezierConnection.SegmentAngles[] getBakedSegments() {
        if (bakedSegments != null)
            return bakedSegments;

        // 获取材质
        TrackMaterial material = ((BezierConnection) (Object) this).getMaterial();
        double gaugeOffset;

        // 如果是自定义材质（GaugeTrackMaterial），使用其自带轨距
        if (material instanceof GaugeTrackMaterial) {
            gaugeOffset = ((GaugeTrackMaterial) material).getGaugeOffset();
        } else {
            // 原版材质使用默认值 0.965
            gaugeOffset = 0.965;
        }

        int segmentCount = getSegmentCount();
        bakedSegments = new BezierConnection.SegmentAngles[segmentCount + 1];
        Couple<Vec3> previousOffsets = null;

        for (BezierConnection.Segment segment : (BezierConnection) (Object) this) {
            int i = segment.index;
            boolean end = i == 0 || i == segmentCount;

            BezierConnection.SegmentAngles angles = bakedSegments[i] = new BezierConnection.SegmentAngles();
            Couple<Vec3> railOffsets = Couple.create(
                    segment.position.add(segment.normal.scale(gaugeOffset)),
                    segment.position.subtract(segment.normal.scale(gaugeOffset))
            );
            Vec3 railMiddle = railOffsets.getFirst()
                    .add(railOffsets.getSecond())
                    .scale(0.5);

            if (previousOffsets == null) {
                previousOffsets = railOffsets;
                continue;
            }

            // 枕木
            Vec3 prevMiddle = previousOffsets.getFirst()
                    .add(previousOffsets.getSecond())
                    .scale(0.5);
            Vec3 tieAngles = TrackRenderer.getModelAngles(segment.normal, railMiddle.subtract(prevMiddle));
            angles.lightPosition = BlockPos.containing(railMiddle);
            angles.railTransforms = Couple.create(null, null);

            PoseStack poseStack = new PoseStack();
            TransformStack.cast(poseStack)
                    .translate(prevMiddle)
                    .rotateYRadians(tieAngles.y)
                    .rotateXRadians(tieAngles.x)
                    .rotateZRadians(tieAngles.z)
                    .translate(-0.5f, -2/16f - 1/256f, 0);
            angles.tieTransform = poseStack.last();

            // 铁轨
            float scale = end ? 2.2f : 2.1f;
            for (boolean first : Iterate.trueAndFalse) {
                Vec3 railI = railOffsets.get(first);
                Vec3 prevI = previousOffsets.get(first);
                Vec3 diff = railI.subtract(prevI);
                Vec3 anglesI = TrackRenderer.getModelAngles(segment.normal, diff);

                poseStack = new PoseStack();
                TransformStack.cast(poseStack)
                        .translate(prevI)
                        .rotateYRadians(anglesI.y)
                        .rotateXRadians(anglesI.x)
                        .rotateZRadians(anglesI.z)
                        .translate(0, -2/16f - 1/256f, -1/32f)
                        .scale(1, 1, (float) diff.length() * scale);
                angles.railTransforms.set(first, poseStack.last());
            }

            previousOffsets = railOffsets;
        }

        return bakedSegments;
    }
}