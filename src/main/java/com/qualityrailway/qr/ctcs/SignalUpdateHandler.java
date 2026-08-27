package com.qualityrailway.qr.ctcs;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import com.qualityrailway.qr.qr;

@Mod.EventBusSubscriber
public class SignalUpdateHandler {
    private static int tickCounter = 0;
    
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) {
            return;
        }
        
        tickCounter++;
        
        // 每20个tick更新一次信号状态（约1秒）
        if (tickCounter % 20 == 0) {
            qr.LOGGER.info("SignalUpdateHandler: 定时更新信号状态, tickCounter=" + tickCounter);

            // 只有在驾驶火车时才更新
            if (CTCSUtils.isPlayerDrivingTrain()) {
                qr.LOGGER.info("SignalUpdateHandler: 玩家正在驾驶火车，更新信号状态");
                CTCSUtils.updateCTCSSignalState();
            } else {
                // 不在驾驶火车时清除信号状态
                qr.LOGGER.info("SignalUpdateHandler: 玩家不在驾驶火车，清除信号状态");
                CTCSOverlay.getInstance().clearSignalState();
            }
        }
    }
}