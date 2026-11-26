package com.example.server.controller;

import com.example.server.model.MailBox;
import com.example.server.model.DataModel;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LogController {
    DataModel dataModel;

    @FXML
    private ListView<MailBox>inboxView;
    @FXML
    private TextArea mailView;
    @FXML
    private ListView<String>serverLogView;

    public void setup(DataModel dataModel) {
        this.dataModel = dataModel;
        inboxView.setItems(dataModel.getSelectedInboxProperty());
        mailView.textProperty().bind(dataModel.getCurrentMailProperty());
        serverLogView.setItems(dataModel.getTerminalListProperty());

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