module com.application.calendarmaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.application.calendarmaker to javafx.fxml, com.google.gson;
    exports com.application.calendarmaker;
}