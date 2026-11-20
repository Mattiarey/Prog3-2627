package com.example.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Client {
    public static void main(String[] args) {
            Application.launch(StartClient.class, args);
    }

    public static class StartClient extends Application {
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(StartClient.class.getResource("login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            stage.setTitle("Client");
            stage.setScene(scene);
            stage.show();
        }
    }
}
