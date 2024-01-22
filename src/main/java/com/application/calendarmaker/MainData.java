package com.application.calendarmaker;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Objects;

public class MainData {

    private ArrayList<String> employees;
    private final File dataFile;

    //String []employees = {"Elena", "Franko", "juan", "camila", "richardo", "yo", "dawd", "dawd", "dwadawd", "leo"};
    public MainData()  {
        employees = new ArrayList<>();

        try {
            dataFile = new File(getClass().getResource("/data.txt").toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        chargeEmployees();
    }

    private void chargeEmployees()  {

        try {


            BufferedReader reader = new BufferedReader(new FileReader(dataFile));

            String aux = reader.readLine();

            while(aux != null){
                employees.add(aux);
                aux = reader.readLine();
            }

            reader.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public void saveEmployee(String name){

        try {
            File file = new File(new URI("file:/D:/java-projects/CalendarMaker/src/main/resources/data.txt"));

            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));


            writer.write(name);

            writer.close();


        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<String> getEmployees(){

        return employees;
    }
}
