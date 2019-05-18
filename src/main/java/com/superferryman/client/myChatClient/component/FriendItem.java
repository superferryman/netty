package com.superferryman.client.myChatClient.component;

import com.superferryman.client.myChatClient.api.SendAPI;
import com.superferryman.client.myChatClient.bean.UserInfoVo;
import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class FriendItem {

    public Pane getChatFriendItem(String imgUrl,String name,String message,String id){
        Pane pane = new Pane();
        //初始化容器
        initPane(pane,255,80);
        //装配控件
        pane.getChildren().addAll(getImageView(imgUrl,50,50),getNameLabe(name),getMessLabel(message),getMessagePoint());
        pane.setId(id);
        //添加监听事件
        addEvent(pane);
        return pane;
    }

    public Pane getFriendItem(String imgUrl,String name,String id){
        Pane pane  =  new Pane();
        initPane(pane,255,80);
        Label label = getNameLabe(name);
        label.setLayoutY(25);
        label.setLayoutX(90);
        pane.getChildren().addAll(getImageView(imgUrl,50,50),label);
        pane.setId(id);
        setEnterStyle(pane);
        return pane;
    }

    public Pane getSimpleFriendItem(String imgUrl,String name,String id, Boolean flag)
    {
        Pane pane = new Pane();
        initPane(pane,250,66);
        Label label = getNameLabe(name);
        Circle circle = getCircle();
        label.setLayoutY(23);
        label.setLayoutX(75);
        if(flag) {
            pane.getChildren().addAll(getImageView(imgUrl, 40, 40), label, circle);
        } else {
            pane.getChildren().addAll(getImageView(imgUrl,40,40),label);
        }
        pane.setId(id);
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                int cnt = Integer.parseInt(circle.getId());
                cnt++; circle.setId(cnt+"");
                if(cnt % 2 == 1)
                {
                    circle.setFill(Paint.valueOf("rgb(26,173,25)"));
                }
                else
                {
                    circle.setFill(Paint.valueOf("white"));
                }
                GlobalState.groupChatController.renderRightList();
            }
        });
        setEnterStyle(pane);
        return pane;
    }

    public Pane getAddFriendItem(String username,String id,String avatorURL){
        Pane pane = new Pane();
        pane.setPrefSize(180,80);
        ImageView avatorImg = getImageView(avatorURL,60,60);
        avatorImg.setLayoutX(10);
        avatorImg.setLayoutY(10);
        Label idLabel = getLabel(80,10,100,20,"ID："+id,"#797272");
        Label nameLabel = getLabel(80,33,100,20,"昵称："+username,"#797272");
        Button button = getButton(81,56,55,22,12,"+好友","white","-fx-background-color: rgb(143,192,231);");
        button.setFont(Font.font("System",FontWeight.BOLD,12));
        button.setCursor(Cursor.HAND);
        //点击触发添加好友信息发送
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String myId = GlobalState.userManager.getMyInfo().getId();
                //先判断该用户是不是已经是好友了
                if(!GlobalState.userManager.judgeIsFriend(id)){
                    new SendAPI().sendAddFriendMessage(myId,id);
                }
            }
        });
        pane.getChildren().addAll(avatorImg,idLabel,nameLabel,button);
        return pane;
    }

    public Pane getAddGroupItem(String groupName,String groupId,String avatorURL){
        Pane pane = new Pane();
        pane.setPrefSize(180,80);
        ImageView avatorImg = getImageView(avatorURL,60,60);
        avatorImg.setLayoutX(10);
        avatorImg.setLayoutY(10);
        Label idLabel = getLabel(80,10,100,20,"群ID："+groupId,"#797272");
        Label nameLabel = getLabel(80,33,100,20,"群昵称："+groupName,"#797272");
        Button button = getButton(81,56,55,22,12,"+群","white","-fx-background-color: rgb(143,192,231);");
        button.setFont(Font.font("System",FontWeight.BOLD,12));
        button.setCursor(Cursor.HAND);
        //点击触发添加群信息发送
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String myId = GlobalState.userManager.getMyInfo().getId();
                //先判断该用户是不是已经是好友了
                if(!GlobalState.userManager.judgeIsFriend(groupId)){
                    new SendAPI().sendAddGroupMessage(myId,groupId);
                }
            }
        });
        pane.getChildren().addAll(avatorImg,idLabel,nameLabel,button);
        return pane;
    }

    public Button getButton(double x,double y,double w,double h,double size,String word,String textFill,String style){
        Button button = new Button();
        button.setPrefSize(w,h);
        button.setLayoutY(y);
        button.setLayoutX(x);
        button.setText(word);
        button.setTextFill(Paint.valueOf(textFill));
        button.setStyle(style);
        button.setFont(Font.font(size));
        return button;
    }

    public Label getLabel(double x,double y,double w,double h,String word,String textFill){
        Label label = new Label(word);
        label.setPrefSize(w,h);
        label.setLayoutX(x);
        label.setLayoutY(y);
        if(textFill != null) label.setTextFill(Paint.valueOf(textFill));
        return label;
    }

    public Circle getCircle()
    {
        Circle circle = new Circle();
        circle.setId("0");
        circle.setRadius(10);
        circle.setFill(Paint.valueOf("white"));
        circle.setStrokeWidth(1);
        circle.setStroke(Paint.valueOf("rgb(212,212,212)"));
        circle.setLayoutX(220);
        circle.setLayoutY(35);
        return circle;
    }

    public ImageView getMessagePoint()
    {
        ImageView imageView = new ImageView(new Image("img/point.png"));
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        imageView.setLayoutY(7);
        imageView.setLayoutX(60);
        imageView.setStyle("visibility:false");
        //imageView.setVisible(true);
        return imageView;
    }

    private void initPane(Pane pane,double w,double h){
        pane.setPrefSize(w,h);
        pane.setMaxSize(w,h);
        pane.setMinSize(w,h);
        pane.setStyle("isclick:0;");
    }

    private ImageView getImageView(String imgUrl,double w,double h){
        //imageView
        ImageView imageView = new ImageView();
        imageView.setFitHeight(h);
        imageView.setFitWidth(w);
        imageView.setImage(new Image(imgUrl));
        imageView.setLayoutX(20);
        imageView.setLayoutY(13);
        return imageView;
    }

    private Label getNameLabe(String name){
        //nameLabel
        Label nameLabel = new Label(name);
        nameLabel.setLayoutX(87);
        nameLabel.setLayoutY(14);
        nameLabel.setPrefSize(120,23);
        nameLabel.setFont(Font.font("system",FontWeight.BOLD,16));
        return  nameLabel;
    }

    private Label getMessLabel(String message){
        //messLabel
        Label messLabel = new Label();
        messLabel.setLayoutX(87);
        messLabel.setLayoutY(37);
        messLabel.setFont(Font.font(13));
        messLabel.setText(message);
        messLabel.setTextFill(Paint.valueOf("#8a8383"));
        messLabel.setPrefSize(134,36);
        return messLabel;
    }

    public void addEvent(Pane pane){
       setEnterStyle(pane);
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //调出聊天面板
                GlobalState.right.getChildren().remove(2);
                GlobalState.right.getChildren().add(GlobalState.rightPane);
                GlobalState.centerSeparator.setVisible(true);
                //取消其他被选中样式
                for(UserInfoVo v : GlobalState.userManager.getUserList()){
                    v.getUserInfoPane().setStyle("isclick:0;-fx-background-color:null");
                }
                //设置当前焦点对象并渲染相应列表
                GlobalState.userManager.setCurrentUser(pane.getId());
                //设置选中样式
                pane.setStyle("isclick:1;-fx-background-color:rgb(233,233,237);");
                pane.getChildren().get(3).setStyle("visibility: false");
                //修改聊天框上方标题
                GlobalState.userManager.getChatNameLabel().setText("     "+GlobalState.userManager.getCurrentUser().getUserInfo().getUsername());
                //若有开启右侧个人信息框，关闭
                GlobalState.closeMoreInfo();
            }
        });
    }
    public void setEnterStyle(Pane pane)
    {
        //鼠标事件
        pane.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(pane.getStyle().split(";")[0].equals("isclick:0")) {
                    pane.setStyle("isclick:0;-fx-background-color:rgb(233,233,237);");
                }
            }
        });
        pane.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(pane.getStyle().split(";")[0].equals("isclick:0")) {
                    pane.setStyle("isclick:0;-fx-background-color:null");
                }
            }
        });
    }
}
