package com.qualityrailway.qr.items;

import com.qualityrailway.qr.blocks.signals.ChinaHighspeedSignalBlock;
import com.qualityrailway.qr.blockentity.signals.ChinaHighspeedSignalBlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ChinaHighspeedSignalItem extends TrackTargetingBlockItem {

    public ChinaHighspeedSignalItem(Block block, Item.Properties properties) {
        super(block, properties, state -> {
            // 提供一个默认的轨道验证器
            String registryName = state.getBlock().getRegistryName().toString();
            return registryName.contains("create:track");
        });
    }

    @Override
    protected boolean placeBlock(net.minecraft.world.item.context.BlockPlaceContext context, net.minecraft.world.level.block.state.BlockState state) {
        boolean placed = super.placeBlock(context, state);

        if (placed) {
            // 额外的放置后处理
            net.minecraft.world.level.Level level = context.getLevel();
            net.minecraft.core.BlockPos pos = context.getClickedPos();

            if (level.getBlockEntity(pos) instanceof ChinaHighspeedSignalBlockEntity be) {
                // 确保信号机被正确初始化
                be.initializeSignal();
            }
        }

        return placed;
    }
}