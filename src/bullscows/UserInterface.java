package bullscows;

import java.util.Collections;
import java.util.Scanner;

class UserInterface {
    private final Scanner sc = new Scanner(System.in);
    private String secretCode;

    void start() {
        if (secretCodeWasGenerated()) {
            runGameLoop();
        }
    }

    private boolean secretCodeWasGenerated() {
        System.out.println("Input the length of the secret code:");
        int desiredLength = Integer.parseInt(sc.nextLine().trim());
        if (desiredLength > 36) {
            System.out.println("Error: can't generate a secret number with a length" +
                " of 37 because there aren't enough unique symbols(digits + lowercase english letters).\n" +
                "Please enter a number not greater than 36.");
            return false;
        }
        System.out.println("Input the number of possible symbols in the code:");
        int desiredSymbols = Integer.parseInt(sc.nextLine().trim());
        if (desiredLength > desiredSymbols) {
            System.out.println("Error! The length of the secret code can't be bigger than" +
                "the number of possible symbols");
            return false;
        }
        String dict = Util.generateDictionary(desiredSymbols);
        secretCode = Util.generateSecretCode(desiredLength, dict);
        System.out.println("The secret is prepared: "
            + String.join("", Collections.nCopies(secretCode.length(), "*"))
            + " (" + dict + ").");
        return true;
    }

    private void runGameLoop() {
        System.out.print("Okay, let's start a game! It's your ");
        int turn = 1;
        boolean numberNotGuessed = true;
        while (numberNotGuessed) {
            System.out.println("Turn " + turn++ + ":");
            String userGuess = sc.nextLine().trim();
            Grade grade = Grader.grade(userGuess, secretCode);
            printGrade(grade);
            if (grade.getBulls() == secretCode.length()) {
                numberNotGuessed = false;
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
