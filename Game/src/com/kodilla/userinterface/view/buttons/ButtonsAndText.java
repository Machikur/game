package com.kodilla.userinterface.view.buttons;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


public class ButtonsAndText {

    public HBox addHBoxOfButtons(Button[] buttons) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 15, 0, 15));
        hbox.setSpacing(100);
        hbox.setStyle("-fx-background-color: \t#808080;");

        for (Button button : buttons) {
            hbox.getChildren().add(button);
        }

        return hbox;
    }

    public Button newButton(String text, int prefWidth, int prefHeight) {
        Button button = new Button();
        button.setPrefSize(prefWidth, prefHeight);
        button.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 20));
        button.setText(text);
        return button;
    }

    public Text getTextEffect(Text text) {
        DropShadow ds = new DropShadow();
        text.setFont(Font.font(null, FontWeight.BOLD, 30));
        ds.setOffsetY(3.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        text.setCache(true);
        text.setX(10.0f);
        text.setY(270.0f);
        text.setFill(Color.BLUE);
        text.setEffect(ds);
        return text;
    }

}
