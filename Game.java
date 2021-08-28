package bullscows;

import java.util.Random;
import java.util.Scanner;

public class Game {


    public static String createCode() {
        Random random = new Random();
        String alphabetNum = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuilder secretNumber = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the length of the secret code:");
        int inputNumbers;
        String input = scanner.nextLine();
        try {
            inputNumbers = Integer.parseInt(input);
        } catch(NumberFormatException e) {
            return "Error: " + input + " isn't a valid number.";
        }
        inputNumbers = Integer.parseInt(input);
        System.out.println("Input the number of possible symbols in the code:");
        int inputLetters = scanner.nextInt();
        if (inputLetters < inputNumbers || inputNumbers == 0) {
            return "Error: it's not possible to generate a code with a length of " + input + " with " + inputLetters + " unique symbols.";
        }
        if (inputLetters > 36) {
            return "Error: maximum number of possible symbols in the code is 36 (0-9, a-z).";
        } else {
            String[] alphabetNumeric = alphabetNum.substring(0, inputLetters).split("");
            for (int i = 0; i < inputNumbers; i ++) {
                if (secretNumber.length() == inputNumbers) {
                    break;
                }
                int index = random.nextInt(alphabetNumeric.length);
                if (secretNumber.indexOf(alphabetNumeric[index]) == -1) {
                    String randomChar = alphabetNumeric[index];
                    secretNumber.append(randomChar);
                }
                if (i == inputNumbers - 1 && secretNumber.length() < inputNumbers) {
                    i = 0;
                }
            }
            System.out.printf("The secret is prepared: %s\n", stringStars(inputNumbers, inputLetters));
            System.out.println("Okay, let's start a game!");
        }
        return secretNumber.toString();
    }


    public static boolean codeChecker(String secretCode) {
        int bullCount = 0, cowCount = 0;
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split("");
        for (int i = 0; i < input.length; i++) {
            if (input[i].equals(String.valueOf(secretCode.charAt(i)))) {
                bullCount++;
            } else if (secretCode.contains(input[i])) {
                cowCount++;
            }
        }
        printOutput(bullCount, cowCount, secretCode);
        return bullCount == secretCode.length();
    }


    public static void printOutput(int bullCount, int cowCount, String secretCode) {
        String bullCountString = "bulls";
        String cowCountString = "cows";
        int secretCodeLen = secretCode.length();
        if (bullCount == secretCodeLen && secretCodeLen > 1) {
            System.out.printf("Grade: %d bulls\n", secretCodeLen);
            System.out.println("Congratulations! You guessed the secret code.");
        } else if (bullCount == secretCodeLen && secretCodeLen == 1) {
            System.out.printf("Grade: %d bull\n", secretCodeLen);
            System.out.println("Congratulations! You guessed the secret code.");
        } else {
            if (bullCount == 1) {
                bullCountString = "bull";
            }
            if (cowCount == 1) {
                cowCountString = "cow";
            }
            if (bullCount > 0 && cowCount > 0) {
                System.out.printf("Grade: %s %s and %s %s\n", bullCount, bullCountString, cowCount, cowCountString);
            } else if (bullCount > 0) {
                System.out.printf("Grade: %s %s\n", bullCount, bullCountString);
            } else if (cowCount > 0) {
                System.out.printf("Grade: %s %s\n", cowCount, cowCountString);
            } else {
                System.out.println("Grade: None");
            }
        }
    }

    public static String stringStars(int input, int symbols) {
        StringBuilder string = new StringBuilder();
        string.append("*".repeat(Math.max(0, input)));
        if (symbols > 10) {
            string.append(" (0-9, a-").append((char) (symbols + 86)).append(").");
        } else {
            string.append(" (0-9).");
        }
        return string.toString();
    }
}