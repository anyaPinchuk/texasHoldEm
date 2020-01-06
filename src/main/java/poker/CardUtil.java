package poker;

import poker.model.Card;
import poker.model.Rank;
import poker.model.Suit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CardUtil {

    public static List<Card> sortCards(List<Card>... lists) {
        return Stream.of(lists).flatMap(Collection::stream)
                // sorts 7 cards by rank
                .sorted(Card::compareTo)
                .collect(Collectors.toList());
    }

    public static List<Card> parseCards(String input) {
        if (input.trim().length() % 2 != 0) return null;

        List<Card> cards = new ArrayList<>(input.length() / 2);
        char[] arr = input.toCharArray();
        for (int i = 0; i < input.length() - 1; i += 2) {
            Rank rank = Rank.fromValue(arr[i]);
            Suit suit = Suit.fromName(String.valueOf(arr[i + 1]));
            if (rank == null || suit == null) return null;
            else {
                cards.add(new Card(suit, rank));
            }
        }

        return cards;
    }

    public static StringBuilder getCardsAsString(List<Card> cards) {
        StringBuilder builder = new StringBuilder();
        cards.forEach(card -> builder
                .append(card.getRank().getValue())
                .append(card.getSuit().name()));
        return builder;
    }
}
