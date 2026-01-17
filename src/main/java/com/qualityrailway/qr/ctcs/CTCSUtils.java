package com.qualityrailway.qr.ctcs;

import com.simibubi.create.content.contraptions.actors.trainControls.ControlsHandler;
import com.simibubi.create.content.trains.entity.Carriage;
import com.simibubi.create.content.trains.entity.CarriageContraptionEntity;
import com.simibubi.create.content.trains.entity.Train;
import net.minecraft.util.Mth;

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
     * 0km/h = -145度
     * 80km/h = 0度
     * 160km/h = 165度
     * 线性关系：角度 = (速度 - 80) * (155/80)
     *
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
}