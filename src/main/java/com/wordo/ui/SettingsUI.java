package com.wordo.ui;

import com.wordo.ui.layout.SharedMethods;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SettingsUI extends Application {

    private Stage settingStage;
    private GridPane grid = new GridPane();
    private SharedMethods sharedMethods = new SharedMethods();

    @Override
    public void start(Stage primaryStage) {
        settingStage = primaryStage;
        sharedMethods.createRowsColumnsForGridPane(grid, 9, 7);

        Button btnBack = new Button("Back");
        btnBack.setMaxSize(50, 25);
        btnBack.setOnAction(e -> {
           TitleUI title = new TitleUI();
           title.start(settingStage);
        });

        HBox hboxBack = new HBox(btnBack);
        hboxBack.setMaxSize(100, 75);
        grid.add(hboxBack, 0, 0);

        Scene settingScene = new Scene(grid, 450, 375);
        settingStage.setScene(settingScene);
        settingStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
