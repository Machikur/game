package com.kodilla.userinterface.view.background;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class BackgroundScene {

    private Image imageback = new Image("resources/castle.jpg");
    private Image backgroundImage = new Image("resources/green.jpg");

    public ImageView getImageView() {
        ImageView imageView = new ImageView(imageback);
        imageView.setFitHeight(600);
        imageView.setFitWidth(1024);
        return imageView;
    }

    public Background getBackground() {
        Image image = new Image("resources/castles.jpg.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(1024, 768, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage);

    }

    public Background getBackgroundForMatches() {
        BackgroundSize backgroundSize = new BackgroundSize(102, 76, false, false, false, true);
        BackgroundImage backgroundImage1 = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage1);
    }


}
