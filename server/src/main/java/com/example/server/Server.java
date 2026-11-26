package com.example.server;

import com.example.server.model.DataModel;
import com.example.server.view.ViewChanger;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Server {
    public static void main(String[] args) {
        Application.launch(StartServer.class, args);
    }

    public static class StartServer extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            DataModel dataModel = new DataModel();
            ViewChanger viewChanger = new ViewChanger(stage, dataModel);
            viewChanger.setView("checkingAccounts.fxml", "Server - Terminale dei LOG", 600, 500);
        }
    }
}
