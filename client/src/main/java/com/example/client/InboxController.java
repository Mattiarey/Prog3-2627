package com.example.client;

import com.example.client.model.DataModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.concurrent.*;

public class InboxController implements DataModelAwareController{
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

        /* POOL DI PROCESSI PER IL POLLING DI RICHIESTA
        ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
        ScheduledFuture<String>pollingInbox = exec.schedule(new Polling(), 5, TimeUnit.SECONDS);
        */
    }
    
    public static class Polling implements Callable<String> {
        @Override
        public String call() throws Exception {
            //chiamata al server con richiesta della mia inbox
            return "";
        }
    }

    @FXML
    private void onExitButtonClick() throws IOException {
        viewChanger.setView("login.fxml", "Client - Posta in arrivo", 600, 500);
    }

}