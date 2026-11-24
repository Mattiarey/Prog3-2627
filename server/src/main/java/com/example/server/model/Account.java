package com.example.server.model;

public class Account {
    private final String email;
    private final String name;
    private final MailBox mailBox;

    public Account(String email, String name, MailBox mailBox) {
        this.email = email;
        this.name = name;
        this.mailBox = mailBox;
    }

    @Override
    public String toString() {
        return "Name: " + name + "Email: " + email;
    }
}
