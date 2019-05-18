package com.superferryman.client.myChatClient.controller;

import com.superferryman.client.myChatClient.api.SendAPI;
import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.List;

public class GroupChatPageController {

    public Pane closePane;
    public VBox leftVBox;
    public VBox rightVBox;
    public Button commitButton;
    public Button cancelButton;

    public void onCloseClick(MouseEvent mouseEvent) {
        cancelFillStyle(leftVBox.getChildren());
        GlobalState.groupStage.close();
    }

    public void onCloseEntered(MouseEvent mouseEvent) {
        closePane.setStyle("-fx-background-color:rgb(248,84,84)");
    }

    public void onCloseExited(MouseEvent mouseEvent) {
        closePane.setStyle("-fx-background-color: null");
    }

    public void cancelMouseClicked(MouseEvent mouseEvent) {
        //关闭前清空leftPane的点击cnt
       cancelFillStyle(leftVBox.getChildren());
        GlobalState.groupStage.close();
    }
    public void cancelMouseEntered(MouseEvent mouseEvent) {
        cancelButton.setStyle("-fx-background-color: rgb(239,239,239); -fx-border-color: rgb(231,231,231); -fx-border-width: 1; -fx-border-radius: 0;");
    }
    public void cancelMouseExited(MouseEvent mouseEvent) {
        cancelButton.setStyle("-fx-background-color: white; -fx-border-color: rgb(231,231,231); -fx-border-width: 1; -fx-border-radius: 0;");
    }

    /***
     * 确认创建群聊按钮，获取选中好友并发送请求，关闭面板
     * */
    public void commitMouseClicked(MouseEvent mouseEvent) {
        String message = GlobalState.userManager.getMyInfo().getId();
        String myId = message;
        for(Node n : leftVBox.getChildren())
        {
            Pane p = (Pane)n;
            int cnt = Integer.parseInt(p.getChildren().get(2).getId());
            p.getChildren().get(2).setId("0");
            if(cnt % 2 == 1)
            {
                message = message + ","+ p.getId();
            }
        }
        cancelFillStyle(leftVBox.getChildren());
        // 增加了来源 Id
        new SendAPI().sendCreateGroupMessage(myId, message);
        GlobalState.groupStage.close();
    }
    public void commitMouseEntered(MouseEvent mouseEvent) {

    }
    public void commitMouseExited(MouseEvent mouseEvent) {

    }
    public void cancelFillStyle(List<Node> list)
    {
        for(Node n : list)
        {
            Pane p = (Pane)n;
            Circle circle = (Circle)p.getChildren().get(2);
            circle.setFill(Paint.valueOf("white"));
            circle.setId("0");
        }
    }
}
