package com.application.calendarmaker.controllers;

import com.application.calendarmaker.*;
import com.application.calendarmaker.daos.Employee;
import com.application.calendarmaker.daos.ListNamesPOJO;
import com.application.calendarmaker.data.CalenderImageHandler;
import com.application.calendarmaker.data.MainData;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;

import java.net.URL;
import java.util.*;

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


    private void chargeGridPane(){

        int k = 0;
        for(int i = 1; i < myPane.getRowCount(); i++){
            for(int j =0; j < myPane.getColumnCount(); j++){
                Node aux = getNodeFromGridPane(j, i);

                if(!(aux instanceof Label || aux instanceof VBox)) {
                    //
                    ArrayList<String> list =  saveData.get(k).getNames();


                    if(list.size() != 0){
                        ListView<String> listView = (ListView<String>) aux;
//                        listView.getItems().setAll(list);
                        ObservableList<String> observableList = FXCollections.observableList(list);
                        listView.setItems(observableList);
                        System.out.println("Adding:" + list + "to Node: [" + i + "][" + j + "]");
                    }else{
                        System.out.println(list);
                    }

                    k++;

                }

            }
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        mainData = new MainData();

        ObservableList<Employee> content = FXCollections.observableList(mainData.getEmployees());
        employeeslistView.setItems(content);


        saveData = new ArrayList<ListNamesPOJO>();

        ListNamesPOJO[] loadedData = mainData.getLoadCalender();

        if(loadedData.length != 0){
            Collections.addAll(saveData, loadedData);
            chargeGridPane();
        }


        for(ListNamesPOJO list : saveData){
            System.out.println(list.getNames());
        }




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
            ObservableList<Employee> observableList = FXCollections.observableList(mainData.getEmployees());
            employeeslistView.setItems(observableList);

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
        this.saveData.clear();

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

                }
                else if (aux instanceof VBox) {
                    minutesToWork = -2;
                }
                else if(aux instanceof ListView<?>){
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
            case "09:00 a 13:00" -> 240;
            case "13:00 a 18:00", "17:00 a 22:00" -> 300;
            case "17:00 a 21:30" -> 270;
            case "Descanso" -> -1;
            default -> 0;
        };




    }

    private void processListView(ListView<String> list, int minutesWork){

        //code for data saving
        //solved
        //error here, i want to save the new data but if i do this, the data saved is more short, i lost the cells that are not listView
        this.saveData.add(new ListNamesPOJO(list.getItems().toArray()));




        //code for data saving


        for(String name : list.getItems()){

            employeeslistView.getItems().forEach(aux -> {



                if(minutesWork == -1 && Objects.equals(aux.getName(), name)){
                    aux.addRestDays(1);
                    System.out.println("Adding rest day " + aux.getName());
                }
                else if(minutesWork == -2 && Objects.equals(aux.getName(), name)){
                    aux.addHalfRestDay(1);
                    System.out.println("Adding half rest to " + aux.getName());
                }
                else if(Objects.equals(aux.getName(), name)){
                    aux.addTimeWork(minutesWork);
                    System.out.println("Adding work time to " + aux.getName());
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

    //methods for dowload the calender

    public void downloadCalender(){
        System.out.println("here init the calender processing for download");
        CalenderImageHandler calenderImageHandler = new CalenderImageHandler();

        myPane.snapshot(calenderImageHandler, null, null);


    }


}