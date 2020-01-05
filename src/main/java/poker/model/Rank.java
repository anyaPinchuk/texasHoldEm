package poker.model;

import java.util.Arrays;

public enum Rank {
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    J('J'),
    Q('Q'),
    K('K'),
    A('A');

    private char value;

    Rank(char value) {
        this.value = value;
    }

    public static Rank fromValue(char value) {
        return Arrays.stream(values()).filter(rank -> rank.value == value).findFirst().orElse(null);
    }

    public char getValue() {
        return value;
    }
}
