package com.example.server.model;

import javafx.beans.property.ReadOnlyIntegerProperty;

public class Mail {
    private String messageId;
    private String from;
    private String subject;
    private String body;
    private boolean isRead;

    public Mail(String messageId, String from, String subject, String body, boolean isRead) {
        this.messageId = messageId;
        this.from = from;
        this.subject = subject;
        this.body = body;
        this.isRead = isRead;
    }

    @Override
    public String toString() {
        return subject + " | " + from + "\n";
    }

    public String getCompleteMail() {
        return toString() + "Body: \n" + body;
    }

}