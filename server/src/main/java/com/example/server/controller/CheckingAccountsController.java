package com.example.server.controller;

import com.example.server.model.Account;
import com.example.server.model.Mail;
import com.example.server.view.ViewChanger;
import com.example.server.model.DataModel;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class CheckingAccountsController implements DataModelAwareController{
    DataModel dataModel;
    ViewChanger viewChanger;

    @FXML
    private ListView<Account>accountsView;
    @FXML
    private ListView<Mail>inboxView;
    @FXML
    private ListView<String>serverLogView;
    @FXML
    private TextArea mailView;

    public void setup(DataModel dataModel, ViewChanger viewChanger) {
        this.dataModel = dataModel;
        dataModel.InitializeData();
        this.viewChanger = viewChanger;
        mailView.textProperty().bind(dataModel.getCurrentMailProperty());
        accountsView.setItems(dataModel.getAccountsListProperty());
        inboxView.setItems(dataModel.getSelectedAccountProperty());
        serverLogView.setItems(dataModel.getTerminalListProperty());

        serverLogView.setFocusTraversable(true);
        Platform.runLater(() -> serverLogView.requestFocus());

        dataModel.getSelectedAccountProperty().bind(
                Bindings.createObjectBinding(
                        () -> {
                            Account selected = accountsView.getSelectionModel().getSelectedItem();
                            if(selected == null || selected.getMailBox() == null) {
                                return FXCollections.observableArrayList();
                            }
                            return FXCollections.observableArrayList(selected.getMailBox().getMails());
                        },
                        accountsView.getSelectionModel().selectedItemProperty()
                )
        );

        dataModel.getCurrentMailProperty().bind(
                Bindings.createStringBinding(
                        () -> {
                            Mail selectedMail = inboxView.getSelectionModel().getSelectedItem();
                            if(selectedMail == null) {
                                return "";
                            }
                            return selectedMail.getCompleteMail();
                        },
                        inboxView.getSelectionModel().selectedItemProperty()
                )
        );
    }
}