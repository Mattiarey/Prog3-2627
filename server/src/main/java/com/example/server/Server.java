package com.example.server;

import javafx.application.Application;

import java.util.ArrayList;

public class Server {
    private ArrayList<Account>mailAccounts;

    public Server() {
        this.mailAccounts = new ArrayList<>();
    }

    public static void main(String[] args) {
        Application.launch(StartServer.class, args);
    }
}
