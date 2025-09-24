package com.qualityrailway.qr;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class TrainSlidingDoorProperties {

    SoundEvent open = SoundEvents.DISPENSER_FAIL;
    SoundEvent close = SoundEvents.DISPENSER_FAIL;
    float speed = .15f;

    public TrainSlidingDoorProperties(SoundEvent open, SoundEvent close, float speed) {
        this.open = open;
        this.close = close;
        this.speed = speed;
    }

    public SoundEvent GetOpen() {
        return open;
    }

    public SoundEvent GetClose() {
        return close;
    }

    public float GetSpeed() {
        return speed;
    }
}