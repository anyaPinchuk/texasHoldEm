package poker;

import org.junit.Test;
import poker.model.Card;
import poker.model.Strength;

import java.util.List;

public class RoyalFlushTest extends RuleTest{
    @Test
    public void shouldPassWhenAThrough10() {
        List<Card> allCards = CardUtil.parseCards("AsAdKsQsJsTs2d");

        assert StrengthCalculator.testRoyalFlush(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("AsKsQsJsTs");
        assert player.getStrength() == Strength.ROYAL_FLUSH;
    }

    @Test
    public void shouldNotPassWhenTenThrough6() {
        List<Card> allCards = CardUtil.parseCards("JsTh9d9h8h7h6h");

        assert !StrengthCalculator.testRoyalFlush(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("Th9h8h7h6h");
        assert player.getStrength() == Strength.STRAIGHT_FLUSH;
    }
}
