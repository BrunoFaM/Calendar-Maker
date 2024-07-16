package com.application.calendarmaker;

import com.application.calendarmaker.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;


public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        Toolkit myToolkit = Toolkit.getDefaultToolkit();

        Dimension dimension = myToolkit.getScreenSize();

        System.out.println("H:" +  dimension.height);
        System.out.println("W:" +  dimension.width);

        FXMLLoader fxmlLoader;

        if(dimension.width < 1521){
           fxmlLoader = new FXMLLoader(MainApplication.class.getResource("mainHD.fxml"));
        }else{
            fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
        }

        Scene scene = new Scene(fxmlLoader.load());

        stage.setFullScreen(true);
        stage.setTitle("");
        stage.setScene(scene);



        stage.show();
        stage.setOnCloseRequest(event -> {
            event.consume();
            logout(stage, fxmlLoader);
        });
    }
    public void logout(Stage stage, FXMLLoader loader){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cerrarando Aplicacion");
        alert.setHeaderText("Quieres cerrar la aplicacion?");


        if(alert.showAndWait().get() == ButtonType.OK) {
            MainController mc = (MainController)loader.getController();
            mc.saveData();
            stage.close();
        }
    }


    public static void main(String[] args) {
        launch();
    }
}