package com.superferryman.client.myChatClient.controller;


import com.superferryman.client.myChatClient.Main;
import com.superferryman.client.myChatClient.api.AcceptAPI;
import com.superferryman.client.myChatClient.api.SendAPI;
import com.superferryman.client.myChatClient.bean.TalkItem;
import com.superferryman.client.myChatClient.bean.User;
import com.superferryman.client.myChatClient.bean.UserInfo;
import com.superferryman.client.myChatClient.component.ChatItem;
import com.superferryman.client.myChatClient.component.EmojPane;
import com.superferryman.client.myChatClient.component.InfoPage;
import com.superferryman.client.myChatClient.component.MessageItemFactory;
import com.superferryman.client.myChatClient.utils.EmojSet;
import com.superferryman.client.myChatClient.utils.GlobalState;
import com.superferryman.client.myChatClient.utils.MessagePrepare;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;


public class RightController {
    public Pane close;
    public Pane biggest;
    public Pane smallest;
    public TextField textField;
    public ToolBar toolBar;
    public VBox scrollPaneVBox;
    public ScrollPane scrollerPane;
    public TextFlow textFlow;
    public Pane emojP;
    public Pane fileP;
    public Pane moreP;
    public Label chatNameLabel;
    public Pane moreInfo;
    public double xOffset;
    public double yOffset;
    public double stageX;
    public double stageY;
    public ChatItem chatItem = new ChatItem();
    public static Stage emojStage = null;
    public static TextField myTextField;
    public boolean emojClicked = false;
    public boolean fileClicked = false;
    private int tag = 0;
    private MessageItemFactory messageItemFactory = new MessageItemFactory();
    public void mouseDragged(MouseEvent mouseEvent) {
        Main.stage.setX(stageX+ mouseEvent.getScreenX() - xOffset);
        Main.stage.setY(stageY+ mouseEvent.getScreenY() - yOffset);
    }

    public void mousePressed(MouseEvent mouseEvent) {
        xOffset = mouseEvent.getScreenX();
        yOffset = mouseEvent.getScreenY();
        stageX = Main.stage.getX();
        stageY = Main.stage.getY();
    }

    public void closeClicked(MouseEvent mouseEvent) {

        new SendAPI().closeAll();
        Main.stage.close();
    }

    public void closeEntered(MouseEvent mouseEvent) {
        close.setStyle("-fx-background-color:rgb(248,84,84)");
    }

    public void closeExited(MouseEvent mouseEvent) {
        close.setStyle("-fx-background-color:null");
    }

    public void biggestClicked(MouseEvent mouseEvent) {

    }

    public void biggestEntered(MouseEvent mouseEvent) {
        biggest.setStyle("-fx-background-color:rgb(81,183,220)");
    }

    public void smallExited(MouseEvent mouseEvent) {
        biggest.setStyle("-fx-background-color:null");
    }

    public void smallestClick(MouseEvent mouseEvent) {
        Main.stage.setIconified(true);
    }

    public void smallestEntered(MouseEvent mouseEvent) {
        smallest.setStyle("-fx-background-color:rgb(81,183,220)");
    }

    public void smallestExited(MouseEvent mouseEvent) {
        smallest.setStyle("-fx-background-color:null");
    }

    public void toolBarClicked(MouseEvent mouseEvent) {
        tag--;
        if(tag != 0)
            closeEmojPane();
        tag = 0;
    }

    @FXML
    public void initialize(){
        myTextField = textField;
        GlobalState.chatVBox = scrollPaneVBox;
        GlobalState.scrollerPane = scrollerPane;
        GlobalState.textField = textField;
        GlobalState.userManager.setChatVBox(scrollPaneVBox);
        GlobalState.userManager.setChatNameLabel(chatNameLabel);
        GlobalState.emojPane = emojP;
        //关闭表情面板
        Main.root.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                closeEmojPane();
            }
        });
        /**初始化表情数据*/
        for(int i = 0; i<84; i++)
        {
            String imgName;
            if(i == 0) imgName = "000";
            else if(i < 10) imgName = "00"+i;
            else imgName = "0"+i;
            EmojSet.emojSet.add(imgName);
        }
    }
    public void textFieldPressed(KeyEvent keyEvent) {

        if(keyEvent.getCode().getName().equals("Enter") && GlobalState.userManager.getCurrentUser().getUserInfo() != null)
        {
                new MessagePrepare().sendWordMessage(textField.getText(),scrollPaneVBox,0,null);
                textField.clear();
        }
    }

    public void textFieldReleased(KeyEvent keyEvent) {
        if(keyEvent.getCode().getName().equals("Enter"))
        {
            scrollerPane.setVvalue(1);
        }
    }

    public void mouseExited(MouseEvent mouseEvent) {
        scrollerPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    }

    public void mouseEntered(MouseEvent mouseEvent) {
        scrollerPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    }

    public void emojClick(MouseEvent mouseEvent) {
        tag = 1;
        if(!emojClicked)
        {
            emojStage = new EmojPane().getEmojPane();
            emojClicked = true;
            emojP.setStyle("-fx-background-color:rgb(225,228,230);-fx-background-radius: 5px");
            emojStage.show();
        }
        else
        {
            closeEmojPane();
        }
    }

    public void closeEmojPane()
    {
        emojP.setStyle("-fx-background-color:null;-fx-background-radius: 5px");
        emojClicked = false;
        if(emojStage != null)
        {
            emojStage.close();
            emojStage = null;
        }
    }

    public void textFieldMouseClicked(MouseEvent mouseEvent) {
        closeEmojPane();
    }

    public void fileMouseClicked(MouseEvent mouseEvent) {
        fileClicked = true;
        fileP.setStyle("-fx-background-color:rgb(225,228,230);-fx-background-radius: 5px");
        //打开文件选择器
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
        if(file != null){
            //发送文件信息
            new MessagePrepare().sendWordMessage(file.getName(),scrollPaneVBox,1,file);
        }
        fileClicked = false;
        fileP.setStyle("-fx-background-color:null;-fx-background-radius: 5px");
        //刷新滚动条
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                    scrollerPane.setVvalue(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    //详细信息项
    public void moreInfoEntered(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView)moreInfo.getChildren().get(0);
        imageView.setImage(new Image("img/more3.png"));
    }

    public void moreInfoExited(MouseEvent mouseEvent) {
        ImageView imageView = (ImageView)moreInfo.getChildren().get(0);
        imageView.setImage(new Image("img/more2.png"));
    }

    public void moreInfoClicked(MouseEvent mouseEvent) {
        //获取被点击项信息
        UserInfo userInfo = GlobalState.userManager.getCurrentUser().getUserInfo();
        if(userInfo == null) return;
        String id = userInfo.getId();
        String name = userInfo.getUsername();
        String avatorURL = userInfo.getAvatorURL();
        //获取根容器
        AnchorPane root = (AnchorPane)Main.stage.getScene().getRoot();
        if(root.getChildren().size() <= 4) {
            //展示详细信息
            Main.stage.setWidth(1200);
            if(userInfo.isGroup()){
                root.getChildren().add(new InfoPage().getGroupPane(name, id, avatorURL));
                //发送请求列表
                new SendAPI().sendGroupUsersListMessage(GlobalState.userManager.getMyInfo().getId(),id);
            }else{
                root.getChildren().add(new InfoPage().getPersonPane(name, id, avatorURL));
            }
        }else
        {
            root.getChildren().remove(4);
            Main.stage.setWidth(928);
        }
    }

    //菜单栏移入移出效果
    public void emojMouseExited(MouseEvent mouseEvent) {
        if(!emojClicked)
            exitStyle(emojP);
    }
    public void emojMouseEntered(MouseEvent mouseEvent) {
        if(!emojClicked)
            enterStyle(emojP);
    }
    public void fileMouseEntered(MouseEvent mouseEvent) {
        if(!fileClicked)
            enterStyle(fileP);
    }
    public void fileMouseExited(MouseEvent mouseEvent) {
        if(!fileClicked)
            exitStyle(fileP);
    }
    public void moreMouseEntered(MouseEvent mouseEvent) {
        enterStyle(moreP);
    }
    public void moreMouseExited(MouseEvent mouseEvent) {
        exitStyle(moreP);
    }
    public void enterStyle(Pane pane){
        pane.setStyle("-fx-background-color:rgb(236,239,241);-fx-background-radius: 5px");
    }
    public void exitStyle(Pane pane){
        pane.setStyle("-fx-background-color:null;-fx-background-radius: 5px");
    }
}
