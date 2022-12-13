package com.example.projectfx;

import animatefx.animation.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        //Scene scene = new Scene(fxmlLoader.load());
        //stage.initStyle(StageStyle.UNDECORATED); //for disappear the close panel
        stage.setTitle("Our Big Project!!");
        stage.setScene(new Scene(root));
        stage.show();
        new FadeIn(root).play();
    }

    public static void main(String[] args) {
        launch();
    }
}