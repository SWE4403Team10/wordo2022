package com.wordo.ui.components;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;

public class Keyboard {
    private final TilePane keyboard;

    final String querty = "qwertyuiopasdfghjklzxcvbnm";
    private KeyboardButton startButton = new KeyboardButton(' ');

    public TilePane getKeyboard() {
        return keyboard;
    }

    public Keyboard (){
        keyboard = new TilePane();

        for(char c : querty.toCharArray()){
            KeyboardButton button = (KeyboardButton)startButton.clone();
            button.getButton().setText(""+c);
            keyboard.getChildren().add(button.getButton());
        }
        keyboard.setTileAlignment(Pos.CENTER);
        keyboard.setAlignment(Pos.CENTER);
        keyboard.setPrefColumns(10);
        keyboard.setHgap(5);
        keyboard.setVgap(5);
    }

    public KeyboardButton getKey(char c){
        int i = querty.indexOf(c);
        return (KeyboardButton)keyboard.getChildren().toArray()[i];
    }

    public void update(String guess, int[] response){
        if(guess.length() != response.length){
            System.err.println("Guess length did not match response length"); //debug
            return;
        }
        for(int i = 0; i<guess.length(); i++){
            KeyboardButton key = getKey(guess.charAt(i));
            if(response[i] > key.getState()){
                key.setState(response[i]);
            }
        }
    }

}
