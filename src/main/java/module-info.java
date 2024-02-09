module com.application.calendarmaker {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens com.application.calendarmaker to javafx.fxml;
    exports com.application.calendarmaker;
}