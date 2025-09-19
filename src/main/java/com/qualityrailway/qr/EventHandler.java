package com.qualityrailway.qr;
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

        if (KeyBindings.HIGH_HORN_KEY.isDown()) {
            minecraft.player.playSound(
                    ModSounds.high_horn.get(),
                    1.0F,
                    1.0F
            );
        }

        if (KeyBindings.LOW_HORN_KEY.isDown()) {
            minecraft.player.playSound(
                    ModSounds.low_horn.get(),
                    1.0F,
                    1.0F
            );
        }
    }
}
