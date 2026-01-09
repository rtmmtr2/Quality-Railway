package com.qualityrailway.qr.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class CTCSCLientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // CTCS界面配置
    public static final ForgeConfigSpec.BooleanValue ENABLED;
    public static final ForgeConfigSpec.IntValue GUI_X;
    public static final ForgeConfigSpec.IntValue GUI_Y;
    public static final ForgeConfigSpec.DoubleValue SCALE;
    public static final ForgeConfigSpec.BooleanValue AUTO_SHOW;
    public static final ForgeConfigSpec.DoubleValue NEEDLE_Z_OFFSET;

    static {
        BUILDER.push("CTCS Settings");

        ENABLED = BUILDER
                .comment("Enable CTCS interface")
                .define("enabled", true);

        AUTO_SHOW = BUILDER
                .comment("Automatically show CTCS when driving a train")
                .define("autoShow", true);

        GUI_X = BUILDER
                .comment("X position of CTCS interface (0 = left, positive = right)")
                .defineInRange("guiX", -1, -1, 1000); // -1表示默认右上角

        GUI_Y = BUILDER
                .comment("Y position of CTCS interface (0 = top, positive = down)")
                .defineInRange("guiY", 10, 0, 1000);

        // 减小默认缩放，使界面更合适
        SCALE = BUILDER
                .comment("Scale of CTCS interface (0.3 to 1.5)")
                .defineInRange("scale", 0.3, 0.3, 1.5);

        NEEDLE_Z_OFFSET = BUILDER
                .comment("Z offset for needle rendering (higher = above dial)")
                .defineInRange("needleZOffset", 200.0, 0.0, 1000.0);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}