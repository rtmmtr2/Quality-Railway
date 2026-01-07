package com.qualityrailway.qr.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.qualityrailway.qr.blocks.tools.AdvancedSignBlock;
import com.qualityrailway.qr.blockentity.AdvancedSignBlockEntity;
import net.minecraft.client.gui.Font;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;

public class AdvancedSignRenderer implements BlockEntityRenderer<AdvancedSignBlockEntity> {
    
    private final Font font;
    
    public AdvancedSignRenderer(BlockEntityRendererProvider.Context context) {
        this.font = context.getFont();
    }
    
    @Override
    public void render(AdvancedSignBlockEntity blockEntity, float partialTick, PoseStack poseStack,
                      MultiBufferSource bufferSource, int packedLight, int packedOverlay) {
        
        String text = blockEntity.getText();
        if (text == null || text.isEmpty()) {
            return;
        }
        
        poseStack.pushPose();
        
        // 根据方块方向调整位置
        Direction facing = blockEntity.getBlockState().getValue(AdvancedSignBlock.FACING);
        
        // 移动到方块中心(60大小)
        poseStack.translate(0.5, 0.5625, 0.5625);
        
        // 根据面向旋转整个坐标系
        float rotation = -facing.toYRot();
        poseStack.mulPose(com.mojang.math.Vector3f.YP.rotationDegrees(rotation));

        poseStack.translate(0.0, 1.0, -0.49); // 向后移动到碰撞箱内部
        
        // 缩放和偏移
        float scale = blockEntity.getTextSize() * 0.0015f;
        
        // 计算偏移（转换为像素坐标）
        float pixelScale = 0.0625f; // 1像素 = 0.0625块
        float offsetX = blockEntity.getTextX() * pixelScale * 0.5f;  // 使用float
        float offsetY = -blockEntity.getTextY() * pixelScale * 0.5f; // Y轴翻转，使用float

        // 将文字旋转到告示牌平面（X轴旋转180度）
        poseStack.mulPose(com.mojang.math.Vector3f.XP.rotationDegrees(180.0f));
        // 应用用户设置的偏移（在旋转后应用）
        poseStack.translate(offsetX / scale, offsetY / scale, 0.0);

        // 应用缩放
        poseStack.scale(scale, scale, 1.0f);

        // 计算文字宽度以居中显示
        int textWidth = this.font.width(text);
        float xOffset = -textWidth / 2.0f;
        
        // 文字颜色
        int color = blockEntity.getColorARGB();
        
        // 调整文字位置（居中并稍微向上调整）
        poseStack.translate(xOffset, 8.0f, 0.0f);

        // 渲染文字
        this.font.drawInBatch(text, 0, 0, color, false,
            poseStack.last().pose(), bufferSource, false, 0, packedLight);
        
        poseStack.popPose();
    }
    
    @Override
    public boolean shouldRenderOffScreen(AdvancedSignBlockEntity blockEntity) {
        return false;
    }
}