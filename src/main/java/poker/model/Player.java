package poker.model;

import java.util.List;
import java.util.Objects;

public class Player {
    private String id;
    private List<Card> cards;
    private List<Card> bestHand;
    private Strength strength;

    public Player(String id, List<Card> cards) {
        this.id = id;
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getBestHand() {
        return bestHand;
    }

    public void setBestHand(List<Card> bestHand) {
        this.bestHand = bestHand;
    }

    public Strength getStrength() {
        return strength;
    }

    public void setStrength(Strength strength) {
        this.strength = strength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        cards.forEach(card -> builder
                .append(card.getRank().getValue())
                .append(card.getSuit().name()));
        return builder.toString();
    }
}
