package com.qualityrailway.qr;
import com.qualityrailway.qr.blockentity.AdvancedSignBlockEntity;
import com.qualityrailway.qr.renderer.*;
import com.qualityrailway.qr.screen.*;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.core.BlockPos;
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
import org.intellij.lang.annotations.Identifier;


@Mod(qr.MODID)
public class qr {
    public static final String MODID = "qr";
    public static final Logger LOGGER = LogManager.getLogger();
    public qr() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        // 注册方块，物品，声音等
        ModBlocks.BLOCKS.register(bus);
        ModItems.ITEMS.register(bus);
        ModSounds.SOUNDS.register(bus);
        ModBlockEntities.BLOCK_ENTITIES.register(bus);
        ModMenuTypes.MENUS.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
        // 注册双端设置事件监听器
        bus.addListener(this::onClientSetup);
        bus.addListener(this::onCommonSetup);
        // 只在客户端环境下注册EventHandler
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            MinecraftForge.EVENT_BUS.register(new EventHandler());
        });
        LOGGER.info("Quality Railway initialized successfully!");
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        KeyBindings.register(event);
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ArriveGateBlockRight.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.DepartGateBlockRight.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ADVANCED_SIGN.get(), RenderType.translucent());
            MenuScreens.register(ModMenuTypes.ADVANCED_SIGN_MENU.get(), AdvancedSignScreen::new);

            // 注册方块实体渲染器
            BlockEntityRenderers.register(ModBlockEntities.ADVANCED_SIGN.get(),
                    AdvancedSignRenderer::new);
        });
        LOGGER.info("Client setup complete");
    }
    private void onCommonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModBlocks.registerInteractionBehaviours();
            LOGGER.info("Registered train door interaction behaviours");
        });

        LOGGER.info("Common setup complete");
    }
}