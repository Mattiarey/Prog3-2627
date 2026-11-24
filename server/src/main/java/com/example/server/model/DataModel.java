package com.example.server.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataModel {
    private ArrayList<Account>storedAccounts;
    private ObservableList<Account>accountsList;

    public DataModel() throws IOException {
        storedAccounts = new ArrayList<>();
        accountsList = FXCollections.observableArrayList();
    }

    public ObservableList<Account>getAccountsList() {
        return accountsList;
    }

    public void getMailAccounts() {
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader("src/main/resources/accounts.json")) {
            JSONArray accountsArray = (JSONArray) parser.parse(reader);

            for(Object obj : accountsArray) {
                JSONObject account = (JSONObject) obj;
                String nameString = (String) account.get("AccountName");
                String mailString = (String) account.get("AccountMail");

                MailBox mailBox = new MailBox();
                JSONArray inbox = (JSONArray) account.get("Inbox");
                for(Object box : inbox) {
                    JSONObject mail = (JSONObject) box;
                    String messageId = (String) mail.get("MessageId");
                    String from = (String) mail.get("From");
                    String subject = (String) mail.get("Subject");
                    String body = (String) mail.get("Body");
                    boolean isRead = (boolean) mail.get("IsRead");
                    mailBox.addMail(messageId, from, subject, body, isRead);
                }
                storedAccounts.add(new Account(nameString, mailString, mailBox));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayMailAccounts() {
        accountsList.addAll(storedAccounts);
    }
}