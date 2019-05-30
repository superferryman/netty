package com.superferryman.client.myChatClient.component;

import com.superferryman.client.myChatClient.utils.ImageWindow;
import com.superferryman.client.myChatClient.utils.EmojSet;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


public class ChatItem extends ImageItem{
    public HBox getChatItem(String username, String imgUrl, String message, boolean flag){
        double layoutY = 15;
        if(username == null || username.equals("") || flag == true)
            layoutY = 5;
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(15,15,15,15));
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefSize(400,50);
        //自己的聊天项
        if(flag == true) {
            hBox.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
        TextFlow textFlow = getMessage(message,flag);
        textFlow.setLayoutY(layoutY);
        Label nameLabel = getLabel(14,-3,200,20,username,14,"#b2b2b2");
        ImageView imageView = getImageView(imgUrl,35,35,-1,-1);
        anchorPane.getChildren().addAll(nameLabel,textFlow);
        hBox.getChildren().addAll(imageView,anchorPane);
        return hBox;
    }

    public HBox getChatImgItem(String username,String avatorUrl,String imgUrl,boolean flag){
        HBox hBox = getChatItem(username,avatorUrl,"",flag);
        TextFlow textFlow = (TextFlow)((AnchorPane)hBox.getChildren().get(1)).getChildren().get(1);
        textFlow.getChildren().remove(0,textFlow.getChildren().size());
        ImageView imageView = new ImageItem().getImageView(imgUrl,100,150,-1,-1);
        imageView.setPreserveRatio(true);
        textFlow.setCursor(Cursor.HAND);
        textFlow.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    new ImageWindow().start(imgUrl);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        textFlow.getChildren().addAll(imageView);
        return hBox;
    }

    public Label getLabel(double x,double y,double w,double h,String word,int size,String textFill){
        Label label = new Label(word);
        label.setPrefSize(w,h);
        label.setLayoutX(x);
        label.setLayoutY(y);
        label.setFont(Font.font(size));
        if(textFill != null) label.setTextFill(Paint.valueOf(textFill));
        return label;
    }

    /**
     * 获取消息控件
     * */
    private TextFlow getMessage(String message,Boolean flag){
        TextFlow textFlow = new TextFlow();
        textFlow.setMaxWidth(300);
        String [] messArray = message.split("/");
        if(messArray.length > 1){
            for(String s : messArray){
                if(s.length() >= 3 && EmojSet.emojSet.contains(s.substring(0,3)) != false){
                    ImageView emoj = getImageView("img/emoj"+s.substring(0,3)+".png",28,28,-1,-1);
                    textFlow.getChildren().add(emoj);
                    if(s.substring(3).length() > 0)
                    {
                        Text text = new Text();
                        text.setFont(Font.font(14));
                        text.setText(s.substring(3));
                        textFlow.getChildren().add(text);
                    }
                }
                else if(s.length() > 0 ){
                    Text text = new Text();
                    text.setFont(Font.font(14));
                    text.setText(s);
                    textFlow.getChildren().add(text);
                }
            }
        }
        else{
            Text text = new Text();
            text.setFont(Font.font(14));
            text.setText(message);
            textFlow.getChildren().add(text);
        }
        if(textFlow.getChildren().size() == 1){
            textFlow.getChildren().add(new Text(" "));
        }
        textFlow.setPadding(new Insets(5,10,5,10));
        textFlow.setTranslateX(15);
        textFlow.setTranslateY(5);
        textFlow.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);


        if(flag == true) {
            textFlow.setStyle("-fx-background-color: rgb(62,223,255);-fx-background-radius:3");
        } else {
            textFlow.setStyle("-fx-background-color: rgb(255,255,255);-fx-background-radius:3");
        }
        return textFlow;
    }
}
