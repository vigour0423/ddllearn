package com.ddl.guava.eventbus;

/**
 * @author dongdongliu
 * @version 1.0
 * 事件
 */
public class Event {
    private final int message;

    public Event(int message) {
        this.message = message;
        System.out.println("event message:" + message);
    }

    public int getMessage() {
        return message;
    }
}