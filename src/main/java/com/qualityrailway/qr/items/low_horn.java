package com.qualityrailway.qr.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

public class low_horn extends Item {
    public low_horn(Properties properties) {
        super(properties);
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world,
                                List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, world, tooltip, flag);

        // 添加自定义描述文本
        tooltip.add(new TranslatableComponent("tooltip.qr.low_horn.description").withStyle(ChatFormatting.GRAY));
    }
}