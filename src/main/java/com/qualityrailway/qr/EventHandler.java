package com.qualityrailway.qr;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import static com.qualityrailway.qr.qr.LOGGER;

@OnlyIn(Dist.CLIENT)
public class EventHandler {

    @SubscribeEvent
    public void onKeyInput(InputEvent.Key event) {
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft.player == null) return;

        if (KeyBindings.HIGH_HORN_KEY.isDown()) {
            minecraft.player.playSound(
                    ModSounds.high_horn.get(),
                    1.0F,
                    1.0F
            );
            LOGGER.info("HIGH_HORN_KEY pressed");
        }

        if (KeyBindings.LOW_HORN_KEY.isDown()) {
            minecraft.player.playSound(
                    ModSounds.low_horn.get(),
                    1.0F,
                    1.0F
            );
            LOGGER.info("LOW_HORN_KEY pressed");
        }
    }
}
