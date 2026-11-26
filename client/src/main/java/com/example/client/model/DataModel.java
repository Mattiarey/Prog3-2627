package com.example.client.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DataModel {
    private final ObservableList<Email> emailList;
    private SimpleStringProperty currentMailProperty;

    public DataModel() {
        this.emailList = FXCollections.observableArrayList();
        this.currentMailProperty = new SimpleStringProperty();
    }

    public ObservableList<Email>getInboxListProperty() {
        return emailList;
    }

    public SimpleStringProperty getCurrentMailProperty() {
        return currentMailProperty;
    }

    public void updateMails(ArrayList<Email> emailList) {
        getInboxListProperty().addAll(emailList);
    }


}