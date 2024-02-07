package com.application.calendarmaker;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class DataTableController {

    @FXML
    TableView<Employee> myTable;

    @FXML
    TableColumn<Employee, String> nameCol;

    @FXML
    TableColumn<Employee, String> hoursCol;

    @FXML
    TableColumn<Employee, Integer> restCol;

    @FXML
    TableColumn<Employee, Integer> halfRestCol;

    public void insertData(ObservableList<Employee> list){
        myTable.getItems().addAll(list);
//
        //columns with centered items
//        I think that this looks better without center text
//        nameCol.setCellFactory(p -> {
//            TableCell<Employee, String> tc = new TableCell<Employee, String>(){
//                @Override
//                public void updateItem(String item, boolean empty) {
//                    if (item != null){
//                        setText(item);
//                    }
//                }
//            };
//            tc.setAlignment(Pos.CENTER);
//            return tc;
//        });

        hoursCol.setCellFactory(p -> {
            TableCell<Employee, String> tc = new TableCell<Employee, String>(){
                @Override
                public void updateItem(String item, boolean empty) {
                    if (item != null){
                        setText(item);
                    }
                }
            };
            tc.setAlignment(Pos.CENTER);
            return tc;
        });

        restCol.setCellFactory(p -> {
            TableCell<Employee, Integer> tc = new TableCell<Employee, Integer>(){
                @Override
                public void updateItem(Integer item, boolean empty) {
                    if (item != null){
                        setText(item.toString());
                    }
                }
            };
            tc.setAlignment(Pos.CENTER);
            return tc;
        });

        halfRestCol.setCellFactory(p -> {
            TableCell<Employee, Integer> tc = new TableCell<Employee, Integer>(){
                @Override
                public void updateItem(Integer item, boolean empty) {
                    if (item != null){
                        setText(item.toString());
                    }
                }
            };
            tc.setAlignment(Pos.CENTER);
            return tc;
        });

        nameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));

        hoursCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("timeWorkFormated"));
        restCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("restDays"));
        halfRestCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("halfRestDays"));
    }



}
