package com.ddl.guava.eventbus;

import com.google.common.eventbus.Subscribe;

/**
 * @author dongdongliu
 * @version 1.0
 * 订阅
 */
public class EventListener {
    public int lastMessage = 0;

    @Subscribe
    public void listen(Event event) {
        lastMessage = event.getMessage();
        System.out.println("Message:" + lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}
