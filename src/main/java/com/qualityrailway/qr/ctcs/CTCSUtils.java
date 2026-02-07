package com.qualityrailway.qr.ctcs;

import com.simibubi.create.content.contraptions.actors.trainControls.ControlsHandler;
import com.simibubi.create.content.trains.entity.Carriage;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.Train;
import com.simibubi.create.content.trains.signal.SignalBoundary;
import com.simibubi.create.content.trains.graph.EdgePointType;
import com.simibubi.create.content.trains.entity.TravellingPoint;
import com.simibubi.create.content.trains.signal.SignalBlockEntity;
import net.minecraft.util.Mth;
import org.apache.commons.lang3.mutable.MutableDouble;
import org.apache.commons.lang3.mutable.MutableObject;
import com.qualityrailway.qr.qr;

import javax.annotation.Nullable;

public class CTCSUtils {

    /**
     * 获取当前玩家驾驶的火车的速度（km/h）
     */
    public static float getTrainSpeedKmh() {
        if (!isPlayerDrivingTrain()) {
            return 0.0f;
        }

        CarriageContraptionEntity cce = (CarriageContraptionEntity) ControlsHandler.getContraption();
        if (cce == null) {
            return 0.0f;
        }

        Carriage carriage = cce.getCarriage();
        if (carriage == null) {
            return 0.0f;
        }

        Train train = carriage.train;
        if (train == null) {
            return 0.0f;
        }

        // Create的速度单位：方块/刻 → 转换为km/h
        // 1方块/刻 = 20方块/秒 = 72 km/h (因为1方块=1米，1米/秒=3.6km/h)
        double speedBlocksPerTick = train.speed;
        float speedKmh = (float) Math.abs(speedBlocksPerTick) * 20 * 3.6f;

        return speedKmh;
    }

    /**
     * 根据速度计算指针旋转角度（度）
     *
     * 0km/h = -155度
     * 160km/h = 155度
     * 线性关系：角度 = (速度 - 80) * (155/80)
     *
     */
    public static float calculateNeedleRotation(float speedKmh) {
        // 新的线性关系：从(-155, 0)到(155, 160)
        // 公式推导：
        // 已知两点：(0, -155) 和 (160, 155)
        // 斜率 k = (155 - (-155)) / (160 - 0) = 310 / 160 = 1.9375
        // 角度 = -155 + (速度 * 310/160) = -155 + (速度 * 1.9375)

        // 限制速度范围
        float clampedSpeed = Mth.clamp(speedKmh, 0, 160);

        // 计算基础角度
        float rotation = -155.0f + (clampedSpeed * 310.0f / 160.0f);

        // 修正指针偏转：需要顺时针修正7度
        rotation += 7.0f;

        return rotation;
    }

    /**
     * 检查玩家是否正在驾驶火车
     */
    public static boolean isPlayerDrivingTrain() {
        return ControlsHandler.getContraption() instanceof CarriageContraptionEntity;
    }

    /**
     * 获取指针旋转角度（弧度），用于渲染
     */
    public static float getNeedleRotationRadians() {
        float speedKmh = getTrainSpeedKmh();
        float rotationDegrees = calculateNeedleRotation(speedKmh);
        return rotationDegrees * (float)(Math.PI / 180.0);
    }

    /**
     * 获取列车前方下一个信号机的状态和距离
     * @return 包含信号状态和距离的数组，[0]为状态，[1]为距离（米）
     */
    @Nullable
    public static Object[] getNextSignalAhead() {
        qr.LOGGER.info("CTCSUtils: 开始搜索前方信号机...");

        if (!isPlayerDrivingTrain()) {
            qr.LOGGER.info("CTCSUtils: 玩家不在驾驶火车");
            return null;
        }

        CarriageContraptionEntity cce = (CarriageContraptionEntity) ControlsHandler.getContraption();
        if (cce == null) {
            qr.LOGGER.info("CTCSUtils: 未找到CarriageContraptionEntity");
            return null;
        }

        Carriage carriage = cce.getCarriage();
        if (carriage == null) {
            qr.LOGGER.info("CTCSUtils: 未找到Carriage");
            return null;
        }

        Train train = carriage.train;
        if (train == null || train.graph == null) {
            qr.LOGGER.info("CTCSUtils: 列车或列车图为空");
            return null;
        }

        // 确定前进方向：speed>0正向，speed<0反向
        boolean forward = train.speed >= 0;
        qr.LOGGER.info("CTCSUtils: 列车方向=" + (forward ? "正向" : "反向") + ", 速度=" + train.speed);

        // 获取列车前端的travelling point
        TravellingPoint frontPoint = forward ?
            carriage.getLeadingPoint() :
            carriage.getTrailingPoint();

        if (frontPoint.edge == null) {
            qr.LOGGER.info("CTCSUtils: 前端的edge为空");
            return null;
        }

        // 创建搜索用的临时点
        TravellingPoint searchPoint = new TravellingPoint(
            frontPoint.node1, frontPoint.node2, frontPoint.edge,
            frontPoint.position, frontPoint.upsideDown
        );

        qr.LOGGER.info("CTCSUtils: 搜索起点 - node1=" + frontPoint.node1 + ", node2=" + frontPoint.node2 +
                      ", edge=" + frontPoint.edge + ", position=" + frontPoint.position);

        // 存储找到的信号机信息
        MutableObject<SignalBlockEntity.SignalState> foundSignal = new MutableObject<>(null);
        MutableDouble signalDistance = new MutableDouble(0.0);

        // 搜索前方信号机（最大1000米）
        double maxSearchDistance = 1000.0; // 最大搜索距离

        qr.LOGGER.info("CTCSUtils: 开始搜索，最大距离=" + maxSearchDistance);

        searchPoint.travel(train.graph, maxSearchDistance, searchPoint.random(), (distance, couple) -> {
            if (couple.getFirst() instanceof SignalBoundary signal) {
                // 确定接近方向
                boolean isPrimary = signal.isPrimary(searchPoint.node1);

                // 获取信号状态
                SignalBlockEntity.SignalState state = signal.cachedStates.get(isPrimary);

                // 检查是否强制红信号
                if (signal.isForcedRed(isPrimary)) {
                    state = SignalBlockEntity.SignalState.RED;
                }

                foundSignal.setValue(state);
                signalDistance.setValue(distance);

                qr.LOGGER.info("CTCSUtils: 找到信号机！状态=" + state +
                              ", 距离=" + distance +
                              ", isPrimary=" + isPrimary +
                              ", 信号机ID=" + signal.getId());
                return true; // 找到第一个信号后停止搜索
            }
            return false;
        });

        if (foundSignal.getValue() == null) {
            qr.LOGGER.info("CTCSUtils: 未找到前方信号机");
            return null;
        }

        qr.LOGGER.info("CTCSUtils: 搜索完成，返回状态=" + foundSignal.getValue() + ", 距离=" + signalDistance.getValue());
        return new Object[]{foundSignal.getValue(), signalDistance.getValue()};
    }

    /**
     * 更新CTCS界面的信号状态
     */
    public static void updateCTCSSignalState() {
        qr.LOGGER.info("CTCSUtils: 开始更新信号状态...");

        Object[] signalInfo = getNextSignalAhead();
        if (signalInfo == null) {
            qr.LOGGER.info("CTCSUtils: 无信号信息，清除状态");
            CTCSOverlay.getInstance().clearSignalState();
            return;
        }

        SignalBlockEntity.SignalState createState = (SignalBlockEntity.SignalState) signalInfo[0];
        double distance = (double) signalInfo[1];

        // 转换为自定义的信号状态
        SignalState customState = SignalState.fromCreateSignal(createState);

        qr.LOGGER.info("CTCSUtils: 转换信号状态 - 原始=" + createState + ", 转换后=" + customState + ", 距离=" + distance);
        CTCSOverlay.getInstance().updateSignalState(customState, distance);
    }
}