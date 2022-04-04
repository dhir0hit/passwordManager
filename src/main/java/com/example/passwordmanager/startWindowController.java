package com.example.passwordmanager;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class startWindowController implements Initializable {

    @FXML
    private Text accessUserName;

    @FXML
    private TextField hintInputField;

    @FXML
    private Label hintLabel;

    @FXML
    private Text inputErrorDisplay;

    @FXML
    private Text passwordHintOutput;

    @FXML
    private VBox inputContainer;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private ProgressBar progressBarLoading;

    @FXML
    private Text welcomeMessage1;

    @FXML
    private Text welcomeMessage2;

    protected short passTryCount;

    private String accessPass;

    @FXML
    void SubmitPass(ActionEvent event) {
        submit(passwordInput.getText());
    }

    @FXML
    void closeButtonMethod(ActionEvent event) {
        Application.closeWindow("startWindow");
    }

    @FXML
    void passwordInputCheck(KeyEvent event) {
        inputErrorDisplay.setVisible(false);
        if (event.getCode().equals(KeyCode.ENTER)) {
            submit(passwordInput.getText());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] accessInfo = accessData.getAccessPass().split("~");
        accessPass = accessInfo[0];
        if (accessPass.isEmpty()) {
            getPass();
        } else {
            inputContainer.getChildren().remove(hintInputField);
            passwordHintOutput.setText(accessInfo[1]); // setting password hint
        }
    }

    /**
     * Checking if access should be granted or not by comparing saved and input
     * if user entered wrong password for more than 5 times wrong
     * accounts load which are unable to read
     * @param accessPassInput access password got from user
     * */
    private void submit(String accessPassInput) {
        if (accessPassInput.isEmpty()) {
            System.out.println("empty");
        } else {
            if (accessPass.isEmpty()) {
                accessData.changeAccessPass(accessPassInput, hintInputField.getText());
                openMainWindow();
            } else {
                if (passTryCount > 5) {
                    /*opens wrong accounts*/
                    System.out.println("opens wrong accounts");
                    openMainWindow();
                    Application.decryptFile(Integer.parseInt(accessPassInput));
                } else if (accessPass.equals(accessPassInput)) {
                    openMainWindow();
                    Application.decryptFile(Integer.parseInt(accessPassInput));
                } else {
                    inputErrorDisplay.setVisible(true); // displaying input is wrong
                    passTryCount++; // incrementing try count
                }
            }
        }
    }

    private void getPass() {
        hintLabel.setVisible(false);
        passwordHintOutput.setVisible(false);
        welcomeMessage1.setText("Please Type a Password and Hint");
        accessUserName.setVisible(false);
        welcomeMessage2.setVisible(false);
    }

    private void openMainWindow() {
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnRunning(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
            }
        });
        sleeper.setOnScheduled(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                progressBarLoading.setVisible(true);
                Application.openPasswordManager();
            }
        });
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                System.out.println("hello world");
                Application.closeWindow("startWindow");
            }
        });
        new Thread(sleeper).start();
    }
}
