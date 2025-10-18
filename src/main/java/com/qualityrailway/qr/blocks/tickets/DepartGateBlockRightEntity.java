package com.qualityrailway.qr.blocks.tickets;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import com.qualityrailway.qr.ModBlockEntities;

public class DepartGateBlockRightEntity extends BlockEntity {
    private static final String OPEN_TIME_KEY = "OpenTime";
    private static final int CLOSE_DELAY = 40; // 2秒（20 ticks/秒 × 2 = 40 ticks）

    private long openTime = -1;
    private boolean isOpen = false;

    public DepartGateBlockRightEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.DepartGateBlockRightEntity.get(), pos, state);
    }

    // 激活闸机
    public InteractionResult activate(Player player, InteractionHand hand) {
        if (level != null && !level.isClientSide) {
            if (!isOpen) {
                openGate();
                return InteractionResult.CONSUME;
            }
        }
        return InteractionResult.SUCCESS;
    }

    // 开门方法
    private void openGate() {
        this.isOpen = true;
        if (level != null) {
            this.openTime = level.getGameTime();
        }

        // 更新方块状态
        BlockState newState = getBlockState().setValue(DepartGateBlockRight.OPEN, true);
        if (level != null) {
            level.setBlock(worldPosition, newState, 3);
        }

        setChanged();
    }

    // 关门方法
    private void closeGate() {
        this.isOpen = false;
        this.openTime = -1;

        // 更新方块状态
        BlockState newState = getBlockState().setValue(DepartGateBlockRight.OPEN, false);
        if (level != null) {
            level.setBlock(worldPosition, newState, 3);
        }

        setChanged();
    }

    // 每tick更新的逻辑
    public static void tick(Level level, BlockPos pos, BlockState state, DepartGateBlockRightEntity blockEntity) {
        if (level == null || blockEntity == null) return;
        if (!level.isClientSide && blockEntity.isOpen) {
            // 检查是否到了关门时间
            if (level.getGameTime() - blockEntity.openTime >= CLOSE_DELAY) {
                blockEntity.closeGate();
            }
        }
    }

    // 保存数据到NBT
    @Override
    public void saveAdditional(CompoundTag nbt) {
        nbt.putBoolean("IsOpen", isOpen);
        nbt.putLong(OPEN_TIME_KEY, openTime);
        super.saveAdditional(nbt);

    }

    // 从NBT加载数据
    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        isOpen = nbt.getBoolean("IsOpen");
        openTime = nbt.contains(OPEN_TIME_KEY) ? nbt.getLong(OPEN_TIME_KEY) : -1;
    }

    // 获取方块状态
    public BlockState getBlockState() {
        if (getLevel() != null) {
            return getLevel().getBlockState(getBlockPos());
        }
        return null;
    }
}