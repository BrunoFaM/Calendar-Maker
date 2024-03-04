module com.application.calendarmaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires javafx.swing;


    opens com.application.calendarmaker to javafx.fxml, com.google.gson;
    exports com.application.calendarmaker;
    exports com.application.calendarmaker.controllers;
    opens com.application.calendarmaker.controllers to com.google.gson, javafx.fxml;
    exports com.application.calendarmaker.daos;
    opens com.application.calendarmaker.daos to com.google.gson, javafx.fxml;
    exports com.application.calendarmaker.data;
    opens com.application.calendarmaker.data to com.google.gson, javafx.fxml;
}