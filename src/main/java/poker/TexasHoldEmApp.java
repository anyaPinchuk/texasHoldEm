package poker;

import poker.model.Card;
import poker.model.Player;
import poker.model.PlayerRule;
import poker.model.Strength;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import static poker.CardUtil.parseCards;

public class TexasHoldEmApp {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(scanner.nextLine());
        File file = new File("result.txt");
        if (!file.exists()) file.createNewFile();
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            for (int j = 0; j < N; j++) {
                List<Card> boardCards = null;
                List<Player> hands = null;

                Map<PlayerRule, Boolean> cachedRules = null;
                Map<Strength, List<Player>> strengthMap = new TreeMap<>((s1, s2) -> Integer.compare(s2.ordinal(), s1.ordinal()));


                while (true) {
                    String[] input = scanner.nextLine().split(" ");
                    if (input.length == 0) {
                        System.out.println("Wrong input. Try again");
                        continue;
                    }
                    boardCards = parseCards(input[0]);
                    if (boardCards == null || boardCards.size() < 5) {
                        System.out.println("Wrong board cards input. Try again");
                        continue;
                    }

                    hands = new ArrayList<>(input.length - 1);
                    for (int i = 1; i < input.length; i++) {
                        List<Card> inputCards = parseCards(input[i]);
                        if (inputCards == null || inputCards.size() < 2) {
                            System.out.println("Wrong input for hand " + i + ". Try again");
                            hands = null;
                            break;
                        } else {
                            hands.add(new Player(String.valueOf(i), inputCards));
                        }
                    }

                    if (hands != null) break;
                }

                cachedRules = new HashMap<>(hands.size() * 10);
                StrengthCalculator strengthCalculator = new StrengthCalculator(cachedRules);

                for (Player player : hands) {
                    strengthCalculator.calculate(boardCards, player);
                }

                hands.stream().collect(Collectors.groupingBy(Player::getStrength)).forEach(strengthMap::put);

                String output = StrengthPrinter.printSortedStrength(boardCards, strengthMap);

                outputStream.write(output.getBytes());

            }
            outputStream.flush();
        }
    }
}
