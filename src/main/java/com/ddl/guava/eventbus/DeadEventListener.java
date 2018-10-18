package com.ddl.guava.eventbus;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class DeadEventListener {
    boolean notDelivered = false;

    public int lastMessage = 0;

    @Subscribe
    public void listen(DeadEvent event) {

        notDelivered = true;
    }

   /* @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:" + lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }*/

    public boolean isNotDelivered() {
        return notDelivered;
    }
}
