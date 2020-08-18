package bullscows;

import java.util.Scanner;

class UserInterface {
    void start() {
        String input = new Scanner(System.in).nextLine();
        String target = "9999";
        Grade grade = Grader.grade(input, target);
        printGrade(grade, target);
    }

    private void printGrade(Grade grade, String target) {
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

        System.out.println(result
            + (grade.getBulls() == 4 ? " Congrats!" : "")
            + " The secret code is " + target);
    }

    void startRandomNum() {
        int desiredLength = Integer.parseInt(new Scanner(System.in).nextLine().trim());
        if (desiredLength > 10) {
            System.out.println("Error: can't generate a secret number with a length of 11 because there aren't enough unique digits.\n" +
                "Please enter a number not greater than 10.");
            return;
        }

        int result = PseudoRandom.generateNumber(desiredLength);
        System.out.println("The random secret number is " + result + ".");
    }
}
