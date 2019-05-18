package com.superferryman.client.myChatClient;

import com.superferryman.client.myChatClient.component.FriendItem;
import com.superferryman.client.myChatClient.component.ImageItem;
import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application {
    static public Stage stage;
    static public AnchorPane root;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;

        primaryStage.initStyle(StageStyle.UNDECORATED);
        AnchorPane loginLoader = FXMLLoader.load(getClass().getResource("/fxml/login/Login.fxml"));
        GlobalState.login = loginLoader;
        //加载各个面板
        loaderPane();
        //加载主面板
        Scene scene = new Scene(loginLoader);
        //开启登录功能，注释下一句
        //Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        GlobalState.groupChatController.setUserList(GlobalState.userManager.getUserList());
        GlobalState.userManager.renderFriendList(null,null);
    }

    public void loaderPane() throws IOException {
        //加载根节点
        root = FXMLLoader.load(getClass().getResource("/fxml/Root.fxml"));

        //加载左侧面板
        VBox left = FXMLLoader.load(getClass().getResource("/fxml/Left.fxml"));
        root.getChildren().add(left);
        //加载好友列表面板
        FXMLLoader centerLoader = new FXMLLoader(getClass().getResource("/fxml/Center.fxml"));
        VBox center = centerLoader.load();
        root.getChildren().add(center);
        //分割线
        Separator separator = new Separator(Orientation.VERTICAL);
        separator.setPrefSize(1,530);
        separator.setLayoutX(324);
        separator.setLayoutY(70);
        separator.setStyle("-fx-background-color:  rgb(50,50,50)");
        GlobalState.centerSeparator = separator;
        separator.setVisible(false);
        root.getChildren().add(separator);
        //加载右侧聊天面板
        FXMLLoader rightLoader = new FXMLLoader(getClass().getResource("/fxml/Right.fxml"));
        VBox right = rightLoader.load();
        root.getChildren().add(right);
        GlobalState.rightPane = (Pane)right.getChildren().get(2);
        right.getChildren().remove(2);
        right.getChildren().add(new ImageItem().getImageView("img/logol.png",600,530,324,70));
        GlobalState.right = right;
        //加载注册面板
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/fxml/login/Regist.fxml"));
        GlobalState.regist = anchorPane;
        //加载注册成功面板
        AnchorPane registSuccess = FXMLLoader.load(getClass().getResource("/fxml/login/RegistSuccess.fxml"));
        GlobalState.registSuccess = registSuccess;
    }

    public void go(String[] args) {
        launch(args);
    }
}
