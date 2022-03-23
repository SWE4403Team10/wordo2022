package com.wordo.ui;

import com.wordo.gamelogic.GameLogic;
import com.wordo.ui.components.elements.WordoButton;
import com.wordo.ui.components.elements.WordoLabel;
import com.wordo.ui.components.elements.WordoRadioButton;
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
    private GameLogic gameLogic = GameLogic.getInstance();
    private WordoButton playButton;
    private WordoLabel titleLabel;

    @Override
    public void start(Stage stage) {
        titleStage = stage;

//        grid.setGridLinesVisible(true);

        sharedmethods.createRowsColumnsForGridPane(grid, 9, 7);
        grid.getColumnConstraints().get(0).setMaxWidth(25);
        grid.getColumnConstraints().get(6).setMaxWidth(25);

        // Preset difficulty to easy
        gameLogic.setDifficulty("easy");

        // Title
        titleLabel = new WordoLabel("Wordo");
        titleLabel.getLabel().setStyle(" -fx-font-size: 30px;");

        HBox hboxTitle = new HBox(titleLabel.getLabel());
        hboxTitle.setAlignment(Pos.CENTER);

        grid.add(hboxTitle, 2, 0, 3,1);

        // Play Button
        playButton = new WordoButton(100, 75, "Play", "-fx-border: 2px; -fx-border-radius: 10px; -fx-border-color: #000000; -fx-font-size: 15px;");
        playButton.getButton().setOnAction(e -> {
            GameUI game = new GameUI();
            game.start(titleStage);
        });

        HBox hboxPlay = new HBox(playButton.getButton());
        hboxPlay.setAlignment(Pos.CENTER);

        grid.add(hboxPlay , 2, 5, 3, 1);

        // Radio Button Difficulties
        final ToggleGroup tGroup = new ToggleGroup();
        WordoRadioButton rbHard = new WordoRadioButton(tGroup, "Hard");
        rbHard.getRadioButton().setOnAction(e -> { gameLogic.setDifficulty("hard"); });
        WordoRadioButton rbMedium = (WordoRadioButton) rbHard.clone();
        rbMedium.setRbText("Medium");
        rbMedium.getRadioButton().setOnAction(e -> { gameLogic.setDifficulty("normal"); });
        WordoRadioButton rbEasy = (WordoRadioButton) rbHard.clone();
        rbEasy.getRadioButton().setSelected(true);
        rbEasy.setRbText("Easy");
        rbEasy.getRadioButton().setOnAction(e -> { gameLogic.setDifficulty("easy"); });

        HBox hboxDifficulties = new HBox(rbEasy.getRadioButton(), rbMedium.getRadioButton(), rbHard.getRadioButton());
        hboxDifficulties.setAlignment(Pos.CENTER);
        hboxDifficulties.setSpacing(10);

        grid.add(hboxDifficulties, 2, 7, 3, 1);

        Scene titleScene = new Scene(grid, 550, 700);

        titleStage.setTitle("Wordo");
        titleStage.setScene(titleScene);
        titleStage.show();
    }

    public static void main() {
        launch();
    }
}