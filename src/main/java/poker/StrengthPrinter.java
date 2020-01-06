package poker;

import poker.model.Card;
import poker.model.Player;
import poker.model.Strength;

import java.util.List;
import java.util.Map;

public class StrengthPrinter {

    public static void printSortedStrength(List<Card> boardCards, Map<Strength, List<Player>> strengthMap) {
        for (Map.Entry<Strength, List<Player>> e : strengthMap.entrySet()) {
            if (e.getValue().size() > 1)
                StrengthComparator.sort(e.getValue());

            for (Player p : e.getValue()) {
                System.out.print(p + " ");
            }
        }
    }
}
