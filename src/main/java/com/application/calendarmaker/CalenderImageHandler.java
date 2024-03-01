package com.application.calendarmaker;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotResult;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Callback;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalenderImageHandler implements Callback<SnapshotResult, Void> {

    @Override
    public Void call(SnapshotResult param) {

        WritableImage image = param.getImage();

        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);


        try {
            //adjust and call the fileChooser
            //File imageFile = new File(new URI("file:/D:/java-projects/CalendarMaker/src/main/resources/image.png"));

            FileChooser fileChooser = new FileChooser();

            Calendar calendar = Calendar.getInstance();

            int month = calendar.get(Calendar.MONTH) + 1;

            String fec = "" + calendar.get(Calendar.DAY_OF_MONTH) + "-" + month + "-" + calendar.get(Calendar.YEAR);

            fileChooser.setInitialFileName("horario_" + fec);

            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image", "*.png"));

            File imageFile = fileChooser.showSaveDialog(Window.getWindows().get(0));

            if(imageFile != null){
                ImageIO.write(bufferedImage, "png", imageFile);
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
