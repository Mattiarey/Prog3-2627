package com.example.client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField emailField;

    @FXML
    protected void onLoginButtonClick() {
        String email = emailField.getText();

    }
}
