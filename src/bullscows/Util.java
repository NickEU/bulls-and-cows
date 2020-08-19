package bullscows;

import java.util.Random;

class Util {
    static String generateDictionary(int possibleSymbols) {
        char digit = '0';
        char letter = 'a';
        StringBuilder result = new StringBuilder();
        while (result.length() < possibleSymbols) {
            if (result.length() < 10) {
                result.append(digit++);
            } else {
                result.append(letter++);
            }
        }
        return result.toString();
    }
    static String generateSecretCode(int desiredLen, String dictionary) {
        Random rnd = new Random();
        StringBuilder result = new StringBuilder();
        while (result.length() < desiredLen) {
            char nextRandomSymbol;
            do {
                nextRandomSymbol = dictionary.charAt(rnd.nextInt(dictionary.length()));
            } while (result.indexOf(String.valueOf(nextRandomSymbol)) != -1);

            result.append(nextRandomSymbol);
        }
        return result.toString();
    }
}
