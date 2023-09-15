package com.example.csheet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class IntField extends TextField {
    IntField(){
        this.textProperty().addListener((observableValue, s, newValue) -> {
            if (!newValue.matches("/^?[0-9]*$/")) {
                this.setText(newValue.replaceAll("\\D", ""));
            }
        });
    }
}
