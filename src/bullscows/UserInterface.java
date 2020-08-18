package bullscows;

import java.util.Scanner;

class UserInterface {
    private final Scanner sc = new Scanner(System.in);
    private String secretNum;

    void start() {
        if (secretCodeWasGenerated()) {
            runGameLoop();
        }
    }

    private boolean secretCodeWasGenerated() {
        System.out.println("Please, enter the secret code's length:");
        int desiredLength = Integer.parseInt(sc.nextLine().trim());
        if (desiredLength > 10) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.\n" +
                "Please enter a number not greater than 10.");
            return false;
        }
        secretNum = PseudoRandom.generateNumber(desiredLength);
        return true;
    }

    private void runGameLoop() {
        System.out.print("Okay, let's start a game! It's your ");
        int turn = 1;
        boolean numberNotGuessed = true;
        while(numberNotGuessed) {
            System.out.println("Turn " + turn++ + ":");
            String userGuess = sc.nextLine().trim();
            Grade grade = Grader.grade(userGuess, secretNum);
            printGrade(grade);
            if (grade.getBulls() == secretNum.length()) {
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
