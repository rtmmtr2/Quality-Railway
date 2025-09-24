package com.qualityrailway.qr;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;

@Mod(qr.MODID)
public class qr {
    public static final String MODID = "qr";

    public qr() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // 注册方块，物品，声音等
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModSounds.SOUNDS.register(bus);

        // 注册客户端设置事件监听器
        bus.addListener(this::onClientSetup);

        MinecraftForge.EVENT_BUS.register(new EventHandler());
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        KeyBindings.register(event);
    }
}