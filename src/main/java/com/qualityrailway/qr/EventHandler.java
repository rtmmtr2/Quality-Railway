package com.qualityrailway.qr;

import com.qualityrailway.qr.ctcs.CTCSOverlay;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class EventHandler {

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

        // 禁用/启用CTCS
        if (KeyBindings.ENABLE_DISABLE_CTCS_KEY.isDown()) {
            CTCSOverlay overlay = CTCSOverlay.getInstance();
            boolean newEnabledState = !overlay.isEnabled();
            overlay.setEnabled(newEnabledState);
            qr.LOGGER.info("CTCS interface " + (newEnabledState ? "enabled" : "disabled"));
        }
    }
}