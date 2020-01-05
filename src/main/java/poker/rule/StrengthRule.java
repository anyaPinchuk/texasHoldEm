package poker.rule;

import poker.model.Card;
import poker.model.Player;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface StrengthRule {
    boolean test(List<Card> cards, Player player);

    int compare(Player player1, Player player2);
}
