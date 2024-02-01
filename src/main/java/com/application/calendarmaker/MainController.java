package com.application.calendarmaker;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ListView<Employee> employeeslistView;

    @FXML
    private TextField inputTextField;

    @FXML
    private Button saveButton;

    private MainData mainData;

    @FXML
    private GridPane myPane;





    @Override
    public void initialize(URL location, ResourceBundle resources) {


        mainData = new MainData();


        employeeslistView.getItems().addAll(mainData.getEmployees());




    }

    //insert a value en cell list view


    public void showSelect(MouseEvent event){
        Employee selected =employeeslistView.getSelectionModel().getSelectedItem();
        System.out.println(selected);


    }

    //test method
    public void addEmployee(){

        String name = inputTextField.getText();

        if(!name.isEmpty()){

            Employee aux = new Employee(name);

            inputTextField.setText("");
            mainData.saveEmployee(aux);
            employeeslistView.getItems().add(aux);
        }

    }

    public void removeEmployee(){
        Employee aux = employeeslistView.getSelectionModel().getSelectedItem();
        if(aux != null){
            mainData.deleteEmployee(aux);
            //
            employeeslistView.getItems().setAll(mainData.getEmployees());
        }
    }


    //adjust
    public void add(MouseEvent e){
        Label aux = (Label) e.getSource();
        aux.setText(employeeslistView.getSelectionModel().getSelectedItem().getName());
        //myLabel.setText(listView.getSelectionModel().getSelectedItem());
    }

    //myPane related events

    public void insertCellList(MouseEvent event){
        //if is leftClick add element otherwise delete


        if(event.getButton() == MouseButton.PRIMARY) {

            ListView<String> auxList= (ListView<String>) event.getSource();

            String selectedEmployee = employeeslistView.getSelectionModel().getSelectedItem().getName();
            if (!selectedEmployee.isBlank()) {
                auxList.getItems().add(selectedEmployee);
            }
        }else if(event.getButton() == MouseButton.SECONDARY){


            ListView<String> auxList= (ListView<String>) event.getSource();
            //creating a new contextMenu for this listView (only the first time that is called)
            if(auxList.getContextMenu() == null) {


                MenuItem menuItem = new MenuItem("Eliminar");


                menuItem.setOnAction(new EventHandler<ActionEvent>() {
                    final ListView<String> list = auxList;

                    @Override
                    public void handle(ActionEvent event) {
                        list.getItems().remove(list.getSelectionModel().getSelectedIndex());
                    }
                });

                ContextMenu contextMenu = new ContextMenu(menuItem);

                auxList.setContextMenu(contextMenu);


            }
            //System.out.println(auxList.getContextMenu());
            //ContextMenu myContextMenu = new ContextMenu()

        }
    }


    public void analizeCalender(){

//        for(int i = 0; i < myPane.getRowCount(); i++){
//            for(int j =0; j < myPane.getColumnCount(); j++){
//                Node aux = getNodeFromGridPane(j, i);
//                System.out.println(aux);
//            }
//        }


        Node aux = getNodeFromGridPane(4,4);

        System.out.println(aux);


    }

    private Node getNodeFromGridPane(int col, int row) {
        int columnIndex, rowIndex;
        for (Node node : myPane.getChildren()) {

            if(GridPane.getColumnIndex(node) == null) {
                columnIndex = 0;
            }else{
                columnIndex= GridPane.getColumnIndex(node);
            }
            if(GridPane.getRowIndex(node) == null){
                rowIndex = 0;
            }else{
                rowIndex = GridPane.getRowIndex(node);
            }
            if (columnIndex == col && rowIndex == row) {
                return node;
            }
        }
        return null;
    }



    //something wrong with this method
//    public void deleteOfCell(ActionEvent event){
//        MenuItem aux = (MenuItem) event.getSource();
//
//        ContextMenu contextAux = aux.getParentPopup();
//
//        Node listAux = contextAux.getOwnerNode();
//
//
//
//
//
//        System.out.println(listAux);
////        Employee aux = employeeslistView.getSelectionModel().getSelectedItem();
////        if(aux != null){
////            mainData.deleteEmployee(aux);
////            //
////            employeeslistView.getItems().setAll(mainData.getEmployees());
////        }
//    }



}