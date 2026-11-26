package com.example.server.model;

import java.util.ArrayList;
import java.util.List;

public class MailBox {
    private final String mailBoxName;
    private final String mailBoxEmail;
    private final List<Email>emails;

    public MailBox(String mailBoxName, String mailBoxEmail) {
        this.mailBoxName = mailBoxName;
        this.mailBoxEmail = mailBoxEmail;
        this.emails = new ArrayList<>();
    }

    public void addMail(String messageId, String from, String to, String subject, String body, boolean isRead) {
        emails.add(new Email(messageId, from, to, subject, body, isRead));
    }

    public List<Email>getMails() {
        return new ArrayList<>(emails);
    }

    public String toString() {
        return "Nome: " + mailBoxName + " Mail: " + mailBoxEmail;
    }
}