package com.example.server;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class DataModel {
    private ArrayList<Account> mailAccounts;
    private ObjectMapper objectMapper;
    private JsonNode jsonNode;

    public DataModel() throws IOException {
        mailAccounts = new ArrayList<>();
        objectMapper = new ObjectMapper();

        try (InputStream is = DataModel.class.getResourceAsStream("/accounts.json")) {
            if (is == null) {
                throw new IOException("Risorsa /accounts.json non trovata nel classpath");
            }
            this.jsonNode = objectMapper.readTree(is);
        }
    }

    private void getMailAccounts() {
        if (jsonNode != null && jsonNode.isArray()) {
            for (int i = 0; i < jsonNode.size(); i++) {
                Account account = objectMapper.convertValue(jsonNode.get(i), Account.class);
                mailAccounts.add(account);
            }
        }
    }

    public void setup() {
        getMailAccounts();
        System.out.println("Working dir: " + System.getProperty("user.dir"));
        System.out.println(mailAccounts);
    }
}