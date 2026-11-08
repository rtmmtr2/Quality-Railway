package com.qualityrailway.qr;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, qr.MODID);

    public static final RegistryObject<SoundEvent> HIGH_HORN = SOUNDS.register("high_horn",
            () -> new SoundEvent(ResourceLocation.parse(qr.MODID + ":high_horn")));

    public static final RegistryObject<SoundEvent> LOW_HORN = SOUNDS.register("low_horn",
            () -> new SoundEvent(ResourceLocation.parse(qr.MODID + ":low_horn")));

    public static final RegistryObject<SoundEvent> BELL = SOUNDS.register("bell",
            () -> new SoundEvent(ResourceLocation.parse(qr.MODID + ":bell")));
}