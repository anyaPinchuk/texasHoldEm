package poker.rule;

import poker.model.Card;
import poker.model.Player;

import java.util.List;

public class RoyalFlushRule implements StrengthRule {
    private StraightFlushRule straightFlushRule;
    private static Integer strength = 10;

    public RoyalFlushRule(StraightFlushRule straightFlushRule) {
        this.straightFlushRule = straightFlushRule;
    }

    @Override
    public boolean test(List<Card> boardCards, Player player) {
        if (straightFlushRule.test(boardCards, player)){
            //and max card is A or min card is 10
            return true;
        }
        return false;
    }

    @Override
    public int compare(Player player1, Player player2) {
        return 0;
    }
}
