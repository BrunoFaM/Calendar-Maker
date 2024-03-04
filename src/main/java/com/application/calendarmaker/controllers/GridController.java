package com.application.calendarmaker.controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.GridPane;

public class GridController {


    private GridPane myPane;


    public GridController(GridPane myPane) {
        this.myPane = myPane;

        System.out.println(myPane.getColumnCount());
        System.out.println(myPane.getRowCount());
    }
}
