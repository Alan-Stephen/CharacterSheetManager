package com.example.csheet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Flow;

public class HelloController {
    public FlowPane mainPane;
    public VBox abilityScoresPane;
    @FXML
    private Label welcomeText;

    public void displayCharacter(PlayerSheet sheet) {
        for(Map.Entry<String,Integer> ability : sheet._abilityScores.entrySet()){
            VBox box = new VBox();
            Label abilityName = new Label(ability.getKey());
            TextField abilityScore = new TextField(ability.getValue().toString());
            Label abilityModifier = new Label(Integer.toString((ability.getValue() - 10) / 2));


            abilityScore.textProperty().addListener((observable, oldValue, newValue) -> {

                if (!newValue.matches("\\d*")) {
                    abilityScore.setText(newValue.replaceAll("[^\\d]", ""));
                }
                try {
                    int newModifier = (Integer.valueOf(abilityScore.getText()) - 10) / 2;
                    abilityModifier.setText(Integer.toString(newModifier));
                } catch (NumberFormatException ignored) {
                }
            });

            box.getChildren().add(abilityName);
            box.getChildren().add(abilityScore);
            box.getChildren().add(abilityModifier);

            abilityScoresPane.getChildren().add(box);
        }
    }
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}