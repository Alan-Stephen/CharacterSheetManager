package com.example.csheet;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

public class FeaturePane extends VBox {
    FeaturePane(Pair<String, String> feature, CheckBox isDeletable, VBox featureList,PlayerSheet sheet){
        this.getStyleClass().add("individualFeature");
        HBox title = new HBox();

        Button dropDown = new Button(">");
        TextField field = new TextField(feature.getKey());
        field.getStyleClass().add("featureName");

        Button close = new Button("x");
        close.setOnAction(actionEvent -> {
            if(isDeletable.isSelected()) {
                featureList.getChildren().remove(this);
                sheet._features.remove(feature);
                System.out.println(sheet._features.toString());
            }
        });

        title.getChildren().addAll(dropDown,field, close);
        this.getChildren().add(title);

        AutoResizableTextArea description = new AutoResizableTextArea(feature.getValue());
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
    }
}
