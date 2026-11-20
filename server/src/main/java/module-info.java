module com.example.server {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens com.example.server to javafx.fxml;
    exports com.example.server;
}