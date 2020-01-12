package poker;

import org.junit.Test;
import poker.model.Card;
import poker.model.Strength;

import java.util.List;

public class FullHouseTest extends RuleTest {
    @Test
    public void shouldPassWhen3Fours() {
        List<Card> allCards = CardUtil.parseCards("KdKsKh4d4s2h2d");

        assert StrengthCalculator.testFullHouse(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("KdKsKh4d4s");
        assert player.getStrength() == Strength.FULL_HOUSE;
    }

}
