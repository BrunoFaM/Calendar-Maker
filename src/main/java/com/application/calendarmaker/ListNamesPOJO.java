package com.application.calendarmaker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListNamesPOJO {
    private ArrayList<String> names = new ArrayList<>();



    public ListNamesPOJO() {

    }

    public ListNamesPOJO(Object []names) {

        for(Object object : names){
            this.names.add((String)object);
        }
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
    }

    public List<String> namesList(){
        List<String> aux = names;
        return aux;
    }

    @Override
    public String toString() {
        return "ListNamesPOJO{" +
                "names=" + names.toString() +
                '}';
    }
}
