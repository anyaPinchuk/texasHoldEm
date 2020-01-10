package poker;

import org.junit.Test;
import poker.model.Card;
import poker.model.Strength;
import poker.rule.FlushRule;
import poker.rule.StraightFlushRule;
import poker.rule.StraightRule;

import java.util.List;

public class StraightFlushTest extends RuleTest{
    @Test
    public void shouldPassWhenSpadesQThrough8() {
        StraightFlushRule straightFlushRule = new StraightFlushRule(new StraightRule(), new FlushRule());
        List<Card> allCards = CardUtil.parseCards("KdQsJsTs9h9s8s");

        assert straightFlushRule.test(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("QsJsTs9s8s");
        assert player.getStrength() == Strength.STRAIGHT_FLUSH;
    }

    @Test
    public void shouldPassWhenAllHearts() {
        StraightFlushRule straightFlushRule = new StraightFlushRule(new StraightRule(), new FlushRule());
        List<Card> allCards = CardUtil.parseCards("Th9h8h7h6h5h4h");

        assert straightFlushRule.test(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("Th9h8h7h6h");
        assert player.getStrength() == Strength.STRAIGHT_FLUSH;
    }

    @Test
    public void shouldPassWhenClubs5ThroughA() {
        StraightFlushRule straightFlushRule = new StraightFlushRule(new StraightRule(), new FlushRule());
        List<Card> allCards = CardUtil.parseCards("AcKcTd5c4c3c2c");

        assert straightFlushRule.test(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("5c4c3c2cAc");
        assert player.getStrength() == Strength.STRAIGHT_FLUSH;
    }


    @Test
    public void shouldNotPassWhenNoFlushButStraight() {
        StraightFlushRule straightFlushRule = new StraightFlushRule(new StraightRule(), new FlushRule());
        List<Card> allCards = CardUtil.parseCards("JdJsTc9h8c7s6s");

        assert !straightFlushRule.test(allCards, player);
        assert player.getBestHand() == null;
    }

    @Test
    public void shouldNotPassWhenNoStraightButFlush() {
        StraightFlushRule straightFlushRule = new StraightFlushRule(new StraightRule(), new FlushRule());
        List<Card> allCards = CardUtil.parseCards("AdKhKdJd7h7d2d");

        assert !straightFlushRule.test(allCards, player);
        assert player.getStrength() == Strength.FLUSH;
    }
}
