package poker;

import org.junit.Test;
import poker.model.Card;
import poker.model.Strength;

import java.util.List;

public class ThreeOfAKindTest extends RuleTest {
    @Test
    public void shouldPassWhen3Fours() {
        List<Card> allCards = CardUtil.parseCards("AsAdKh8h4c4d4s");
        player.setCards(CardUtil.parseCards("As4c"));

        assert StrengthCalculator.testThreeOfAKind(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("4c4d4s");
        assert player.getStrength() == Strength.THREE_OF_A_KIND;
    }


    @Test
    public void shouldPassWhen4Jackets() {
        List<Card> allCards = CardUtil.parseCards("AsAdAc7h7d7c2h");

        assert StrengthCalculator.testThreeOfAKind(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("AsAdAc");
        assert player.getStrength() == Strength.THREE_OF_A_KIND;
    }

    @Test
    public void shouldNotPassWhenPairsOnly() {
        List<Card> allCards = CardUtil.parseCards("QsQdJsJd8d8h3d");

        assert !StrengthCalculator.testThreeOfAKind(allCards, player);
        assert player.getBestHand() == null;
    }

}
