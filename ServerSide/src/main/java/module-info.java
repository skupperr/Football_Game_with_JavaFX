module com.example.loginpage {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.j;
    requires javafx.media;


    opens com.example.loginpage to javafx.fxml;
    exports com.example.loginpage;
}