package com.qualityrailway.qr.blockentity.signals;

import com.qualityrailway.qr.ModBlockEntities;
import com.qualityrailway.qr.blocks.signals.ChinaHighspeedSignalBlock;
import com.qualityrailway.qr.boundary.ChinaHighspeedSignalBoundary;
import com.qualityrailway.qr.signals.SignalStates;
import com.simibubi.create.Create;
import com.simibubi.create.content.trains.graph.*;
import com.simibubi.create.content.trains.signal.SignalBlockEntity;
import com.simibubi.create.content.trains.signal.SignalBoundary;
import com.simibubi.create.content.trains.signal.SignalBlock.SignalType;
import com.simibubi.create.content.trains.track.TrackTargetingBehaviour;
import com.simibubi.create.content.contraptions.StructureTransform;
import com.simibubi.create.foundation.utility.NBTHelper;
import com.simibubi.create.foundation.utility.Couple;
import com.simibubi.create.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class ChinaHighspeedSignalBlockEntity extends SignalBlockEntity {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChinaHighspeedSignalBlockEntity.class);

    private SignalStates customState = SignalStates.INVALID;
    private SignalBlockEntity.OverlayState customOverlay = SignalBlockEntity.OverlayState.SKIP;
    private String signalModel = "default";
    private CompoundTag migrationData;

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

    @Nullable
    public ChinaHighspeedSignalBoundary getCustomSignal() {
        if (edgePoint == null) return null;
        SignalBoundary boundary = edgePoint.getEdgePoint();
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
    public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
        // 使用标准的 TrackTargetingBehaviour，不覆盖 createEdgePoint
        edgePoint = new TrackTargetingBehaviour<>(this, EdgePointType.SIGNAL);
        behaviours.add(edgePoint);
    }

    @Override
    public void tick() {
        super.tick();
        if (level.isClientSide) {
            return;
        }

        // 确保边缘点是自定义类型
        ensureCustomSignal();

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
        com.qualityrailway.qr.signals.SignalTypes currentType = blockState.getValue(ChinaHighspeedSignalBlock.TYPE);
        com.qualityrailway.qr.signals.SignalTypes targetType = boundary.getCustomTypeFor(worldPosition);
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

    private void ensureCustomSignal() {
        if (edgePoint == null)
            return;

        SignalBoundary signal = edgePoint.getEdgePoint();
        if (signal == null) {
            LOGGER.debug("信号机尚未绑定到轨道，位置: {}", worldPosition);
            return;
        }

        // 如果信号不是自定义类型，转换为自定义类型
        if (!(signal instanceof ChinaHighspeedSignalBoundary)) {
            convertToCustomSignal(signal);
        }
    }

    private void convertToCustomSignal(SignalBoundary original) {
        LOGGER.info("将标准信号转换为高铁信号，位置: {}", worldPosition);

        try {
            // 创建新的自定义信号
            ChinaHighspeedSignalBoundary customSignal = new ChinaHighspeedSignalBoundary();
            customSignal.setId(original.id);

            // 使用反射复制私有字段
            copyField(original, customSignal, "edge");
            copyField(original, customSignal, "position");
            copyField(original, customSignal, "blockEntities");
            copyField(original, customSignal, "groups");
            copyField(original, customSignal, "sidesToUpdate");

            // 处理 types 字段 - 将 SignalType 转换为我们的 SignalTypes
            Couple<SignalType> originalTypes = getField(original, "types");
            if (originalTypes != null) {
                // 直接使用转换后的值，而不是 lambda
                com.qualityrailway.qr.signals.SignalTypes firstType = convertSignalType(originalTypes.getFirst());
                com.qualityrailway.qr.signals.SignalTypes secondType = convertSignalType(originalTypes.getSecond());
                Couple<com.qualityrailway.qr.signals.SignalTypes> customTypes = Couple.create(firstType, secondType);
                setField(customSignal, "types", customTypes);
            }

            // 查找并更新轨道图
            TrackGraph graph = findGraphForSignal(original);
            if (graph != null) {
                // 移除旧的信号点
                graph.removePoint(EdgePointType.SIGNAL, original.id);
                // 添加新的自定义信号点
                graph.addPoint(EdgePointType.SIGNAL, customSignal);

                // 通知方块实体更新
                boolean front = edgePoint.getTargetDirection() == net.minecraft.core.Direction.AxisDirection.POSITIVE;
                customSignal.blockEntityAdded(this, front);

                LOGGER.info("信号转换成功，ID: {}", customSignal.id);
            }
        } catch (Exception e) {
            LOGGER.error("信号转换失败: {}", e.getMessage(), e);
        }
    }

    private <T> void copyField(SignalBoundary source, ChinaHighspeedSignalBoundary target, String fieldName) throws Exception {
        Field field = SignalBoundary.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        Object value = field.get(source);
        field.set(target, value);
    }

    @SuppressWarnings("unchecked")
    private <T> T getField(SignalBoundary obj, String fieldName) throws Exception {
        Field field = SignalBoundary.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return (T) field.get(obj);
    }

    private void setField(ChinaHighspeedSignalBoundary obj, String fieldName, Object value) throws Exception {
        Field field = SignalBoundary.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    private com.qualityrailway.qr.signals.SignalTypes convertSignalType(SignalType signalType) {
        // 将 Create 的 SignalType 转换为我们的 SignalTypes
        // 这里需要根据实际映射关系进行转换
        // 暂时使用默认值
        return com.qualityrailway.qr.signals.SignalTypes.CHINA_HIGHSPEED_SIGNAL;
    }

    @Nullable
    private TrackGraph findGraphForSignal(SignalBoundary signal) {
        if (level == null) return null;

        for (TrackGraph graph : Create.RAILWAYS.sided(level).trackNetworks.values()) {
            if (graph.getPoint(EdgePointType.SIGNAL, signal.id) != null) {
                return graph;
            }
        }
        return null;
    }

    public void setTrackTargetingData(net.minecraft.nbt.CompoundTag data) {
        if (data != null && data.contains("TargetTrack")) {
            LOGGER.info("接收到轨道目标数据");

            // 直接使用传递的CompoundTag作为迁移数据
            this.migrationData = data.copy();

            // 强制创建边缘点
            if (edgePoint != null) {
                try {
                    // 使用反射设置迁移数据
                    java.lang.reflect.Field migrationField = com.simibubi.create.content.trains.track.TrackTargetingBehaviour.class.getDeclaredField("migrationData");
                    migrationField.setAccessible(true);
                    migrationField.set(edgePoint, migrationData);

                    LOGGER.debug("已设置迁移数据，准备创建边缘点");
                } catch (Exception e) {
                    LOGGER.error("设置迁移数据失败: {}", e.getMessage(), e);
                }
            }
        }
    }

    public void initializeSignal() {
        LOGGER.debug("初始化高铁信号机");

        // 如果有迁移数据，立即尝试创建边缘点
        if (migrationData != null && edgePoint != null && level != null && !level.isClientSide) {
            LOGGER.info("尝试立即创建边缘点");

            // 强制调用tick方法（创建边缘点的逻辑在tick中）
            edgePoint.tick();

            // 检查是否成功创建
            if (edgePoint.getEdgePoint() != null) {
                LOGGER.info("边缘点创建成功: {}", edgePoint.getEdgePoint().getId());
            } else {
                LOGGER.warn("边缘点创建失败");
            }
        }
    }

    @Override
    public void transform(StructureTransform transform) {
        if (edgePoint != null) {
            edgePoint.transform(transform);
        }
    }
}