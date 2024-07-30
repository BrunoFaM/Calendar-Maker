package com.application.calendarmaker.data;

import com.application.calendarmaker.MainApplication;
import com.application.calendarmaker.daos.Employee;
import com.application.calendarmaker.daos.ListNamesPOJO;
import com.google.gson.Gson;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Set;

public class MainData {

    //private ArrayList<String> employees;
    private ArrayList<Employee> employees;
    private final File dataFile;

    private final File jsonFile ;
    private String jsonCalendarData;

    //String []employees = {"Elena", "Franko", "juan", "camila", "richardo", "yo", "dawd", "dawd", "dwadawd", "leo"};
    public MainData()  {
        employees = new ArrayList<>();
        //this 2 work only with teh file in my pc
        //this 2 work         new URI ("file:/D:/java-projects/src/main/resources/data.txt")
        //                    new URI ("file:/D:/java-projects/src/main/resources/dataCalender.json")

        String userDir = System.getProperty("user.dir");

        /*
        the path to resorces isn't the same in development that when packgage the app, y need the two different routes
        */

        System.out.println(userDir);

        String c = "\\";

        //packaged routes to resources
        String dataPath = "file:/" + userDir.replace("\\", "/") + "/resources/data.txt";
        String dataCalenderPath = "file:/" + userDir.replace("\\", "/") + "/resources/" +"dataCalender.json";

        //development routes
        String dataPath2 = "file:/D:/java-projects/CalendarMaker/src/main/resources/data.txt";
        String dataCalenderPath2 = "file:/D:/java-projects/CalendarMaker/src/main/resources/dataCalender.json";


        System.out.println("my data path: " + dataPath);
        System.out.println("my dataCalender path: " + dataCalenderPath);


        try {
            dataFile = new File(new URI(dataPath));
            jsonFile = new File(new URI(dataCalenderPath));
        } catch (URISyntaxException e) {
            System.out.println("The error is in the URI");
            throw new RuntimeException(e);
        }



        System.out.println("Absolute file: " + dataFile.getAbsolutePath());
        System.out.println("Absolute path: " + jsonFile.getAbsoluteFile());


        chargeEmployees();
        chargeJsonCalenderData();
    }



    private void chargeJsonCalenderData(){

        try{

            BufferedReader reader = new BufferedReader(new FileReader(jsonFile));


            this.jsonCalendarData = reader.readLine();


            reader.close();

        } catch (IOException e) {
            System.out.println("file empty!!!");
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
            employees.sort(Employee::compareTo);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void deleteEmployee(Employee employee){
        employees.remove(employee);


        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));

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

        } catch (IOException e) {
            throw new RuntimeException(e);
        }





    }

    public void rewriteDocument(){


        try{

            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile, true));

            for(int i = 1; i < employees.size(); i++){
                writer.newLine();

                writer.write(employees.get(i).getName());
            }
            writer.flush();
            writer.close();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void saveEmployee(Employee employee){

        try {
            //File file = new File(new URI("file:/D:/java-projects/CalendarMaker/src/main/resources/data.txt"));

            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile, true));



            writer.newLine();

            writer.write(employee.getName());

            writer.flush();

            writer.close();


        } catch (IOException e) {
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

            //File jsonFile = new File(new URI("file:/D:/java-projects/CalendarMaker/src/main/resources/dataCalender.json"));

            BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));

            writer.write(jsonData);

            writer.flush();

            writer.close();




        } catch (IOException e) {
                throw new RuntimeException(e);
        }

    }

    public ListNamesPOJO[] getLoadCalender(){
        Gson gson = new Gson();

        ListNamesPOJO []data;

        if(this.jsonCalendarData != null){
            data = gson.fromJson(this.jsonCalendarData, ListNamesPOJO[].class);
        }else{
            data = new ListNamesPOJO[0];
        }


        System.out.println("I'm here, deserialization");



        return data;
    }
}
