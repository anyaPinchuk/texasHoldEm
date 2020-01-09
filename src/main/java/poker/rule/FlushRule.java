package poker.rule;

import poker.model.Card;
import poker.model.Player;
import poker.model.Strength;
import poker.model.Suit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlushRule implements StrengthRule {

    @Override
    public boolean test(List<Card> cards, Player player) {
        if (cards == null || cards.size() == 0) return false;

        Map<Suit, List<Card>> cardsMap = new HashMap<>(cards.size());

        for (Card card : cards) {
            List<Card> sameSuit = cardsMap.get(card.getSuit());

            if (sameSuit == null) {
                sameSuit = new ArrayList<>(cards.size());
            }

            sameSuit.add(card);
            cardsMap.put(card.getSuit(), sameSuit);

            if (sameSuit.size() == 5) {
                player.setStrength(Strength.STRAIGHT);
                player.setBestHand(sameSuit);
                return true;
            }
        }

        return false;
    }

    @Override
    public int compare(Player player1, Player player2) {
        return 0;
    }
}
