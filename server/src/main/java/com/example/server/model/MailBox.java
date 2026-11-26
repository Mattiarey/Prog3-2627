package com.example.server.model;

import java.util.ArrayList;
import java.util.List;

public class MailBox {
    private final String mailBoxName;
    private final String mailBoxEmail;
    private final List<Mail>mails;

    public MailBox(String mailBoxName, String mailBoxEmail) {
        this.mailBoxName = mailBoxName;
        this.mailBoxEmail = mailBoxEmail;
        this.mails = new ArrayList<>();
    }

    public void addMail(String messageId, String from, String subject, String body, boolean isRead) {
        mails.add(new Mail(messageId, from, subject, body, isRead));
    }

    public List<Mail>getMails() {
        return new ArrayList<>(mails);
    }

    public String toString() {
        return "Nome: " + mailBoxName + " Mail: " + mailBoxEmail;
    }
}