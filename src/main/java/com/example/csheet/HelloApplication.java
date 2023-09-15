package com.example.csheet;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        PlayerSheet playerSheet = new PlayerSheet();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CSheetfxml.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        SheetController controller = fxmlLoader.getController();
        playerSheet.loadFromJson("src/main/resources/com/example/csheet/characterPerm.json");
        controller.loadCharacterSheet(playerSheet);

        stage.setOnCloseRequest(windowEvent -> {
            playerSheet.toJson("src/main/resources/com/example/csheet/characterPerm.json");
        });

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}