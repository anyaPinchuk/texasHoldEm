package poker.rule;

import poker.model.Card;
import poker.model.Player;

import java.util.List;

public class StraightFlushRule implements StrengthRule {
    private StraightRule straightRule;

    public StraightFlushRule(StraightRule straightRule) {
        this.straightRule = straightRule;
    }

    @Override
    public boolean test(List<Card> cards, Player player) {
        return straightRule.test(cards, player);
    }

    @Override
    public int compare(Player player1, Player player2) {
        return 0;
    }
}
