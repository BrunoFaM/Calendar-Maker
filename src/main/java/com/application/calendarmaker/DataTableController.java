package com.application.calendarmaker;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class DataTableController implements Initializable {

    @FXML
    TableView<Employee> myTable;

    @FXML
    TableColumn<Employee, String> nameCol;

    @FXML
    TableColumn<Employee, Integer> hoursCol;

    @FXML
    TableColumn<Employee, Integer> restCol;

    @FXML
    TableColumn<Employee, Integer> halfRestCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
