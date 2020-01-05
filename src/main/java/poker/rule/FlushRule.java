package poker.rule;

import poker.model.Card;
import poker.model.Player;

import java.util.List;

public class FlushRule implements StrengthRule {

    @Override
    public boolean test(List<Card> boardCards, Player player) {
        return false;
    }

    @Override
    public int compare(Player player1, Player player2) {
        return 0;
    }
}
