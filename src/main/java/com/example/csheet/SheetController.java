package com.example.csheet;

import javafx.animation.FadeTransition;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.util.Pair;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class SheetController {

    @FXML
    TextField ac;
    @FXML
    TextField speed;
    @FXML
    VBox abilityAndProffs;
    @FXML
    TextField currentHP;
    @FXML
    TextField toModify;
    @FXML
    Button addHP;
    @FXML
    Button removeHP;
    @FXML
    TextField maxHP;
    @FXML
    TextField tempHP;
    @FXML
    VBox featureList;
    @FXML
    CheckBox isDeletable;
    @FXML
    Button addFeature;
    @FXML
    TextField characterName;
    @FXML
    TextField background;
    @FXML
    TextField playerClass;
    @FXML
    TextField level;
    @FXML
    TextField alignment;
    @FXML
    TextField race;
    @FXML
    FlowPane resourceList;
    @FXML
    CheckBox isDeletableResources;
    @FXML
    Button addResource;
    public void loadCharacterSheet(PlayerSheet sheet) {
        loadAbilitiesAndProffs(sheet);
        loadHealthModule(sheet);
        loadSpeedAC(sheet);
        loadFeatures(sheet);
        bindCharacterInfo(sheet);
        loadResources(sheet);
    }

    private void loadResources(PlayerSheet sheet) {
        for(Resource resource: sheet._resources) {
            resourceList.getChildren().add(new ResourcePane(resource,resourceList,sheet,isDeletableResources));
        }

        addResource.setOnAction(actionEvent -> {
            Resource resource = new Resource(5,0,"new resource");
            sheet._resources.add(resource);
            resourceList.getChildren().add(new ResourcePane(resource,resourceList,sheet,isDeletableResources));
        });


    }

    private void bindCharacterInfo(PlayerSheet sheet) {
        characterName.textProperty().addListener((observableValue, s, t1) -> {
            sheet._name = characterName.getText();
        });

        alignment.textProperty().addListener((observableValue, s, t1) -> {
            sheet._alignment= alignment.getText();
        });

        background.textProperty().addListener((observableValue, s, t1) -> {
            sheet._background= background.getText();
        });

        race.textProperty().addListener((observableValue, s, t1) -> {
            sheet._raceAge= race.getText();
        });

        playerClass.textProperty().addListener((observableValue, s, t1) -> {
            sheet._class = playerClass.getText();
        });

        level.textProperty().addListener((observableValue, old, newValue) -> {
            if (!newValue.matches("/^?[0-9]*$/")) {
                level.setText(newValue.replaceAll("\\D", ""));
            } if(!level.getText().equals("")) {
                sheet._level = Integer.parseInt(level.getText());
            }
        });
    }

    private void loadFeatures(PlayerSheet sheet) {
        for (Feature feature: sheet._features){

            FeaturePane currFeature = new FeaturePane(feature,isDeletable,featureList,sheet);

            VBox.setVgrow(featureList,Priority.ALWAYS);
            featureList.getChildren().add(currFeature);
        }

        addFeature.setOnAction(actionEvent -> {
           Feature feature = new Feature("Feature Name", "Feature Description");
            sheet._features.add(feature);

            FeaturePane featurePane = new FeaturePane(feature,isDeletable,featureList,sheet);
            featureList.getChildren().add(featurePane);
        });
    }

    private void loadAbilitiesAndProffs(PlayerSheet sheet) {

        for(Map.Entry<String,Integer> abilityScore: sheet._abilityScores.entrySet()){
            HBox abilityProffEntry = new HBox();
            abilityProffEntry.getStyleClass().add("abilityClass");
            VBox abilityEntry = new VBox();
            abilityEntry.setAlignment(Pos.CENTER);

            Button abilityName = new Button(abilityScore.getKey());
            abilityName.getStyleClass().add("abilityName");

            TextField abilityScoreField = new TextField(abilityScore.getValue().toString());
            abilityScoreField.getStyleClass().add("abilityScore");

            Label abilityMod = new Label(Integer.toString((abilityScore.getValue() - 10) / 2));

            abilityScoreField.textProperty().addListener((observable, oldValue, newValue) -> {
                if (!newValue.matches("/^?[0-9]*$/")) {
                    abilityScoreField.setText(newValue.replaceAll("\\D", ""));
                } if(!abilityScoreField.getText().equals("")) {
                    abilityMod.setText(Integer.toString((Integer.parseInt(abilityScoreField.getText()) - 10) / 2));
                    sheet._abilityScores.put(abilityName.getText(),Integer.parseInt(abilityScoreField.getText()));
                }
            });

            abilityEntry.getChildren().addAll(abilityName,abilityScoreField,abilityMod);
            abilityProffEntry.getChildren().add(abilityEntry);


            VBox proffEntries = new VBox();
            for(Proficiency proficiency: sheet._proficiencies){
                if (Objects.equals(abilityScore.getKey(), proficiency._abilityScore)) {
                    HBox prof = new HBox();
                    prof.setAlignment(Pos.CENTER_LEFT);

                    CheckBox hasProf = new CheckBox();
                    hasProf.setSelected(proficiency._hasProficiency);
                    hasProf.selectedProperty().addListener((observableValue, oldVal, newVal) ->
                            proficiency._hasProficiency = newVal);

                    Button proffName = new Button(proficiency._name);
                    prof.getChildren().add(hasProf);
                    prof.getChildren().add(proffName);
                    proffEntries.getChildren().add(prof);
                }
            }

            abilityProffEntry.getChildren().add(proffEntries);
            abilityAndProffs.getChildren().add(abilityProffEntry);
        }
    }
    private void loadHealthModule(PlayerSheet sheet) {

        currentHP.setText(Integer.toString(sheet._currentHitPoints));
        maxHP.setText(Integer.toString(sheet._maxHitPoints));
        tempHP.setText(Integer.toString(sheet._tempHitPoints));
        currentHP.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("/^?[0-9]*$/")) {
                currentHP.setText(newValue.replaceAll("\\D", ""));
            } if(!currentHP.getText().equals("")) {
                sheet._currentHitPoints = Integer.parseInt(currentHP.getText());
            }
        });

        tempHP.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("/^?[0-9]*$/")) {
                tempHP.setText(newValue.replaceAll("\\D", ""));
            } if(!tempHP.getText().equals("")) {
                sheet._tempHitPoints = Integer.parseInt(tempHP.getText());
            }
        });

        maxHP.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("/^?[0-9]*$/")) {
                maxHP.setText(newValue.replaceAll("\\D", ""));
            } if(!maxHP.getText().equals("")) {
                sheet._maxHitPoints = Integer.parseInt(tempHP.getText());
            }
        });


        toModify.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("/^?[0-9]*$/"))
                toModify.setText(newValue.replaceAll("\\D", ""));
        });

        removeHP.setOnAction((e) -> {
            if (Objects.equals(toModify.getText(), ""))
                return;
            int toChange = Integer.parseInt(toModify.getText());
            sheet._tempHitPoints -= toChange;
            if (sheet._tempHitPoints < 0) {
                sheet._currentHitPoints += sheet._tempHitPoints;
                sheet._currentHitPoints = Integer.max(sheet._currentHitPoints,0);
                sheet._tempHitPoints = 0;
            }

            currentHP.setText(Integer.toString(sheet._currentHitPoints));
            tempHP.setText(Integer.toString(sheet._tempHitPoints));
        });
    }
    private void loadSpeedAC(PlayerSheet sheet) {
        speed.setText(Integer.toString(sheet._speed));
        speed.textProperty().addListener((observable, oldValue, newValue) -> {
                    if (!newValue.matches("/^?[0-9]*$/")) {
                        speed.setText(newValue.replaceAll("\\D", ""));
                    } if(!speed.getText().equals("")) {
                        sheet._speed = Integer.parseInt(speed.getText());
                    }
        });

        ac.setText(Integer.toString(sheet._armourClass));
        ac.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("/^?[0-9]*$/")) {
                ac.setText(newValue.replaceAll("\\D", ""));
            } if(!ac.getText().equals("")) {
                sheet._armourClass = Integer.parseInt(ac.getText());
            }
        });
    }
}
