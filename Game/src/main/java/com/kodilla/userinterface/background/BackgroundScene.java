package com.kodilla.userinterface.background;

import com.kodilla.datahandler.GameStatics;
import javafx.scene.image.Image;
import javafx.scene.layout.*;


public class BackgroundScene {

    private Image imageback = new Image("resources/castle.jpg", GameStatics.SCENE_WIDTH, GameStatics.SCENE_HEIGHT, true, true);
    private Image backgroundImage = new Image("resources/green.jpg");

    public Background getBackgroundForGame() {
        BackgroundSize backgroundSize = new BackgroundSize(GameStatics.SCENE_WIDTH, GameStatics.SCENE_HEIGHT, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(imageback, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage);
    }

    public Background getBackground() {
        Image image = new Image("resources/castles.jpg.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(GameStatics.SCENE_WIDTH, GameStatics.SCENE_HEIGHT, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage);
    }

    public Background getBackgroundForMatches() {
        BackgroundSize backgroundSize = new BackgroundSize(GameStatics.SCENE_HEIGHT / 10, GameStatics.SCENE_HEIGHT / 10, false, false, false, true);
        BackgroundImage backgroundImage1 = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        return new Background(backgroundImage1);
    }

    public Image getImageback() {
        return imageback;
    }
}
