// java
package com.qualityrailway.qr.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class RedTicket extends Item {
    public RedTicket(Properties properties) {
        super(properties);
    }

    public void appendHoverText(ItemStack stack, @Nullable Level world,
                                List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, TooltipContext.of(world), tooltip, flag);

        tooltip.add(Component.translatable("tooltip.qr.red_ticket.description_1").withStyle(ChatFormatting.BLUE));
        tooltip.add(Component.translatable("tooltip.qr.red_ticket.description_2").withStyle(ChatFormatting.GRAY));
    }
}
