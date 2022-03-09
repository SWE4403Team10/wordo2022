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
//    TableView<String> table = new TableView<>();
//    ObservableList<String> list = FXCollections.observableArrayList();
    private Stage titleStage;
    private GridPane grid = new GridPane();
    private SharedMethods sharedmethods = new SharedMethods();
    @Override
    public void start(Stage stage) {
        titleStage = stage;

        grid.setGridLinesVisible(true);

        sharedmethods.createRowsColumnsForGridPane(grid, 9, 7);
//        grid.getColumnConstraints().get(0).setMinWidth(30);
        grid.getColumnConstraints().get(0).setMaxWidth(25);
        grid.getColumnConstraints().get(6).setMaxWidth(25);


        Button btnSettings = new Button();
        btnSettings.getStyleClass().add("settings-button");
        btnSettings.setMinSize(25, 25);
        btnSettings.setAlignment(Pos.TOP_LEFT);
        btnSettings.setOnAction(e -> {
            SettingsUI settings = new SettingsUI();
            settings.start(titleStage);
        });

        HBox hboxSettings = new HBox(btnSettings);
        hboxSettings.setMaxSize(100, 75);

        grid.add(hboxSettings, 0, 0);

        Label lblTitle = new Label("Wordo");

        HBox hboxTitle = new HBox(lblTitle);
        hboxTitle.setAlignment(Pos.CENTER);

        grid.add(hboxTitle, 2, 0, 3,1);

        Button btnPlay = new Button("Play");
        btnPlay.setMinSize(100, 75);
        btnPlay.setOnAction(e -> {
            GameUI game = new GameUI();
            game.start(titleStage);
        });

        HBox hboxPlay = new HBox(btnPlay);
        hboxPlay.setAlignment(Pos.CENTER);

        grid.add(hboxPlay , 2, 5, 3, 1);

        final ToggleGroup tGroup = new ToggleGroup();
        RadioButton rbEasy = new RadioButton("Easy");
        rbEasy.setToggleGroup(tGroup);
        rbEasy.setSelected(true);
        RadioButton rbMedium = new RadioButton("Medium");
        rbMedium.setToggleGroup(tGroup);
        RadioButton rbHard = new RadioButton("Hard");
        rbHard.setToggleGroup(tGroup);

        HBox hboxDifficulties = new HBox(rbEasy, rbMedium, rbHard);
        hboxDifficulties.setAlignment(Pos.CENTER);
        hboxDifficulties.setSpacing(10);


        grid.add(hboxDifficulties, 2, 7, 3, 1);

        Scene titleScene = new Scene(grid, 450, 375);
        titleScene.getStylesheets().add("src/main/resources/styles/mainPage.css");

        titleStage.setTitle("Wordo");
        titleStage.setScene(titleScene);
        titleStage.show();
    }

    public static void main() {
        launch();
    }
}