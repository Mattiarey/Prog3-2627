package com.example.server;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataModel {
    private ArrayList<Account>mailAccounts;

    public DataModel() throws IOException {
        mailAccounts = new ArrayList<>();
    }

    private void getMailAccounts() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("src/main/resources/accounts.json")) {
            JSONArray accountsArray = (JSONArray) parser.parse(reader);

            for (Object obj : accountsArray) {
                JSONObject account = (JSONObject) obj;

                int id = Integer.parseInt(String.valueOf(account.get("AccountId")));
                String name = (String) account.get("AccountName");
                String mail = (String) account.get("AccountMail");

                mailAccounts.add(new Account(id, name, mail));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setup() {
        getMailAccounts();
    }
}