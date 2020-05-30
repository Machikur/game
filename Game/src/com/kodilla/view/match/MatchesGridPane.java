package com.kodilla.view.match;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


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

}
