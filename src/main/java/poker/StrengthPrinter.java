package poker;

import poker.model.Card;
import poker.model.Player;
import poker.model.Rank;
import poker.model.Strength;

import java.util.*;
import java.util.stream.Collectors;

public class StrengthPrinter {

    public static void printSortedStrength(List<Card> boardCards, Map<Strength, List<Player>> strengthMap) {
        for (Map.Entry<Strength, List<Player>> e : strengthMap.entrySet()) {
            List<Player> players = e.getValue();

            if (e.getValue().size() > 1)
                players = StrengthComparator.sort(e.getKey(), e.getValue());

            Map<String, List<Player>> playerMap = players.stream().collect(Collectors.groupingBy(StrengthPrinter::getID));

            String spaceSeparator = " ";
            String equalSeparator = "=";

            StringBuilder output = new StringBuilder();
            for (Map.Entry<String, List<Player>> entry : playerMap.entrySet()
                    .stream()
                    .sorted((o1, o2) -> {
                        String cards1 = o1.getKey();
                        String cards2 = o2.getKey();
                        int length = Integer.min(cards1.length(), cards2.length());
                        for (char i = 0; i < length; i++) {
                            int result = Integer.compare(Rank.fromValue(cards2.charAt(i)).ordinal(),
                                    Rank.fromValue(cards1.charAt(i)).ordinal());
                            if (result != 0) return result;
                        }

                        return 0;
                    })
                    .collect(Collectors.toList())) {
                for (Player player : entry.getValue()) {
                    output.append(player).append(equalSeparator);
                }
                output.append(spaceSeparator);
            }

            System.out.println(output);
        }

    }

    public static String getID(Player player) {
        return player.getBestHand().stream().map(card -> String.valueOf(card.getRank().getValue())).collect(Collectors.joining());
    }
}
