package com.example.csheet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class FeaturePane extends VBox {
    FeaturePane(Feature feature, CheckBox isDeletable, VBox featureList,PlayerSheet sheet){
        this.getStyleClass().add("individualFeature");
        HBox title = new HBox();

        Button dropDown = new Button(">");
        TextField field = new TextField(feature._title);
        field.getStyleClass().add("featureName");

        Button close = new Button("x");
        close.setOnAction(actionEvent -> {
            if(isDeletable.isSelected()) {
                featureList.getChildren().remove(this);
                sheet._features.remove(feature);
            }
        });

        title.getChildren().addAll(dropDown,field, close);
        this.getChildren().add(title);

        AutoResizableTextArea description = new AutoResizableTextArea(feature._desciption);
        //description.getStyleClass().add("featureDescription");
        // line above brekas the thing

        dropDown.setOnAction(actionEvent -> {
            if (this.getChildren().size() == 1) {
                dropDown.setText("↓");
                this.getChildren().add(description);
            } else {
                dropDown.setText(">");
                this.getChildren().remove(1);
            }
        });

        field.textProperty().addListener((observableValue, s, t1) -> {
            feature._title= field.getText();
        });

        description.textProperty().addListener((observableValue, s, t1) -> {
            feature._desciption = description.getText();
        });
    }
}
