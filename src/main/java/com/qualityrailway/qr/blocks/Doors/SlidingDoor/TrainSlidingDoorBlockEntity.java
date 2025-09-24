package com.qualityrailway.qr.blocks.Doors.SlidingDoor;

import com.qualityrailway.qr.TrainSlidingDoorProperties;
import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorBlockEntity;
import com.simibubi.create.foundation.utility.animation.LerpedFloat;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.sounds.SoundEvent;

public class TrainSlidingDoorBlockEntity extends SlidingDoorBlockEntity {

    public LerpedFloat animation;
    private final TrainSlidingDoorProperties properties;

    public TrainSlidingDoorBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        animation = LerpedFloat.linear()
                .startWithValue(isOpen(state) ? 1 : 0);
        // Initialize with default properties
        properties = new TrainSlidingDoorProperties(SoundEvents.DISPENSER_FAIL, SoundEvents.DISPENSER_FAIL, .15f);
    }

    @Override
    public void tick() {
        boolean open = isOpen(getBlockState());
        boolean wasSettled = animation.settled();
        animation.chase(open ? 1 : 0, .15f, LerpedFloat.Chaser.LINEAR);
        animation.tickChaser();

        if (!open && !wasSettled && animation.settled() && !isVisible(getBlockState()))
            showBlockModel();

        super.tick();
    }

    public static SoundEvent getDoorSoundValue(BlockState state) {
        // Assuming you have a way to get the properties from the block state
        // For now, we use the default properties
        TrainSlidingDoorProperties properties = new TrainSlidingDoorProperties(SoundEvents.DISPENSER_FAIL, SoundEvents.DISPENSER_FAIL, .15f);
        return state.getValue(TrainSlidingDoorBlock.DOOR_SOUND) == 0 ? properties.GetOpen() : properties.GetClose();
    }

    @Override
    protected boolean shouldRenderSpecial(BlockState state) {
        return super.shouldRenderSpecial(state);
    }
}