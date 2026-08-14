package com.qualityrailway.qr.register;

import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class GaugeRegistry {
    private static final Map<ResourceLocation, Double> GAUGE_MAP = new HashMap<>();
    private static double defaultGauge = 0.965; // 原版默认

    /**
     * 注册一个材质的轨距（全轨距，单位：格）
     */
    public static void registerGauge(ResourceLocation materialId, double gauge) {
        GAUGE_MAP.put(materialId, gauge / 2); // 存储半轨距
    }

    /**
     * 获取指定材质的半轨距
     */
    public static double getHalfGauge(ResourceLocation materialId) {
        return GAUGE_MAP.getOrDefault(materialId, defaultGauge);
    }

    /**
     * 设置默认轨距（所有未注册的材质使用此值）
     */
    public static void setDefaultGauge(double gauge) {
        defaultGauge = gauge / 2;
    }
}