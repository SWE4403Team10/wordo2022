package com.wordo.ui.components.elements;

import javafx.scene.control.Button;

public class WordoButton implements Element {

    private double width;
    private double height;
    private String btnText;
    private Button button;
    private String style;

    public WordoButton(double width, double height, String text, String style){
        this.height = height;
        this.width = width;
        this.btnText = text;
        this.style = style;
        this.button = new Button(text);
        this.button.setMaxSize(width, height);
        this.button.setMinSize(width, height);
        this.button.setStyle(style);

    }

    /**
     * Getter for the button.
     * @return
     */
    public Button getButton() {
        return button;
    }

    /**
     * Clone for the button.
     * @return
     */
    @Override
    public Element clone(){ return new WordoButton(this.width, this.height, this.btnText, this.style); }

}
