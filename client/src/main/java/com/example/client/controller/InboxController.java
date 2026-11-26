package com.example.client.controller;

import com.example.client.model.Email;
import com.example.client.view.ViewChanger;
import com.example.client.model.DataModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.*;

public class InboxController implements DataModelAwareController {
    private DataModel dataModel;
    private ViewChanger viewChanger;

    @FXML
    private ListView<Email>mailListView;
    @FXML
    private TextArea mailView;

    public void setup(DataModel dataModel, ViewChanger viewChanger) {
        this.dataModel = dataModel;
        this.viewChanger = viewChanger;
        mailListView.setItems(dataModel.getInboxListProperty());
        mailView.textProperty().bind(dataModel.getCurrentMailProperty());
    }

    @FXML
    private void onExitButtonClick() throws IOException {
        viewChanger.setView("login.fxml", "Client - Login", 600, 500);
    }

}