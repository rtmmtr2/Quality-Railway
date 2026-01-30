package com.qualityrailway.qr.signals;

import net.minecraft.util.StringRepresentable;

public enum SignalStates implements StringRepresentable {
    RED("red"),                   // 红灯 - 停止
    YELLOW("yellow"),             // 黄灯 - 注意/减速
    GREEN_YELLOW("green_yellow"), // 绿黄灯 - 预告减速
    GREEN("green"),               // 绿灯 - 进行
    WHITE("white"),               // 白灯 - 引导/故障
    YELLOW_YELLOW("yellow_yellow"), // 双黄灯 - 引导/侧线
    INVALID("invalid");           // 无效

    private final String name;

    SignalStates(String name) {
        this.name = name;
    }

    @Override
    public String getSerializedName() {
        return name;
    }

    public boolean isRedLight(float renderTime) {
        return this == RED || this == INVALID && renderTime % 40 < 3;
    }

    public boolean isYellowLight(float renderTime) {
        return this == YELLOW || this == GREEN_YELLOW || this == YELLOW_YELLOW;
    }

    public boolean isGreenLight(float renderTime) {
        return this == GREEN || this == GREEN_YELLOW;
    }

    public boolean isGreenYellowLight(float renderTime) {
        return this == GREEN_YELLOW;
    }

    public boolean isYellowYellowLight(float renderTime) {
        return this == YELLOW_YELLOW;
    }

    public boolean isWhiteLight(float renderTime) {
        return this == WHITE;
    }
}