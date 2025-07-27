package com.qualityrailway.qr;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {
    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent.RightClickItem event) {
        // 直接获取玩家实体，事件由玩家触发
        Player player = event.getEntity();
        ItemStack stack = event.getItemStack();

        // 添加空物品检查
        if (stack.isEmpty()) {
            return;
        }

        if (stack.getItem() == ModItems.high_horn.get()) {
            // 添加客户端检查
            if (!player.level.isClientSide) {
                player.level.playSound(
                        player, // 提供声音源实体
                        player.getX(), player.getY(), player.getZ(),
                        ModSounds.high_horn.get(),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );
            }
            event.setCanceled(true);
        }
        else if (stack.getItem() == ModItems.low_horn.get()) {
            // 添加客户端检查
            if (!player.level.isClientSide) {
                player.level.playSound(
                        player,
                        player.getX(), player.getY(), player.getZ(),
                        ModSounds.low_horn.get(),
                        SoundSource.PLAYERS,
                        1.0F,
                        1.0F
                );
            }
            event.setCanceled(true);
        }
    }
}