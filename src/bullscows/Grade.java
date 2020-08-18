package bullscows;

class Grade {
    private final int bulls;
    private final int cows;

    public Grade(int cows, int bulls) {
        this.cows = cows;
        this.bulls = bulls;
    }

    int getBulls() {
        return bulls;
    }

    int getCows() {
        return cows;
    }
}
