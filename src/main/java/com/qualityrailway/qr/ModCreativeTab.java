package com.qualityrailway.qr;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegisterEvent;

@Mod.EventBusSubscriber(modid = qr.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTab {
    public static CreativeModeTab TRAINS;
    public static CreativeModeTab TOOLS;
    public static CreativeModeTab SIGNS;

    @SubscribeEvent
    public static void registerCreativeModeTabs(RegisterEvent event) {
        event.register(Registries.CREATIVE_MODE_TAB, helper -> {
            // 注册三个标签页
            TRAINS = CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.qr.trains"))
                    .icon(() -> new ItemStack(ModItems.c70_left_end_board.get()))
                    .build();

            TOOLS = CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.qr.tools"))
                    .icon(() -> new ItemStack(ModItems.spanner.get()))
                    .build();

            SIGNS = CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.qr.signs"))
                    .icon(() -> new ItemStack(ModItems.sign.get()))
                    .build();

            // 注册到游戏
            helper.register("trains", TRAINS);
            helper.register("tools", TOOLS);
            helper.register("signs", SIGNS);
        });
    }

    @SubscribeEvent
    public static void buildCreativeTabContents(BuildCreativeModeTabContentsEvent event) {
        // 填充 trains 标签页
        if (event.getTab() == TRAINS) {
            event.accept(ModItems.c70_left_end_board);
            event.accept(ModItems.c70_right_end_board);
            event.accept(ModItems.c70_front_board);
            event.accept(ModItems.c70_left_board);
            event.accept(ModItems.c70_door);
            event.accept(ModItems.c70_floor);
            event.accept(ModItems.gq70_end_tank);
            event.accept(ModItems.gq70_tank_a);
            event.accept(ModItems.gq70_tank_b);
            event.accept(ModItems.gq70_tank_c);
        }
        // 填充 tools 标签页
        else if (event.getTab() == TOOLS) {
            event.accept(ModItems.high_horn);
            event.accept(ModItems.low_horn);
            event.accept(ModItems.steel_plate);
            event.accept(ModItems.bell);
            event.accept(ModItems.railway_ballast);
            event.accept(ModItems.railway_ballast_slab);
            event.accept(ModItems.gravel);
            event.accept(ModItems.gravel_slab);
        }
        // 填充 signs 标签页
        else if (event.getTab() == SIGNS) {
            event.accept(ModItems.ticket_check);
            event.accept(ModItems.police);
            event.accept(ModItems.information);
            event.accept(ModItems.toilet);
            event.accept(ModItems.sign_post);
            event.accept(ModItems.first_approach_section_sign);
            event.accept(ModItems.second_approach_section_sign);
            event.accept(ModItems.third_approach_section_sign);
            event.accept(ModItems.dual_pantograph_prohibited_sign);
        }
    }
}