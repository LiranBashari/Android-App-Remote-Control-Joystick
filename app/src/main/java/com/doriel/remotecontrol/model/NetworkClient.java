package com.doriel.remotecontrol.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkClient {
    private final ExecutorService singleThreadPool;
    private Socket socket;
    private PrintWriter out;

    public NetworkClient() {
        this.singleThreadPool = Executors.newSingleThreadExecutor();
    }

    public void connect(String ipAddress, int port) {
        singleThreadPool.submit(() ->
                doConnect(ipAddress, port)
        );
    }

    public void sendCommand(String command) {
        singleThreadPool.submit(() ->
                doSendCommand(command)
        );
    }

    private boolean doConnect(String ipAddress, int port) {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            socket = new Socket(ipAddress, port);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private boolean doSendCommand(String command) {
        if (socket == null || !socket.isConnected()) {
            return false;
        }
        out.print(command + "\r\n");
        out.flush();
        return true;
    }
}
