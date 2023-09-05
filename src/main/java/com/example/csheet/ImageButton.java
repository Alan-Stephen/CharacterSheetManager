package com.example.csheet;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URISyntaxException;

public class ImageButton extends Button {
    ImageButton(String url,double size) {
        try {
            Image img = new Image(getClass().getResource(url).toURI().toString());
            ImageView imgView = new ImageView(img);
            imgView.setFitHeight(size);
            imgView.setPreserveRatio(true);

            this.setGraphic(imgView);
        } catch (URISyntaxException exception) {
            exception.printStackTrace();
            System.exit(1);
        }
    }
}
