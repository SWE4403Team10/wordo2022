package com.wordo.ui;

import com.wordo.gamelogic.GameLogic;
import com.wordo.ui.components.Keyboard;
import com.wordo.ui.components.Table;
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

        Button btnExitGame = new Button("Exit Game");
        btnExitGame.setMaxSize(75, 25);
        btnExitGame.setStyle("-fx-background-color: transparent; -fx-background-size: 50px; -fx-background-repeat: no-repeat; -fx-border-color: transparent;");
        btnExitGame.setOnAction(e -> {
            TitleUI title = new TitleUI();
            title.start(gameStage);
        });

        HBox hboxExit = new HBox(btnExitGame);
        hboxExit.setMaxSize(100, 75);
        grid.add(hboxExit, 0, 0);

        //Table
        Table table = new Table(diff);
        table.createColumns();
        table.addData();
        table.setNumberOfVisibleCells(50);

        correctWord = gameLogic.generateCorrectWord();

        Label lblWord = new Label("");
        HBox hboxWord = new HBox(lblWord);
        hboxWord.setAlignment(Pos.CENTER);

        grid.add(hboxWord,2, 2, 2, 1);

        //Keyboard
        Keyboard keyboard = new Keyboard();
        grid.add(keyboard.getKeyboard(), 3, 7);

        if(!gameLogic.getNumGuesses() || gameLogic.isCorrect()){
            lblWord.setText(correctWord);
        } else {
            lblWord.setText("");
        }


        grid.add(table.getTable(), 1, 3, 5, 1);

        Scene gameScene = new Scene(grid, 550, 700);
        gameStage.setScene(gameScene);
        gameStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
