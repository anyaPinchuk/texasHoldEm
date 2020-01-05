package poker;

import poker.model.Card;
import poker.model.Player;

import java.util.List;
import java.util.Map;

public class StrengthPrinter {

    public static void printSortedStrength(List<Card> boardCards, Map<Integer, List<Player>> strengthMap) {
        for (Map.Entry<Integer, List<Player>> e : strengthMap.entrySet()) {
            if (e.getValue().size() > 1)
                StrengthComparator.sort(e.getValue());

            for (Player p : e.getValue()) {
                System.out.print(p + " ");
            }
        }
    }
}
