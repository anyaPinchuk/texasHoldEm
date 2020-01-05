package poker.rule;

import poker.model.Card;
import poker.model.Player;

import java.util.List;

public class StraightFlushRule implements StrengthRule {
    private StraightRule straightRule;
    private FlushRule flushRule;

    public StraightFlushRule(StraightRule straightRule, FlushRule flushRule) {
        this.straightRule = straightRule;
        this.flushRule = flushRule;
    }

    @Override
    public boolean test(List<Card> boardCards, Player player) {
        if (straightRule.test(boardCards, player) && flushRule.test(boardCards, player)) {
            return true;
        }
        return false;
    }
    @Override
    public int compare(Player player1, Player player2) {
        return 0;
    }
}
