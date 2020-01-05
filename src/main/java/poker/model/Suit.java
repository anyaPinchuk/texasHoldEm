package poker.model;

import java.util.Arrays;

public enum Suit {
    h("Hearts"),
    d("Diamonds"),
    c("Clubs"),
    s("Spades");

    private String description;

    Suit(String description) {
        this.description = description;
    }

    public static Suit fromName(String name){
        return Arrays.stream(values()).filter(suit -> suit.name().equals(name)).findFirst().orElse(null);
    }
}
