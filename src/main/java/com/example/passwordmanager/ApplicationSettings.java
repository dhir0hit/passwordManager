package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class ApplicationSettings implements Initializable {

    @FXML
    private Button EditHintButton;

    @FXML
    private Button EditMailButton;

    @FXML
    private Button EditNameButton;

    @FXML
    private Button EditPassButton;

    @FXML
    private ScrollPane HomePageWindow;

    @FXML
    private Button SaveHintButton;

    @FXML
    private Button SaveMailButton;

    @FXML
    private Button SaveNameButton;

    @FXML
    private Button SavePassButton;

    @FXML
    private TextField accessHintInput;

    @FXML
    private Text accessHintOutput;

    @FXML
    private TextField accessPassInput;

    @FXML
    private Text accessPassOutput;

    @FXML
    private Rectangle highLightColor_Show;

    @FXML
    private TextField mailInput;

    @FXML
    private Text mailOutput;

    @FXML
    private TextField nameInput;

    @FXML
    private Text nameOutput;

    @FXML
    private Rectangle primaryColor_Show;

    @FXML
    private Text secondaryColor_Show;


    //    ArrayList<String> AppTheme = new ArrayList<>();
    String[] AppTheme = new String[5];
    String primaryColor, secondaryColor, highlightColor;

    @FXML
    void CancelThemeChange(ActionEvent event) {

    }

    @FXML
    void EditHint(ActionEvent event) {
        accessHintOutput.setVisible(false);
        accessHintInput.setVisible(true);
        EditHintButton.setVisible(false);
        SaveHintButton.setVisible(true);
    }

    @FXML
    void EditMail(ActionEvent event) {
        mailOutput.setVisible(false);
        mailInput.setVisible(true);
        EditMailButton.setVisible(false);
        SaveMailButton.setVisible(true);
    }

    @FXML
    void EditName(ActionEvent event) {
        nameOutput.setVisible(false);
        nameInput.setVisible(true);
        EditNameButton.setVisible(false);
        SaveNameButton.setVisible(true);
    }

    @FXML
    void EditPass(ActionEvent event) {
        accessPassOutput.setVisible(false);
        accessPassInput.setVisible(true);
        EditPassButton.setVisible(false);
        SavePassButton.setVisible(true);
    }

    @FXML
    void SaveHint(ActionEvent event) {
        accessHintOutput.setVisible(true);
        accessHintInput.setVisible(false);
        EditHintButton.setVisible(true);
        SaveHintButton.setVisible(false);

        accessHintOutput.setText(accessHintInput.getText());
        accessData.changeAccessPass(accessPassOutput.getText(), accessHintOutput.getText());

    }

    @FXML
    void SaveMail(ActionEvent event) {
        mailOutput.setVisible(true);
        mailInput.setVisible(false);
        EditMailButton.setVisible(true);
        SaveMailButton.setVisible(false);

        mailOutput.setText(mailInput.getText());
        accessData.ChangeAccessInfo(nameOutput.getText(), mailOutput.getText());
    }

    @FXML
    void SaveName(ActionEvent event) {
        nameOutput.setVisible(true);
        nameInput.setVisible(false);
        EditNameButton.setVisible(true);
        SaveNameButton.setVisible(false);

        nameOutput.setText(nameInput.getText());
        accessData.ChangeAccessInfo(nameOutput.getText(), mailOutput.getText());
    }

    @FXML
    void SavePass(ActionEvent event) {
        accessPassOutput.setVisible(true);
        accessPassInput.setVisible(false);
        EditPassButton.setVisible(true);
        SavePassButton.setVisible(false);

        accessPassOutput.setText(accessPassInput.getText());
        accessData.changeAccessPass(accessPassOutput.getText(), accessHintOutput.getText());
    }

    @FXML
    void SaveThemeChange(ActionEvent event) {
        accessData.ChangeTheme(AppTheme[0], AppTheme[1], AppTheme[2], AppTheme[3], AppTheme[4]);
    }

    @FXML
    void Theme1(ActionEvent event) {
        AppTheme[0] = "#333533";
        AppTheme[1] = "#202020";
        AppTheme[2] = "#ffd100";
        AppTheme[3] = "#D6D6D6";
        AppTheme[4] = "#adadad";
        setSampleTheme();
    }

//    @FXML
//    void Theme2(ActionEvent event) {
//        AppTheme[0] = "linear-gradient(to top right, #0081b5, #dce36e)";
//        AppTheme[1] = "#0d0702";
//        AppTheme[2] = "#020330";
//        AppTheme[3] = "#D6D6D6";
//        AppTheme[4] = "#adadad";
//        setSampleTheme();
//    }
    @FXML
    void Theme2(ActionEvent event) {
        AppTheme[0] = "#333533";
        AppTheme[1] = "#0d0702";
        AppTheme[2] = "#020330";
        AppTheme[3] = "#D6D6D6";
        AppTheme[4] = "#adadad";
        setSampleTheme();
    }

    @FXML
    void Theme3(ActionEvent event) {
        AppTheme[0] = "#cdc7f5";
        AppTheme[1] = "#0d0702";
        AppTheme[2] = "#3f7bc4";
        AppTheme[3] = "#D6D6D6";
        AppTheme[4] = "#adadad";
        setSampleTheme();
    }
    @FXML
    void Theme4(ActionEvent event) {
        AppTheme[0] = "#ffffff";
        AppTheme[1] = "#d6e6ed";
        AppTheme[2] = "#50545d";
        AppTheme[3] = "#D6D6D6";
        AppTheme[4] = "#adadad";
        setSampleTheme();
    }

    @FXML
    void Theme5(ActionEvent event) {
        AppTheme[0] = "#333533";
        AppTheme[1] = "#202020";
        AppTheme[2] = "#f0401f";
        AppTheme[3] = "#D6D6D6";
        AppTheme[4] = "#adadad";
        setSampleTheme();
    }

    @FXML
    void Theme6(ActionEvent event) {
        AppTheme[0] = "#303a32";
        AppTheme[1] = "#202020";
        AppTheme[2] = "#dce63f";
        AppTheme[3] = "#D6D6D6";
        AppTheme[4] = "#adadad";
        setSampleTheme();
    }
    @FXML
    void Theme7(ActionEvent event) {
        AppTheme[0] = "#d6e6ed";
        AppTheme[1] = "#ffffff";
        AppTheme[2] = "#a4c3c9";
        AppTheme[3] = "#080F18";
        AppTheme[4] = "#50545d";
        setSampleTheme();
    }
    @FXML
    void Theme8(ActionEvent event) {
        AppTheme[0] = "#303a32";
        AppTheme[1] = "#202020";
        AppTheme[2] = "linear-gradient(to top right, #0081b5, #dce36e)";
        AppTheme[3] = "#D6D6D6";
        AppTheme[4] = "#adadad";
        setSampleTheme();
    }
    @FXML
    void Theme9(ActionEvent event) {
        AppTheme[0] = "#d6e6ed";
        AppTheme[1] = "#ffffff";
        AppTheme[2] = "linear-gradient(to top right, #d6e6ed, #beecf5)";
        AppTheme[3] = "#080F18";
        AppTheme[4] = "#50545d";
        setSampleTheme();
    }

    void setSampleTheme() {
        primaryColor_Show.setStyle("-fx-fill: " + AppTheme[0]);
        secondaryColor_Show.setStyle("-fx-fill: " + AppTheme[1]);
        highLightColor_Show.setStyle("-fx-fill: " + AppTheme[2]);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        short count = 0;

        nameOutput.setText(accessData.getAccessUserName());
        mailOutput.setText(accessData.getAccessMail());
        accessPassOutput.setText(accessData.ACCESSPASS);
        accessHintOutput.setText(accessData.getAccessPass().split("~")[1]);



        try {
            Scanner scanner = new Scanner(new File("src/main/resources/com/example/passwordmanager/css/Theme.css"));
            while (scanner.hasNextLine()) {
                String tempString = scanner.nextLine();
                if (tempString.contains("-fx-background-color")) {
                    AppTheme[count++] = String.valueOf(tempString.subSequence(23, tempString.indexOf(';')));
                    System.out.println(AppTheme[count - 1]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        setSampleTheme();
    }
}
