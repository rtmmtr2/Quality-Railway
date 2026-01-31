package com.qualityrailway.qr.blockentity.signals;

import com.qualityrailway.qr.ModBlockEntities;
import com.qualityrailway.qr.blocks.signals.ChinaHighspeedSignalBlock;
import com.qualityrailway.qr.signals.SignalStates;
import com.qualityrailway.qr.signals.SignalTypes;
import com.qualityrailway.qr.boundary.ChinaHighspeedSignalBoundary;
import com.simibubi.create.content.contraptions.StructureTransform;
import com.simibubi.create.content.trains.graph.EdgePointType;
import com.simibubi.create.content.trains.signal.SignalBlockEntity;
import com.simibubi.create.content.trains.signal.SignalBoundary;
import com.simibubi.create.content.trains.track.TrackTargetingBehaviour;
import com.simibubi.create.foundation.utility.NBTHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ChinaHighspeedSignalBlockEntity extends SignalBlockEntity {

    private SignalStates customState = SignalStates.INVALID;
    private SignalBlockEntity.OverlayState customOverlay = SignalBlockEntity.OverlayState.SKIP;
    private String signalModel = "default";

    public ChinaHighspeedSignalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHINA_HIGHSPEED_SIGNAL.get(), pos, state);
    }

    @Override
    protected void write(CompoundTag tag, boolean clientPacket) {
        super.write(tag, clientPacket);
        NBTHelper.writeEnum(tag, "CustomState", customState);
        NBTHelper.writeEnum(tag, "CustomOverlay", customOverlay);
        tag.putString("SignalModel", signalModel);
    }

    @Override
    protected void read(CompoundTag tag, boolean clientPacket) {
        super.read(tag, clientPacket);
        customState = NBTHelper.readEnum(tag, "CustomState", SignalStates.class);
        customOverlay = NBTHelper.readEnum(tag, "CustomOverlay", SignalBlockEntity.OverlayState.class);
        signalModel = tag.getString("SignalModel");
        if (signalModel.isEmpty()) {
            signalModel = "default";
        }
    }

    public ChinaHighspeedSignalBoundary getCustomSignal() {
        SignalBoundary boundary = super.getSignal();
        return boundary instanceof ChinaHighspeedSignalBoundary ? (ChinaHighspeedSignalBoundary) boundary : null;
    }

    public SignalStates getCustomState() {
        return customState;
    }

    public SignalBlockEntity.OverlayState getCustomOverlay() {
        return customOverlay;
    }

    public String getSignalModel() {
        return signalModel;
    }

    public void setSignalModel(String model) {
        this.signalModel = model;
        notifyUpdate();
    }

    @Override
    public void addBehaviours(List<com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour> behaviours) {
        edgePoint = new TrackTargetingBehaviour<SignalBoundary>(this, EdgePointType.SIGNAL) {
            @Override
            public SignalBoundary createEdgePoint() {
                return new ChinaHighspeedSignalBoundary();
            }
        };
        behaviours.add(edgePoint);
    }

    @Override
    public void tick() {
        super.tick();
        if (level.isClientSide) {
            return;
        }

        ChinaHighspeedSignalBoundary boundary = getCustomSignal();
        if (boundary == null) {
            customState = SignalStates.INVALID;
            customOverlay = SignalBlockEntity.OverlayState.RENDER;
            // 更新BlockState到红灯
            ChinaHighspeedSignalBlock.updateLightState(level, worldPosition, customState);
            return;
        }

        BlockState blockState = getBlockState();

        // 检查红石信号变化
        boolean powered = blockState.getValue(ChinaHighspeedSignalBlock.POWERED);
        boolean lastReportedPower = getReportedPower();
        if (lastReportedPower != powered) {
            boundary.updateBlockEntityPower(this);
            notifyUpdate();
        }

        // 检查信号类型是否需要更新
        SignalTypes currentType = blockState.getValue(ChinaHighspeedSignalBlock.TYPE);
        SignalTypes targetType = boundary.getCustomTypeFor(worldPosition);
        if (currentType != targetType) {
            level.setBlock(worldPosition, blockState.setValue(ChinaHighspeedSignalBlock.TYPE, targetType), 3);
        }

        // 获取中国高铁信号状态
        SignalStates newState = boundary.getCustomStateFor(worldPosition);
        if (customState != newState) {
            customState = newState;
            // 更新BlockState中的灯光状态
            ChinaHighspeedSignalBlock.updateLightState(level, worldPosition, customState);
            notifyUpdate();
        }

        // 获取覆盖状态
        SignalBlockEntity.OverlayState overlay = boundary.getOverlayFor(worldPosition);
        if (customOverlay != overlay) {
            customOverlay = overlay;
            notifyUpdate();
        }
    }

    public void setCustomState(SignalStates state) {
        if (this.customState == state) {
            return;
        }
        this.customState = state;
        ChinaHighspeedSignalBlock.updateLightState(level, worldPosition, state);
        notifyUpdate();
    }

    public void setCustomOverlay(SignalBlockEntity.OverlayState overlay) {
        if (this.customOverlay == overlay) {
            return;
        }
        this.customOverlay = overlay;
        notifyUpdate();
    }

    @Override
    public void transform(StructureTransform transform) {
        if (edgePoint != null) {
            edgePoint.transform(transform);
        }
    }
}