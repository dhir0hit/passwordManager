package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
        openWindow("AccountsPage", accessData.reUsedMails);
    }

    @FXML
    void IdenticalPasswordFilter(ActionEvent event) {
        openWindow("AccountsPage", accessData.reUsedPassWords);
    }

    @FXML
    void NewAccountFilterButton(ActionEvent event) {
        openWindow("AccountsPage", accessData.newAccounts);
    }

    @FXML
    void NormalAccountFilterButton(ActionEvent event) {
        openWindow("AccountsPage", accessData.NormalPassWords);
    }

    @FXML
    void StrongAccountFilterButton(ActionEvent event) {
        openWindow("AccountsPage", accessData.StrongPassWords);
    }

    @FXML
    void WeakAccountFilterButton(ActionEvent event) {
        openWindow("AccountsPage", accessData.WeakPassWords);
    }

    @FXML
    void favoriteAccountsList(ActionEvent event) {
        openWindow("AccountsPage", accessData.FavoriteAccounts);
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

    void openWindow(String pageName, List<Integer> AccountIds) {
        VBox parent = (VBox) HomePageWindow.getParent();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pageName + ".fxml"));
        Object pane = null;
        try {
            switch (pageName) {
                case "AccountsPage":
                    Main_Application.accountIdsList = AccountIds;
                    pane = (HBox) fxmlLoader.load();
                    break;
                default:
                    pane = (ScrollPane) fxmlLoader.load();
                    break;
            }

            VBox.setVgrow((Node) pane, Priority.ALWAYS);
            parent.getChildren().remove(1);
            parent.getChildren().add((Node) pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
