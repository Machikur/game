package com.kodilla.userinterface.view.ranking;

import com.kodilla.datahandler.DataHandler;
import com.kodilla.datahandler.GameStatics;
import com.kodilla.userinterface.view.background.BackgroundScene;
import com.kodilla.userinterface.view.buttons.ButtonsAndText;
import com.kodilla.userinterface.view.game.Game;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Ranking {


    private Stage stage;
    private Scene scene;
    private Scene menuScene;
    private BackgroundScene backgroundScene;
    private ButtonsAndText buttonsAndText = new ButtonsAndText();
    private List<UserScore> bestUsers;

    public Ranking(Scene menuScene, BackgroundScene backgroundScene) {
        this.menuScene = menuScene;
        this.backgroundScene = backgroundScene;
        try {
            DataHandler dataHandler = new DataHandler();
            bestUsers = (ArrayList<UserScore>) dataHandler.loadFile(GameStatics.RANKING_PATH);
            if (Objects.isNull(bestUsers)) {
                bestUsers = new ArrayList<>();
            }
        } catch (Exception s) {
            s.printStackTrace();
        }
    }

    public void getRanking(Stage primaryStage) {
        setScene();
        this.stage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void addUser(UserScore user) {
        if (bestUsers.size() < 10 || bestUsers.get(9).getPoints() < user.getPoints()) {
            bestUsers.add(user);
        }
        bestUsers.sort(Comparator.comparingInt(UserScore::getPoints));
    }

    private Text bestUserToString() {
        Text text = new Text();
        if (bestUsers.size() == 0) {
            text.setText("Brak pozycji w rankingu");
        } else {
            StringBuilder records = new StringBuilder();
            for (int i = 0; i < bestUsers.size(); i++) {
                records.append(i + 1).append(" ").append(bestUsers.get(i)).append("\n");
            }
            text.setText(records.toString());
        }

        return buttonsAndText.getTextEffect(text);

    }

    private HBox getDefinedHBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0, 270, 0, 300));
        hBox.getChildren().add(getButton());
        return hBox;
    }

    private Button getButton() {
        Button button = buttonsAndText.newButton("Menu", 200, 20);
        button.setOnAction(param -> {
            stage.setScene(menuScene);
            stage.show();
        });
        return button;

    }

    public List<UserScore> getBestUsers() {
        return bestUsers;
    }

    public void setScene() {
        BorderPane border = new BorderPane();
        border.setBackground(backgroundScene.getBackground());
        Text text = bestUserToString();
        text.setTextAlignment(TextAlignment.CENTER);
        border.setCenter(text);
        HBox hBox = getDefinedHBox();
        hBox.setAlignment(Pos.CENTER);
        border.setBottom(hBox);


        scene = new Scene(border, Game.SCENE_WIDTH, Game.SCENE_HEIGHT);
    }
}




