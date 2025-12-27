package com.qualityrailway.qr.blockentity;

import com.qualityrailway.qr.ModBlockEntities;
import com.qualityrailway.qr.blocks.doors.TrainDoorBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class TrainDoorBlockEntity extends BlockEntity {
    private boolean wasOpen = false;
    private int animationTick = 0;
    private final int ANIMATION_LENGTH = 10;

    public TrainDoorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.TRAIN_DOOR.get(), pos, state);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putBoolean("WasOpen", wasOpen);
        tag.putInt("AnimationTick", animationTick);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        wasOpen = tag.getBoolean("WasOpen");
        animationTick = tag.getInt("AnimationTick");
    }

    public boolean wasOpen() {
        return wasOpen;
    }

    public void setWasOpen(boolean open) {
        this.wasOpen = open;
        setChanged();
    }

    public float getAnimationProgress(float partialTicks) {
        if (animationTick <= 0) {
            return wasOpen ? 1.0f : 0.0f;
        }

        float progress = (float) animationTick / ANIMATION_LENGTH;
        return wasOpen ? progress : 1.0f - progress;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, TrainDoorBlockEntity blockEntity) {
        boolean currentOpen = state.getValue(TrainDoorBlock.OPEN);

        if (currentOpen != blockEntity.wasOpen) {
            blockEntity.wasOpen = currentOpen;
            blockEntity.animationTick = blockEntity.ANIMATION_LENGTH;
            blockEntity.setChanged();
        }

        if (blockEntity.animationTick > 0) {
            blockEntity.animationTick--;
            if (blockEntity.animationTick <= 0) {
                blockEntity.setChanged();
            }
        }
    }
}
