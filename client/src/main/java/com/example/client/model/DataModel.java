package com.example.client.model;

import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {
    private final SimpleListProperty<MailMessage>mailList = new SimpleListProperty<>(this, "mailList", FXCollections.observableArrayList());

    public SimpleListProperty<MailMessage>getInboxListProperty() {
        return mailList;
    }

    public ObservableList<MailMessage>getMailList() {
        return mailList.get();
    }

}