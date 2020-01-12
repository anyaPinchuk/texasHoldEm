package poker;

import org.junit.Test;
import poker.model.Card;
import poker.model.Strength;

import java.util.List;

public class FlushTest extends RuleTest{
    @Test
    public void shouldPassWhen5Spades() {
        List<Card> allCards = CardUtil.parseCards("AsKsJsTd7s4c2s");

        assert StrengthCalculator.testFlush(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("AsKsJs7s2s");
        assert player.getStrength() == Strength.FLUSH;
    }

    @Test
    public void shouldPassWhen7Spades() {
        List<Card> allCards = CardUtil.parseCards("AsKsJsTs7s4s2s");

        assert StrengthCalculator.testFlush(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("AsKsJsTs7s4s2s");
        assert player.getStrength() == Strength.FLUSH;
    }

    @Test
    public void shouldNotPassWhenNoSameSuit() {
        List<Card> allCards = CardUtil.parseCards("AcAsAdJs9h2d2c");

        assert !StrengthCalculator.testFlush(allCards, player);
        assert player.getBestHand() == null;
    }
}
