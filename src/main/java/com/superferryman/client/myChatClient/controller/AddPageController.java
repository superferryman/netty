package com.superferryman.client.myChatClient.controller;

import com.superferryman.client.myChatClient.Main;
import com.superferryman.client.myChatClient.api.SendAPI;
import com.superferryman.client.myChatClient.enums.MessageSender;
import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class AddPageController {
    //关闭模块
    public Pane close;
    //输入模块
    public TextField textField;
    //查询好友按钮
    public Button userButton;
    //查询群组按钮
    public Button groupButton;
    public ScrollPane addPaneMess;
    public Pane findNull;

    @FXML
    public void initialize(){
        GlobalState.addPaneScroll = addPaneMess;
        GlobalState.findNull = findNull;
    }

    public void closeClicked(MouseEvent mouseEvent) {
        if(GlobalState.addPageStage != null){
            GlobalState.addPageStage.close();
        }
    }

    public void closeEntered(MouseEvent mouseEvent) {
        close.setStyle("-fx-background-color:rgb(248,84,84)");
    }

    public void closeExited(MouseEvent mouseEvent) {
        close.setStyle("-fx-background-color:null");
    }

    public void onUserButtonClicked(MouseEvent mouseEvent) {
        String message = textField.getText();
        if(message.trim().length() == 0) return;
        String myId = GlobalState.userManager.getMyInfo().getId();
        new SendAPI().sendQueryUserByIdMessage(myId,message);
    }

    public void onGroupButtonClicked(MouseEvent mouseEvent) {
        String message = textField.getText();
        if(message.trim().length() == 0) return;
        String myId = GlobalState.userManager.getMyInfo().getId();
        new SendAPI().sendQueryGroupByIdMessage(myId,message);
    }
}
