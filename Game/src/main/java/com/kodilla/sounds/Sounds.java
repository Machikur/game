package com.kodilla.sounds;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Sounds {

    private final MediaPlayer intro = new MediaPlayer(new Media(getClass().getClassLoader().getResource("resources/sounds/intro.wav").toString()));
    private final AudioClip dragon = new AudioClip(getClass().getClassLoader().getResource("resources/sounds/dragon.mp3").toString());
    private final AudioClip gamer = new AudioClip(getClass().getClassLoader().getResource("resources/sounds/gamer.mp3").toString());
    private final AudioClip winner = new AudioClip(getClass().getClassLoader().getResource("resources/sounds/win.mp3").toString());


    public void playIntro() {
        intro.setVolume(10);
        intro.setCycleCount(MediaPlayer.INDEFINITE);
        intro.play();
    }

    public void playGamerVoice() {
        gamer.play();
    }

    public void playDragonVoice() {
        dragon.play();
    }

    public void playWinner() {
        winner.play();
    }

}
