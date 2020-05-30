package com.kodilla.view.rules;

import com.kodilla.userinterface.BoardUI;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Rules {

    public Scene getRules() {

        double nodeWidth = BoardUI.SCENE_WIDTH / 1.4f;
        double nodeHeight = BoardUI.SCENE_HEIGHT / 2f;

        BorderPane border = new BorderPane();
        Text text = new Text("Znalazłeś sie u bram zamku którego strzegą smoki\n Aby się dostać musisz je pokonać\n" +
                " Chcą one abyście na zmiane ciągneli po jednej,\n dwóch lub trzech zapałkach.\n Wygrywa ten który pociągnie ostatni\n Powodzenia");
        text.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));
        border.setCenter(text);

        return new Scene(border, nodeWidth, nodeHeight, Color.BLACK);


    }
}
