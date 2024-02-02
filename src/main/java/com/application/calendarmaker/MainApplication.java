package com.application.calendarmaker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

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

        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
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