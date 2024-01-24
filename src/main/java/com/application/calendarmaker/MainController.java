package com.application.calendarmaker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ListView<String> listView;


    @FXML
    private TextField inputTextField;

    @FXML
    private Button saveButton;

    private MainData mainData;



    @Override
    public void initialize(URL location, ResourceBundle resources) {


        mainData = new MainData();


        listView.getItems().addAll(mainData.getEmployees());


    }

    public void showSelect(MouseEvent event){
        String selected =listView.getSelectionModel().getSelectedItem();
        System.out.println(selected);


    }

    //test method
    public void addEmployee(){

        String name = inputTextField.getText();

        if(!name.isEmpty()){
            inputTextField.setText("");
            mainData.saveEmployee(name);
            listView.getItems().add(name);
        }

    }

    public void removeEmployee(){
        String name = listView.getSelectionModel().getSelectedItem();
        if(!name.isEmpty()){
            mainData.deleteEmployee(name);
            //
            listView.getItems().setAll(mainData.getEmployees());
        }
    }


    //adjust
    public void add(MouseEvent e){
        Label aux = (Label) e.getSource();
        aux.setText(listView.getSelectionModel().getSelectedItem());
        //myLabel.setText(listView.getSelectionModel().getSelectedItem());
    }



}