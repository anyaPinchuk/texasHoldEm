package poker;

import poker.model.Card;
import poker.model.Player;
import poker.model.Strength;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StrengthComparator {

    public static List<Player> sort(Strength strength, List<Player> players) {
        // sort alphabetically if equal
        players.sort((o1, o2) -> {
            int length = Integer.min(o1.getBestHand().size(), o2.getBestHand().size());
            List<Card> cards1 = o1.getBestHand();
            List<Card> cards2 = o2.getBestHand();
            for (int i = 0; i < length; i++) {
                int result = Integer.compare(cards2.get(i).getRank().ordinal(), cards1.get(i).getRank().ordinal());
                if (result != 0) return result;
            }

            return 0;
        });

        return players;
    }
}
