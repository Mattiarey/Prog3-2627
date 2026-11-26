package com.example.server.network;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream inStream = socket.getInputStream();
            OutputStream outStream = socket.getOutputStream();

            Scanner in = new Scanner(inStream);
            PrintWriter out = new PrintWriter(outStream, true);

            //Logica di ricezione Email
        }
        catch(Exception e) {

        }
        finally {
        }
    }
}
