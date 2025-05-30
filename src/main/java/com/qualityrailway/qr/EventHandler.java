package com.qualityrailway.qr;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {
    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickItem event) {
        Player player = event.getPlayer();
        ItemStack stack = event.getItemStack();

        if (stack.getItem() == ModItems.high_horn.get()) {
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    ModSounds.high_horn.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            event.setCanceled(true);
        }
        else if (stack.getItem() == ModItems.low_horn.get()) {
            player.level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    ModSounds.low_horn.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
            event.setCanceled(true);
        }
    }
}