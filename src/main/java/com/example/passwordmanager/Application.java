package com.example.passwordmanager;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.FileInputStream;
import java.io.FileOutputStream;
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

    protected static void encryptFile(int Key) {
        try{
            int ctr;
            Key = Key + 1000;

            FileInputStream inputStream = new FileInputStream("accountDataFile.csv");
            FileOutputStream outputStream = new FileOutputStream("accountsInfo.csv");

            while ((ctr = inputStream.read()) != -1) {
                ctr -= Key;
                System.out.print((char)ctr);
                outputStream.write(ctr);
//                Thread.sleep(100);
            }
            outputStream.close();
        } catch (Exception error) {
            error.printStackTrace();
        }

//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(new File(OutputPath));
//            while (scanner.hasNextLine()) {
//                System.out.println(scanner.nextLine());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }

    protected static void decryptFile(int Key) {
        try{
            int ctr;
            Key = Key + 1000;


//            FileInputStream inputStream = new FileInputStream("src/text.txt");
            FileInputStream inputStream = new FileInputStream("accountsInfo.csv");
//            FileOutputStream outputStream = new FileOutputStream("src/output.txt");
            FileOutputStream outputStream = new FileOutputStream("accountDataFile.csv");

            while ((ctr = inputStream.read()) != -1) {
                ctr += Key;
                System.out.print((char)ctr);
                outputStream.write(ctr);
//                Thread.sleep(100);
            }
            outputStream.close();
        } catch (Exception error) {
            error.printStackTrace();
        }

//        Scanner scanner = null;
//        try {
//            scanner = new Scanner(new File(OutputPath));
//            while (scanner.hasNextLine()) {
//                System.out.println(scanner.nextLine());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }
}