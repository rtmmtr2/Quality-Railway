package com.qualityrailway.qr.blockentity.signals;

import com.qualityrailway.qr.blocks.signals.ChinaHighspeedSignalBlock;
import com.qualityrailway.qr.signals.SignalStates;
import com.qualityrailway.qr.signals.SignalTypes;
import com.qualityrailway.qr.boundary.ChinaHighspeedSignalBoundary;
import com.qualityrailway.qr.boundary.ChinaHighspeedSignalBoundary.OverlayState;
import com.simibubi.create.content.contraptions.StructureTransform;
import com.simibubi.create.content.trains.graph.EdgePointType;
import com.simibubi.create.content.trains.signal.SignalBlockEntity;
import com.simibubi.create.content.trains.signal.SignalBoundary;
import com.simibubi.create.content.trains.track.TrackTargetingBehaviour;
import com.simibubi.create.foundation.utility.NBTHelper;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ChinaHighspeedSignalBlockEntity extends SignalBlockEntity {

    private SignalStates customState;
    private OverlayState customOverlay;
    private String signalModel = "default";

    public ChinaHighspeedSignalBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.customState = SignalStates.INVALID;
        this.customOverlay = OverlayState.SKIP;
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
        customOverlay = NBTHelper.readEnum(tag, "CustomOverlay", OverlayState.class);
        signalModel = tag.getString("SignalModel");
        if (signalModel.isEmpty()) signalModel = "default";
        invalidateRenderBoundingBox();
    }

    public ChinaHighspeedSignalBoundary getCustomSignal() {
        SignalBoundary boundary = super.getSignal();
        return boundary instanceof ChinaHighspeedSignalBoundary ? (ChinaHighspeedSignalBoundary) boundary : null;
    }

    public SignalStates getCustomState() {
        return customState;
    }

    public OverlayState getCustomOverlay() {
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
        // 创建自定义的TrackTargetingBehaviour来返回ChinaHighspeedSignalBoundary
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
        if (level.isClientSide)
            return;

        ChinaHighspeedSignalBoundary boundary = getCustomSignal();
        if (boundary == null) {
            setCustomState(SignalStates.INVALID);
            setCustomOverlay(OverlayState.RENDER);
            return;
        }

        BlockState blockState = getBlockState();

        blockState.getOptionalValue(ChinaHighspeedSignalBlock.POWERED).ifPresent(powered -> {
            boolean lastReportedPower = getReportedPower();
            if (lastReportedPower == powered)
                return;
            boundary.updateBlockEntityPower(this);
            notifyUpdate();
        });

        blockState.getOptionalValue(ChinaHighspeedSignalBlock.TYPE)
                .ifPresent(stateType -> {
                    SignalTypes targetType = boundary.getCustomTypeFor(worldPosition);
                    if (stateType != targetType) {
                        level.setBlock(worldPosition, blockState.setValue(ChinaHighspeedSignalBlock.TYPE, targetType), 3);
                        refreshBlockState();
                    }
                });

        setCustomState(boundary.getCustomStateFor(worldPosition));
        setCustomOverlay(boundary.getCustomOverlayFor(worldPosition));
    }

    public void setCustomState(SignalStates state) {
        if (this.customState == state)
            return;
        this.customState = state;
        notifyUpdate();
    }

    public void setCustomOverlay(OverlayState overlay) {
        if (this.customOverlay == overlay)
            return;
        this.customOverlay = overlay;
        notifyUpdate();
    }

    @Override
    public void transform(StructureTransform transform) {
        edgePoint.transform(transform);
    }
}