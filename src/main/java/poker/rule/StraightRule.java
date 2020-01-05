package poker.rule;

import poker.model.Card;
import poker.model.Player;
import poker.model.Rank;
import poker.model.Strength;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class StraightRule implements StrengthRule {

    @Override
    public boolean test(List<Card> cards, Player player) {
        if (cards == null || cards.size() == 0) return false;

        TreeSet<Card> uniqueCards = new TreeSet<>(cards);
        Card prevItem = null;
        boolean isStraight = false;
        boolean isThereAce = false;
        List<Card> sequence = new ArrayList<>(5);
        // check if there's ACE
        if (cards.get(0).getRank().equals(Rank.A))
            isThereAce = true;

        for (Card card : uniqueCards) {
            if (prevItem == null) prevItem = card;


        }

        if (isStraight) {
            player.setStrength(Strength.STRAIGHT);
            player.setBestHand(sequence);
        }
        return false;
    }

    @Override
    public int compare(Player player1, Player player2) {
        return 0;
    }
}
