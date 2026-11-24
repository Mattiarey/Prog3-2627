package com.example.server.view;

import com.example.server.controller.DataModelAwareController;
import com.example.server.model.DataModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;

public class ViewChanger {
    private final Stage stage;
    private final DataModel dataModel;

    public ViewChanger(Stage stage, DataModel dataModel) {
        this.stage = stage;
        this.dataModel = dataModel;
    }

    public void setView(String fxmlFileName, String title, double width, double height) throws IOException {
        URL fxmlUrl = ViewChanger.class.getResource("/com/example/server/" + fxmlFileName);
        if(fxmlUrl == null) {
            throw new IOException("File FXML non trovato: " + fxmlFileName);
        }

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

        Object controller = loader.getController();
        if(controller instanceof DataModelAwareController awareController) {
            awareController.setup(dataModel, this);
        }

        Scene scene = new Scene(root, width, height);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }
}