package com.example.client.model;

import java.util.ArrayList;

public class Email {
    private String messageId;
    private String from;
    private ArrayList<Account>to;
    private String subject;
    private String body;
    private boolean isRead;

    public Email(String messageId, String from, String subject, String body, boolean isRead) {
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