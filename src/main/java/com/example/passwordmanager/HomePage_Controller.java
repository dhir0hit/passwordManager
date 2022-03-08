package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePage_Controller implements Initializable {

    @FXML
    private Label FavoriteAccountsCountOutput;

    @FXML
    private ScrollPane HomePageWindow;

    @FXML
    private ProgressBar OverallPasswordStrengthBar;

    @FXML
    private Text OverallPasswordStrengthPercentageOutput;

    @FXML
    private Label ReUsedEmailCountOutput;

    @FXML
    private Label ReusedPasswordCountOutput;

    @FXML
    private Label ReusedWebsiteCountOutput;

    @FXML
    private Text StrongPasswordCountOutput;

    @FXML
    private Text TotalAccountsCountOutput;

    @FXML
    private Text WeakPasswordCountOutput;

    @FXML
    private Label newlyCreatedAccountsCountOutput;

    @FXML
    private Label normalPassCountOutput;

    @FXML
    private Text NormalPasswordCountOutput;

    @FXML
    private Label strongPassCountOutput;

    @FXML
    private Label weakPassCountOutput;

    @FXML
    void IdenticalAccountMailFilter(ActionEvent event) {

    }

    @FXML
    void IdenticalPasswordFilter(ActionEvent event) {

    }

    @FXML
    void NewAccountFilterButton(ActionEvent event) {

    }

    @FXML
    void NormalAccountFilterButton(ActionEvent event) {

    }

    @FXML
    void StrongAccountFilterButton(ActionEvent event) {

    }

    @FXML
    void WeakAccountFilterButton(ActionEvent event) {

    }

    @FXML
    void favoriteAccountsList(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        OverallPasswordStrengthBar.setProgress(accessData.OverAllPasswordStrength);
        OverallPasswordStrengthPercentageOutput.setText(String.format("%.2f", accessData.OverAllPasswordStrength*100) + "%");

        FavoriteAccountsCountOutput.setText(String.valueOf(accessData.FavoriteAccounts.size()));
        ReusedPasswordCountOutput.setText(String.valueOf(accessData.reUsedPassWords.size()));
        ReUsedEmailCountOutput.setText(String.valueOf(accessData.reUsedMails.size()));
        ReusedWebsiteCountOutput.setText(String.valueOf(accessData.reUsedWebsites.size()));
        newlyCreatedAccountsCountOutput.setText(String.valueOf(accessData.newAccounts.size()));

        StrongPasswordCountOutput.setText(String.valueOf(accessData.StrongPassWords.size()));
        strongPassCountOutput.setText(String.valueOf(accessData.StrongPassWords.size()));
        NormalPasswordCountOutput.setText(String.valueOf(accessData.NormalPassWords.size()));
        normalPassCountOutput.setText(String.valueOf(accessData.NormalPassWords.size()));
        WeakPasswordCountOutput.setText(String.valueOf(accessData.WeakPassWords.size()));
        weakPassCountOutput.setText(String.valueOf(accessData.WeakPassWords.size()));
        TotalAccountsCountOutput.setText(String.valueOf(accessData.AccountCount));



        if (accessData.OverAllPasswordStrength <= 0.18) {
            OverallPasswordStrengthBar.setStyle("-fx-accent:  #f8301e");
        }else if (accessData.OverAllPasswordStrength <=0.40) {
            OverallPasswordStrengthBar.setStyle("-fx-accent: #f8961e");
        } else if (accessData.OverAllPasswordStrength <=0.60) {
            OverallPasswordStrengthBar.setStyle("-fx-accent: #f9c74f");
        } else {
            OverallPasswordStrengthBar.setStyle("-fx-accent: #90be6d");
        }
    }
}
