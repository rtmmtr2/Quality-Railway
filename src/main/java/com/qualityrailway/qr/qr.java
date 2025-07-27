package com.qualityrailway.qr;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.common.MinecraftForge;

@Mod(qr.MODID)
public class qr {
    public static final String MODID = "qr";

    // 使用构造函数注入 mod 事件总线
    public qr(IEventBus modEventBus) {
        // 注册方块和物品（使用注入的 modEventBus）
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModSounds.SOUNDS.register(modEventBus);

        // 注册 Forge 事件总线处理器
        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }
}