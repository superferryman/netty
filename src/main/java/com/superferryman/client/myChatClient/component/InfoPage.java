package com.superferryman.client.myChatClient.component;

import com.superferryman.client.myChatClient.Main;
import com.superferryman.client.myChatClient.api.SendAPI;
import com.superferryman.client.myChatClient.bean.User;
import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.StageStyle;

import java.util.ArrayList;
import java.util.List;


public class InfoPage extends ImageItem{

    public AnchorPane getPersonPane(String name,String id,String imgURL)
    {
        id = "  ID   ："+id;
        name = "昵称  ："+name;
        AnchorPane anchorPane = getAnchorPane();
        Label headLabel = getLabel("好友信息",107,25,84,49,19);
        Label nameLabel = getLabel(name,90,216,202,43,15);
        Label idLabel = getLabel(id,90,263,202,43,15);
        ImageView avator = getImageView(imgURL,70,70,-1,-1);
        avator.setLayoutX(111);
        avator.setLayoutY(103);
        Button button = getButton("删除该好友",40, 491,220,40,17);
        //删除好友事件
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new AlertItem().getAlert("确定删除该好友？",280,170);
                alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent event) {
                        String tid= GlobalState.userManager.getCurrentUser().getUserInfo().getId();
                        String res = alert.getResult().getText();
                        if(res.equals("确定")){
                            new SendAPI().sendDeleteFriendMessage(GlobalState.userManager.getMyInfo().getId(),tid);
                            GlobalState.closeMoreInfo();
                        }
                    }
                });
                alert.show();
            }
        });
        anchorPane.getChildren().addAll(headLabel,nameLabel,idLabel,avator,button);
        return anchorPane;
    }

    public AnchorPane getGroupPane(String name, String id, String imgURL)
    {
        id = "群ID   ："+id;
        name = "群昵称  ："+name;
        AnchorPane anchorPane = getAnchorPane();
        Label headLabel = getLabel("群信息",116,25,84,49,19);
        Label nameLabel = getLabel(name,90,172,202,43,15);
        Label idLabel = getLabel(id,90,209,202,43,15);
        Button button = getButton("删除并退出该群",40, 491,220,40,17);
        //退群
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Alert alert = new AlertItem().getAlert("确定退出该群？",280,170);
                alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
                    @Override
                    public void handle(DialogEvent event) {
                        String tid= GlobalState.userManager.getCurrentUser().getUserInfo().getId();
                        String res = alert.getResult().getText();
                        if(res.equals("确定")){
                            new SendAPI().sendDeleteGroupId(GlobalState.userManager.getMyInfo().getId(),tid);
                            GlobalState.closeMoreInfo();
                        }
                    }
                });
                alert.show();
            }
        });
        ImageView avator = getImageView(imgURL,70,70,-1,-1);
        avator.setLayoutX(111);
        avator.setLayoutY(87);
        ScrollPane scrollPane = getScrollPane(10,255,260,220);
        FlowPane flowPane = getFlowPane(260,220);
        scrollPane.setContent(flowPane);
        anchorPane.getChildren().addAll(headLabel,avator,nameLabel,idLabel,scrollPane,button);
        return anchorPane;
    }

    public void addGroupList(List<User> list){
        if(list != null || list.size() == 0){
            //取得右侧信息面板
            AnchorPane anchorPane = (AnchorPane)((AnchorPane)Main.stage.getScene().getRoot()).getChildren().get(4);
            //取得成员列表
            FlowPane flowPane = (FlowPane)((ScrollPane)(anchorPane.getChildren().get(4))).getContent();
            //添加
            for(User u: list){
                Pane p = getImagePane(50,60,GlobalState.getImgUrl(u.getAvator()),7,5,35,35);
                p.getChildren().add(getLabel(u.getUsername(),6,45,45,15,12));
                flowPane.getChildren().add(p);
            }
        }
        else{
            GlobalState.findNull.setVisible(true);
        }
    }

    public Pane getPane(double w,double h)
    {
        Pane pane = new Pane();
        pane.setPrefHeight(h);
        pane.setPrefWidth(w);
        return pane;
    }

    public AnchorPane getAnchorPane(){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(300,600);
        anchorPane.setLayoutX(925);
        anchorPane.setStyle("-fx-background-color: rgb(245,251,253);");
        return anchorPane;
    }

    public ScrollPane getScrollPane(double x,double y,double w,double h)
    {
        ScrollPane pane = new ScrollPane();
        pane.setPrefHeight(h);
        pane.setPrefWidth(w);
        pane.setLayoutX(x);
        pane.setLayoutY(y);
        pane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        pane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return pane;
    }

    public Label getLabel(String message,double x,double y,double w,double h,double size)
    {
        Label label = new Label(message);
        label.setPrefHeight(h);
        label.setPrefWidth(w);
        label.setTextFill(Paint.valueOf("#8a8282"));
        if(x != -1) label.setLayoutX(x);
        if(y != -1) label.setLayoutY(y);
        label.setFont(Font.font(size));
        return label;
    }

    public FlowPane getFlowPane(double w,double h)
    {
        FlowPane flowPane = new FlowPane();
        flowPane.setPrefHeight(h);
        flowPane.setPrefWidth(w);
        flowPane.setStyle("-fx-background-color: rgb(245,251,253)");
        return flowPane;
    }

    public Button getButton(String name,double x,double y,double w,double h,double size)
    {
        Button button = new Button();
        button.setLayoutX(x);
        button.setLayoutY(y);
        button.setPrefHeight(h);
        button.setPrefWidth(w);
        button.setText(name);
        button.setFont(Font.font(size));
        button.setStyle("-fx-background-color: rgb(248,84,84); -fx-background-radius: 15;");
        button.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-background-color: red; -fx-background-radius: 15;");
            }
        });
        button.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                button.setStyle("-fx-background-color: rgb(248,84,84); -fx-background-radius: 15;");
            }
        });
        return button;
    }

    public Pane getImagePane(double w,double h,String imgURL,double imgX,double imgY,double imgW,double imgH){
        Pane pane = new Pane();
        pane.setPrefSize(w,h);
        ImageView imageView = getImageView(imgURL,imgW,imgH,-1,-1);
        imageView.setLayoutX(imgX);
        imageView.setLayoutY(imgY);
        pane.getChildren().add(imageView);
        return pane;
    }
}
