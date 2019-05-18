package com.superferryman.client.myChatClient.component;

import com.superferryman.client.myChatClient.Main;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class AlertItem {
    public Alert getAlert(String message,double x,double y){
        ButtonType commit = new ButtonType("确定");
        ButtonType cancel = new ButtonType("取消");
        Alert alert  = new Alert(Alert.AlertType.CONFIRMATION, message, new ButtonType[]{commit,cancel});
        alert.setX(Main.stage.getX()+280);
        alert.setY(Main.stage.getY()+170);
        alert.setHeaderText("");
        return alert;
    }

    public Alert getInfoAlert(String message,double x,double y){
        ButtonType commit = new ButtonType("确定");
        Alert alert  = new Alert(Alert.AlertType.INFORMATION, message, new ButtonType[]{commit});
        alert.setX(Main.stage.getX()+x);
        alert.setY(Main.stage.getY()+y);
        alert.setHeaderText("");
        return alert;
    }

    public Label getInfoLabel(double x,double y,double w,double h,String message){
        Label label = new Label();
        label.setPrefSize(w,h);
        label.setTranslateX(x);
        label.setLayoutY(y);
        label.setText(message);
        label.setFont(Font.font(14));
        label.setTextFill(Paint.valueOf("white"));
        label.setStyle("-fx-background-color: rgb(218,218,218); -fx-background-radius: 5;");
        label.setAlignment(Pos.CENTER);
        return  label;
    }
}
