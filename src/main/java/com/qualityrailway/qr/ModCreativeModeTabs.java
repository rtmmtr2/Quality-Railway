package com.qualityrailway.qr;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.CreativeModeTab;
public class ModCreativeModeTabs {
    public static final DeferredRegister<net.minecraft.world.item.CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(
            BuiltInRegistries.CREATIVE_MODE_TAB, qr.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> trains =
            ModCreativeModeTabs.CREATIVE_MODE_TAB.register("trains",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("creative_mode_tab.qr.trains"))
                            .icon(() -> new ItemStack(ModItems.qr_item.get()))
                            .displayItems((params, output) -> {

                                output.accept(ModItems.c70_left_end_board.get());
                                output.accept(ModItems.c70_right_end_board.get());
                                output.accept(ModItems.c70_front_board.get());
                                output.accept(ModItems.c70_left_board.get());
                                output.accept(ModItems.c70_door.get());
                                output.accept(ModItems.c70_floor.get());
                                output.accept(ModItems.gq70_end_tank.get());
                                output.accept(ModItems.gq70_tank_a.get());
                                output.accept(ModItems.gq70_tank_b.get());
                                output.accept(ModItems.gq70_tank_c.get());
                                output.accept(ModItems.df7g_cab.get());
                                output.accept(ModItems.df7g_cowcatcher_a.get());
                                output.accept(ModItems.df7g_cowcatcher_b.get());
                                output.accept(ModItems.df7g_enclosure_a.get());
                                output.accept(ModItems.df7g_enclosure_b.get());
                                output.accept(ModItems.df7g_enclosure_c.get());
                                output.accept(ModItems.df7g_enclosure_d.get());
                                output.accept(ModItems.df7g_enclosure_e.get());
                                output.accept(ModItems.df7g_enclosure_f.get());
                                output.accept(ModItems.df7g_enclosure_end.get());
                                output.accept(ModItems.df7g_floor_a.get());
                                output.accept(ModItems.df7g_floor_b.get());
                                output.accept(ModItems.df7g_floor_c.get());
                                output.accept(ModItems.df7g_floor_d.get());
                                output.accept(ModItems.df7g_floor_e.get());
                                output.accept(ModItems.df7g_floor_f.get());
                                output.accept(ModItems.df4d_cab.get());
                                output.accept(ModItems.df4d_cab_door_a.get());
                                output.accept(ModItems.df4d_cab_door_b.get());
                                output.accept(ModItems.df4d_chimney_a.get());
                                output.accept(ModItems.df4d_floor_a.get());
                                output.accept(ModItems.df4d_floor_b.get());
                                output.accept(ModItems.df4d_floor_c.get());
                                output.accept(ModItems.df4d_floor_d.get());
                                output.accept(ModItems.df4d_left_board_a.get());
                                output.accept(ModItems.df4d_left_board_b.get());
                                output.accept(ModItems.df4d_left_board_c.get());
                                output.accept(ModItems.df4d_left_board_d.get());
                                output.accept(ModItems.df4d_left_board_e.get());
                                output.accept(ModItems.df4d_left_board_f.get());
                                output.accept(ModItems.df4d_left_board_g.get());
                                output.accept(ModItems.df4d_right_board_a.get());
                                output.accept(ModItems.df4d_right_board_b.get());
                                output.accept(ModItems.df4d_right_board_c.get());
                                output.accept(ModItems.df4d_right_board_d.get());
                                output.accept(ModItems.df4d_right_board_e.get());
                                output.accept(ModItems.df4d_right_board_f.get());
                                output.accept(ModItems.df4d_right_board_g.get());
                                output.accept(ModItems.df4d_radiator_a.get());
                                output.accept(ModItems.df4d_radiator_b.get());
                                output.accept(ModItems.df4d_roof_a.get());
                                output.accept(ModItems.df4d_roof_b.get());
                                output.accept(ModItems.df4d_roof_c.get());
                                output.accept(ModItems.df4d_roof_d.get());
                                output.accept(ModItems.df4d_roof_e.get());
                                output.accept(ModItems.df4d_middle_door.get());

                            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> railway_tools =
            ModCreativeModeTabs.CREATIVE_MODE_TAB.register("railway_tools",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("creative_mode_tab.qr.railway_tools"))
                            .icon(() -> new ItemStack(ModItems.spanner.get()))
                            .displayItems((params, output) -> {

                                output.accept(ModItems.steel_plate.get());
                                output.accept(ModItems.bell.get());
                                output.accept(ModItems.railway_ballast.get());
                                output.accept(ModItems.railway_ballast_slab.get());
                                output.accept(ModItems.gravel.get());
                                output.accept(ModItems.gravel_slab.get());
                                output.accept(ModItems.ArriveGateBlockRight.get());
                                output.accept(ModItems.DepartGateBlockRight.get());
                                output.accept(ModItems.GateBlockLeft.get());
                                output.accept(ModItems.TicketMachineBlock.get());
                                output.accept(ModItems.RedTicket.get());
                                output.accept(ModItems.BlueTicket.get());

                            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> signs =
            ModCreativeModeTabs.CREATIVE_MODE_TAB.register("signs",
                    () -> CreativeModeTab.builder()
                            .title(Component.translatable("creative_mode_tab.qr.signs"))
                            .icon(() -> new ItemStack(ModItems.sign.get()))
                            .displayItems((params, output) -> {

                                output.accept(ModItems.ticket_check.get());
                                output.accept(ModItems.police.get());
                                output.accept(ModItems.information.get());
                                output.accept(ModItems.toilet.get());
                                output.accept(ModItems.sign_post.get());
                                output.accept(ModItems.first_approach_section_sign.get());
                                output.accept(ModItems.second_approach_section_sign.get());
                                output.accept(ModItems.third_approach_section_sign.get());
                                output.accept(ModItems.dual_pantograph_prohibited_sign.get());
                                output.accept(ModItems.automatic_tickets.get());
                                output.accept(ModItems.entrance.get());
                                output.accept(ModItems.exit.get());
                                output.accept(ModItems.railway_tickets.get());
                                output.accept(ModItems.shopping.get());
                                output.accept(ModItems.vip.get());
                                output.accept(ModItems.passengers_stopped.get());
                                output.accept(ModItems.waiting_room.get());
                                output.accept(ModItems.china_railway_freight_a.get());
                                output.accept(ModItems.china_railway_freight_b.get());
                                output.accept(ModItems.catenary_end.get());
                                output.accept(ModItems.communication_switching.get());
                                output.accept(ModItems.derailer.get());
                                output.accept(ModItems.electrified_zone.get());
                                output.accept(ModItems.fifty_meters_to_buffer_stop.get());
                                output.accept(ModItems.locomotive_stopping_position.get());
                                output.accept(ModItems.no_entry.get());
                                output.accept(ModItems.no_thoroughfare.get());
                                output.accept(ModItems.non_electrified_zone.get());
                                output.accept(ModItems.prepare_to_lower_pantograph.get());
                                output.accept(ModItems.road_rail_vehicle_coordination.get());
                                output.accept(ModItems.track_scale.get());
                                output.accept(ModItems.platform_1.get());
                                output.accept(ModItems.platform_1_and_2.get());
                                output.accept(ModItems.platform_1_and_2_left.get());
                                output.accept(ModItems.platform_1_and_2_right.get());
                                output.accept(ModItems.platform_1_left.get());
                                output.accept(ModItems.platform_1_right.get());
                                output.accept(ModItems.platform_2.get());
                                output.accept(ModItems.platform_2_and_3.get());
                                output.accept(ModItems.platform_2_and_3_left.get());
                                output.accept(ModItems.platform_2_and_3_right.get());
                                output.accept(ModItems.platform_3.get());
                                output.accept(ModItems.platform_3_and_4.get());
                                output.accept(ModItems.platform_3_and_4_left.get());
                                output.accept(ModItems.platform_3_and_4_right.get());
                                output.accept(ModItems.platform_4.get());
                                output.accept(ModItems.platform_4_and_5.get());
                                output.accept(ModItems.platform_4_and_5_left.get());
                                output.accept(ModItems.platform_4_and_5_right.get());
                                output.accept(ModItems.platform_5.get());
                                output.accept(ModItems.platform_5_and_6.get());
                                output.accept(ModItems.platform_5_and_6_left.get());
                                output.accept(ModItems.platform_5_and_6_right.get());
                                output.accept(ModItems.platform_6.get());
                                output.accept(ModItems.platform_6_and_7.get());
                                output.accept(ModItems.platform_6_and_7_left.get());
                                output.accept(ModItems.platform_6_and_7_right.get());
                                output.accept(ModItems.platform_7.get());
                                output.accept(ModItems.platform_7_and_8.get());
                                output.accept(ModItems.platform_7_and_8_left.get());
                                output.accept(ModItems.platform_7_and_8_right.get());
                                output.accept(ModItems.platform_8.get());
                                output.accept(ModItems.drinking_water.get());
                                output.accept(ModItems.crh_emu_stop.get());
                                output.accept(ModItems.crh_fight_location.get());

                            }).build());
}