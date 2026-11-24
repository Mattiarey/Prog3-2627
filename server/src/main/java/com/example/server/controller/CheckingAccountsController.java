package com.example.server.controller;

import com.example.server.view.ViewChanger;
import com.example.server.model.DataModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;

public class CheckingAccountsController implements DataModelAwareController{
    private DataModel dataModel;
    private ViewChanger viewChanger;

    @FXML
    private ListView accountsView;

    @FXML
    private TableView mailTableView;

    @FXML
    private Button exitButton;

    @FXML
    private void onExitButtonClick() throws IOException {

    }

    public void setup(DataModel dataModel, ViewChanger viewChanger) {
        this.dataModel = dataModel;
        this.viewChanger = viewChanger;
        accountsView.setItems(dataModel.getAccountsList());
        dataModel.getMailAccounts();
        dataModel.displayMailAccounts();;
    }
}