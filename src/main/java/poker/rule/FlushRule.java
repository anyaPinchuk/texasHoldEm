package poker.rule;

import poker.model.Card;
import poker.model.Player;
import poker.model.Strength;
import poker.model.Suit;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlushRule implements StrengthRule {

    @Override
    public boolean test(List<Card> cards, Player player) {
        if (cards == null || cards.size() == 0) return false;

        Map<Suit, List<Card>> cardsMap = cards.stream().collect(Collectors.groupingBy(Card::getSuit));

        List<Card> bestHand = cardsMap.values().stream().filter(val -> val.size() >= 5).findFirst().orElse(null);

        if (bestHand != null) {
            player.setStrength(Strength.FLUSH);
            player.setBestHand(bestHand);
            return true;
        }
        return false;
    }

    @Override
    public int compare(Player player1, Player player2) {
        return 0;
    }
}
