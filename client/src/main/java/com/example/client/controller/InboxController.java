package com.example.client.controller;

import com.example.client.view.ViewChanger;
import com.example.client.model.DataModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.concurrent.*;

public class InboxController implements DataModelAwareController {
    private DataModel dataModel;
    private ViewChanger viewChanger;

    @FXML
    private ListView mailListView;


    @FXML
    private TableView mailTableView;

    @FXML
    private Button exitButton;

    public void setup(DataModel dataModel, ViewChanger viewChanger) {
        this.dataModel = dataModel;
        this.viewChanger = viewChanger;
        /*
            Binding delle properties della inbox nel model con i valori della view
         */
        try(ScheduledExecutorService exec = Executors.newScheduledThreadPool(1)) {
                exec.scheduleAtFixedRate(new Polling(), 3, 3, TimeUnit.SECONDS);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static class Polling implements Runnable {
        @Override
        public void run() {
            //chiamata al server con richiesta della mia inbox
        }
    }

    @FXML
    private void onExitButtonClick() throws IOException {
        viewChanger.setView("login.fxml", "Client - Posta in arrivo", 600, 500);
    }

}