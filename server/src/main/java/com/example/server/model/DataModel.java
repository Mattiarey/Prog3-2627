package com.example.server.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    private SimpleStringProperty currentMailProperty;
    private ObservableList<Account>accountsList;
    private ListProperty<Mail>selectedAccountMails;
    private ObservableList<String>terminalList;

    public DataModel() throws IOException {
        storedAccounts = new ArrayList<>();
        currentMailProperty = new SimpleStringProperty();
        accountsList = FXCollections.observableArrayList();
        selectedAccountMails = new SimpleListProperty<>(FXCollections.observableArrayList());
        terminalList = FXCollections.observableArrayList();
    }

    public ListProperty<Mail>getSelectedAccountProperty() {
        return selectedAccountMails;
    }

    public StringProperty getCurrentMailProperty() {
        return currentMailProperty;
    }

    public ObservableList<Account>getAccountsListProperty() {
        return accountsList;
    }

    public ObservableList<String>getTerminalListProperty() {
        return terminalList;
    }

    public void InitializeData() {
        chargeAccounts();
        displayMailBox(storedAccounts.getFirst());
        addLog("Inizializzazione server - Carico le inbox degli account");
    }

    private void addLog(String message) {
        terminalList.add(message);
    }

    private void displayMailBox(Account account) {
        selectedAccountMails.addAll(account.getMailBox().getMails());
    }

    private void chargeAccounts() {
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader("src/main/resources/accounts.json")) {
            JSONArray accountsArray = (JSONArray) parser.parse(reader);

            for(Object obj : accountsArray) {
                JSONObject account = (JSONObject) obj;
                String mailString = (String) account.get("AccountMail");
                String nameString = (String) account.get("AccountName");

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
                storedAccounts.add(new Account(mailString, nameString, mailBox));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        accountsList.addAll(storedAccounts);
    }
}