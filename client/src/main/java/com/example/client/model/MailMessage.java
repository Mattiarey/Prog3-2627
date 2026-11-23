package com.example.client.model;

import javafx.beans.property.SimpleStringProperty;

class MailMessage {
    private final SimpleStringProperty sender = new SimpleStringProperty(this, "sender", "");
    private final SimpleStringProperty subject = new SimpleStringProperty(this, "subject", "");
    private final SimpleStringProperty body = new SimpleStringProperty(this, "body", "");

    public SimpleStringProperty getSender() {
        return sender;
    }

    public SimpleStringProperty getSubject() {
        return subject;
    }

    public SimpleStringProperty getBody() {
        return body;
    }
}