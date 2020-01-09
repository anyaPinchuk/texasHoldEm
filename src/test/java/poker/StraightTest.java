package poker;

import org.junit.Test;
import poker.model.Card;
import poker.rule.StraightRule;

import java.util.List;

public class StraightTest extends RuleTest {
    @Test
    public void shouldPassWhen8Through4() {
        StraightRule straightRule = new StraightRule();
        List<Card> allCards = CardUtil.parseCards("Kd8s7c6s5d4c4h");

        assert straightRule.test(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("8s7c6s5d4c");
    }

    @Test
    public void shouldPassWhen5ThroughAce() {
        StraightRule straightRule = new StraightRule();
        List<Card> allCards = CardUtil.parseCards("AsKd8d5s4h3c2c");
        String expected = "5s4h3c2cAs";

        //1
        assert straightRule.test(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals(expected);

        //2
        CardUtil.parseCards("AsAc5s5d4h3c2c");
        assert straightRule.test(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals(expected);
    }

    @Test
    public void shouldPassWhenAceThrough10() {
        StraightRule straightRule = new StraightRule();
        List<Card> allCards = CardUtil.parseCards("AdKcKdQcJhTh2s");

        assert straightRule.test(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("AdKcQcJhTh");
    }

    @Test
    public void shouldPassWhen10Through6() {
        StraightRule straightRule = new StraightRule();
        List<Card> allCards = CardUtil.parseCards("QsTd9d8h7s6c5d");

        assert straightRule.test(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("Td9d8h7s6c");
    }

    @Test
    public void shouldPassWhenJThrough7() {
        StraightRule straightRule = new StraightRule();
        List<Card> allCards = CardUtil.parseCards("JsJdTh9c8h8c7d");

        assert straightRule.test(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("JsTh9c8h7d");
    }

    @Test
    public void shouldNotPassWhenNoSequence() {
        StraightRule straightRule = new StraightRule();
        List<Card> allCards = CardUtil.parseCards("9h8h7s6d3d3s");

        assert !straightRule.test(allCards, player);
        assert player.getBestHand() == null;
    }
}
