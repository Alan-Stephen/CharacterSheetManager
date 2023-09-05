package com.example.csheet;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
public class AutoResizableTextArea extends TextArea {
    AutoResizableTextArea(String value) {
        setWrapText(true);
        this.setText(value);

        sceneProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal == null)
                return;
            applyCss();
            Node text = lookup(".text");

            prefHeightProperty().bind(Bindings.createDoubleBinding(() -> getFont().getSize() + text.getBoundsInLocal().getHeight(), text.boundsInLocalProperty()));

            text.boundsInLocalProperty().addListener((observableBoundsAfter, boundsBefore, boundsAfter) -> {
                Platform.runLater(this::requestLayout);
            });
        });
    }
}
