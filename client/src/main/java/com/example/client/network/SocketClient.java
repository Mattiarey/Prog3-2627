package com.example.client.network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class SocketClient {
    private Socket clientSocket;
    private final int port;

    public SocketClient(int port) {
        this.port = port;
    }

    public void startSocket() {
        try {
            clientSocket = new Socket(InetAddress.getLocalHost().getHostName(), port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
