package com.application.calendarmaker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ListView<String> listView;

    //list of proff
    private String []employees = {"Elena", "Franko", "juan", "camila", "richardo", "yo", "dawd", "dawd", "dwadawd", "leo"};


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listView.getItems().addAll(employees);


    }

    public void showSelect(MouseEvent event){
        String selected =listView.getSelectionModel().getSelectedItem();
        System.out.println(selected);
    }

}