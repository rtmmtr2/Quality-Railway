package com.qualityrailway.qr;

import com.qualityrailway.qr.register.QRRegistrate;
import com.qualityrailway.qr.event.EventHandler;
import com.qualityrailway.qr.blocks.ModBlocks;
import com.qualityrailway.qr.blockentity.ModBlockEntities;
import com.qualityrailway.qr.blocks.track.AllTracks;
import com.qualityrailway.qr.event.KeyBindings;
import com.qualityrailway.qr.items.ModItems;
import com.qualityrailway.qr.sound.ModSounds;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(qr.MODID)
public class qr {
    public static final String MODID = "qr";
    public static final Logger LOGGER = LogManager.getLogger();

    public qr() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModSounds.SOUNDS.register(bus);
        AllTracks.register();
        QRRegistrate.REGISTRATE.getClass();
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        MinecraftForge.EVENT_BUS.register(this);

        bus.addListener(this::onClientSetup);
        bus.addListener(this::onCommonSetup);

        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            MinecraftForge.EVENT_BUS.register(new EventHandler());
        });

        LOGGER.info("Quality Railway initialized successfully!");
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        KeyBindings.register(event);
        LOGGER.info("Client setup complete");
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModBlocks.registerInteractionBehaviours();
            LOGGER.info("Registered train door interaction behaviours");
        });
        LOGGER.info("Common setup complete");
    }

    // ✅ 删除了 ClientTickHandler，统一由 ClientEvents 处理
}