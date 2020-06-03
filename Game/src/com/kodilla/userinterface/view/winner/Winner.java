package com.kodilla.userinterface.view.winner;

import com.kodilla.userinterface.BoardUI;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Winner {

    private Integer matches;

    private Stage stage = new Stage();

    public Winner(Integer matches) {
        this.matches = matches;
    }

    public void getWinner(String winner) {
        double nodeWidth = BoardUI.SCENE_WIDTH / 1.5f;
        double nodeHeight = BoardUI.SCENE_HEIGHT / 2f;

        BorderPane border = new BorderPane();
        Text text = new Text("Wygrał " + winner);
        text.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 50));
        border.setCenter(text);

        HBox hBox = getDefinedHBox();
        hBox.getChildren().addAll(getCloseButton(), getRestartButton());
        border.setBottom(hBox);

        Scene scene = new Scene(border, nodeWidth, nodeHeight, Color.BLACK);
        this.stage.setScene(scene);
        this.stage.show();
    }


    private HBox getDefinedHBox() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(0, 150, 0, 150));
        hBox.setSpacing(70);
        return hBox;
    }

    private Button getCloseButton() {
        Button button = new Button();
        button.setText("Opuść grę");
        button.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        button.setMinSize(50, 20);
        button.setOnAction(param -> {
            this.stage.close();

        });
        return button;

    }

    private Button getRestartButton() {
        Button button = new Button();
        button.setText("Zacznij od nowa");
        button.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        button.setMinSize(50, 20);
        button.setOnAction(param -> {
            setMatches();
            stage.close();
        });

        return button;

    }

    public void setMatches() {
        BoardUI.setMatchesValue();
    }
    

}
