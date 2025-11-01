package com.qualityrailway.qr;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

@Mod(qr.MODID)
public class qr {
    public static final String MODID = "qr";

    public static final Logger LOGGER = LoggerFactory.getLogger(qr.MODID);

    public qr(IEventBus modEventBus, ModContainer modContainer) {
        // 注册方块，物品，声音等
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModCreativeModeTabs.CREATIVE_MODE_TAB.register(modEventBus);

        modEventBus.addListener(this::onRegisterEvent);

        LOGGER.info("Quality Railway initialized successfully!");
    }


    private void onRegisterEvent(net.neoforged.neoforge.registries.RegisterEvent event) {

    }

}
