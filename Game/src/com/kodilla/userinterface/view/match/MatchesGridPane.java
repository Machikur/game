package com.kodilla.userinterface.view.match;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;


public class MatchesGridPane {
    private Image image = new Image("file:Game/resources/zapalka.bmp");

    public GridPane setGridPane(int value) {

        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setPadding(new Insets(0, 10, 0, 10));

        int columnIndex = 18;
        for (int i = 0; i < value; i++) {
            columnIndex += 2;
            if (i % 5 == 0) {
                columnIndex += 2;
            }
            ImageView imageView = new ImageView(image);
            gridPane.add(imageView, columnIndex, 60, 1, 2);

        }
        return gridPane;
    }

    public HBox gethBox(int value) {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(600, 60, 0, 160));
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
