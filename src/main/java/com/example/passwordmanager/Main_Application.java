package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.List;
import java.util.ResourceBundle;

public class Main_Application implements Initializable {

    @FXML
    private TextField AccountSearchFilter;

    @FXML
    private VBox MainWindow;

    @FXML
    private Text accessGreetingsOutput;

    @FXML
    private Text accessNameOutput;

    @FXML
    private SVGPath dayIconGreeting;

    @FXML
    private Button missingCredentialAccountFilterButton;

    @FXML
    private SVGPath nightIconGreeting;

    @FXML
    void ArchivedAccountsFilter(ActionEvent event) {

    }

    @FXML
    void DeleteAccounts(ActionEvent event) {

    }

    @FXML
    void FilterAccountList(KeyEvent event) {

    }

    @FXML
    void HelpWindow(ActionEvent event) {

    }

    @FXML
    void IdenticalAccountMailFilter(ActionEvent event) {

    }

    @FXML
    void IdenticalPasswordFilter(ActionEvent event) {

    }

    @FXML
    void IdenticalAccountWebsiteFilter(ActionEvent event) {

    }

    @FXML
    void MissingCredentialAccountListFilter(ActionEvent event) {

    }

    @FXML
    void OpenHomeWindow(ActionEvent event) {
        openWindow("HomePage");
    }

    @FXML
    void RecentCreatedAccounts(ActionEvent event) {

    }

    @FXML
    void RecentlyDeletedAccountsFilter(ActionEvent event) {

    }

    @FXML
    void WeakAccountFilterButton(ActionEvent event) {

    }

    @FXML
    void createNewAccount(ActionEvent event) {

    }

    @FXML
    void favoriteAccountsList(ActionEvent event) {

    }

    @FXML
    void openAccountsWindow(ActionEvent event) {
        openWindow("AccountsPage",
                accessData.accountPlatforms,
                accessData.accountNames,
                accessData.accountMails,
                accessData.accountIcons
        );
    }

    @FXML
    void passwordManagerAccessOptions(ActionEvent event) {

    }

    @FXML
    void refreshAccounts(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        accessNameOutput.setText(accessData.getAccessUserName());

        String[] timeGreeting = {"Good Morning", "Good Afternoon", "Good Evening"};
        Boolean[] timeGreetingLogo = {true, true, false};


        accessGreetingsOutput.setText(timeGreeting[greetingForUser(null)]);
        dayIconGreeting.setVisible(timeGreetingLogo[greetingForUser(null)]);
        nightIconGreeting.setVisible(!timeGreetingLogo[greetingForUser(null)]);

        accessData.getAccounts();

        openWindow("HomePage");
    }


    public static short greetingForUser(LocalTime currentTime) {
        short result = -1;

        if (currentTime == null) {
            currentTime = LocalTime.now();
        }

        /*setting up time greeting in application*/
        if (currentTime.isAfter(LocalTime.of(17, 59, 59))) {
            result = 2;
        }
        else if (currentTime.isAfter(LocalTime.of(11, 59, 59))){
            result = 1;
        }
        else if (currentTime.isBefore(LocalTime.of(12, 0))) {
            result = 0;
        }
        return result;
    }

    void openWindow(String pageName) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pageName + ".fxml"));
        Object pane = null;
        try {
            switch (pageName) {
                case "HomePage":
                    pane = (ScrollPane) fxmlLoader.load();
                    break;
                default:
                    pane = (ScrollPane) fxmlLoader.load();
                    break;
            }
            if (!MainWindow.getChildren().contains(pane)) {
                if (MainWindow.getChildren().size() == 2) {
                    System.out.println(MainWindow.getChildren().size());
                    MainWindow.getChildren().remove(1);
                }
                MainWindow.getChildren().add((Node) pane);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void openWindow(String pageName, List<String> AccountPlatforms, List<String> AccountNames, List<String> AccountMail, List<String> AccountIcon) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pageName + ".fxml"));
        Object pane = null;
        try {
            switch (pageName) {
                case "AccountsPage":
                    pane = (HBox) fxmlLoader.load();
                    AccountsPage_Controller.accountPlatformList = AccountPlatforms;
                    AccountsPage_Controller.accountNameList = AccountNames;
                    AccountsPage_Controller.accountMailList = AccountMail;
                    AccountsPage_Controller.accountIconList = AccountIcon;
                    break;
                default:
                    pane = (ScrollPane) fxmlLoader.load();
                    break;
            }
//            pane.fitToHeightProperty().set(true);

            if (!MainWindow.getChildren().contains(pane)) {
                if (MainWindow.getChildren().size() == 2) {
                    System.out.println(MainWindow.getChildren().size());
                    MainWindow.getChildren().remove(1);
                }
                MainWindow.getChildren().add((Node) pane);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}