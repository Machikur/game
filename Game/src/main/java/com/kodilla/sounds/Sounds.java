package com.kodilla.sounds;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Sounds {

    private final MediaPlayer intro = new MediaPlayer(new Media(new File("file/Game/src/main/java/resources/sounds/intro.wav").toURI().toString()));
    private final MediaPlayer gamer = new MediaPlayer(new Media(new File("sounds/gamer.mp3").toURI().toString()));
    private final MediaPlayer dragon = new MediaPlayer(new Media(new File("sounds/dragon.mp3").toURI().toString()));


    public void playIntro() {
        intro.setVolume(50);
        intro.setCycleCount(MediaPlayer.INDEFINITE);
        intro.play();
    }

    public void playGamerVoice(){
        gamer.play();
        gamer.stop();
    }
    public void playDragonVoice(){
        dragon.play();
        dragon.stop();
    }
}
