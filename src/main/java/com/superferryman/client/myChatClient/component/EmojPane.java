package com.superferryman.client.myChatClient.component;
import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.superferryman.client.myChatClient.Main;
import com.superferryman.client.myChatClient.controller.RightController;


public class EmojPane {
    public Stage getEmojPane(){
        Stage emojStage = new Stage();
        FlowPane flowPane = new FlowPane();
        flowPane.setStyle("-fx-background-color: rgb(255,255,255);-fx-border-width:2px; -fx-border-color: gray");

        flowPane.setPadding(new Insets(10,10,10,10));
        emojStage.setScene(new Scene(flowPane,445,265));
        emojStage.initStyle(StageStyle.UNDECORATED);
        emojStage.setY(Main.stage.getY()+165);
        emojStage.setX(Main.stage.getX()+330);
        emojStage.setWidth(445);
        emojStage.setHeight(265);
        for(int i = 0; i<84; i++)
        {
            String imgName;
            if(i == 0) imgName = "000";
            else if(i < 10) imgName = "00"+i;
            else imgName = "0"+i;
            flowPane.getChildren().add(getImg("img/emoj"+imgName+".png"));
        }
        return emojStage;
    }

    public Pane getImg(String url){
        Pane pane = new Pane();
        pane.setPrefWidth(35);
        pane.setPrefHeight(35);
        ImageView img = new ImageView();
        img.setFitWidth(28);
        img.setFitHeight(28);
        img.setLayoutX(3);
        img.setLayoutY(3);
        img.setImage(new Image(url));
        pane.getChildren().add(img);
        pane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setStyle("-fx-background-color: null;");
            }
        });
        pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setStyle("-fx-background-color: rgb(243,243,243);");
            }
        });
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String text = RightController.myTextField.getText();
                String emojName = url.substring(8,url.length()-4);
                text = text+'/'+emojName;
                RightController.myTextField.setText(text);
                RightController.emojStage.close();
                RightController.emojStage = null;
                GlobalState.emojPane.setStyle("-fx-background-color: null");
            }
        });
        return  pane;
    }
}
