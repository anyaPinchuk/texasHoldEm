package poker;

import poker.model.*;
import poker.rule.*;

import java.util.*;

public class TexasHoldEmApp {
    private static Scanner scanner = new Scanner(System.in);
    private static List<StrengthRule> strengthRules = new ArrayList<>();

    public static void main(String[] args) {
        List<Card> boardCards = null;
        List<Player> hands = null;

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


        Map<Integer, List<Player>> strengthMap = new TreeMap<>();
        Map<PlayerRule, Boolean> cachedRules = new HashMap<>(hands.size() * strengthRules.size());
        StrengthCalculator strengthCalculator = new StrengthCalculator(strengthRules, cachedRules);

        for (Player player : hands) {
            strengthCalculator.calculate(strengthMap, boardCards, player);
        }

        StrengthPrinter.printSortedStrength(boardCards, strengthMap);

    }

    public static List<Card> parseCards(String input) {
        if (input.trim().length() % 2 != 0) return null;

        List<Card> cards = new ArrayList<>(input.length() / 2);
        char[] arr = input.toCharArray();
        for (int i = 0; i < input.length() - 1; i += 2) {
            Rank rank = Rank.fromValue(arr[i]);
            Suit suit = Suit.fromName(String.valueOf(arr[i + 1]));
            if (rank == null || suit == null) return null;
            else {
                cards.add(new Card(suit, rank));
            }
        }

        return cards;
    }


    public void initRules() {
        FlushRule flushRule = new FlushRule();
        StraightRule straightRule = new StraightRule();
        StraightFlushRule straightFlushRule = new StraightFlushRule(straightRule, flushRule);

        strengthRules.add(new RoyalFlushRule(straightFlushRule));
        strengthRules.add(straightFlushRule);

    }
}
