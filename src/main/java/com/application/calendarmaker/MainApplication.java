package com.application.calendarmaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;


public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        //test writer
        /*
        String userDir = System.getProperty("user.dir");

        URL aux = getClass().getResource("/data.txt");

        URI u;

        try {
            u = aux.toURI();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        int i = 100;

        File file = null;
        try {
            file = new File(new URI("file:/D:/java-projects/CalendarMaker/src/main/resources/data.txt"));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }


        FileWriter writer = new FileWriter(file);

        writer.write("hola como estas?");
*/
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
    }

    public static void main(String[] args) {
        launch();
    }
}