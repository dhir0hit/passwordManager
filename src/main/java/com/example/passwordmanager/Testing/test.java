package com.example.passwordmanager.Testing;

import com.example.passwordmanager.Main_Application;

import java.time.LocalTime;

class test {

    public static void main(String[] args) {

        System.out.println((double) (0 + 0 + 0) / 3);
//        String data = "";
//        int count=0;
//        String image = "media/Grey-Background.png";
//        try {
//            Scanner scanner = new Scanner(new File("data.csv"));
//            FileWriter writer = new FileWriter("accountDataFile.csv");
//            while (scanner.hasNextLine()) {
//                count++;
//                if (count%3==0) {
//                    image = "media/R.png";
//                }
//                data = scanner.nextLine() + ", 2022-03-05, 2022-01-05, false, " + image;
//                writer.write(data + "\n");
//            }
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            Scanner scanner = new Scanner(new File("data.csv"));
//            scanner.useDelimiter(",");
//            while (scanner.hasNext()) {
//                System.out.println(scanner.next().strip());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

    }

    static void testGreeting() {
        short totalTest=0;
        short isCorrect=0;
        short isWrong=0;
        short testOutput;
        for (short hour=0; hour < 24; hour++) {
            LocalTime testTime = LocalTime.of(hour, 0);
            testOutput = Main_Application.greetingForUser(testTime);

            if (hour < 12) {
                if (testOutput == 0) {
                    isCorrect++;
                } else {
                    System.out.println("morning-wrong" + isWrong++);
                }
            } else if (hour < 18) {
                if (testOutput == 1) {
                    isCorrect++;
                } else {
                    System.out.println("noon-wrong" + isWrong++);
                }
            } else if (hour < 24) {
                if (testOutput == 2) {
                    isCorrect++;
                } else {
                    System.out.println("night-wrong" + isWrong++);
                }
            } else {
                System.out.println(isWrong++);
            }
            totalTest++;
        }
        printResult(isCorrect, isWrong, totalTest);
    }

    static void testPassStrength() {
        String testPassword = "";
        String tempStr = "";
        String[] tempChr;
        int tempInt = 0;
        char[] lowercase = {'a', 'b', 'c', 'd', 'e', 'f', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] uppercase = {'A', 'B', 'C', 'D', 'E', 'F', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        char[] SpecialChars = {'/','*','!','@','#','$','%','^','&','*','(',')','\"','{','}','_','[',']','|','\\','?','/','<','>',',','.'};
        char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        for (int x = 0; (x < (int) (Math.random() * 10)); x++) {
            tempStr = String.valueOf(lowercase[(int) Math.random() * lowercase.length]).repeat((int) Math.random() * 3);


            testPassword += String.valueOf(SpecialChars[(int) Math.random() * SpecialChars.length]).repeat((int) Math.random() * 3);
            testPassword += String.valueOf(uppercase[(int) Math.random() * uppercase.length]).repeat((int) Math.random() * 3);
            testPassword += String.valueOf(numbers[(int) Math.random() * numbers.length]).repeat((int) Math.random() * 3);
        }
    }

    static void printResult(short correct, short wrong, short total) {
        System.out.println("Correct: " + correct + "/" + total);
        System.out.println("Wring: " + wrong + "/" + total);
    }
}
