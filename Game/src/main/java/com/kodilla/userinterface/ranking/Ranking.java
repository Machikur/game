package com.kodilla.userinterface.ranking;

import com.kodilla.datahandler.GameStatics;
import com.kodilla.userinterface.background.BackgroundScene;
import com.kodilla.userinterface.buttons.ButtonsAndText;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class Ranking {
    private Stage stage;
    private Scene scene;
    private Scene menuScene;
    private BackgroundScene backgroundScene;
    private ButtonsAndText buttonsAndText = new ButtonsAndText();

    public Ranking(Scene menuScene, BackgroundScene backgroundScene) {
        this.menuScene = menuScene;
        this.backgroundScene = backgroundScene;

    }

    public void getRanking(Stage primaryStage,String bestPlayers) {
        setScene(bestPlayers);
        this.stage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private HBox getDefinedHBox() {
        HBox hBox = new HBox();
        Button button = buttonsAndText.newButton("Menu", 200, 20);
        button.setOnAction(param -> {
            stage.setScene(menuScene);
            stage.show();
        });
        hBox.setPadding(new Insets(0, 270, 0, 300));
        hBox.getChildren().add(button);
        return hBox;
    }


    public void setScene(String string) {
        BorderPane border = new BorderPane();
        border.setBackground(backgroundScene.getBackground());
        Text text = new Text(string);
        text=buttonsAndText.getTextEffect(text);
        text.setTextAlignment(TextAlignment.CENTER);
        border.setCenter(text);
        HBox hBox = getDefinedHBox();
        hBox.setAlignment(Pos.CENTER);
        border.setBottom(hBox);
        scene = new Scene(border, GameStatics.SCENE_WIDTH, GameStatics.SCENE_HEIGHT);
    }
}




