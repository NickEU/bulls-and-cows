package bullscows;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class PseudoRandom {
    static String generateNumber(int desiredLen){
        StringBuilder result = new StringBuilder();
        while (result.length() < desiredLen) {
            List<String> randomDigits = String.valueOf(System.nanoTime())
                .chars().distinct().mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.toList());
            // who needs KISS when you've got this xD
            randomDigits.removeAll(Arrays.asList(result.toString().split("")));
            while (result.length() < desiredLen && randomDigits.size() > 0) {
                result.append(randomDigits.remove(randomDigits.size() - 1));
            }
        }
        return result.toString();
    }
}
