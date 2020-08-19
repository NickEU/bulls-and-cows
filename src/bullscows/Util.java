package bullscows;

import java.util.Random;

class Util {
    static String generateDictionary(int possibleSymbols) {
        return "";
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
