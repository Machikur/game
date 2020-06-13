package com.kodilla.userinterface.view.loadgame;

import com.kodilla.engine.GameData;
import com.kodilla.userinterface.view.background.BackgroundScene;
import com.kodilla.userinterface.view.buttons.ButtonsAndText;
import com.kodilla.userinterface.view.game.Game;
import com.kodilla.userinterface.view.menu.Menu;
import com.kodilla.userinterface.view.ranking.Ranking;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameLoader {
    private List<GameData> list;
    private VBox vBox = new VBox();
    private HBox hBox = new HBox();
    private ButtonsAndText buttonsAndText = new ButtonsAndText();
    private ArrayList<Button> buttonsList = new ArrayList<>();
    private GameData gameData = null;
    private Stage primaryStage;
    private Scene menuScene;
    private Ranking ranking;
    private Game game;
    private BackgroundScene backgroundScene;
    private Button selectedButton;

    public GameLoader(List<GameData> list, Ranking ranking, Scene menuScene, BackgroundScene backgroundScene, Game game) {
        this.backgroundScene = backgroundScene;
        this.menuScene = menuScene;
        this.ranking = ranking;
        this.game = game;
        this.list=list;
    }

    public void Start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        buildVBoxButtons();
        buildHBoxButtons();
        vBox.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        borderPane.setBottom(hBox);
        BorderPane.setAlignment(vBox, Pos.CENTER);
        BorderPane.setAlignment(hBox, Pos.CENTER);

        borderPane.setBackground(backgroundScene.getBackground());
        Scene scene = new Scene(borderPane, Game.SCENE_WIDTH, Game.SCENE_HEIGHT, Color.WHITE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void buildVBoxButtons() {
        if (Objects.nonNull(list)&&list.size()!=0) {
            vBox.setSpacing(100 / list.size());
            for (int i = 0; i < list.size(); i++) {
                final int index = i;
                Button button = buttonsAndText.newButton( i+1 + ". " + list.get(index),350,60);
                buttonsList.add(button);
                button.setOnAction(p -> {
                    if (Objects.nonNull(selectedButton)) {
                        selectedButton.setStyle(Menu.DEFAULTBUTTONSTYLE);
                    }
                    gameData = list.get(index);
                    selectedButton = button;
                    button.setStyle("-fx-background-color: chartreuse");
                });
            }
            vBox.getChildren().addAll(buttonsList);
        } else {
            Text text = new Text("Brak zapisanych gier");
            buttonsAndText.getTextEffect(text);
            vBox.getChildren().add(text);
        }
    }

    private void buildHBoxButtons() {
        Button exit = buttonsAndText.newButton("Exit",200,20);
        exit.setOnAction(p -> {
            primaryStage.setScene(menuScene);
            primaryStage.show();
        });
        hBox.getChildren().add(exit);

        if (Objects.nonNull(list)) {
            hBox.setSpacing(100);
            Button loadGame = buttonsAndText.newButton("Load game",200,20);
            loadGame.setOnAction(p -> {
                if (Objects.nonNull(gameData)) {
                    game = new Game(gameData, ranking, menuScene,list);
                    game.game(primaryStage);
                }
            });
            hBox.getChildren().add(loadGame);
        }


    }


}
