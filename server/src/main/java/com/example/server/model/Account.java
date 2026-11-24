package com.example.server.model;

public class Account {
    private final String mail;
    private final String name;
    private final MailBox mailBox;

    public Account(String mail, String name, MailBox mailBox) {
        this.mail = mail;
        this.name = name;
        this.mailBox = mailBox;
    }

    public MailBox getMailBox() {
        return mailBox;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Email: " + mail;
    }
}
