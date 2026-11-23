package com.example.client;

import com.example.client.model.DataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class LoginController implements DataModelAwareController{
    private DataModel dataModel;
    private ViewChanger viewChanger;
    private static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    public void setup(DataModel dataModel, ViewChanger viewChanger) {
        this.dataModel = dataModel;
        this.viewChanger = viewChanger;
    }

    @FXML
    private TextField emailField;

    @FXML
    private Button loginButton;

    @FXML
    private Label feedbackLabel;

    @FXML
    private void onLoginButtonClick() throws IOException {
        Matcher matcher = EMAIL_REGEX.matcher(emailField.textProperty().getValue());
        if(matcher.matches()) {
            viewChanger.setView("inbox.fxml", "Client - Login", 600, 500);
        }
        else {
            feedbackLabel.setText("Invalid email!");
        }
    }
}
