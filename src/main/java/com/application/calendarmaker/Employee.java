package com.application.calendarmaker;

public class Employee {

    private String name;
    //in seconds
    private int timeWork = 0;
    private int restDays = 0;
    private int halfRestDay = 0;



    public Employee(String name) {
        this.name = name;

    }

    public void resetData(){
        this.timeWork = 0;
        this.restDays = 0;
        this.halfRestDay = 0;

    }

    public String getName() {
        return name;
    }


    public int getTimeWork() {
        return timeWork;
    }

    public void addTimeWork(int timeWork) {
        this.timeWork += timeWork;
    }



    public int getRestDays() {
        return restDays;
    }

    public void addRestDays(int restDays) {
        this.restDays += restDays;
    }

    public int getHalfRestDays(){
        return halfRestDay;
    }

    public void addHalfRestDay(int halfRestDay){
        this.halfRestDay += halfRestDay;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
