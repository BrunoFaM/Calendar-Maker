package com.application.calendarmaker.daos;

public class Employee implements Comparable<Employee> {

    private String name;

    private String timeWorkFormated = "00:00";
    //campo for gestion time
    private int timeWork = 0;
    private int restDays = 0;
    private int halfRestDay = 0;

    public String getTimeWorkFormated() {
        return timeWorkFormated;
    }



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

    private void formatTime(){
        int hours = this.timeWork / 60;
        int minutes = this.timeWork % 60;

        if(hours < 10 && minutes < 10) {
            this.timeWorkFormated = "0" + hours + ":0" + minutes;
        }
        else if(hours < 10 ) {
            this.timeWorkFormated = "0" + hours + ":" + minutes;
        }
        else if(minutes < 10) {
            this.timeWorkFormated = hours + ":0" + minutes;
        }else{
            this.timeWorkFormated = hours + ":" + minutes;
        }


    }


    public int getTimeWork() {
        return this.timeWork;
    }

    public void addTimeWork(int timeWork) {
        this.timeWork += timeWork;
        this.formatTime();
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


    @Override
    public int compareTo(Employee o) {
        return this.name.toLowerCase().compareTo(o.getName().toLowerCase());
    }
}
