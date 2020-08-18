package bullscows;

class Grader {
    static Grade grade(String input, String target) {
        int cows = 0;
        int bulls = 0;
        for (int i = 0; i < input.length(); i++) {
            char digit = input.charAt(i);
            if (target.charAt(i) == digit) {
                bulls++;
            } else if (target.indexOf(digit) != -1) {
                cows++;
            }
        }
        return new Grade(cows, bulls);
    }
}
