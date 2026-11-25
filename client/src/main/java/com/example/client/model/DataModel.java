package com.example.client.model;

import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.swing.text.html.ListView;
import java.util.ArrayList;

public class DataModel {
    private final ObservableList<Mail>mailList;
    private SimpleStringProperty currentMailProperty;

    public DataModel() {
        this.mailList = FXCollections.observableArrayList();
        this.currentMailProperty = new SimpleStringProperty();
    }

    public ObservableList<Mail>getInboxListProperty() {
        return mailList;
    }

    public SimpleStringProperty getCurrentMailProperty() {
        return currentMailProperty;
    }

    public void updateMails(ArrayList<Mail>mailList) {
        getInboxListProperty().addAll(mailList);
    }


}