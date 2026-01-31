package com.qualityrailway.qr.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.qualityrailway.qr.items.TrackTargetingBlockItem;
import com.simibubi.create.content.trains.track.TrackTargetingBehaviour;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@OnlyIn(Dist.CLIENT)
public class TrackTargetingRenderer {
    private static final Logger LOGGER = LogManager.getLogger(TrackTargetingRenderer.class);

    @SubscribeEvent
    public static void onRenderLevel(RenderLevelStageEvent event) {
        if (event.getStage() != RenderLevelStageEvent.Stage.AFTER_TRANSLUCENT_BLOCKS) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;

        if (player == null || mc.level == null || mc.hitResult == null) {
            return;
        }

        // 检查手持物品
        ItemStack mainHand = player.getMainHandItem();
        ItemStack offHand = player.getOffhandItem();

        // 处理主手物品
        if (mainHand.getItem() instanceof TrackTargetingBlockItem) {
            renderTrackTargeting(event, mainHand, mc, player);
            return;
        }

        // 处理副手物品
        if (offHand.getItem() instanceof TrackTargetingBlockItem) {
            renderTrackTargeting(event, offHand, mc, player);
            return;
        }
    }

    private static void renderTrackTargeting(RenderLevelStageEvent event, ItemStack stack, Minecraft mc, Player player) {
        PoseStack poseStack = event.getPoseStack();

        // 保存当前的PoseStack状态
        poseStack.pushPose();

        try {
            // 获取物品中的轨道目标数据
            CompoundTag tag = stack.getTag();
            if (tag != null && tag.contains(TrackTargetingBlockItem.TRACK_TARGETING_DATA)) {
                // 渲染已选择的轨道目标
                renderSelectedTarget(event, stack, mc);
            } else {
                // 渲染轨道目标预览
                renderTargetingPreview(event, stack, mc, player);
            }
        } finally {
            poseStack.popPose();
        }
    }

    private static void renderSelectedTarget(RenderLevelStageEvent event, ItemStack stack, Minecraft mc) {
        CompoundTag tag = stack.getTag();
        if (tag == null) return;

        CompoundTag targetingTag = tag.getCompound(TrackTargetingBlockItem.TRACK_TARGETING_DATA);
        if (!targetingTag.contains("TargetTrack")) {
            return;
        }

        BlockPos trackPos = NbtUtils.readBlockPos(targetingTag.getCompound("TargetTrack"));
        net.minecraft.core.Direction.AxisDirection direction = targetingTag.getBoolean("TargetDirection")
            ? net.minecraft.core.Direction.AxisDirection.POSITIVE
            : net.minecraft.core.Direction.AxisDirection.NEGATIVE;

        // 渲染轨道目标箭头
        TrackTargetingBehaviour.RenderedTrackOverlayType overlayType =
            TrackTargetingBehaviour.RenderedTrackOverlayType.SIGNAL;

        TrackTargetingBehaviour.render(
            mc.level,
            trackPos,
            direction,
            null, // 贝塞尔曲线信息
            event.getPoseStack(),
            mc.renderBuffers().bufferSource(),
            mc.getEntityRenderDispatcher().getPackedLightCoords(mc.player, event.getPartialTick()),
            0,
            overlayType,
            1.0f
        );
    }

    private static void renderTargetingPreview(RenderLevelStageEvent event, ItemStack stack, Minecraft mc, Player player) {
        // 检查鼠标指向
        HitResult hitResult = mc.hitResult;
        if (!(hitResult instanceof BlockHitResult blockHit)) {
            return;
        }

        BlockPos pos = blockHit.getBlockPos();
        net.minecraft.world.level.block.state.BlockState state = mc.level.getBlockState(pos);

        // 检查是否是轨道
        if (!isCreateTrack(state)) {
            return;
        }

        // 计算预览方向
        net.minecraft.world.phys.Vec3 hitLoc = blockHit.getLocation();
        net.minecraft.world.phys.Vec3 relativeHit = hitLoc.subtract(net.minecraft.world.phys.Vec3.atCenterOf(pos));
        net.minecraft.core.Direction.AxisDirection axisDirection = calculatePreviewDirection(relativeHit, blockHit.getDirection());

        // 渲染预览箭头
        TrackTargetingBehaviour.RenderedTrackOverlayType overlayType =
            TrackTargetingBehaviour.RenderedTrackOverlayType.SIGNAL;

        TrackTargetingBehaviour.render(
            mc.level,
            pos,
            axisDirection,
            null,
            event.getPoseStack(),
            mc.renderBuffers().bufferSource(),
            mc.getEntityRenderDispatcher().getPackedLightCoords(player, event.getPartialTick()),
            0,
            overlayType,
            0.7f // 半透明预览
        );
    }

    private static net.minecraft.core.Direction.AxisDirection calculatePreviewDirection(
            net.minecraft.world.phys.Vec3 relativeHit, net.minecraft.core.Direction face) {
        // 简化计算
        if (face.getAxis() == net.minecraft.core.Direction.Axis.X) {
            return relativeHit.z > 0 ? net.minecraft.core.Direction.AxisDirection.POSITIVE : net.minecraft.core.Direction.AxisDirection.NEGATIVE;
        } else if (face.getAxis() == net.minecraft.core.Direction.Axis.Z) {
            return relativeHit.x > 0 ? net.minecraft.core.Direction.AxisDirection.POSITIVE : net.minecraft.core.Direction.AxisDirection.NEGATIVE;
        }
        return net.minecraft.core.Direction.AxisDirection.POSITIVE;
    }

    private static boolean isCreateTrack(net.minecraft.world.level.block.state.BlockState state) {
        String registryName = state.getBlock().getRegistryName().toString();
        return registryName.contains("create:track");
    }
}