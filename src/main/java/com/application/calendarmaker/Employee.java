package com.application.calendarmaker;

public class Employee {

    private String name;
    //in seconds
    private int timeWork;
    private int restDays;

    public Employee(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public int getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(int timeWork) {
        this.timeWork = timeWork;
    }

    public int getRestDays() {
        return restDays;
    }

    public void setRestDays(int restDays) {
        this.restDays = restDays;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
