package poker;

import poker.model.Card;
import poker.model.Player;
import poker.model.Rank;
import poker.model.Strength;

import java.util.*;
import java.util.stream.Collectors;

public class StrengthPrinter {

    public static String printSortedStrength(List<Card> boardCards, Map<Strength, List<Player>> strengthMap) {
        String spaceSeparator = " ";
        String equalSeparator = "=";
        StringBuilder output = new StringBuilder();

        for (Map.Entry<Strength, List<Player>> e : strengthMap.entrySet()) {
            List<Player> players = e.getValue();
            NavigableMap<String, List<Player>> playerMapSorted = new TreeMap<String, List<Player>>((o1, o2) -> {
                int length = Integer.min(o1.length(), o2.length());
                for (char i = 0; i < length; i++) {
                    int result = Integer.compare(Rank.fromValue(o2.charAt(i)).ordinal(),
                            Rank.fromValue(o1.charAt(i)).ordinal());
                    if (result != 0) return result;
                }
                return 0;
            }).descendingMap();

            if (players.size() > 1) {
                players.stream().collect(Collectors.groupingBy(StrengthPrinter::getHandAsString)).forEach(playerMapSorted::put);
            } else {
                Player player = players.get(0);
                playerMapSorted.put(getHandAsString(player), Collections.singletonList(player));
            }

            for (List<Player> playerList : playerMapSorted.values()) {
                if (playerList.size() == 1) {
                    output.append(playerList.get(0));
                    output.append(spaceSeparator);
                    continue;
                }

                int i = 0;
                for (Player player : playerList) {
                    output.append(player);
                    if (++i != playerList.size())
                        output.append(equalSeparator);
                }
                output.append(spaceSeparator);
            }

        }
        return output.toString().trim() + "\n";
    }

    public static String getHandAsString(Player player) {
        return player.getBestHand().stream().map(card -> String.valueOf(card.getRank().getValue())).collect(Collectors.joining());
    }
}
