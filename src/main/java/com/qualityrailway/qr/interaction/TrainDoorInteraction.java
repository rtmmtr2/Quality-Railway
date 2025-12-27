package com.qualityrailway.qr.interaction;

import com.qualityrailway.qr.blocks.doors.TrainDoorBlock;
import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.contraptions.behaviour.SimpleBlockMovingInteraction;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class TrainDoorInteraction extends SimpleBlockMovingInteraction {

    @Override
    protected BlockState handle(Player player, Contraption contraption, BlockPos pos, BlockState currentState) {
        // 检查是否为火车门方块
        if (!(currentState.getBlock() instanceof TrainDoorBlock)) {
            return currentState;
        }

        // 切换门的状态
        boolean newOpenState = !currentState.getValue(TrainDoorBlock.OPEN);
        BlockState newState = currentState.setValue(TrainDoorBlock.OPEN, newOpenState);

        // 播放开关门声音
        playSound(player,
                newOpenState ? SoundEvents.IRON_DOOR_OPEN : SoundEvents.IRON_DOOR_CLOSE,
                1.0f);

        // 返回新的方块状态
        return newState;
    }

    @Override
    protected boolean updateColliders() {
        // 门的状态改变需要更新碰撞箱
        return true;
    }
}