package com.wordo.ui.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;

public class KeyboardButton {
    private Button button;
    private int state;

    public Button getButton(){
        return button;
    }

    // Constructor
    public KeyboardButton(char c){
        button = new Button(""+c);
        button.setAlignment(Pos.BOTTOM_CENTER);
        button.setPrefHeight(50);
        button.setPrefWidth(33);
        button.setStyle("-fx-background-color: #A9A9A9; -fx-text-fill: #ffffff; ");
        button.setStyle("-fx-background-color: #A9A9A9; -fx-text-fill: #ffffff; ");
        /*
        button.setOnAction(actionEvent -> {
            System.out.println(button.getText());
            this.setState(1);
        });*/ //Disable input via buttons
    }

    public void setState(int color){
        switch(color){
            case 2:
                button.setStyle("-fx-background-color: #008000; -fx-text-fill: #ffffff; ");
                state = 2;
                break;
            case 1:
                button.setStyle("-fx-background-color: #FFD700; -fx-text-fill: #ffffff; ");
                state = 1;
                break;
            case 0:
                button.setStyle("-fx-background-color: #3C3C3C; -fx-text-fill: #ffffff; ");
                state = 0;
                //button.setDisable(true); Allow users to click the button even if letter is not in word
                break;
            default:
                button.setStyle("-fx-background-color: #A9A9A9; -fx-text-fill: #ffffff; ");
                state = -1;
        }
    }

    public int getState(){
        return state;
    }

}
