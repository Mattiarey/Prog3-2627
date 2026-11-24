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
        ArrayList<Mail>returnList = new ArrayList<>();
        mails.forEach(mail -> returnList.add(mail));
        return returnList;
    }

    public static class Mail {
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
    }
}