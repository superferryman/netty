package com.superferryman.client.myChatClient.controller;

import com.superferryman.client.myChatClient.Main;
import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class RegistSuccessController {
    public Button loginBtn;
    public TextField message;

    public void onLoginClicked(MouseEvent mouseEvent) {
        Main.stage.setHeight(320);
        Main.stage.getScene().setRoot(GlobalState.login);
    }

    public void onLoginEntered(MouseEvent mouseEvent) {
        loginBtn.setStyle("-fx-background-color: rgb(52,210,210);-fx-background-radius: 20");
    }

    public void onLoginExited(MouseEvent mouseEvent) {
        loginBtn.setStyle("-fx-background-color: rgb(64,192,192);-fx-background-radius: 20");
    }
}
