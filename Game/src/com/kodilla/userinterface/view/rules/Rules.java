package com.kodilla.userinterface.view.rules;

import com.kodilla.datahandler.GameStatics;
import com.kodilla.userinterface.view.background.BackgroundScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Rules {

    private Stage stage;
    private Scene menuScene;


    public void getRules(Stage primaryStage, Scene menuScene) {
        this.stage = primaryStage;
        this.menuScene = menuScene;

        BackgroundScene backgroundScene = new BackgroundScene();

        double nodeWidth = GameStatics.SCENE_WIDTH;
        double nodeHeight = GameStatics.SCENE_HEIGHT;

        BorderPane border = new BorderPane();
        border.setBackground(backgroundScene.getBackground());

        DropShadow ds = new DropShadow();
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));

        Text text = new Text(" Znalazłeś sie u bram zamku którego strzegą smoki\n Aby się dostać musisz je pokonać\n" +
                " Chcą one abyście na zmiane ciągneli po jednej,\n dwóch lub trzech zapałkach.\n Wygrywa ten który pociągnie ostatni\n Musisz pokonać go 5 razy\n Powodzenia");
        text.setFont(Font.font(null, FontWeight.BOLD, 40));
        text.setCache(true);
        text.setX(10.0f);
        text.setY(270.0f);
        text.setFill(Color.BLUE);
        text.setEffect(ds);
        border.setCenter(text);

        HBox hBox = getDefinedHBox();
        hBox.setAlignment(Pos.CENTER);
        border.setBottom(hBox);

        Scene scene = new Scene(border, nodeWidth, nodeHeight, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox getDefinedHBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0, 270, 0, 300));
        hBox.getChildren().add(getButton());
        return hBox;
    }

    private Button getButton() {
        Button button = new Button();
        button.setText("Continue");
        button.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        button.setMinSize(200, 80);
        button.setOnAction(param -> {
            stage.setScene(menuScene);
            stage.show();
        });
        return button;

    }


}
