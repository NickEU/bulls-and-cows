package bullscows;

class Grader {
    static Grade grade(String input, String target) {
        int cows = 0;
        int bulls = 0;
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            if (target.charAt(i) == symbol) {
                bulls++;
            } else if (target.indexOf(symbol) != -1) {
                cows++;
            }
        }
        return new Grade(cows, bulls);
    }
}
