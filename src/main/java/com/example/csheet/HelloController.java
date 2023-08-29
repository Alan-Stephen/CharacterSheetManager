package com.example.csheet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

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
        String cssStyle = """
                -fx-background-color: #c2c2c2;
                """;
        abilityAndProffenciesList.setStyle(cssStyle);

        for (Map.Entry<String,Integer> entry: abilityScores.entrySet()) {
            HBox abilityProffEntry = new HBox();
            String abilityProffEntryStyle = """
                    -fx-border-color: black;
                    -fx-border-width: 3 0 0 0;
                    """;
            abilityProffEntry.setStyle(abilityProffEntryStyle);

            VBox abilityEntry = new VBox();
            String abilityEntryStyle = """
                    -fx-alignment: center; 
                    -fx-border-color: black;
                    -fx-border-width: 0 3 0 0;
                    """;
            abilityEntry.setStyle(abilityEntryStyle);
            VBox proffsEntry = new VBox();
            ArrayList<Proficiency> proficienciesRelatedtoAbility = new ArrayList<>();

            String abilityNameStyle = """
                    -fx-font-family: 'fantasy'; 
                    -fx-font-weight: bold;
                    -fx-min-width: 100;
                    -fx-alignment: center;
                    """;
            Label abilityName = new Label(entry.getKey());
            abilityName.setStyle(abilityNameStyle);
            TextField abilityScore = new TextField(entry.getValue().toString());

            String abilityScoreStyle = """
            -fx-font-size: 17;
            -fx-max-width: 45;
            -fx-alignment: center;
            -fx-background-color: #c2c2c2;
            """;
            abilityScore.setStyle(abilityScoreStyle);


            String abilityModifierStyle = """
                    -fx-font-weight: bold;
                    -fx-font-size: 10;
                    """;

            Label abilityModifier = new Label(Integer.toString((entry.getValue() - 10) / 2));
            abilityModifier.setStyle(abilityModifierStyle);

            abilityScore.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("/^?[0-9]*$/")) {
                    abilityScore.setText(newValue.replaceAll("\\D", ""));
                } if(!abilityScore.getText().equals("")) {
                    abilityModifier.setText(Integer.toString((Integer.parseInt(abilityScore.getText()) - 10) / 2));
                    abilityScores.put(abilityName.getText(),Integer.parseInt(abilityScore.getText()));
                    System.out.println(abilityScores.get("Strength"));
                }
            });


            abilityEntry.getChildren().addAll(abilityName,abilityScore,abilityModifier);

            for(Proficiency proficiency: proficiencies){
                System.out.println(entry.getKey());
                System.out.println(proficiency._abilityScore);
                if (Objects.equals(entry.getKey(), proficiency._abilityScore)) {
                    String profCss = """
                            -fx-padding: 0 15 5 10;
                            """;
                    HBox prof = new HBox();
                    prof.setStyle(profCss);

                    String checkBoxCss = """
                                        """;
                    CheckBox checkBox = new CheckBox();
                    checkBox.setStyle(checkBoxCss);
                    checkBox.setSelected(proficiency._hasProficiency);
                    checkBox.selectedProperty().addListener((observableValue, oldVal, newVal) ->
                            proficiency._hasProficiency = newVal);

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

    private void setUpSpeedAndAC(VBox paneToAddTo, PlayerSheet sheet){
        HBox speedAndACPane = new HBox();

        TextField AC = new TextField(Integer.toString(sheet._armourClass));
        AC.textProperty().addListener((observableValue, oldVal, newValue) -> {
            if (!newValue.matches("/^?[0-9]*$/")) {
                AC.setText(newValue.replaceAll("\\D", ""));
            } if(!AC.getText().equals("")) {
                sheet._armourClass = Integer.parseInt(AC.getText());
            }
        });

        TextField speed = new TextField(Integer.toString(sheet._speed));
        speed.textProperty().addListener((observableValue, oldVal, newValue) -> {
            if (!newValue.matches("/^?[0-9]*$/")) {
                speed.setText(newValue.replaceAll("\\D", ""));
            } if(!speed.getText().equals("")) {
                sheet._speed = Integer.parseInt(speed.getText());
            }
        });

        speedAndACPane.getChildren().addAll(AC,speed);
        paneToAddTo.getChildren().add(speedAndACPane);
    }

    private void setUpHp(VBox paneToAddTo,PlayerSheet sheet) {
         VBox wholeHPPane = new VBox();
         HBox hpPane = new HBox();
         TextField maxHP = new TextField(Integer.toString(sheet._maxHitPoints));

         maxHP.textProperty().addListener((observableValue, old, newValue) -> {
             if (!newValue.matches("/^?[0-9]*$/")) {
                 maxHP.setText(newValue.replaceAll("\\D", ""));
             } if(!maxHP.getText().equals("")) {
                 sheet._maxHitPoints = Integer.parseInt(maxHP.getText());
             }
         });

         TextField currHP = new TextField(Integer.toString(sheet._currentHitPoints));
         currHP.textProperty().addListener((observableValue, old, newValue) -> {
            if (!newValue.matches("/^?[0-9]*$/")) {
                currHP.setText(newValue.replaceAll("\\D", ""));
            } if(!currHP.getText().equals("")) {
                sheet._currentHitPoints = Integer.parseInt(currHP.getText());
            }
         });

         hpPane.getChildren().addAll(currHP,maxHP);

         TextField hpModify = new TextField("0");

         hpModify.textProperty().addListener((observableValue, old, newValue) -> {
             if (!newValue.matches("/^?[0-9]*$/")) {
                 hpModify.setText(newValue.replaceAll("\\D", ""));
             }
         });

         HBox addRemovePane = new HBox();
         Button add = new Button();
         Button remove = new Button();

         add.setOnAction(actionEvent -> {
             int newHealth = Integer.parseInt(currHP.getText()) + Integer.parseInt(hpModify.getText());
             currHP.setText(Integer.toString(newHealth));
         });

        remove.setOnAction(actionEvent -> {
            int newHealth = Integer.parseInt(currHP.getText()) - Integer.parseInt(hpModify.getText());
            newHealth = Integer.max(0,newHealth);
            currHP.setText(Integer.toString(newHealth));
        });

        addRemovePane.getChildren().addAll(add,remove);

        wholeHPPane.getChildren().addAll(hpPane,hpModify,addRemovePane);
        paneToAddTo.getChildren().add(wholeHPPane);
    }

    public void displayCharacter(PlayerSheet sheet) {
        setUpAbilityScoresAndProffencies(sheet._abilityScores,sheet._proficiencies);
        VBox hpACSpeedHitDiceDeathSavesPane = new VBox();
        setUpSpeedAndAC(hpACSpeedHitDiceDeathSavesPane,sheet);
        setUpHp(hpACSpeedHitDiceDeathSavesPane,sheet);
        mainPane.getChildren().add(hpACSpeedHitDiceDeathSavesPane);
    }
}