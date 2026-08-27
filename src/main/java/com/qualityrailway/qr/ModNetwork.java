package com.qualityrailway.qr;

import com.qualityrailway.qr.network.UpdateAdvancedSignPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetwork {
    private static final String PROTOCOL_VERSION = "1";
    @SuppressWarnings("removal")
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
        new ResourceLocation(qr.MODID, "main"),
        () -> PROTOCOL_VERSION,
        PROTOCOL_VERSION::equals,
        PROTOCOL_VERSION::equals
    );
    
    private static int packetId = 0;
    
    public static void register() {
        CHANNEL.registerMessage(packetId++, UpdateAdvancedSignPacket.class,
            UpdateAdvancedSignPacket::encode,
            UpdateAdvancedSignPacket::decode,
            UpdateAdvancedSignPacket::handle);
    }
}