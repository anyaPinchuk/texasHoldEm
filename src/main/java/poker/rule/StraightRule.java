package poker.rule;

import poker.model.Card;
import poker.model.Player;
import poker.model.Rank;
import poker.model.Strength;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

public class StraightRule implements StrengthRule {

    @Override
    public boolean test(List<Card> cards, Player player) {
        if (cards == null || cards.size() == 0) return false;

        TreeSet<Card> uniqueCards = new TreeSet<>(cards);
        boolean isStraight = false;
        List<Card> sequence = new ArrayList<>(5);

        Iterator<Card> iterator = uniqueCards.iterator();
        Card prevItem = iterator.next();

        while (iterator.hasNext()) {
            Card currItem = iterator.next();

            if (prevItem.getRank().ordinal() - currItem.getRank().ordinal() == 1) {
                sequence.add(prevItem);

                if (sequence.size() == 4 || !iterator.hasNext()) {
                    sequence.add(currItem);
                    isStraight = true;
                    break;
                }

            } else {
                sequence.clear();
            }


            prevItem = currItem;
        }

        // handle special straight case when TWO is followed by A
        if (sequence.size() == 4 && sequence.get(3).getRank().equals(Rank.TWO)) {
            //check if there's ACE
            if (cards.get(0).getRank().equals(Rank.A)) {
                sequence.add(cards.get(0));
                isStraight = true;
            }
        }

        if (isStraight) {
            player.setStrength(Strength.STRAIGHT);
            player.setBestHand(sequence);
            return true;
        }

        return false;
    }

    @Override
    public int compare(Player player1, Player player2) {
        return 0;
    }
}
