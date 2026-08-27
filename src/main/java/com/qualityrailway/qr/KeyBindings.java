package com.qualityrailway.qr;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
public class KeyBindings {
    public static final KeyMapping HIGH_HORN_KEY = new KeyMapping(
            "key.qr.high_horn",
            GLFW.GLFW_KEY_H,
            "category.qr.horns"
    );

    public static final KeyMapping LOW_HORN_KEY = new KeyMapping(
            "key.qr.low_horn",
            GLFW.GLFW_KEY_L,
            "category.qr.horns"
    );

    // 启用/禁用CTCS功能
    public static final KeyMapping ENABLE_DISABLE_CTCS_KEY = new KeyMapping(
            "key.qr.enable_disable_ctcs",
            GLFW.GLFW_KEY_C,
            "category.qr.ctcs"
    );

    public static void register(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            net.minecraft.client.Minecraft.getInstance().options.keyMappings =
                    org.apache.commons.lang3.ArrayUtils.addAll(
                            net.minecraft.client.Minecraft.getInstance().options.keyMappings,
                            HIGH_HORN_KEY,
                            LOW_HORN_KEY,
                            ENABLE_DISABLE_CTCS_KEY
                    );
        });
    }
}
