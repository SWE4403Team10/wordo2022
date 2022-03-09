package com.wordo.ui;

import com.wordo.ui.layout.SharedMethods;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class GameUI extends Application {

    private Stage gameStage = new Stage();
    private final GridPane grid = new GridPane();
    private final SharedMethods sharedMethods = new SharedMethods();

    @Override
    public void start(Stage primaryStage) {
        gameStage = primaryStage;
        sharedMethods.createRowsColumnsForGridPane(grid, 9, 7);

        Button btnExitGame = new Button("Exit Game");
        btnExitGame.setMaxSize(75, 25);
        btnExitGame.setOnAction(e -> {
            TitleUI title = new TitleUI();
            title.start(gameStage);
        });

        HBox hboxExit = new HBox(btnExitGame);
        hboxExit.setMaxSize(100, 75);
        grid.add(hboxExit, 0, 0);

        Scene gameScene = new Scene(grid, 500, 400);

        gameStage.setScene(gameScene);
        gameStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}
