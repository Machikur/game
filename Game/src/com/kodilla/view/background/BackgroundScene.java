package com.kodilla.view.background;

import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class BackgroundScene {

    private Image imageback = new Image("file:Game/resources/zamek.bmp");


    public Background background() {
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);
        return background;
    }

}
