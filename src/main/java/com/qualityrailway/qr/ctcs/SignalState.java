package com.qualityrailway.qr.ctcs;

/**
 * CTCS信号状态枚举
 */
public enum SignalState {
    GREEN,      // 绿灯
    YELLOW,     // 黄灯
    REDYELLOW,  // 红黄灯
    NONE;       // 无信号
    
    /**
     * 从Create的信号状态转换为CTCS信号状态
     */
    public static SignalState fromCreateSignal(com.simibubi.create.content.trains.signal.SignalBlockEntity.SignalState createState) {
        if (createState == null) {
            return NONE;
        }
        
        switch (createState) {
            case GREEN:
                return GREEN;
            case YELLOW:
                return YELLOW;
            case RED:
                return REDYELLOW;
            case INVALID:
            default:
                return NONE;
        }
    }
}