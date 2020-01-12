package poker;

import org.junit.Test;
import poker.model.Card;
import poker.model.Strength;

import java.util.List;

public class StraightFlushTest extends RuleTest{
    @Test
    public void shouldPassWhenSpadesQThrough8() {
        List<Card> allCards = CardUtil.parseCards("KdQsJsTs9h9s8s");

        assert StrengthCalculator.testStraightFlush(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("QsJsTs9s8s");
        assert player.getStrength() == Strength.STRAIGHT_FLUSH;
    }

    @Test
    public void shouldPassWhenAllHearts() {
        List<Card> allCards = CardUtil.parseCards("Th9h8h7h6h5h4h");

        assert StrengthCalculator.testStraightFlush(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("Th9h8h7h6h");
        assert player.getStrength() == Strength.STRAIGHT_FLUSH;
    }

    @Test
    public void shouldPassWhenClubs5ThroughA() {
        List<Card> allCards = CardUtil.parseCards("AcKcTd5c4c3c2c");

        assert StrengthCalculator.testStraightFlush(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("5c4c3c2cAc");
        assert player.getStrength() == Strength.STRAIGHT_FLUSH;
    }


    @Test
    public void shouldNotPassWhenNoFlushButStraight() {
        List<Card> allCards = CardUtil.parseCards("JdJsTc9h8c7s6s");

        assert !StrengthCalculator.testStraightFlush(allCards, player);
        assert player.getBestHand() == null;
    }

    @Test
    public void shouldNotPassWhenNoStraightButFlush() {
        List<Card> allCards = CardUtil.parseCards("AdKhKdJd7h7d2d");

        assert !StrengthCalculator.testStraightFlush(allCards, player);
        assert player.getStrength() == Strength.FLUSH;
    }
}
