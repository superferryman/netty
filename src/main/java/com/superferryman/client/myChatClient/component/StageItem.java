package com.superferryman.client.myChatClient.component;


import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class StageItem {
    public Stage getStage(double x,double y,double w, double h ){
        Stage stage = new Stage();
        Scene scene = new Scene(new Pane());
        stage.setScene(scene);
        stage.setWidth(w);
        stage.setHeight(h);
        stage.setX(x);
        stage.setY(y);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        return stage;
    }
}
