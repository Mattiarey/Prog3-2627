package com.example.server;

public class Account {
    private final int id;
    private final String email;
    private final String name;

    public Account(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name: " + name + "Email: " + email;
    }
}
