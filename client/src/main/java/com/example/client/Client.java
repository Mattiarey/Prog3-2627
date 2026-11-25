package com.example.client;

import com.example.client.model.DataModel;
import com.example.client.view.ViewChanger;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Client {
    public static void main(String[] args) {
            Application.launch(StartClient.class, args);
    }

    public static class StartClient extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            DataModel dataModel = new DataModel();
            ViewChanger viewChanger = new ViewChanger(stage, dataModel);
            viewChanger.setView("login.fxml", "Client - Login", 600, 500);
        }
    }
}
