package com.kodilla.userinterface.view.match;

import com.kodilla.userinterface.view.background.BackgroundScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;


public class MatchesHBox {
    private Image image = new Image("file:Game/resources/zapalka.bmp");
    BackgroundScene backgroundScene=new BackgroundScene();



    public HBox gethBox(int value) {
        HBox hBox = new HBox();
        hBox.setBackground(backgroundScene.getBackgroundForMatches());
        hBox.setPadding(new Insets(0, 0, 0, 200));
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
            hBox.setAlignment(Pos.CENTER);
        }
        return hBox;
    }


}
