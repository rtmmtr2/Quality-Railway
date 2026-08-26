package com.qualityrailway.qr.blocks.hxd3c.bogey;

import com.simibubi.create.AllBogeyStyles;
import com.simibubi.create.content.trains.bogey.BogeySizes;
import com.simibubi.create.content.trains.bogey.BogeyStyle;
import com.simibubi.create.foundation.utility.Components;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.resources.ResourceLocation;

import static com.qualityrailway.qr.register.QRRegistrate.REGISTRATE;

public class AllCustomBogeyStyles {

    // ===== 1. 自定义两个尺寸（半径相同，名称不同） =====
    // 仿照官方 LARGE/SMALL，用不同 ID 区分正向/反向
    public static final BogeySizes.BogeySize HXD3C_TOWARD =
            BogeySizes.addSize("qr", "toward", 12.5f / 16f);
    public static final BogeySizes.BogeySize HXD3C_BACKWARD =
            BogeySizes.addSize("qr", "backward", 12.5f / 16f);

    // ===== 2. 创建样式（独立组，不污染原版） =====
    public static final BogeyStyle HXD3C_STYLE =
            AllBogeyStyles.create(
                            new ResourceLocation("qr", "hxd3c"),
                            new ResourceLocation("create", "standard")   // 👈 加入原版组
                    )
                    .displayName(Components.literal("HXD3C 转向架"))
                    // 正向尺寸（注册顺序决定切换顺序）
                    .size(HXD3C_TOWARD,
                            () -> HXD3CBogeyRenderer::new,
                            new ResourceLocation("qr", "hxd3c_bogey")
                    )
                    // 反向尺寸
                    .size(HXD3C_BACKWARD,
                            () -> HXD3CBackwardBogeyRenderer::new,
                            new ResourceLocation("qr", "hxd3c_backward_bogey")
                    )
                    .build();

    // ===== 3. 注册正向方块 =====
    public static final BlockEntry<HXD3CBogeyBlock> HXD3C_BOGEY =
            REGISTRATE.block("hxd3c_bogey",
                            p -> new HXD3CBogeyBlock(p, HXD3C_STYLE, HXD3C_TOWARD, false))
                    .properties(p -> p.noOcclusion().strength(2.0f, 3.0f))
                    .blockstate((c, p) -> {})   // 使用手动 blockstates
                    .item()
                    .build()
                    .register();

    // ===== 4. 注册反向方块 =====
    public static final BlockEntry<HXD3CBogeyBlock> HXD3C_BACKWARD_BOGEY =
            REGISTRATE.block("hxd3c_backward_bogey",
                            p -> new HXD3CBogeyBlock(p, HXD3C_STYLE, HXD3C_BACKWARD, true))
                    .properties(p -> p.noOcclusion().strength(2.0f, 3.0f))
                    .blockstate((c, p) -> {})   // 使用手动 blockstates
                    .item()
                    .build()
                    .register();

    public static void register() {
        System.out.println("[QR] HXD3C 样式已注册: " + HXD3C_STYLE.name);
    }
}