package com.application.calendarmaker;

import com.google.gson.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
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

    private ArrayList<ListNamesPOJO> saveData;





    @Override
    public void initialize(URL location, ResourceBundle resources) {


        mainData = new MainData();


        employeeslistView.getItems().addAll(mainData.getEmployees());

        saveData = new ArrayList<ListNamesPOJO>();




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
        //reseting the data
        for(Employee employee : employeeslistView.getItems()){
            employee.resetData();
        }

        int minutesToWork = 0, isHourChanger;

        for(int i = 1; i < myPane.getRowCount(); i++){
            for(int j =0; j < myPane.getColumnCount(); j++){
                Node aux = getNodeFromGridPane(j, i);

                if(aux instanceof Label ){
                    isHourChanger = processLabel((Label)aux);
                    if(isHourChanger != 0){
                        minutesToWork = isHourChanger;
                    }

                }else{
                    processListView((ListView<String>) aux, minutesToWork);
                }



            }
        }

        for(Employee employee : employeeslistView.getItems()){
            System.out.println(employee + " WorkedMinutes: " + employee.getTimeWork() + "\n " + "RestDays: " + employee.getRestDays() + "\n " + "HalfRestDays: " + employee.getHalfRestDays());
        }

        initializeTable();


    }

    private void initializeTable(){

        Stage stage = new Stage();

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("dataTable.fxml"));

        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataTableController tableController = fxmlLoader.getController();

        tableController.insertData(employeeslistView.getItems());

        stage.setTitle("");
        stage.setScene(scene);


        stage.show();
    }

    private int processLabel(Label label){

        return switch (label.getText()) {
            case "9:00 a 13:00" -> 240;
            case "13:00 a 18:00", "17:00 a 22:00" -> 300;
            case "17:00 a 21:30" -> 270;
            case "Descanso" -> -1;
            case "Medio Descanso" -> -2;
            default -> 0;
        };




    }

    private void processListView(ListView<String> list, int minutesWork){

        //code for data saving


        //if(!list.getItems().isEmpty()){
            this.saveData.add(new ListNamesPOJO(list.getItems().toArray()));
        //}



        //code for data saving


        for(String name : list.getItems()){
            employeeslistView.getItems().forEach(aux -> {



                if(minutesWork == -1 && aux.getName() == name){
                    aux.addRestDays(1);
                }
                else if(minutesWork == -2 && aux.getName() == name){
                    aux.addHalfRestDay(1);
                }
                else if(aux.getName() == name){
                    aux.addTimeWork(minutesWork);
                }
            });
        }

        System.out.println("ListView");
        //System.out.println(saveData.get(0).getNames());

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

    /**
     * serialising the data and saving it in a JSON
     */
    public void saveData(){
        mainData.saveCalendarData(this.saveData);
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