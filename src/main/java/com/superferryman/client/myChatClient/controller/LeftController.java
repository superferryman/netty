package com.superferryman.client.myChatClient.controller;

import com.superferryman.client.myChatClient.Main;
import com.superferryman.client.myChatClient.component.ImageItem;
import com.superferryman.client.myChatClient.component.StageItem;
import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class LeftController {
    public VBox left;
    public ImageView chat;
    public ImageView friend;
    public ImageView person;
    public ImageView myAvator;

    @FXML
    public void initialize(){
        GlobalState.myAvator = myAvator;
    }
    public void onblur(){
        friend.setImage(new Image("img/friend1.png"));
        person.setImage(new Image("img/add1.png"));
        chat.setImage(new Image("img/chat1.png"));
    }

    public void chatClick(MouseEvent mouseEvent) {
        onblur();
        chat.setImage(new Image("img/chat2.png"));
        setLogol();
        GlobalState.centerScrollPane.setContent(GlobalState.userManager.getFriendsVBox());
    }

    public void friendClick(MouseEvent mouseEvent) {
        setLogol();
        onblur();
        friend.setImage(new Image("img/friend2.png"));
//        VBox vBox = new VBox();
//        vBox.setStyle("-fx-background-color: white");
//        FriendItem friendItem = new FriendItem();
//        for(UserInfoVo userInfoVo : GlobalState.userManager.getUserList()){
//            vBox.getChildren().add(friendItem.getFriendItem("",userInfoVo.getUserInfo().getUsername(),userInfoVo.getUserInfo().getId()));
//        }
//        GlobalState.centerScrollPane.setContent(vBox);
    }

    public void personClick(MouseEvent mouseEvent) {
        onblur();
        person.setImage(new Image("img/add2.png"));
        try {
            AnchorPane addPage = FXMLLoader.load(getClass().getResource("/fxml/AddPage.fxml"));
            Stage stage = new StageItem().getStage(Main.stage.getX()+100,Main.stage.getY()+100,750,450);
            stage.getScene().setRoot(addPage);
            GlobalState.addPageStage = stage;
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setLogol(){
        ((Label)((Pane)GlobalState.right.getChildren().get(1)).getChildren().get(0)).setText("");
        GlobalState.right.getChildren().remove(2);
        GlobalState.right.getChildren().add(new ImageItem().getImageView("img/logol.png",600,530,323,70));
        GlobalState.centerSeparator.setVisible(false);
    }

    public void setChat(){
        GlobalState.right.getChildren().remove(2);
        GlobalState.right.getChildren().add(GlobalState.rightPane);
    }
}
