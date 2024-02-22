package com.application.calendarmaker;


import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotResult;
import javafx.scene.image.WritableImage;
import javafx.util.Callback;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class CalenderImageHandler implements Callback<SnapshotResult, Void> {

    @Override
    public Void call(SnapshotResult param) {

        WritableImage image = param.getImage();

        BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);



        try {
            File imageFile = new File(new URI("file:/D:/java-projects/CalendarMaker/src/main/resources/image.png"));

            ImageIO.write(bufferedImage, "png", imageFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}
