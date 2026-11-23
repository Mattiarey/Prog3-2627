package com.example.client;

import com.example.client.model.DataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController implements DataModelAwareController{
    private DataModel dataModel;
    private ViewChanger viewChanger;

    public void setup(DataModel dataModel, ViewChanger viewChanger) {
        this.dataModel = dataModel;
        this.viewChanger = viewChanger;
    }

    @FXML
    private Label welcomeText;
    @FXML
    private TextField emailField;

    @FXML
    private void onLoginButtonClick() throws IOException {
        viewChanger.setView("inbox.fxml", "Client - Login", 600, 500);
    }
}
