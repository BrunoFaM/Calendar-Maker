package com.application.calendarmaker;

import com.google.gson.Gson;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

public class MainData {

    //private ArrayList<String> employees;
    private ArrayList<Employee> employees;
    private final File dataFile;

    private final File jsonFile;
    private String jsonCalendarData;

    //String []employees = {"Elena", "Franko", "juan", "camila", "richardo", "yo", "dawd", "dawd", "dwadawd", "leo"};
    public MainData()  {
        employees = new ArrayList<>();

        try {
            dataFile = new File(getClass().getResource("/data.txt").toURI());
            jsonFile = new File(getClass().getResource("/dataCalender.json").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        chargeEmployees();
        chargeJsonCalenderData();
    }



    private void chargeJsonCalenderData(){

        try{

            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));

            this.jsonCalendarData = reader.readLine();


            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void chargeEmployees()  {

        try {


            BufferedReader reader = new BufferedReader(new FileReader(dataFile));

            String aux = reader.readLine();

            while(aux != null){
                employees.add(new Employee(aux));
                aux = reader.readLine();
            }

            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void deleteEmployee(Employee employee){
        employees.remove(employee);


        try {
            File file = new File(new URI("file:/D:/java-projects/CalendarMaker/src/main/resources/data.txt"));

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            //cleaning the document
            if(employees.size() == 0){
                writer.write("");
                writer.flush();
            }else{
                writer.write(employees.get(0).getName());
                writer.flush();
                rewriteDocument();
            }
            writer.close();

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }





    }

    public void rewriteDocument(){


        try{
            File file = new File(new URI("file:/D:/java-projects/CalendarMaker/src/main/resources/data.txt"));

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));

            for(int i = 1; i < employees.size(); i++){
                writer.newLine();

                writer.write(employees.get(i).getName());
            }
            writer.flush();
            writer.close();
        }catch (URISyntaxException | IOException e){
            throw new RuntimeException(e);
        }
    }

    public void saveEmployee(Employee employee){

        try {
            File file = new File(new URI("file:/D:/java-projects/CalendarMaker/src/main/resources/data.txt"));

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));



            writer.newLine();

            writer.write(employee.getName());

            writer.flush();

            writer.close();


        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Employee> getEmployees(){

        return employees;
    }

    //methods for saving the calendar data
    public void saveCalendarData(ArrayList<ListNamesPOJO> saveData){

        Gson gson = new Gson();

        String jsonData = gson.toJson(saveData);

        //String jsonData = gson.toJson(this.saveData.get(0));
        //System.out.println(jsonData);

        try {

            File jsonFile = new File(new URI("file:/D:/java-projects/CalendarMaker/src/main/resources/dataCalender.json"));

            BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));

            writer.write(jsonData);

            writer.flush();

            writer.close();




        } catch (URISyntaxException | IOException e) {
                throw new RuntimeException(e);
        }

    }

    public ListNamesPOJO[] getLoadCalender(){
        Gson gson = new Gson();

        ListNamesPOJO []data = gson.fromJson(this.jsonCalendarData, ListNamesPOJO[].class);
        System.out.println("I'm here, deserialization");



        return data;
    }
}
