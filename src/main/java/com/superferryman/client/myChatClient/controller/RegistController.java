package com.superferryman.client.myChatClient.controller;

import com.superferryman.client.myChatClient.Main;
import com.superferryman.client.myChatClient.api.SendAPI;
import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class RegistController {
    public Button registBtn;
    public Button loginBtn;
    public ImageView avator;
    public PasswordField pwd;
    public PasswordField rePwd;
    public TextField account;
    public Label usernameLabel;
    public Label passwordLabel;
    public Label rePasswordLabel;
    private int avatorCnt = 0;

    @FXML
    public void initialize(){

    }

    public void onPwdPressed(KeyEvent keyEvent) {
    }

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

    public void onRegistClicked(MouseEvent mouseEvent) {
        String name = account.getText();
        String pass = pwd.getText();
        String rePass = rePwd.getText();
        int avator_num = avatorCnt;
        setUnVis();
        if(pass.trim().length() == 0 || name.trim().length() == 0 || rePass.trim().length() == 0){
            if(pass.trim().length() == 0){
                passwordLabel.setVisible(true);
            }
            if(rePass.trim().length() == 0){
                rePasswordLabel.setVisible(true);
            }
            if(name.trim().length() == 0){
                usernameLabel.setVisible(true);
            }
            return;
        }
        if(!pass.equals(rePass)){
            rePasswordLabel.setVisible(true);
            return;
        }
        new SendAPI().sendRegisterMessage(name,pass,avator_num);
        account.clear();
        pwd.clear();
        rePwd.clear();
        avatorCnt = 0;
        avator.setImage(new Image("img/0.jpg"));
    }
    public void setUnVis(){
        passwordLabel.setVisible(false);
        rePasswordLabel.setVisible(false);
        usernameLabel.setVisible(false);
    }

    public void onAccountKeyPressed(KeyEvent keyEvent) {
    }

    public void onRegistEntered(MouseEvent mouseEvent) {
        registBtn.setStyle("-fx-background-color: rgb(52,210,210);-fx-background-radius: 20");
    }

    public void onRegistExited(MouseEvent mouseEvent) {
        registBtn.setStyle("-fx-background-color: rgb(64,192,192);-fx-background-radius: 20");
    }

    public void onChangeClicked(MouseEvent mouseEvent) {
        int cnt = (++avatorCnt)%10;
        avator.setImage(new Image("img/"+cnt+".jpg"));
    }
}
