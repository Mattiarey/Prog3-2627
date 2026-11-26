package com.example.server.network;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
    private ServerSocket serverSocket;
    private ExecutorService exec;
    private final int port;
    private final int numThread;

    public SocketServer(int numThread, int port) {
        this.numThread = numThread;
        this.port = port;
    }

    public void startSocket() {
        exec = Executors.newFixedThreadPool(numThread);
        try {
            serverSocket = new ServerSocket(port);
            while(true) {
                Socket s = serverSocket.accept();
                Runnable r = new ClientHandler(s);
                exec.execute(r);
            }
        }
        catch(Exception e) {
            System.out.println("Errore nella creazione del ServerSocket");
        }
        finally {
            try {
                serverSocket.close();
                exec.shutdown();
            }
            catch(Exception e){
                System.out.println("Errore nella chiusura del ServerSocket");
            }
        }
    }
}