package com.qualityrailway.qr;

import com.qualityrailway.qr.blocks.Doors.SlidingDoor.TrainSlidingDoorBlock;
import com.simibubi.create.content.contraptions.behaviour.MovementBehaviour;
import com.simibubi.create.content.contraptions.behaviour.SimpleBlockMovingInteraction;
import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.ItemDescription;
import com.simibubi.create.foundation.item.KineticStats;
import com.simibubi.create.foundation.item.TooltipHelper;
import com.simibubi.create.foundation.item.TooltipModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;

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
        // 使用 DistExecutor 检查当前环境
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ModPartialModels.init());
        ModTags.register();
    }

//    public static void gatherData(DataGenerator.PackGenerator gen) {
//        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, CTUTagGen::generateBlockTags);
//        REGISTRATE.addDataGenerator(ProviderType.ITEM_TAGS, CTUTagGen::generateItemTags);
//    }
@SuppressWarnings("removal")
    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MODID, path);
    }

    @Nullable
    public static KineticStats create(Item item) {
        if (item instanceof BlockItem blockItem) {
            Block block = blockItem.getBlock();
            if (block instanceof TrainSlidingDoorBlock) {
                return new KineticStats(block);
            }
            // ...
        }
        return null;
    }

    static {
        REGISTRATE.setTooltipModifierFactory(item -> {
            return new ItemDescription.Modifier(item, TooltipHelper.Palette.STANDARD_CREATE).andThen(TooltipModifier.mapNull(create(item)));
        });
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        // 客户端相关注册
    }
}







