package com.example.passwordmanager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Application extends javafx.application.Application {
    private static Stage startWindow;
    private static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("startWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.setAlwaysOnTop(true);
        stage.show();
        startWindow = stage;
    }

    public static void main(String[] args) {
        launch();
    }

    public static void openPasswordManager() {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("Main_Application.fxml"));
        Scene scene = null;
        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage newStage = new Stage();
        newStage.setTitle("Password Manager");
        newStage.setScene(scene);
        newStage.show();
        primaryStage = newStage;
    }

    public static void closeWindow(String stageName) {
        if (stageName.equals("startWindow")) {
            startWindow.close();
        }else if (stageName.equals("primaryStage")){
            primaryStage.close();
        }
    }

}