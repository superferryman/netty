package com.superferryman.client.myChatClient.test;

import com.sun.javafx.robot.FXRobot;
import com.sun.javafx.robot.FXRobotImage;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.channels.FileChannel;

public class TestMain extends Application {
    @Override
    public void start(final Stage stage) throws Exception {
        final Group group = new Group();
        final Scene scene = new Scene(group, 600, 400, Color.GHOSTWHITE);
        stage.setScene(scene);
        stage.setTitle("JavaFX 2 Animations");
        stage.show();
        ImageView imageView = new ImageView("file:G:\\uploads\\1.jpg");
        group.getChildren().add(imageView);
    }

    public static void main(final String[] arguments) {
        Application.launch(arguments);
    }
}
