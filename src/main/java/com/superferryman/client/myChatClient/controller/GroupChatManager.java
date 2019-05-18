package com.superferryman.client.myChatClient.controller;

import com.superferryman.client.myChatClient.Main;
import com.superferryman.client.myChatClient.bean.UserInfoVo;
import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建群聊面板
 * userList：好友列表userInfo和三种Pane
 * leftVBox：左侧列表
 * rightVBox：右侧选中列表
 * commitButton：确认按钮
 * */
public class GroupChatManager {
    //好友列表信息和三个Pane
    private List<UserInfoVo> userList = new ArrayList<>();
    private  VBox leftVBox;
    private  VBox rightVBox;
    private Button commitButton;

    public void go() throws IOException {
        Stage stage = new Stage();
        GlobalState.groupStage = stage;
        stage.initStyle(StageStyle.UNDECORATED);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GroupChatPage.fxml"));
        Pane page = loader.load();
        GroupChatPageController controller = loader.getController();
        leftVBox = controller.leftVBox;
        rightVBox = controller.rightVBox;
        commitButton = controller.commitButton;
        Scene scene = new Scene(page);
        stage.setScene(scene);
        stage.setX(Main.stage.getX()+150);
        stage.setY(Main.stage.getY()+60);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        renderList();
    }
    /***
     * 渲染群聊面包的左侧好友列表
     * */
    public void renderList()
    {
        for(UserInfoVo u : userList)
        {
            if(u.getUserInfo().isGroup() == false) {
                leftVBox.getChildren().add(u.getLeftUserInfoPane());
            }
        }
    }
    /***
     * 根据左侧选中的好友，重新渲染右侧选中列表
     * */
    public void renderRightList() {
        rightVBox.getChildren().remove(0,rightVBox.getChildren().size());
        Boolean flag = false;
        for(UserInfoVo u : userList)
        {
            int cnt = Integer.parseInt(u.getLeftUserInfoPane().getChildren().get(2).getId());
            if(cnt % 2 == 1)
            {
                rightVBox.getChildren().add(u.getRightUserInfoPane());
               flag = true;
            }
        }
        if(flag) {
            GlobalState.groupChatController.commitButton.setStyle("-fx-background-color: rgb(18,150,17); -fx-border-width: 1; -fx-background-radius: 0;");
        } else {
            GlobalState.groupChatController.commitButton.setStyle("-fx-background-color: rgb(140,213,140); -fx-border-width: 1; -fx-background-radius: 0;");
        }
    }

    public List<UserInfoVo> getUserList() {
        return userList;
    }

    public void setUserList(List<UserInfoVo> userList) {
        this.userList = userList;
    }

    public VBox getLeftVBox() {
        return leftVBox;
    }

    public void setLeftVBox(VBox leftVBox) {
        this.leftVBox = leftVBox;
    }

    public VBox getRightVBox() {
        return rightVBox;
    }

    public void setRightVBox(VBox rightVBox) {
        this.rightVBox = rightVBox;
    }
}
