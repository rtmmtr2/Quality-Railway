package com.qualityrailway.qr.blocks.tickets;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import com.qualityrailway.qr.blocks.tickets.TurnstileBlock;
import com.qualityrailway.qr.ModBlockEntities;

// 闸机方块实体类，负责处理开门逻辑和状态保存
public class TurnstileBlockEntity extends BlockEntity {
    private static final String OPEN_TIME_KEY = "OpenTime";
    private static final int CLOSE_DELAY = 60; // 3秒（20 ticks/秒 × 3 = 60 ticks）

    private long openTime = -1;
    private boolean isOpen = false;

    public TurnstileBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TurnstileBlockEntity.get(), pos, state);
    }

    // 激活闸机（开门）
    public InteractionResult activate(Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            if (!isOpen) {
                openGate();
                // 消耗一张车票（如果不在创造模式）
                if (!player.isCreative()) {
                    player.getItemInHand(hand).shrink(1);
                }
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.SUCCESS;
    }

    // 开门方法
    private void openGate() {
        this.isOpen = true;
        this.openTime = level.getGameTime();

        // 更新方块状态
        BlockState newState = getBlockState().setValue(TurnstileBlock.OPEN, true);
        level.setBlock(worldPosition, newState, 3);

        setChanged();
    }

    // 关门方法
    private void closeGate() {
        this.isOpen = false;
        this.openTime = -1;

        // 更新方块状态
        BlockState newState = getBlockState().setValue(TurnstileBlock.OPEN, false);
        level.setBlock(worldPosition, newState, 3);

        setChanged();
    }

    // 每tick更新的逻辑
    public static void tick(Level level, BlockPos pos, BlockState state, TurnstileBlockEntity blockEntity) {
        if (!level.isClientSide && blockEntity.isOpen) {
            // 检查是否到了关门时间
            if (level.getGameTime() - blockEntity.openTime >= CLOSE_DELAY) {
                blockEntity.closeGate();
            }
        }
    }

    // 保存数据到NBT
    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("IsOpen", isOpen);
        tag.putLong(OPEN_TIME_KEY, openTime);
    }

    // 从NBT加载数据
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        isOpen = tag.getBoolean("IsOpen");
        openTime = tag.getLong(OPEN_TIME_KEY);
    }

    // 获取方块状态
    public BlockState getBlockState() {
        return getLevel().getBlockState(getBlockPos());
    }
}