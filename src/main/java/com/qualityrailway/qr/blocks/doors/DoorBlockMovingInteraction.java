package com.qualityrailway.qr.blocks.doors;

import com.simibubi.create.content.contraptions.Contraption;
import com.simibubi.create.content.contraptions.behaviour.SimpleBlockMovingInteraction;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

public class DoorBlockMovingInteraction extends SimpleBlockMovingInteraction {

    @Override
    protected BlockState handle(Player player, Contraption contraption, BlockPos pos, BlockState currentState) {
        if (!(currentState.getBlock() instanceof DoorBlock))
            return currentState;

        SoundEvent sound = currentState.getValue(DoorBlock.OPEN) ? SoundEvents.WOODEN_DOOR_CLOSE : SoundEvents.WOODEN_DOOR_OPEN;

        BlockPos otherPos = currentState.getValue(DoorBlock.HALF) == DoubleBlockHalf.LOWER ? pos.above() : pos.below();
        StructureTemplate.StructureBlockInfo info = contraption.getBlocks().get(otherPos);
        if (info != null && info.state.hasProperty(DoorBlock.OPEN)) {
            BlockState newState = info.state.cycle(DoorBlock.OPEN);
            setContraptionBlockData(contraption.entity, otherPos, new StructureTemplate.StructureBlockInfo(info.pos, newState, info.nbt));
        }

        currentState = currentState.cycle(DoorBlock.OPEN);

        if (player != null && sound != null) {
            float pitch = player.level.random.nextFloat() * 0.1F + 0.9F;
            playSound(player, sound, pitch);
        }

        return currentState;
    }

    @Override
    protected boolean updateColliders() {
        return true;
    }
}
