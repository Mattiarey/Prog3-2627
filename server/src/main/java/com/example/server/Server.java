package com.example.server;

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
            DataModel dataModel = new DataModel();
            FXMLLoader fxmlLoader = new FXMLLoader(StartServer.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();
            dataModel.setup();
        }
    }
}
