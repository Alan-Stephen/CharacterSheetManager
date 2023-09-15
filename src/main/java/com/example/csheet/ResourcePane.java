package com.example.csheet;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class ResourcePane extends VBox {
   ResourcePane(Resource resource, FlowPane pane, PlayerSheet sheet, CheckBox isDeletable) {
       HBox numbers = new HBox();
       HBox controls = new HBox();
       this.getStyleClass().add("resourcePane");
       controls.setAlignment(Pos.CENTER);

       _delete = new Button("x");
       _increase = new Button("+");
        _decrease = new Button("-");
        controls.getChildren().addAll(_increase,_decrease,_delete);

        _delete.getStyleClass().add("controlButtons");
        _increase.getStyleClass().add("controlButtons");
       _decrease.getStyleClass().add("controlButtons");

       _resourceName = new TextField();
       _total = new IntField();
       _current = new IntField();

       _resourceName.setText(resource._name);
       _total.setText(Integer.toString(resource._total));
       _current.setText(Integer.toString(resource._current));

       _resourceName.textProperty().addListener((observableValue, old, newValue) -> {
          resource._name = _resourceName.getText();
       });

       _total.textProperty().addListener((observableValue, old, newValue) -> {
           if(Objects.equals(_total.getText(), "")){
               return;
           }
           resource._total= Integer.parseInt(_total.getText());
       });

       _current.textProperty().addListener((observableValue, old, newValue) -> {
           if(Objects.equals(_total.getText(), "")){
               return;
           }
           resource._current= Integer.parseInt(_current.getText());
       });

       _increase.setOnAction(actionEvent -> {
           if(Objects.equals(_total.getText(), "")){
               return;
           }
           int curr = Integer.parseInt(_current.getText());
           _current.setText(Integer.toString(curr + 1));
       });

       _decrease.setOnAction(actionEvent -> {
           if(Objects.equals(_total.getText(), "")){
               return;
           }
           int curr = Integer.parseInt(_current.getText());
           _current.setText(Integer.toString(Integer.max(0,curr - 1)));
       });

       _delete.setOnAction(actionEvent -> {
          if(!isDeletable.isSelected()) {
              return;
          }

          pane.getChildren().remove(this);
          sheet._resources.remove(resource);
       });

       _resourceName.getStyleClass().add("resourceName");
       _total.getStyleClass().add("resourceTotal");
       _current.getStyleClass().add("resourceCurrent");

       numbers.getChildren().addAll(_current,_total);
       this.getChildren().addAll(_resourceName,numbers,controls);
   }

   TextField _resourceName;
   IntField _total;
   IntField _current;
   Button _increase;
   Button _decrease;
   Button _delete;
}
