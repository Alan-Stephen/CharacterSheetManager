package com.example.csheet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Flow;

public class HelloController {
    @FXML
    public FlowPane mainPane;

    public void setUpAbilityScoresAndProffencies(HashMap<String,Integer> abilityScores,
                                                 ArrayList<Proficiency> proficiencies){
        VBox abilityAndProffenciesList = new VBox();

        for (Map.Entry<String,Integer> entry: abilityScores.entrySet()) {
            HBox abilityProffEntry = new HBox();
            VBox abilityEntry = new VBox();
            VBox proffsEntry = new VBox();
            ArrayList<Proficiency> proficienciesRelatedtoAbility = new ArrayList<>();

            Label abilityName = new Label(entry.getKey());
            TextField abilityScore = new TextField(entry.getValue().toString());
            TextField abilityModifier = new TextField(Integer.toString((entry.getValue() - 10) / 2));

            abilityScore.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("/^?[0-9]*$/")) {
                    abilityScore.setText(newValue.replaceAll("\\D", ""));
                } if(!abilityScore.getText().equals(""))
                    abilityModifier.setText(Integer.toString((Integer.parseInt(abilityScore.getText()) - 10) / 2));
            });

            abilityModifier.textProperty().addListener((observableValue, oldValue, newValue) -> {
                if(!newValue.matches("/^-?[0-9]*$/")){
                    abilityModifier.setText(newValue.replaceAll("-\\D",""));
                }
            });

            abilityEntry.getChildren().addAll(abilityName,abilityScore,abilityModifier);

            for(Proficiency proficiency: proficiencies){
                System.out.println(entry.getKey());
                System.out.println(proficiency._abilityScore);
                if (Objects.equals(entry.getKey(), proficiency._abilityScore)) {
                    HBox prof = new HBox();
                    CheckBox checkBox = new CheckBox();
                    checkBox.setSelected(proficiency._hasProficiency);

                    Label proffName = new Label(proficiency._name);
                    prof.getChildren().add(checkBox);
                    prof.getChildren().add(proffName);
                    proffsEntry.getChildren().add(prof);
                }
            }

            abilityProffEntry.getChildren().add(abilityEntry);
            abilityProffEntry.getChildren().add(proffsEntry);
            abilityAndProffenciesList.getChildren().add(abilityProffEntry);
        }

        mainPane.getChildren().add(abilityAndProffenciesList);
    }

    public void displayCharacter(PlayerSheet sheet) {
        setUpAbilityScoresAndProffencies(sheet._abilityScores,sheet._proficiencies);
    }
}