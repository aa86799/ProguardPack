package com.stone.myapplication;

/**
 * author : stone
 * email  : aa86799@163.com
 * time   : 15/4/21 15 50
 *
 * 自定义消息事件类型
 */
public class StoneEvent {
    public static final int EVENT_RECYCLER_BACK_TO_MAIN = 0x10;//从Recycler-acti 按Back键 退到 Main-acti
    public static final int EVENT_CARD_BACK_TO_MAIN = 0x11;//从ard-acti 按Back键 退到 Main-acti

    private int currentEvent;
    private Object data;

    public StoneEvent() {

    }

    public int getCurrentEvent() {
        return currentEvent;
    }

    public StoneEvent setCurrentEvent(int currentEvent) {
        this.currentEvent = currentEvent;
        return this;
    }

    public Object getData() {
        return data;
    }

    public StoneEvent setData(Object data) {
        this.data = data;
        return this;
    }
}
