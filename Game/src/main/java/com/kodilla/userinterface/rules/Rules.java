package com.kodilla.userinterface.rules;

import com.kodilla.datahandler.GameStatics;
import com.kodilla.userinterface.background.BackgroundScene;
import com.kodilla.userinterface.buttons.ButtonsAndText;
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

        Text text = new Text("You got the gates of the castle guarded by dragons \n To get there you must defeat them \n" +
                "They want you to take one, two or three matches alternately. \n The last one wins \n You have to beat him 5 times \n Good luck");
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
