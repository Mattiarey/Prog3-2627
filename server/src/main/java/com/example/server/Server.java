package com.example.server;

import com.example.server.controller.LogController;
import com.example.server.model.DataModel;
import com.example.server.network.SocketServer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Server {
    public static void main(String[] args) {
        Application.launch(StartServer.class, args);
    }

    public static class StartServer extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(Server.class.getResource("checkingAccounts.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            stage.setTitle("Server - Console di LOG");
            stage.setScene(scene);
            DataModel dataModel = new DataModel();
            dataModel.initializeData();
            LogController controller = fxmlLoader.getController();
            controller.setup(dataModel);
            stage.show();
            SocketServer welcomeSocket = new SocketServer((dataModel.getMailBoxSize() * 2), 9000);
            new Thread(welcomeSocket::startSocket, "welcomeSocket").start();
        }
    }
}
