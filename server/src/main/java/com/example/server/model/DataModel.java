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
import java.util.ArrayList;

public class DataModel {
    private static ArrayList<MailBox>storedMailBox;
    private final ListProperty<MailBox>selectedInboxMails;
    private final SimpleStringProperty currentMailProperty;
    private final ObservableList<String>terminalList;

    public DataModel() {
        storedMailBox = new ArrayList<>();
        selectedInboxMails = new SimpleListProperty<>(FXCollections.observableArrayList());
        currentMailProperty = new SimpleStringProperty();
        terminalList = FXCollections.observableArrayList();
    }

    public ListProperty<MailBox>getSelectedInboxProperty() {
        return selectedInboxMails;
    }

    public StringProperty getCurrentMailProperty() {
        return currentMailProperty;
    }

    public ObservableList<String>getTerminalListProperty() {
        return terminalList;
    }

    public int getMailBoxSize() {
        return storedMailBox.size();
    }

    public void initializeData() {
        loadMailBox();
        displayMailBox(storedMailBox.getFirst());
        addLog("Inizializzazione server - Carico le inbox degli account");
    }

    private void addLog(String message) {
        terminalList.add(message);
    }

    private void displayMailBox(MailBox box) {
        selectedInboxMails.addAll(storedMailBox);
    }

    private void loadMailBox() {
        JSONParser parser = new JSONParser();
        try(FileReader reader = new FileReader("src/main/resources/accounts.json")) {
            JSONArray accountsArray = (JSONArray) parser.parse(reader);

            for(Object obj : accountsArray) {
                JSONObject account = (JSONObject) obj;
                String mailString = (String) account.get("AccountMail");
                String nameString = (String) account.get("AccountName");

                MailBox mailBox = new MailBox(nameString, mailString);
                JSONArray inbox = (JSONArray) account.get("Inbox");
                for(Object box : inbox) {
                    JSONObject mail = (JSONObject) box;
                    String messageId = (String) mail.get("MessageId");
                    String from = (String) mail.get("From");
                    String to = (String) mail.get("To");
                    String subject = (String) mail.get("Subject");
                    String body = (String) mail.get("Body");
                    boolean isRead = (boolean) mail.get("IsRead");
                    mailBox.addMail(messageId, from, to, subject, body, isRead);
                }
                storedMailBox.add(mailBox);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}