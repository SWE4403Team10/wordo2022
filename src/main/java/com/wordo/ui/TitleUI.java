package com.wordo.ui;

import com.wordo.ui.layout.SharedMethods;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class TitleUI extends Application {
    private Stage titleStage;
    private GridPane grid = new GridPane();
    private SharedMethods sharedmethods = new SharedMethods();
    private int difficulty = 4;

    @Override
    public void start(Stage stage) {
        titleStage = stage;

//        grid.setGridLinesVisible(true);

        sharedmethods.createRowsColumnsForGridPane(grid, 9, 7);
        grid.getColumnConstraints().get(0).setMaxWidth(25);
        grid.getColumnConstraints().get(6).setMaxWidth(25);

        // Settings Button
        Button btnSettings = new Button("Settings");
        btnSettings.setStyle("-fx-background-color: transparent; -fx-background-size: 50px; -fx-background-repeat: no-repeat; -fx-border-color: transparent;");
        btnSettings.setMinSize(75, 50);
        btnSettings.setAlignment(Pos.TOP_LEFT);
        btnSettings.setOnAction(e -> {
            SettingsUI settings = new SettingsUI();
            settings.start(titleStage);
        });
        HBox hboxSettings = new HBox(btnSettings);
        hboxSettings.setMaxSize(100, 75);

        grid.add(hboxSettings, 0, 0);

        // Title
        Label lblTitle = new Label("Wordo");
        lblTitle.setStyle(" -fx-font-size: 30px;");

        HBox hboxTitle = new HBox(lblTitle);
        hboxTitle.setAlignment(Pos.CENTER);

        grid.add(hboxTitle, 2, 0, 3,1);

        // Play Button
        Button btnPlay = new Button("Play");
        btnPlay.setMinSize(100, 75);
        btnPlay.setStyle("-fx-border: 2px; -fx-border-radius: 10px; -fx-font-size: 15px;");
        btnPlay.setOnAction(e -> {
            GameUI game = new GameUI();
//            GameLogic gameLogic = new GameLogic();
//            gameLogic.setDifficulty();
            game.setDiff(difficulty);
            game.start(titleStage);
        });

        HBox hboxPlay = new HBox(btnPlay);
        hboxPlay.setAlignment(Pos.CENTER);

        grid.add(hboxPlay , 2, 5, 3, 1);

        // Radio Button Difficulties
        final ToggleGroup tGroup = new ToggleGroup();
        RadioButton rbEasy = new RadioButton("Easy");
        rbEasy.setToggleGroup(tGroup);
        rbEasy.setSelected(true);
        rbEasy.setOnAction(e -> { difficulty = 4; });
        RadioButton rbMedium = new RadioButton("Medium");
        rbMedium.setToggleGroup(tGroup);
        rbMedium.setOnAction(e -> { difficulty = 5; });
        RadioButton rbHard = new RadioButton("Hard");
        rbHard.setToggleGroup(tGroup);
        rbHard.setOnAction(e -> { difficulty = 6; });

        HBox hboxDifficulties = new HBox(rbEasy, rbMedium, rbHard);
        hboxDifficulties.setAlignment(Pos.CENTER);
        hboxDifficulties.setSpacing(10);

        grid.add(hboxDifficulties, 2, 7, 3, 1);

        Scene titleScene = new Scene(grid, 450, 375);

        titleStage.setTitle("Wordo");
        titleStage.setScene(titleScene);
        titleStage.show();
    }

    public static void main() {
        launch();
    }
}