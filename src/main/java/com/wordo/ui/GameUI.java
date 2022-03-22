package com.wordo.ui;

import com.wordo.gamelogic.GameLogic;
import com.wordo.ui.components.Keyboard;
import com.wordo.ui.components.Table;
import com.wordo.ui.components.elements.Element;
import com.wordo.ui.components.elements.WordoButton;
import com.wordo.ui.components.elements.WordoLabel;
import com.wordo.ui.layout.SharedMethods;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameUI extends Application {

    private Stage gameStage = new Stage();
    private final GridPane grid = new GridPane();
    private final SharedMethods sharedMethods = new SharedMethods();
    private int diff = 0;
    private String correctWord = "";
    private GameLogic gameLogic = GameLogic.getInstance();
    private WordoLabel lblWord = new WordoLabel("");
    private WordoLabel lblWin = (WordoLabel) lblWord.clone();
    private WordoButton exitGameButton;

    @Override
    public void start(Stage primaryStage) {
        gameStage = primaryStage;
        sharedMethods.createRowsColumnsForGridPane(grid, 9, 7);

        diff = gameLogic.getDifficultyWordLength();

//        grid.setGridLinesVisible(true);
        grid.getColumnConstraints().get(0).setMaxWidth(70);
        grid.getColumnConstraints().get(6).setMaxWidth(70);
        grid.getColumnConstraints().get(0).setMinWidth(70);
        grid.getColumnConstraints().get(6).setMinWidth(70);
        grid.getRowConstraints().get(8).setMinHeight(30);

        // ExitGame
        exitGameButton = new WordoButton(75, 25,"Exit Game", "-fx-background-color: transparent; -fx-background-size: 50px; -fx-background-repeat: no-repeat; -fx-border-color: transparent;");
        exitGameButton.getButton().setOnAction(e -> {
            TitleUI title = new TitleUI();
            title.start(gameStage);
        });

        HBox hboxExit = new HBox(exitGameButton.getButton());
        hboxExit.setMaxSize(100, 75);
        grid.add(hboxExit, 0, 0);

        // Word
        HBox hboxWord = new HBox(lblWin.getLabel(), lblWord.getLabel());
        hboxWord.setAlignment(Pos.CENTER);
        hboxWord.setSpacing(15);

        grid.add(hboxWord,2, 2, 2, 1);

        //Table
        Table table = new Table(diff);
        table.createColumns();
        table.addData();
        table.setNumberOfVisibleCells(50);
        table.setGameUI(this);

        correctWord = gameLogic.generateCorrectWord();

        //Keyboard
        Keyboard keyboard = new Keyboard();
        grid.add(keyboard.getKeyboard(), 3, 7);

        grid.add(table.getTable(), 1, 3, 5, 1);

        // Scene
        Scene gameScene = new Scene(grid, 550, 700);

        // Stage
        gameStage.setScene(gameScene);
        gameStage.show();

    }

    /**
     * Updates the Win label and the Word label to the correct text based on the result of the user.
     * @param show
     */
    public void showWord(boolean show){
        if(show){
            lblWin.setLblText("Winner!");
            lblWord.setLblText(correctWord);
        } else {
            lblWin.setLblText("Good luck next time!");
            lblWord.setLblText("");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
