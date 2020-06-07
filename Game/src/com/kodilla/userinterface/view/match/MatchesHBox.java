package com.kodilla.userinterface.view.match;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


public class MatchesHBox {
    private Image image = new Image("file:Game/resources/zapalka.bmp");


    public HBox gethBox(int value) {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0, 60, 0, 160));
        hBox.setSpacing(40);

        for (int i = 0; i < value / 5; i++) {
            hBox.getChildren().add(littleHBox(5));
        }
        hBox.getChildren().add(littleHBox(value % 5));
        return hBox;
    }

    private HBox littleHBox(int value) {
        HBox hBox = new HBox();
        hBox.setSpacing(20);
        for (int i = 0; i < value; i++) {
            ImageView imageView = new ImageView(image);
            hBox.getChildren().add(imageView);
        }
        return hBox;
    }

}
