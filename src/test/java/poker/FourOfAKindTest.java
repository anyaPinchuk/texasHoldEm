package poker;

import org.junit.Test;
import poker.model.Card;
import poker.model.Strength;

import java.util.List;

public class FourOfAKindTest extends RuleTest {
    @Test
    public void shouldPassWhen4Aces() {
        List<Card> allCards = CardUtil.parseCards("AsAdAcAhKsTh7d");
        player.setCards(CardUtil.parseCards("AdTh"));

        assert StrengthCalculator.testFourOfAKind(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("AsAdAcAh");
        assert player.getStrength() == Strength.FOUR_OF_A_KIND;
    }


    @Test
    public void shouldPassWhen4Jackets() {
        List<Card> allCards = CardUtil.parseCards("KdJdJhJcJs8h3d");
        player.setCards(CardUtil.parseCards("8hJd"));

        assert StrengthCalculator.testFourOfAKind(allCards, player);
        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("JdJhJcJs");
        assert player.getStrength() == Strength.FOUR_OF_A_KIND;
        //2
//        player.setCards(CardUtil.parseCards("JsJd"));
//
//        assert fourOfAKindRule.test(allCards, player);
//        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("JdJhJcJsKd");
//        //3
//        player.setCards(CardUtil.parseCards("3d8h"));
//
//        assert fourOfAKindRule.test(allCards, player);
//        assert CardUtil.getCardsAsString(player.getBestHand()).toString().equals("JdJhJcJs8h");
    }

}
