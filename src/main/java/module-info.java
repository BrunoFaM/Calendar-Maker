module com.application.calendarmaker {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.application.calendarmaker to javafx.fxml;
    exports com.application.calendarmaker;
}