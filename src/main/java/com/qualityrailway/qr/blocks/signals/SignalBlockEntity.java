package com.qualityrailway.qr.blocks.signals;
import com.qualityrailway.qr.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

// 信号方块的方块实体类，用于存储状态数据
public class SignalBlockEntity extends BlockEntity {
    // 当前红石信号强度
    private int currentSignalStrength = 0;
    // 上一次触发改变的红石信号强度（用于实现状态保持）
    private int lastTriggerSignal = -1;
    // 当前模型状态
    private int currentModelState = 0;

    public SignalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SIGNAL_BLOCK_ENTITY.get(), pos, state);
    }

    // 更新红石信号强度
    public void updateSignalStrength(int newSignalStrength) {
        // 只有新信号大于等于3时，才更新模型状态
        if (newSignalStrength >= 3) {
            updateModelState(newSignalStrength);
            lastTriggerSignal = newSignalStrength;
        }
        // 如果信号强度发生变化，更新但不一定改变模型
        else if (currentSignalStrength != newSignalStrength) {
            currentSignalStrength = newSignalStrength;
            setChanged();
        }
    }

    // 根据信号强度更新模型状态
    private void updateModelState(int signalStrength) {
        int newModelState = 0;

        // 根据信号强度确定模型状态
        if (signalStrength == 0) {
            newModelState = 0; // green（默认）
        } else if (signalStrength == 3) {
            newModelState = 0; // green
        } else if (signalStrength == 6) {
            newModelState = 1; // green_yellow
        } else if (signalStrength == 9) {
            newModelState = 2; // yellow
        } else if (signalStrength == 12) {
            newModelState = 3; // red_yellow
        } else {
            newModelState = 4; // red
        }

        // 更新模型状态
        if (currentModelState != newModelState) {
            currentModelState = newModelState;
            currentSignalStrength = signalStrength;

            // 更新方块状态
            if (level != null) {
                BlockState state = level.getBlockState(worldPosition);
                level.setBlock(worldPosition, state.setValue(SignalBlock.MODEL_STATE, newModelState)
                        .setValue(SignalBlock.SIGNAL_STRENGTH, signalStrength), 3);
            }

            setChanged();
        }
    }

    // 保存数据到NBT
    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("CurrentSignal", currentSignalStrength);
        tag.putInt("LastTrigger", lastTriggerSignal);
        tag.putInt("ModelState", currentModelState);
    }

    // 从NBT加载数据
    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        currentSignalStrength = tag.getInt("CurrentSignal");
        lastTriggerSignal = tag.getInt("LastTrigger");
        currentModelState = tag.getInt("ModelState");
    }

    // 获取客户端更新数据包
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        tag.putInt("ModelState", currentModelState);
        return tag;
    }

    // 处理客户端更新数据
    @Override
    public void handleUpdateTag(CompoundTag tag) {
        super.handleUpdateTag(tag);
        currentModelState = tag.getInt("ModelState");
    }
}