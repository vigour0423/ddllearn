package com.ddl.guava.eventbus;

import com.google.common.eventbus.EventBus;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class EventBusChat {

    public static void main(String[] args) {
        EventBus channel = new EventBus();
        ServerSocket socket;
        try {
            socket = new ServerSocket(8088);
            while (true) {
                Socket connection = socket.accept();
                UserThreadListener newUser = new UserThreadListener(connection, channel);
                channel.register(newUser);
                newUser.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}