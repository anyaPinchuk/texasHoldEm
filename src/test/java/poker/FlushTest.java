package poker;

import org.junit.Test;
import poker.model.Card;
import poker.rule.FlushRule;

import java.util.List;

public class FlushTest extends RuleTest{
    @Test
    public void shouldPassWhen5Spades() {
        FlushRule flushRule = new FlushRule();
        List<Card> allCards = CardUtil.parseCards("AsKsJsTd7s4c2s");

        assert flushRule.test(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("AsKsJs7s2s");
    }

    @Test
    public void shouldPassWhen7Spades() {
        FlushRule flushRule = new FlushRule();
        List<Card> allCards = CardUtil.parseCards("AsKsJsTs7s4s2s");

        assert flushRule.test(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("AsKsJsTs7s");
    }

    @Test
    public void shouldNotPassWhenNoSameSuit() {
        FlushRule flushRule = new FlushRule();
        List<Card> allCards = CardUtil.parseCards("AcAsAdJs9h2d2c");

        assert !flushRule.test(allCards, player);
        assert player.getBestHand() == null;
    }
}
