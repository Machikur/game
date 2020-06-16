package com.kodilla.userinterface.view.rules;

import com.kodilla.datahandler.GameStatics;
import com.kodilla.userinterface.view.background.BackgroundScene;
import com.kodilla.userinterface.view.buttons.ButtonsAndText;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Rules {

    private Stage stage;
    private Scene menuScene;
    private ButtonsAndText buttonsAndText = new ButtonsAndText();


    public void getRules(Stage primaryStage, Scene menuScene) {
        this.stage = primaryStage;
        this.menuScene = menuScene;

        BackgroundScene backgroundScene = new BackgroundScene();

        BorderPane border = new BorderPane();
        border.setBackground(backgroundScene.getBackground());

        Text text = new Text(" Znalazłeś sie u bram zamku którego strzegą smoki\n Aby się dostać musisz je pokonać\n" +
                " Chcą one abyście na zmiane ciągneli po jednej,\n dwóch lub trzech zapałkach.\n Wygrywa ten który pociągnie ostatni\n Musisz pokonać go 5 razy\n Powodzenia");
        text = buttonsAndText.getTextEffect(text);
        border.setCenter(text);

        HBox hBox = getDefinedHBox();
        hBox.setAlignment(Pos.CENTER);
        border.setBottom(hBox);

        Scene scene = new Scene(border, GameStatics.SCENE_WIDTH, GameStatics.SCENE_HEIGHT, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox getDefinedHBox() {
        HBox hBox = new HBox();
        Button button = buttonsAndText.newButton("Continue", 200, 80);
        button.setOnAction(param -> {
            stage.setScene(menuScene);
            stage.show();
        });
        hBox.setPadding(new Insets(0, 270, 0, 300));
        hBox.getChildren().add(button);
        return hBox;
    }

}
