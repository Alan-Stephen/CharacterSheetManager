package com.example.csheet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        PlayerSheet playerSheet = new PlayerSheet();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("CSheetfxml.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        SheetController controller = fxmlLoader.getController();
        controller.loadCharacterSheet(playerSheet);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}