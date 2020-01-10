package poker.rule;

import poker.model.Card;
import poker.model.Player;
import poker.model.Strength;

import java.util.List;

public class StraightFlushRule implements StrengthRule {
    private StraightRule straightRule;
    private FlushRule flushRule;

    public StraightFlushRule(StraightRule straightRule, FlushRule flushRule) {
        this.straightRule = straightRule;
        this.flushRule = flushRule;
    }

    @Override
    public boolean test(List<Card> cards, Player player) {
        if (flushRule.test(cards, player)) {
            if (straightRule.test(player.getBestHand(), player)) {
                player.setStrength(Strength.STRAIGHT_FLUSH);
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
