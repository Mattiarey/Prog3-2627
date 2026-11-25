package com.example.client.controller;

import com.example.client.model.Mail;
import com.example.client.view.ViewChanger;
import com.example.client.model.DataModel;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.*;

public class InboxController implements DataModelAwareController {
    private DataModel dataModel;
    private ViewChanger viewChanger;

    @FXML
    private ListView<Mail>mailListView;
    @FXML
    private TextArea mailView;

    public void setup(DataModel dataModel, ViewChanger viewChanger) {
        this.dataModel = dataModel;
        this.viewChanger = viewChanger;
        mailListView.setItems(dataModel.getInboxListProperty());
        mailView.textProperty().bind(dataModel.getCurrentMailProperty());
        try {
            ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
            exec.scheduleAtFixedRate(new Polling(), 3, 3, TimeUnit.SECONDS);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public class Polling implements Runnable {
        @Override
        public void run() {
            ArrayList<Mail>fakeMails = new ArrayList<>();
            fakeMails.add(new Mail("1", "mario", "prova", "prova messaggio", false));
            dataModel.updateMails(fakeMails);
        }
    }

    @FXML
    private void onExitButtonClick() throws IOException {
        viewChanger.setView("login.fxml", "Client - Login", 600, 500);
    }

}