package poker;

import poker.model.Card;
import poker.model.Player;
import poker.model.PlayerRule;
import poker.model.Strength;
import poker.rule.*;

import java.util.*;

import static poker.CardUtil.parseCards;

public class TexasHoldEmApp {
    private static List<StrengthRule> strengthRules = new ArrayList<>(10);
    private static Map<Strength, List<Player>> strengthMap = new TreeMap<>();
    private static Map<PlayerRule, Boolean> cachedRules;

    public static void main(String[] args) {
        List<Card> boardCards = null;
        List<Player> hands = null;
        Scanner scanner = new Scanner(System.in);
        StrengthCalculator strengthCalculator = new StrengthCalculator(strengthRules, cachedRules);
        initRules();

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

        cachedRules = new HashMap<>(hands.size() * strengthRules.size());

        for (Player player : hands) {
            strengthCalculator.calculate(strengthMap, boardCards, player);
        }

        StrengthPrinter.printSortedStrength(boardCards, strengthMap);

    }


    public static void initRules() {
        FlushRule flushRule = new FlushRule();
        StraightRule straightRule = new StraightRule();
        straightRule.setSameSuit(true);
        StraightFlushRule straightFlushRule = new StraightFlushRule(straightRule);

        strengthRules.add(new RoyalFlushRule(straightFlushRule));
        strengthRules.add(straightFlushRule);

    }
}
