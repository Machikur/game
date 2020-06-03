package com.kodilla.userinterface.view.background;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class BackgroundScene {

    private Image imageback = new Image("file:Game/resources/zamek.bmp");


    public Background background() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage);
    }
    public ImageView getImageView(){
        ImageView imageView= new ImageView(imageback);
        imageView.setFitHeight(800);
        imageView.setFitWidth(1050);
        return imageView;
    }

}
