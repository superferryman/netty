package com.superferryman.client.myChatClient.utils;

import com.superferryman.client.myChatClient.Main;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.superferryman.client.myChatClient.controller.GroupChatManager;
import com.superferryman.client.myChatClient.controller.UserManager;


public class GlobalState {
    //右侧面板的聊天框vBox
    public static VBox chatVBox;
    //右侧面板聊天框滚动框
    public static ScrollPane scrollerPane;
    //中间好友列表滚动框
    public static ScrollPane centerScrollPane;
    //右侧文字输入框
    public static TextField textField;
    //个人头像
    public static ImageView myAvator;
    //聊天及输入框
    public static Pane rightPane;
    //中间分割线
    public static Separator centerSeparator;
    //右侧面板
    public static VBox right;
    //注册面板
    public static AnchorPane regist;
    //登录面板
    public static AnchorPane login;
    //注册成功面板
    public  static  AnchorPane registSuccess;
    //添加面板的消息模块
    public static ScrollPane addPaneScroll;
    //添加好友找不到模块
    public static Pane findNull;
    //用户管理模块
    public static UserManager userManager = new UserManager();
    //群聊管理模块
    public static GroupChatManager groupChatController = new GroupChatManager();
    //好友优先级计数
    public static int cnt = 0;
    public static Pane emojPane;
    public static Stage groupStage;

    //添加好友面板
    public static Stage addPageStage;

    //图片路径前后缀
    public static String ImgPrefix = "img/";
    public static String ImgSuffix = ".jpg";
    //根据avatorId获得图片id
    public static String getImgUrl(Integer url){

        return ImgPrefix+url+ImgSuffix;
    }
    //关闭右侧个人信息框
    public static void closeMoreInfo(){
        AnchorPane root = (AnchorPane)Main.stage.getScene().getRoot();
        if(Main.stage.getWidth() > 1000){
            root.getChildren().remove(4);
            Main.stage.setWidth(928);
        }
    }
}
