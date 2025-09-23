package com.qualityrailway.qr;

import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.SimpleBlockMovingInteraction;
import com.simibubi.create.foundation.data.CreateRegistrate;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;

@Mod(qr.MODID)
public class qr {
    public static final String MODID = "qr";
    public static final CreateRegistrate REGISTRATE = CreateRegistrate.create(MODID);
    public qr() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);

        bus.addListener(this::onClientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }





    private void onClientSetup(final FMLClientSetupEvent event) {
        // 客户端相关注册
    }
}

