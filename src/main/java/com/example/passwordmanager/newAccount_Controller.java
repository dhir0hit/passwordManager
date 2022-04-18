package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class newAccount_Controller implements Initializable {

    @FXML
    private TextArea newAccountAdditionalInfo;

    @FXML
    private TextField newAccountEmail;

    @FXML
    private TextField newAccountName;

    @FXML
    private Text newAccountNumber;

    @FXML
    private PasswordField newAccountPassword;

    @FXML
    private TextField newAccountPasswordVisible;

    @FXML
    private TextField newAccountPlatform;

    @FXML
    private TextField newAccountWebsite;

    @FXML
    private CheckBox passwordDisplayCheckBox;

    @FXML
    private SVGPath visibleOffSvg;

    @FXML
    private SVGPath visibleSvg;

    @FXML
    void CancelAccountCreation(ActionEvent event) {

    }

    @FXML
    void SaveAccountInfo(ActionEvent event) {
        accessData.CreateNewAccount(
                newAccountName.getText(),
                newAccountPassword.getText(),
                newAccountEmail.getText(),
                newAccountPlatform.getText(),
                newAccountWebsite.getText(),
                newAccountAdditionalInfo.getText()
        );
    }

    @FXML
    void passwordDisplay(ActionEvent event) {
        visibleOffSvg.setVisible(passwordDisplayCheckBox.isSelected());
        visibleSvg.setVisible(!passwordDisplayCheckBox.isSelected());

        newAccountPassword.setVisible(!passwordDisplayCheckBox.isSelected());
        newAccountPasswordVisible.setVisible(passwordDisplayCheckBox.isSelected());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
