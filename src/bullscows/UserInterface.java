package bullscows;

import java.util.Collections;
import java.util.Scanner;

class UserInterface {
    private final Scanner sc = new Scanner(System.in);
    private int desiredCodeLength;
    private int desiredSymbolsInDict;
    private String secretCode;


    void start() {
        if (secretCodeWasGenerated()) {
            runGameLoop();
        }
    }

    private boolean secretCodeWasGenerated() {
        System.out.println("Input the length of the secret code:");
        if (!gotLegalCodeLengthFromUser()) {
            return false;
        }
        System.out.println("Input the number of possible symbols in the code:");
        if (!gotLegalDictLengthFromUser()) {
            return false;
        }
        String dict = Util.generateDictionary(desiredSymbolsInDict);
        secretCode = Util.generateSecretCode(desiredCodeLength, dict);
        System.out.println("The secret is prepared: "
            + String.join("", Collections.nCopies(secretCode.length(), "*"))
            + " (" + dict + ").");
        return true;
    }

    private boolean gotLegalDictLengthFromUser() {
        desiredSymbolsInDict = getLengthFromUser();
        if (desiredSymbolsInDict < 0) {
            return false;
        }
        if (desiredCodeLength > desiredSymbolsInDict) {
            System.out.printf(
                "Error: it's not possible to generate a code with a length of %d with %d unique symbols.",
                desiredCodeLength, desiredSymbolsInDict);
            return false;
        }
        if (desiredSymbolsInDict > 36) {
            System.out.println("Error: Length is invalid! Maximum number of possible symbols in the code is 36 (0-9, a-z).");
            return false;
        }
        return true;
    }

    private boolean gotLegalCodeLengthFromUser() {
        desiredCodeLength = getLengthFromUser();
        if (desiredCodeLength < 0) {
            return false;
        }
        if (desiredCodeLength > 36) {
            System.out.println("Error: can't generate a secret number with a length" +
                " of 37 because there aren't enough unique symbols(digits + lowercase english letters).\n" +
                "Please enter a number not greater than 36.");
            return false;
        }
        return true;
    }

    private int getLengthFromUser() {
        String userInput = sc.nextLine().trim();
        try {
            int number = Integer.parseInt(userInput);
            if (number <= 0) {
                System.out.println("Error: Length is invalid! Can only be a positive number between 1 and 36");
                return -1;
            }
            return number;
        } catch(NumberFormatException e) {
            System.out.println("Error: \"" + userInput + "\" is an invalid number.");
            return -1;
        }
    }

    private void runGameLoop() {
        System.out.print("Okay, let's start a game! It's your ");
        int turn = 1;
        boolean codeNotGuessed = true;
        while (codeNotGuessed) {
            System.out.println("Turn " + turn++ + ":");
            String userGuess = sc.nextLine().trim();
            Grade grade = Grader.grade(userGuess, secretCode);
            printGrade(grade);
            if (grade.getBulls() == secretCode.length()) {
                codeNotGuessed = false;
            }
        }
        System.out.println("Congratulations! You guessed the secret code.");
    }

    private void printGrade(Grade grade) {
        String result = "Grade: ";
        if (grade.getBulls() > 0) {
            result += grade.getBulls() + " bull(s)";
        }

        if (grade.getCows() > 0) {
            result += (grade.getBulls() > 0 ? " and " : "") + grade.getCows() + " cow(s).";
        }

        if (grade.getCows() == 0 && grade.getBulls() == 0) {
            result += "None.";
        }

        System.out.println(result);
    }
}
