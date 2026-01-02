package com.qualityrailway.qr.network;

import com.qualityrailway.qr.blockentity.AdvancedSignBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class UpdateAdvancedSignPacket {
    
    private final BlockPos pos;
    private final String text;
    private final int textX;
    private final int textY;
    private final int textSize;
    private final String colorHex;
    
    public UpdateAdvancedSignPacket(BlockPos pos, String text, int textX, int textY, int textSize, String colorHex) {
        this.pos = pos;
        this.text = text;
        this.textX = textX;
        this.textY = textY;
        this.textSize = textSize;
        this.colorHex = colorHex;
    }
    
    public static UpdateAdvancedSignPacket decode(FriendlyByteBuf buf) {
        return new UpdateAdvancedSignPacket(
            buf.readBlockPos(),
            buf.readUtf(256),
            buf.readInt(),
            buf.readInt(),
            buf.readInt(),
            buf.readUtf(10)
        );
    }
    
    public void encode(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
        buf.writeUtf(text, 256);
        buf.writeInt(textX);
        buf.writeInt(textY);
        buf.writeInt(textSize);
        buf.writeUtf(colorHex, 10);
    }
    
    public static void handle(UpdateAdvancedSignPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null && player.level instanceof ServerLevel) {
                BlockEntity blockEntity = player.level.getBlockEntity(packet.pos);
                if (blockEntity instanceof AdvancedSignBlockEntity) {
                    AdvancedSignBlockEntity sign = (AdvancedSignBlockEntity) blockEntity;
                    sign.setText(packet.text);
                    sign.setTextX(packet.textX);
                    sign.setTextY(packet.textY);
                    sign.setTextSize(packet.textSize);
                    sign.setColorHex(packet.colorHex);
                    
                    // 标记方块已更改并同步到客户端
                    sign.setChanged();
                    player.level.sendBlockUpdated(packet.pos, sign.getBlockState(), sign.getBlockState(), 3);
                }
            }
        });
        context.setPacketHandled(true);
    }
}