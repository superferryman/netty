package com.superferryman.client.myChatClient.component;

import com.superferryman.client.myChatClient.utils.GlobalState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageItem {
    public ImageView getImageView(String imgUrl, double width, double height,double x,double y){
        //imgUrl = GlobalState.imgURL;
        //imageView
        ImageView imageView = new ImageView();
        imageView.setFitHeight(height);
        imageView.setFitWidth(width);
        if(y == -1) {
            imageView.setTranslateY(5);
        }
        else{
            imageView.setLayoutY(y);
        }
        if(x != -1){
            imageView.setLayoutX(x);
        }
        imageView.setImage(new Image(imgUrl));
        return imageView;
    }
}
