package bullscows;

import java.util.Random;

class PseudoRandom {
    static String generateNumber(int desiredLen) {
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
