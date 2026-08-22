package com.qualityrailway.qr.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.qualityrailway.qr.blocks.track.GaugeTrackMaterial;
import com.simibubi.create.content.trains.track.BezierConnection;
import com.simibubi.create.content.trains.track.TrackMaterial;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = BezierConnection.class, remap = false, priority = 500)
public class MixinBezierConnection {

    @ModifyExpressionValue(
            method = "getBakedSegments",
            at = @At(value = "CONSTANT", args = "doubleValue=0.9649999737739563")
    )
    private double modifyGaugeOffset(double original) {
        BezierConnection bc = (BezierConnection) (Object) this;
        TrackMaterial material = bc.getMaterial();

        if (material != null && "qr".equals(material.id.getNamespace())
                && material instanceof GaugeTrackMaterial) {
            return ((GaugeTrackMaterial) material).getGaugeOffset();
        }
        return original;
    }
}