package com.example.server.controller;

import com.example.server.model.Mail;
import com.example.server.model.MailBox;
import com.example.server.view.ViewChanger;
import com.example.server.model.DataModel;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CheckingAccountsController implements DataModelAwareController{
    DataModel dataModel;
    ViewChanger viewChanger;

    @FXML
    private ListView<MailBox>inboxView;
    @FXML
    private TextArea mailView;
    @FXML
    private ListView<String>serverLogView;

    public void setup(DataModel dataModel, ViewChanger viewChanger) {
        this.dataModel = dataModel;
        this.viewChanger = viewChanger;
        dataModel.InitializeData();
        inboxView.setItems(dataModel.getSelectedInboxProperty());
        mailView.textProperty().bind(dataModel.getCurrentMailProperty());
        serverLogView.setItems(dataModel.getTerminalListProperty());

        serverLogView.setFocusTraversable(true);
        Platform.runLater(() -> serverLogView.requestFocus());

        dataModel.getCurrentMailProperty().bind(
                Bindings.createStringBinding(
                        () -> {
                            MailBox selectedInbox = inboxView.getSelectionModel().getSelectedItem();
                            if(selectedInbox == null) {
                                return "";
                            }
                            return selectedInbox.getMails().toString();
                        },
                        inboxView.getSelectionModel().selectedItemProperty()
                )
        );
    }
}