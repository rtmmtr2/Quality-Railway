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
    public static CreativeModeTab RAILWAY_TOOLS;
    public static CreativeModeTab SIGNS;

    @SubscribeEvent
    public static void registerCreativeModeTabs(RegisterEvent event) {
        event.register(Registries.CREATIVE_MODE_TAB, helper -> {
            // 注册三个标签页
            TRAINS = CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.qr.trains"))
                    .icon(() -> new ItemStack(ModItems.qr_item.get()))
                    .build();

            RAILWAY_TOOLS = CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.qr.railway_tools"))
                    .icon(() -> new ItemStack(ModItems.spanner.get()))
                    .build();

            SIGNS = CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.qr.signs"))
                    .icon(() -> new ItemStack(ModItems.sign.get()))
                    .build();

            // 注册到游戏
            helper.register("trains", TRAINS);
            helper.register("railway_tools", RAILWAY_TOOLS);
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
            event.accept(ModItems.df7g_cab);
            event.accept(ModItems.df7g_cowcatcher_a);
            event.accept(ModItems.df7g_cowcatcher_b);
            event.accept(ModItems.df7g_enclosure_a);
            event.accept(ModItems.df7g_enclosure_b);
            event.accept(ModItems.df7g_enclosure_c);
            event.accept(ModItems.df7g_enclosure_d);
            event.accept(ModItems.df7g_enclosure_e);
            event.accept(ModItems.df7g_enclosure_f);
            event.accept(ModItems.df7g_enclosure_end);
            event.accept(ModItems.df7g_floor_a);
            event.accept(ModItems.df7g_floor_b);
            event.accept(ModItems.df7g_floor_c);
            event.accept(ModItems.df7g_floor_d);
            event.accept(ModItems.df7g_floor_e);
            event.accept(ModItems.df7g_floor_f);
            event.accept(ModItems.df4d_cab);
            event.accept(ModItems.df4d_cab_door_a);
            event.accept(ModItems.df4d_cab_door_b);
            event.accept(ModItems.df4d_chimney_a);
            event.accept(ModItems.df4d_floor_a);
            event.accept(ModItems.df4d_floor_b);
            event.accept(ModItems.df4d_floor_c);
            event.accept(ModItems.df4d_floor_d);
            event.accept(ModItems.df4d_left_board_a);
            event.accept(ModItems.df4d_left_board_b);
            event.accept(ModItems.df4d_left_board_c);
            event.accept(ModItems.df4d_left_board_d);
            event.accept(ModItems.df4d_left_board_e);
            event.accept(ModItems.df4d_left_board_f);
            event.accept(ModItems.df4d_left_board_g);
            event.accept(ModItems.df4d_right_board_a);
            event.accept(ModItems.df4d_right_board_b);
            event.accept(ModItems.df4d_right_board_c);
            event.accept(ModItems.df4d_right_board_d);
            event.accept(ModItems.df4d_right_board_e);
            event.accept(ModItems.df4d_right_board_f);
            event.accept(ModItems.df4d_right_board_g);
            event.accept(ModItems.df4d_radiator_a);
            event.accept(ModItems.df4d_radiator_b);
            event.accept(ModItems.df4d_roof_a);
            event.accept(ModItems.df4d_roof_b);
            event.accept(ModItems.df4d_roof_c);
            event.accept(ModItems.df4d_roof_d);
            event.accept(ModItems.df4d_roof_e);
            event.accept(ModItems.df4d_middle_door);
        }
        // 填充 tools 标签页
        else if (event.getTab() == RAILWAY_TOOLS) {
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
            event.accept(ModItems.automatic_tickets);
            event.accept(ModItems.entrance);
            event.accept(ModItems.exit);
            event.accept(ModItems.railway_tickets);
            event.accept(ModItems.shopping);
            event.accept(ModItems.vip);
            event.accept(ModItems.passengers_stopped);
            event.accept(ModItems.waiting_room);
        }
    }
}