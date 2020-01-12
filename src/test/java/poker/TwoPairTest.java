package poker;

import org.junit.Test;
import poker.model.Card;
import poker.model.Strength;

import java.util.List;

public class TwoPairTest extends RuleTest {
    @Test
    public void shouldPassWhen2Pairs() {
        List<Card> allCards = CardUtil.parseCards("AsAdQh7d7s3c2h");

        assert StrengthCalculator.testTwoPairs(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("AsAd7d7s");
        assert player.getStrength() == Strength.TWO_PAIR;
    }

    @Test
    public void shouldNotPassWhen1Pair() {
        List<Card> allCards = CardUtil.parseCards("AsKdQh7d7s3c2h");

        assert !StrengthCalculator.testTwoPairs(allCards, player);
        assert player.getBestHand() == null;
    }

}
