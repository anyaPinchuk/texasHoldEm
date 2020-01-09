package poker;

import org.junit.Test;
import poker.model.Card;
import poker.rule.StraightFlushRule;
import poker.rule.StraightRule;

import java.util.List;

public class StraightFlushTest extends RuleTest{
    @Test
    public void shouldPassWhenSpadesQThrough8() {
        StraightRule sameSuitStraightRule = new StraightRule();
        sameSuitStraightRule.setSameSuit(true);
        StraightFlushRule straightFlushRule = new StraightFlushRule(sameSuitStraightRule);
        List<Card> allCards = CardUtil.parseCards("KdQsJsTs9h9s8s");

        assert straightFlushRule.test(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("QsJsTs9s8s");
    }

    @Test
    public void shouldPassWhenAllHearts() {
        StraightRule sameSuitStraightRule = new StraightRule();
        sameSuitStraightRule.setSameSuit(true);
        StraightFlushRule straightFlushRule = new StraightFlushRule(sameSuitStraightRule);
        List<Card> allCards = CardUtil.parseCards("Th9h8h7h6h5h4h");

        assert straightFlushRule.test(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("Th9h8h7h6h");
    }
}
