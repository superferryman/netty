package com.superferryman.client.myChatClient.component;

import com.superferryman.protocol.Packet;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;


public class FileItem extends ImageItem{
    public HBox getFileItem(String name,String imgUrl, String message, Boolean isMe,String fileURL){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15,15,15,15));
        hBox.setPrefHeight(118);
        hBox.setMinHeight(118);
        double imgX = 10,imgY = 10,laX = 62,laY = 13 ;
        if(isMe){
            imgX = 140;
            imgY =10;
            laX = 8;
            laY = 13;
            hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
        Pane pane = getPane(202,87,"-fx-border-color: rgb(202,202,202); -fx-border-width: 2;-fx-background-color:white  ");
        pane.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        pane.setTranslateX(10);
        ImageView fileImg = getImageView("img/file2.png",50,50,-1,-1);
        fileImg.setLayoutY(imgY);
        fileImg.setLayoutX(imgX);
        Label nameLabel = getLabel(laX,laY,130,50,message,null);
        nameLabel.setFont(Font.font(14));
        nameLabel.setWrapText(true);
        if(isMe){
            nameLabel.setAlignment(Pos.CENTER);
        }else{
            nameLabel.setAlignment(Pos.CENTER);
        }
        Separator separator = new Separator();
        separator.setPrefSize(200,1);
        separator.setLayoutY(67);
        Label tagLabel = getLabel(5,67,100,19,"文件信息","#7c7575");
        Label hyperlink = getHyperlink(117,68,"打开文件夹","#3f71f2",fileURL);
        pane.getChildren().addAll(fileImg,nameLabel,separator,tagLabel,hyperlink);
        addEvent(pane);
        hBox.getChildren().addAll(getImageView(imgUrl,35,35,-1,-1),pane);
       if(name != null && name.trim().length() > 0){
           Label label = new ChatItem().getLabel(14,-3,200,20,name,14,"#b2b2b2");
           pane.getChildren().add(label);
           pane.setTranslateY(20);
           label.setTranslateY(-20);
           label.setTranslateX(-5);
           hBox.setPrefHeight(140);
           hBox.setMaxHeight(140);
           hBox.setMinHeight(140);
           pane.setMaxHeight(87);
           pane.setMinHeight(87);
       }
        return hBox;
    }

    public Label getHyperlink(double x,double y,String text,String textFill,String fileURL){
        Label hyperlink = new Label();
        hyperlink.setPrefSize(100,19);
        hyperlink.setLayoutX(x);
        hyperlink.setLayoutY(y);
        hyperlink.setText(text);
        hyperlink.setTextFill(Paint.valueOf(textFill));
        hyperlink.setCursor(Cursor.HAND);
        hyperlink.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                File pFile = new File(fileURL).getParentFile();
                try {
                    Desktop.getDesktop().open(pFile);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return hyperlink;
    }

    public Pane getPane(double w,double h,String style)
    {
        Pane pane = new Pane();
        pane.setPrefHeight(h);
        pane.setPrefWidth(w);
        pane.setStyle(style);
        return pane;
    }

    public Label getLabel(double x,double y,double w,double h,String word,String textFill){
        Label label = new Label(word);
        label.setPrefSize(w,h);
        label.setLayoutX(x);
        label.setLayoutY(y);
        if(textFill != null) label.setTextFill(Paint.valueOf(textFill));
        return label;
    }

    public void addEvent(Pane pane)
    {
        pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setStyle("-fx-background-color: rgb(246,247,247);-fx-border-color: rgb(202,202,202);-fx-border-width: 2");
            }
        });
        pane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pane.setStyle("-fx-background-color: white;-fx-border-color: rgb(202,202,202);-fx-border-width: 2");
            }
        });
    }
}
