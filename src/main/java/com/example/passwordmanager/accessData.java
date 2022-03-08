package com.example.passwordmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class accessData {

    protected static int AccountCount = 0;

    static String accessFileName = "accessData.txt";
    static String dataFileName = "accountDataFile.csv";
    protected static double OverAllPasswordStrength=0;

    protected static List<String> reUsedPassWords = new ArrayList<>();
    protected static List<String> reUsedMails = new ArrayList<>();
    protected static List<String> reUsedWebsites = new ArrayList<>();

    protected static List<String> WeakPassWords = new ArrayList<>();
    protected static List<String> NormalPassWords = new ArrayList<>();
    protected static List<String> StrongPassWords = new ArrayList<>();

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
        boolean isPassChanged = false;

        /* Checking if hint is empty so replacing with no hint*/
        if (newHint.isEmpty()) newHint = "No Hint";

        try {
            Scanner scanFile = new Scanner(accessFile);

            FileWriter writeAccessFile = new FileWriter(accessFile);
            if (scanFile.hasNext()) {
                while (scanFile.hasNextLine()) {
                    fileData += scanFile.nextLine();
                }

                writeAccessFile.write(newAccessPass + "\n");
                writeAccessFile.write(newHint + "\n");
                writeAccessFile.write(fileData + "\n");
                isPassChanged = true;
            } else {
                writeAccessFile.write(newAccessPass + "\n");
                writeAccessFile.write(newHint + "\n");
                isPassChanged = true;
            }
            writeAccessFile.close();
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

    protected static void getAccounts() {
        String[] accounts, currentDate, modifiedDate;

        AccountCount = 0;
        accountIds = new ArrayList<>();
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

        try {
            Scanner scanner = new Scanner(new File(dataFileName));
            while (scanner.hasNextLine()) {
                AccountCount++;

                accounts = (scanner.nextLine().split(","));
                accountIds.add(Integer.valueOf(accounts[0]));
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

                // password strength list
                if (PasswordStrengthPercent(accounts[2].strip()) <= 0.30) {
                    WeakPassWords.add(accounts[2].strip());
                }
                else if(PasswordStrengthPercent(accounts[2].strip()) <= 0.60) {
                    NormalPassWords.add(accounts[2].strip());
                }
                else {
                    StrongPassWords.add(accounts[2].strip());
                }

            }
//            System.out.println(accountIds);
//            System.out.println(accountNames);
//            System.out.println(accountPasswords);
//            System.out.println(accountMails);
//            System.out.println(accountPlatforms);
//            System.out.println(accountWebsites);
//            System.out.println(additionalInfos);
//            System.out.println(accountCreationDate);
//            System.out.println(accountModifiedDate);
//            System.out.println(isAccountsFavorite);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OverAllPasswordStrength /= AccountCount;
        reusedInfo();
    }

    public static void ChangeFavoriteAccount(Boolean isAccountFavorite, int AccountId) {
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
        HashSet unique=new HashSet();
        for (String s:accountPasswords) {
            if(!unique.add(s));
        }
        reUsedPassWords.addAll(unique);

        unique=new HashSet();
        for (String s:accountMails) {
            if(!unique.add(s));
        }
        reUsedMails.addAll(unique);

        unique=new HashSet();
        for (String s:accountWebsites) {
            if(!unique.add(s));
        }
        reUsedWebsites.addAll(unique);
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
