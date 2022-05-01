package com.example.passwordmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class accessData {

    protected static int AccountCount = 0;
    protected static int LastAccountId = 0;
    protected static String ACCESSPASS = "";

    static String accessFileName = "accessData.txt";
    static String dataFileName = "accountDataFile.csv";
    protected static double OverAllPasswordStrength = 0;

    protected static List<Integer> reUsedPassWords = new ArrayList<>();
    protected static List<Integer> reUsedMails = new ArrayList<>();
    protected static List<Integer> reUsedWebsites = new ArrayList<>();
    protected static List<Integer> FavoriteAccounts = new ArrayList<>();
    protected static List<Integer> newAccounts = new ArrayList<>();

    protected static List<Integer> WeakPassWords = new ArrayList<>();
    protected static List<Integer> NormalPassWords = new ArrayList<>();
    protected static List<Integer> StrongPassWords = new ArrayList<>();

    protected static List<Integer> accountNumbers = new ArrayList<>();
    protected static List<Integer> accountIds = new ArrayList<>();
    protected static List<String> accountNames = new ArrayList<>();
    protected static List<String> accountPasswords = new ArrayList<>();
    protected static List<String> accountMails = new ArrayList<>();
    protected static List<String> accountPlatforms = new ArrayList<>();
    protected static List<String> accountWebsites = new ArrayList<>();
    protected static List<String> additionalInfos = new ArrayList<>();
    protected static List<LocalDate> accountCreationDate = new ArrayList<>();
    protected static List<LocalDate> accountModifiedDate = new ArrayList<>();
    protected static List<Boolean> isAccountsFavorite = new ArrayList<>();
    protected static List<String> accountIcons = new ArrayList<>();


    /**
     * Getting pass to access password Manager
     * @throws FileNotFoundException if data file not found
     * @throws IOException if unable to create new file
     * @return access password and hint is returned (no hint is passed if there is no hint provided)
     * if file is already there else empty string
     * */
    protected static String getAccessPass() {
        /*declaring variables to store access password and file to get access password*/
        String accessPass = "";
        String accessHint = "no Hint";
        File accessFile = new File(accessFileName);

        try {
            /* if file exists get access password */
            if (accessFile.exists()) {
                Scanner scanner = new Scanner(accessFile); // Scanning file
                /*checking if file has any line to read else access pass remain empty*/
                if (scanner.hasNextLine()) {
                    accessPass = scanner.nextLine(); // getting accessPass
                    if (scanner.hasNextLine()) {
                        accessHint = scanner.nextLine(); //getting access hint
                    }
                }
            }
            else {
                /*creating new file and getting result if file is created or not*/
                boolean isFileCreated = accessFile.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ACCESSPASS = accessPass;
        /*returning access pass*/
        return accessPass +"~"+accessHint;
    }

    /**
     * changing access password and hint from data file,
     * if file is not there creating new file,
     * if content is not there writing new,
     * if no hint is provided no hint is written in file.
     * @param newAccessPass new password got from user
     * @param newHint new hint got from user
     * @return boolean value if password is changed or not
     * */
    protected static boolean changeAccessPass(String newAccessPass, String newHint) {
        String fileData = "";
        File accessFile = new File(accessFileName);
        int i = 0;
        boolean isPassChanged = false;

        /* Checking if hint is empty so replacing with no hint*/
        if (newHint.isEmpty()) newHint = "No Hint";

        try {
            Scanner scanFile = new Scanner(accessFile);
            scanFile.nextLine();
            scanFile.nextLine();
            while (scanFile.hasNextLine()) {
                fileData += scanFile.nextLine() + "\n";
            }

            FileWriter writeAccessFile = new FileWriter(accessFile, false);

            writeAccessFile.write(newAccessPass + "\n");
            writeAccessFile.write(newHint + "\n");
            writeAccessFile.write(fileData.strip() + "\n");
            isPassChanged = true;
            writeAccessFile.close();

            ACCESSPASS = newAccessPass;

        } catch (IOException e) {
            e.printStackTrace();
            isPassChanged = false;
        }

        return isPassChanged;
    }

    protected static String getAccessUserName() {
        String accessUserName = "";
        File accessFile = new File(accessFileName);

        try {
            /* if file exists get access password */
            if (accessFile.exists()) {
                Scanner scanner = new Scanner(accessFile); // Scanning file
                /*checking if file has any line to read else access username remain empty*/
                for (int i=0; i < 2; i++) {
                    scanner.nextLine();
                }
                if (scanner.hasNextLine()) {
                    accessUserName = scanner.nextLine(); // getting access username
                }
            }
            else {
                /*creating new file and getting result if file is created or not*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessUserName;
    }

    protected static void ChangeAccessInfo(String UserName, String UserMail) {
        String fileData1 = "";
        String fileData2 = "";
        File accessFile = new File(accessFileName);
        int count = 0;

        try {
            Scanner scanFile = new Scanner(accessFile);
            while (scanFile.hasNextLine()) {
                if (count < 2) {
                    fileData1 += scanFile.nextLine() + "\n";
                } else if (count == 2 && count == 3) {
                    scanFile.nextLine();
                    scanFile.nextLine();
                } else {
                    fileData2 += scanFile.nextLine() + "\n";
                }
                count++;
            }

            FileWriter writeAccessFile = new FileWriter(accessFile, false);
            writeAccessFile.write(fileData1.strip() + "\n");
            writeAccessFile.write(UserName + "\n");
            writeAccessFile.write(UserMail + "\n");
            writeAccessFile.write(fileData2.strip() + "\n");
            writeAccessFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static String getAccessMail() {
        String accessMail = "";
        File accessFile = new File(accessFileName);

        try {
            /* if file exists get access password */
            if (accessFile.exists()) {
                Scanner scanner = new Scanner(accessFile); // Scanning file
                /*checking if file has any line to read else access username remain empty*/
                for (int i=0; i < 3; i++) {
                    scanner.nextLine();
                }
                if (scanner.hasNextLine()) {
                    accessMail = scanner.nextLine(); // getting mail
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return accessMail;
    }

    protected static void getAccounts() {
        String[] accounts, currentDate, modifiedDate;

        AccountCount = 0;
        accountIds = new ArrayList<>();
        accountNumbers = new ArrayList<>();
        accountNames = new ArrayList<>();
        accountPasswords = new ArrayList<>();
        accountMails = new ArrayList<>();
        accountPlatforms = new ArrayList<>();
        accountWebsites = new ArrayList<>();
        additionalInfos = new ArrayList<>();
        accountCreationDate = new ArrayList<>();
        accountModifiedDate = new ArrayList<>();
        isAccountsFavorite = new ArrayList<>();
        accountIcons = new ArrayList<>();
        OverAllPasswordStrength = 0;
        FavoriteAccounts = new ArrayList<>();
        WeakPassWords = new ArrayList<>();
        NormalPassWords = new ArrayList<>();
        StrongPassWords = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(dataFileName));
            while (scanner.hasNextLine()) {

                accounts = (scanner.nextLine().split(","));
                LastAccountId = Integer.parseInt(accounts[0].strip());

                accountNumbers.add(AccountCount++);
                accountIds.add(Integer.valueOf(accounts[0].strip()));
                accountNames.add(accounts[1].strip());
                accountPasswords.add(accounts[2].strip());
                accountMails.add(accounts[3].strip());
                accountPlatforms.add(accounts[4].strip());
                accountWebsites.add(accounts[5].strip());
                additionalInfos.add(accounts[6].strip());

                currentDate = accounts[7].split("-");
                modifiedDate = accounts[8].split("-");

                accountCreationDate.add(LocalDate.of(
                                Integer.parseInt(currentDate[0].strip()),
                                Integer.parseInt(currentDate[1].strip()),
                                Integer.parseInt(currentDate[2].strip())
                        )
                );
                accountModifiedDate.add(LocalDate.of(
                                Integer.parseInt(modifiedDate[0].strip()),
                                Integer.parseInt(modifiedDate[1].strip()),
                                Integer.parseInt(modifiedDate[2].strip())
                        )
                );

                isAccountsFavorite.add(Boolean.parseBoolean(accounts[9].strip()));
                accountIcons.add(accounts[10].strip());


                // strength
                OverAllPasswordStrength += PasswordStrengthPercent(accounts[2]);

                if (Boolean.parseBoolean(accounts[9].strip())) {
                    FavoriteAccounts.add(Integer.parseInt(accounts[0].strip()));
                }

                // password strength list
                if (PasswordStrengthPercent(accounts[2].strip()) <= 0.30) {
                    WeakPassWords.add(Integer.parseInt(accounts[0]));
                }
                else if(PasswordStrengthPercent(accounts[2].strip()) <= 0.60) {
                    NormalPassWords.add(Integer.parseInt(accounts[0]));
                }
                else {
                    StrongPassWords.add(Integer.parseInt(accounts[0]));
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OverAllPasswordStrength /= AccountCount;
        reusedInfo();
    }

    protected static void deleteAccount(ArrayList<Integer> selectedAccounts){
        try {
            new FileWriter(dataFileName, false).close();
            FileWriter dataFile = new FileWriter(dataFileName);
            System.out.println(selectedAccounts);
            for (int account=0; account<AccountCount; account++) {
                if (!selectedAccounts.contains(accountIds.get(account))) {
//                    System.out.println(account);
                    dataFile.write(String.format("%d, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n",
                            accountIds.get(account),
                            accountNames.get(account),
                            accountPasswords.get(account),
                            accountMails.get(account),
                            accountPlatforms.get(account),
                            accountWebsites.get(account),
                            additionalInfos.get(account),
                            accountCreationDate.get(account),
                            accountModifiedDate.get(account),
                            isAccountsFavorite.get(account),
                            accountIcons.get(account))
                    );
                } else {
                    System.out.println("deleted account");
                    System.out.println(account);
                }
            }
            dataFile.close();
            getAccounts();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CreateNewAccount(String userName, String password, String mail, String platform, String website, String additionalInfo) {
        try {
            FileWriter datafile = new FileWriter(dataFileName, true);
            datafile.write(String.format("%d, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n",
                    ++LastAccountId,
                    userName,
                    password,
                    mail,
                    platform,
                    website,
                    additionalInfo,
                    LocalDate.now(),
                    LocalDate.now(),
                    false,
                    accountIcons.get(0)
                    ));
            datafile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getAccounts();
    }

    public static void ChangeAccountDetails(Boolean isAccountFavorite, int AccountId) {
        try {
            new FileWriter(dataFileName, false).close();
            FileWriter dataFile = new FileWriter(dataFileName);
            for (int account=0; account<AccountCount; account++) {
                if (AccountId == account) {
                    dataFile.write(String.format("%d, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n",
                            accountIds.get(account),
                            accountNames.get(account),
                            accountPasswords.get(account),
                            accountMails.get(account),
                            accountPlatforms.get(account),
                            accountWebsites.get(account),
                            additionalInfos.get(account),
                            accountCreationDate.get(account),
                            accountModifiedDate.get(account),
                            isAccountFavorite,
                            accountIcons.get(account))
                    );
                } else {
                    dataFile.write(String.format("%d, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s\n",
                            accountIds.get(account),
                            accountNames.get(account),
                            accountPasswords.get(account),
                            accountMails.get(account),
                            accountPlatforms.get(account),
                            accountWebsites.get(account),
                            additionalInfos.get(account),
                            accountCreationDate.get(account),
                            accountModifiedDate.get(account),
                            isAccountsFavorite.get(account),
                            accountIcons.get(account))
                    );
                }
            }
            dataFile.close();
            getAccounts();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected static int[] PasswordStrength(String password) {
        char[] SpecialChars = {'/','*','!','@','#','$','%','^','&','*','(',')','\"','{','}','_','[',']','|','\\','?','/','<','>',',','.'};

        int Numbers = 0, specialChar = 0, upperCaseChar = 0, totalChar = 0;

        if (!password.isEmpty()) {
            for (int iter=0; iter<password.length(); iter++) {
                char ch = password.charAt(iter);
                if (Character.isDigit(ch)) {
                    Numbers++;
                }
                for (char sc: SpecialChars) {
                    if (ch == sc) {
                        specialChar++;
                    }
                }
                if (Character.isUpperCase(ch)) {
                    upperCaseChar++;
                }
                totalChar++;
            }
        }
        return new int[]{Numbers, specialChar, upperCaseChar, totalChar};
    }


    protected static double PasswordStrengthPercent(String password) {
        int[] PasswordStrengthList = PasswordStrength(password);
        double totalStrength;

        // 30%
        double passwordLengthStrength = ((double) PasswordStrengthList[3] / 10) * 50;
        double average = (double) (PasswordStrengthList[0] + PasswordStrengthList [1] + PasswordStrengthList[2])/3;

        double passwordCharStrength = (average / (PasswordStrengthList[3] * (double) 3/4)) * 50;

        for (int iter=0; iter<3; iter++) {
            if (average == ((double) PasswordStrengthList[iter]/3)) {
                if (PasswordStrengthList[iter] == PasswordStrengthList[3]) {
                    passwordCharStrength = 0;
                }
            }
        }
        if (average == 0) {
            passwordLengthStrength /= 2;
        }

        return (passwordLengthStrength + passwordCharStrength) / 100;
    }

    static void reusedInfo() {

        // add these values above

        HashSet unique=new HashSet();
        for (int i=0; i<AccountCount; i++) {
            if(!unique.add(accountPasswords.get(i))){
                reUsedPassWords.add(accountIds.get(i));
            }

            if(!unique.add(accountMails.get(i))){
                reUsedMails.add(accountIds.get(i));
            }

            if(!unique.add(accountWebsites.get(i))){
                reUsedWebsites.add(accountIds.get(i));
            }

            if (LocalDate.now().getMonth() == (accountCreationDate.get(i).getMonth().plus(1))
                    && LocalDate.now().getYear() == accountCreationDate.get(i).getYear()) {
                newAccounts.add(accountIds.get(i));
            }
        }
    }

    public static void ChangeTheme(String primaryColor, String secondaryColor, String highlightColor, String textColor, String labelColor) {
        try {
            FileWriter AppTheme = new FileWriter("src/main/resources/com/example/passwordmanager/css/Theme.css", false);
            AppTheme.write(".primary_color {\n" +
                    "-fx-background-color:  " + primaryColor + "; -fx-fill:  " + primaryColor + ";\n" +
                    "}\n" +
                    "\n" +
                    ".secondary_color {\n" +
                    "-fx-background-color:  " + secondaryColor + "; -fx-fill:  " + secondaryColor + ";\n" +
                    "}\n" +
                    "\n" +
                    ".highlight_color {\n" +
                    "-fx-background-color:  " + highlightColor + "; -fx-fill:  " + highlightColor + ";\n" +
                    "}\n" +
                    "\n" +
                    ".text-color {\n" +
                    "-fx-text-fill: " + textColor + "; -fx-fill: " + labelColor + ";\n" +
                    "}\n");
            AppTheme.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    static void reusedInfo() {
//        String LastPassword = "";
//        String LastEmail = "";
//        String LastWebsite = "";
//
//        // additional lists (reused)
//        for (int i = 0; i < AccountCount; i++) {
//            for (int j = i+1; j<AccountCount; j++){
//                if (accountPasswords.get(i).equals(accountPasswords.get(j))) {
//                    if(LastPassword.equals(accountPasswords.get(i))){
//                        reUsedPassWords.add(accountPasswords.get(i));
//                    } else {
//                        LastPassword = accountPasswords.get(i);
//                    }
//                    if(LastEmail.equals(accountMails.get(i))){
//                        reUsedMails.add(accountMails.get(i));
//                    } else {
//                        LastEmail = accountMails.get(i);
//                    }
//                    if(LastWebsite.equals(accountWebsites.get(i))){
//                        reUsedWebsites.add(accountWebsites.get(i));
//                    } else {
//                        LastWebsite = accountWebsites.get(i);
//                    }
//                }
//            }
//        }
//    }
}
