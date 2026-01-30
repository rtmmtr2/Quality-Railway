package com.qualityrailway.qr.signals;

import net.minecraft.util.StringRepresentable;

public enum SignalTypes implements StringRepresentable {
    ENTRY_SIGNAL("entry_signal"),           // 进站信号机
    CROSS_SIGNAL("cross_signal"),           // 通过信号机
    AUTOMATIC_SIGNAL("automatic_signal"),   // 自动信号机
    AUTOMATIC_SPEED_SIGNAL("automatic_speed_signal"), // 自动通过信号机
    CHINA_HIGHSPEED_SIGNAL("china_highspeed_signal"); // 中国高铁信号机

    private final String name;

    SignalTypes(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public static SignalTypes getDefault() {
        return ENTRY_SIGNAL;
    }

    public boolean isAutomaticSignal() {
        return this == AUTOMATIC_SIGNAL || this == AUTOMATIC_SPEED_SIGNAL || this == CHINA_HIGHSPEED_SIGNAL;
    }
}