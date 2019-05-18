package com.superferryman.client.myChatClient.controller;

import com.superferryman.client.myChatClient.Main;
import com.superferryman.client.myChatClient.api.SendAPI;
import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class LoginController {
    public PasswordField password;
    public TextField account;
    public Pane closePane;
    public Button btn;
    public Button btn2;
    public Label usernameLabel;
    public Label passwordLabel;

    public void onLoginClicked(MouseEvent mouseEvent) {
        send();
    }

    public void onCloseClick(MouseEvent mouseEvent) { Main.stage.close(); }

    public void onCloseEntered(MouseEvent mouseEvent) {
        closePane.setStyle("-fx-background-color:rgb(248,84,84)");
    }

    public void onCloseExited(MouseEvent mouseEvent) {
        closePane.setStyle("-fx-background-color:null");
    }

    @FXML
    public void initialize(){
        account.requestFocus();
    }

    public void onEnterKeyPress(KeyEvent keyEvent) {
        if(keyEvent.getCode().getName().equals("Enter")){
            send();
        }
    }
    public void send(){
        if(!check()) return;
        String pwd = password.getText().trim();
        String acnt = account.getText().trim();
        new SendAPI().sendLoginMessage(acnt,pwd);
    }

    public void onAccountKeyPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().getName().equals("Tab")){
            password.requestFocus();
        }
    }

    public void onPwdPressed(KeyEvent keyEvent) {
        if(keyEvent.getCode().getName().equals("Tab")){
            account.requestFocus();
        }
    }

    public void onLoginEntered(MouseEvent mouseEvent) {
        btn.setStyle("-fx-background-color: rgb(52,210,210);-fx-background-radius: 20");
    }

    public void onLoginExited(MouseEvent mouseEvent) {
        btn.setStyle("-fx-background-color: rgb(64,192,192);-fx-background-radius: 20");
    }

    public void onRegistClicked(MouseEvent mouseEvent) {
        Main.stage.setHeight(500);
        Main.stage.getScene().setRoot(GlobalState.regist);
    }

    public void onRegistExited(MouseEvent mouseEvent) {
        btn2.setStyle("-fx-background-color: rgb(64,192,192);-fx-background-radius: 20");
    }

    public void onRegistEntered(MouseEvent mouseEvent) {
        btn2.setStyle("-fx-background-color: rgb(52,210,210);-fx-background-radius: 20");
    }
    public boolean check(){
        String pass = password.getText();
        String name = account.getText();
        setUnVis();
        if(pass.trim().length() == 0 || name.trim().length() == 0){
            if(pass.trim().length() == 0){
                passwordLabel.setVisible(true);
            }
            if(name.trim().length() == 0){
                usernameLabel.setVisible(true);
            }
            return false;
        }
        return true;
    }
    public void setUnVis(){
        passwordLabel.setVisible(false);
        usernameLabel.setVisible(false);
    }
}
