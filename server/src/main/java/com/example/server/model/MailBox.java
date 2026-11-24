package com.example.server.model;

import java.util.ArrayList;
import java.util.List;

public class MailBox {
    private final List<Mail>mails;

    public MailBox() {
        this.mails = new ArrayList<>();
    }

    public void addMail(String messageId, String from, String subject, String body, boolean isRead) {
        mails.add(new Mail(messageId, from, subject, body, isRead));
    }

    public List<Mail>getMails() {
        return new ArrayList<>(mails);
    }
}