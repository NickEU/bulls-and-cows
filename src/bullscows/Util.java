package bullscows;

import java.util.Random;

class Util {
    static String generateDictionary(int possibleSymbols) {
        char digit = '0';
        char letter = 'a';
        StringBuilder result = new StringBuilder();
        while (result.length() < possibleSymbols) {
            if (result.length() == 35) {
                System.out.println("s");
            }
            if (result.length() < 10) {
                System.out.println(digit);
                result.append(digit++);
            } else {
                System.out.println(letter);
                result.append(letter++);
            }
        }
        return result.toString();
    }
    static String generateSecretCode(int desiredLen, String dictionary) {
        Random rnd = new Random();
        StringBuilder result = new StringBuilder();
        while (result.length() < desiredLen) {
            int nextRandomDigit;
            do {
                nextRandomDigit = rnd.nextInt(10);
            } while (result.indexOf(String.valueOf(nextRandomDigit)) != -1);

            result.append(nextRandomDigit);
        }
        return result.toString();
    }
}
