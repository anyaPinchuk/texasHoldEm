package poker;

import org.junit.Test;
import poker.model.Card;
import poker.model.Strength;

import java.util.List;

public class PairTest extends RuleTest {
    @Test
    public void shouldPassWhen2JacketsAnd2Fours() {
        List<Card> allCards = CardUtil.parseCards("KdJdJsTh4d4s2h");
        player.setCards(CardUtil.parseCards("4dJs"));

        assert StrengthCalculator.testPair(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("JdJs");
        assert player.getStrength() == Strength.ONE_PAIR;
    }

    @Test
    public void shouldPassWhen2Fours() {
        List<Card> allCards = CardUtil.parseCards("AhKdJdTh4d4s2h");
        player.setCards(CardUtil.parseCards("4dJd"));

        assert StrengthCalculator.testPair(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("4d4s");
        assert player.getStrength() == Strength.ONE_PAIR;
    }

    @Test
    public void shouldNotPassWhenNoPairs() {
        List<Card> allCards = CardUtil.parseCards("AsKhQcJh8d7h2s");
        player.setCards(CardUtil.parseCards("Kh8d"));

        assert !StrengthCalculator.testPair(allCards, player);
        assert player.getBestHand() == null;
    }
}
