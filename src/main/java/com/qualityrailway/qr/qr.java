package com.qualityrailway.qr;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(qr.MODID)
public class qr {
    public static final String MODID = "qr";

    public qr() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // 注册方块和物品
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
    }
}