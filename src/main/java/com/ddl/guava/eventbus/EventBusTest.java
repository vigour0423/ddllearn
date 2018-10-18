package com.ddl.guava.eventbus;

import com.google.common.eventbus.EventBus;
import org.junit.Test;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class EventBusTest {


    public static void main(String[] args) {
        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);
        eventBus.post(new Event(200));
        eventBus.post(new Event(300));
        eventBus.post(new Event(400));

        System.out.println("LastMessage:" + listener.getLastMessage());

    }

    @Test
    public void testDeadEventListeners() throws Exception {
        //如果没有消息订阅者监听消息， EventBus将发送DeadEvent消息，这时我们可以通过log的方式来记录这种状态。
        EventBus eventBus = new EventBus("test");
        DeadEventListener deadEventListener = new DeadEventListener();

        eventBus.register(deadEventListener);
        eventBus.post(new Event(200));
        eventBus.post(new Event(300));

        System.out.println("deadEvent:" + deadEventListener.isNotDelivered());

    }

}
