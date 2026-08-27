package com.qualityrailway.qr;
import com.qualityrailway.qr.screen.AdvancedSignMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, qr.MODID);

    public static final RegistryObject<MenuType<AdvancedSignMenu>> ADVANCED_SIGN_MENU =
    MENUS.register("advanced_sign_menu", () ->
        IForgeMenuType.create(AdvancedSignMenu::new
        ));
}
