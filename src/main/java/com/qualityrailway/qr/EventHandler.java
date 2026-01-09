package com.qualityrailway.qr;

import com.qualityrailway.qr.ctcs.CTCSOverlay;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.lwjgl.glfw.GLFW;

@OnlyIn(Dist.CLIENT)
public class EventHandler {

    private CTCSOverlay ctcsOverlay;
    private boolean ctcsInitialized = false;

    private CTCSOverlay getCTCSUI() {
        if (!ctcsInitialized) {
            ctcsOverlay = new CTCSOverlay();
            ctcsInitialized = true;
        }
        return ctcsOverlay;
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) return;

        // 高音喇叭
        if (KeyBindings.HIGH_HORN_KEY.isDown()) {
            minecraft.player.playSound(
                    ModSounds.high_horn.get(),
                    1.0F,
                    1.0F
            );
            qr.LOGGER.debug("HIGH_HORN_KEY pressed");
        }

        // 低音喇叭
        if (KeyBindings.LOW_HORN_KEY.isDown()) {
            minecraft.player.playSound(
                    ModSounds.low_horn.get(),
                    1.0F,
                    1.0F
            );
            qr.LOGGER.debug("LOW_HORN_KEY pressed");
        }

        // CTRL+C 切换CTCS界面显示
        if (event.getKey() == GLFW.GLFW_KEY_C &&
            (event.getModifiers() & GLFW.GLFW_MOD_CONTROL) != 0) {
            CTCSOverlay overlay = getCTCSUI();
            overlay.toggleVisibility();
            qr.LOGGER.info("CTCS interface toggled (now visible: " + overlay.isVisible() + ")");
        }

        // CTRL+SHIFT+C 禁用/启用CTCS
        if (event.getKey() == GLFW.GLFW_KEY_C &&
            (event.getModifiers() & (GLFW.GLFW_MOD_CONTROL | GLFW.GLFW_MOD_SHIFT)) ==
            (GLFW.GLFW_MOD_CONTROL | GLFW.GLFW_MOD_SHIFT)) {
            CTCSOverlay overlay = getCTCSUI();
            boolean newEnabledState = !overlay.isEnabled();
            overlay.setEnabled(newEnabledState);
            qr.LOGGER.info("CTCS interface " + (newEnabledState ? "enabled" : "disabled"));
        }
    }
}