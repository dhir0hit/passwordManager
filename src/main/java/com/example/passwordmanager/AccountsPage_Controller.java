package com.example.passwordmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;
import javafx.scene.text.Text;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AccountsPage_Controller implements Initializable {

    @FXML
    private Label AccountNameOutput;

    @FXML
    private HBox MenuPageWIndow;

    @FXML
    private Text accountAdditionalInfoOutput;

    @FXML
    private Text accountCreationDateOutput;

    @FXML
    private SVGPath accountFavorite;

    @FXML
    private Text accountIdOutput;

    @FXML
    private ImageView accountLogoOutput;

    @FXML
    private Text accountMailOutput;

    @FXML
    private Text accountModifiedDateOutput;

    @FXML
    private VBox accountOutputList;

    @FXML
    private Text accountPassHiddenOutput;

    @FXML
    private Text accountPassVisibleOutput;

    @FXML
    private Text accountWebsiteOutput;

    @FXML
    private VBox displaySelectedAccountInfo;

    @FXML
    private CheckBox passwordHide;

    @FXML
    private ProgressBar passwordStrength;

    @FXML
    private Text userNameOutput;

    @FXML
    private SVGPath visibilityOff;

    @FXML
    private SVGPath visible;

    protected static Boolean isFavorite;
    protected static List<Integer> accountIdList = new ArrayList<>();

    @FXML
    void AccountMailCopy(ActionEvent event) {
        copyToClipboard(accountMailOutput.getText());
    }

    @FXML
    void AccountPasswordCopy(ActionEvent event) {
        copyToClipboard(accountPassVisibleOutput.getText());
    }

    @FXML
    void AccountWebsiteCopy(ActionEvent event) {
        copyToClipboard(accountWebsiteOutput.getText());
    }


    @FXML
    void EditCurrentAccount(ActionEvent event) {

    }

    @FXML
    void UserNameCopy(ActionEvent event) {
        copyToClipboard(userNameOutput.getText());
    }

    @FXML
    void deleteCurrentAccount(ActionEvent event) {

    }

    @FXML
    void favoriteCurrentAccount(ActionEvent event) {
        accessData.FavoriteAccounts = new ArrayList<>();
        isFavorite = !isFavorite;
        setSelectedAccountFavorite();
        accessData.ChangeFavoriteAccount(isFavorite, Integer.parseInt(accountIdOutput.getText()));

    }

    @FXML
    void passwordShowCheckBox(ActionEvent event) {
        changePasswordOutput();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displaySelectedAccountInfo.setVisible(false);

        loadAccountList();
    }

    public void loadAccountList() {
        accountIdList = Main_Application.accountIdsList;
        HBox tempAccountInfo;
        int accountNo = 0;

        accountOutputList.getChildren().removeAll(accountOutputList.getChildren());

        for (int account: accountIdList) {
            accountNo++;

            tempAccountInfo = new HBox();
            ImageView imageView = new ImageView();
            VBox vBox = new VBox();

            // label 1 style for account name
            Label label1 = new Label();
//            label1.setText("Account Name (Account Name)");
            label1.setText(accessData.accountPlatforms.get(account - 1) + " (" + accessData.accountNames.get(account - 1));
            label1.setStyle("-fx-font-size: 18px");
            label1.getStyleClass().add("text-color");
            label1.prefWidth(144);
            label1.prefHeight(37);
            label1.maxWidth(500);
            label1.setAlignment(Pos.CENTER_LEFT);
            label1.setId("loginList_acc" + accountNo + "Name");
            vBox.getChildren().add(label1);

            // label 2 style for email
            Label label2 = new Label();
//            label2.setText("email");
            label2.setText(accessData.accountMails.get(account - 1));
            label2.setStyle("-fx-font-size: 12px");
            label2.getStyleClass().add("text-color");
            label2.prefWidth(144);
            label2.prefHeight(25);
            label2.maxWidth(500);
            label2.setAlignment(Pos.CENTER_LEFT);
            label2.setId("loginList_acc" + accountNo + "Mail");
            vBox.getChildren().add(label2);

            // vbox style
            vBox.prefWidth(130);
            vBox.prefHeight(60);
            vBox.fillWidthProperty().set(true);
            vBox.setAlignment(Pos.TOP_LEFT);
            vBox.setSpacing(7);

            // styling image view
            try {
                imageView.setImage(new Image(getClass().getResource(accessData.accountIcons.get(account - 1)).toURI().toString(), 60, 60, false, true));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            imageView.setId("loginList_acc" + accountNo + "Image");

            // style temp account info hbox
            tempAccountInfo.getChildren().addAll(imageView, vBox);
            tempAccountInfo.setSpacing(10);
            tempAccountInfo.setPadding(new Insets(5));
            tempAccountInfo.prefWidth(206);
            tempAccountInfo.prefHeight(60);
            tempAccountInfo.fillHeightProperty().set(true);
            tempAccountInfo.setId("account" + accountNo + "OutputValue");
            tempAccountInfo.getStyleClass().add("password_list");


            HBox finalTempAccountInfo = tempAccountInfo;
            tempAccountInfo.setOnMouseClicked(mouseEvent -> {
                displaySelectedAccountInfo.setVisible(true);
                double passwordStrengthPercentage;
                int accountInfoId = accountIdList.get(Integer.parseInt(
                        finalTempAccountInfo.getId()
                                .replace("account","")
                                .replace("OutputValue","")
                ) - 1) -1;

                try {
                    accountLogoOutput.setImage(new Image(getClass().getResource(accessData.accountIcons.get(accountInfoId)).toURI().toString()));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                AccountNameOutput.setText(accessData.accountPlatforms.get(accountInfoId));
                accountIdOutput.setText(String.valueOf(accountInfoId));
                userNameOutput.setText(accessData.accountNames.get(accountInfoId));
                accountMailOutput.setText(accessData.accountMails.get(accountInfoId));
                accountPassVisibleOutput.setText(accessData.accountPasswords.get(accountInfoId));
                accountPassHiddenOutput.setText("●".repeat(accessData.accountPasswords.get(accountInfoId).length()));
                accountWebsiteOutput.setText(accessData.accountWebsites.get(accountInfoId));
                accountAdditionalInfoOutput.setText(accessData.additionalInfos.get(accountInfoId));
                accountCreationDateOutput.setText(String.valueOf(accessData.accountCreationDate.get(accountInfoId)));
                accountModifiedDateOutput.setText(String.valueOf(accessData.accountModifiedDate.get(accountInfoId)));

                passwordStrengthPercentage = accessData.PasswordStrengthPercent(accessData.accountPasswords.get(accountInfoId));
                passwordStrength.setProgress(passwordStrengthPercentage);
                if (passwordStrengthPercentage <= 0.18) {
                    passwordStrength.setStyle("-fx-accent:  #f8301e");
                }else if (passwordStrengthPercentage <=0.40) {
                    passwordStrength.setStyle("-fx-accent: #f8961e");
                } else if (passwordStrengthPercentage <=0.60) {
                    passwordStrength.setStyle("-fx-accent: #f9c74f");
                } else {
                    passwordStrength.setStyle("-fx-accent: #90be6d");
                }

                passwordHide.setSelected(false);
                changePasswordOutput();


                AccountsPage_Controller.isFavorite = accessData.isAccountsFavorite.get(accountInfoId);
                setSelectedAccountFavorite();
            });

            accountOutputList.getChildren().add(tempAccountInfo);
        }
        if (accountIdList.isEmpty()) {
            Label label = new Label();
            label.setText("No Accounts Found");
            label.setStyle("-fx-font-size: 12px");
            label.getStyleClass().add("text-color");
            label.prefWidth(144);
            label.prefHeight(25);
            label.maxWidth(500);
            label.setAlignment(Pos.CENTER);
            accountOutputList.getChildren().add(label);
        }
    }

    void setSelectedAccountFavorite() {
        if (AccountsPage_Controller.isFavorite) {
            accountFavorite.setContent("M 12 17.27 L 18.18 21 l -1.64 -7.03 L 22 9.24 l -7.19 -0.61 L 12 2 L 9.19 8.63 L 2 9.24 l 5.46 4.73 L 5.82 21 L 12 17.27 Z");
            accountFavorite.setStyle("-fx-fill: #FFD100");
        } else {
            accountFavorite.setContent("M 22 9.24 l -7.19 -0.62 L 12 2 L 9.19 8.63 L 2 9.24 l 5.46 4.73 L 5.82 21 L 12 17.27 L 18.18 21 l -1.63 -7.03 L 22 9.24 Z M 12 15.4 l -3.76 2.27 l 1 -4.28 l -3.32 -2.88 l 4.38 -0.38 L 12 6.1 l 1.71 4.04 l 4.38 0.38 l -3.32 2.88 l 1 4.28 L 12 15.4 Z");
            accountFavorite.setStyle("-fx-fill: #adadad");
        }
    }

    /**
     * Method to copy String parameter to clipboard for User
     * */
    void copyToClipboard(String Content) {
        // getting system clipboard
        Clipboard clipboard = Clipboard.getSystemClipboard();
       // getting clipboardContent
        ClipboardContent CpContent = new ClipboardContent();

        // putting string in clipboard content
        CpContent.putString(Content);
        // adding clipboard content to System clipboard
        clipboard.setContent(CpContent);
    }

    void changePasswordOutput() {
        visibilityOff.setVisible(passwordHide.isSelected());
        visible.setVisible(!passwordHide.isSelected());

        accountPassHiddenOutput.setVisible(!passwordHide.isSelected());
        accountPassVisibleOutput.setVisible(passwordHide.isSelected());
    }
}
