package poker;

import poker.model.*;

import java.util.*;
import java.util.stream.Collectors;

import static poker.CardUtil.sortCards;

public class StrengthCalculator {
    private Map<PlayerRule, Boolean> cachedRules;

    public StrengthCalculator(Map<PlayerRule, Boolean> cachedRules) {
        this.cachedRules = cachedRules;
    }

    public void calculate(List<Card> boardCards, Player player) {
        List<Card> allCards = sortCards(boardCards, player.getCards());

        findBestStrength(allCards, player);

//get kicker cards
        List<Card> bestHand = player.getBestHand();
        for (Card card : allCards) {
            if (bestHand.size() >= 5) break;
            if (!bestHand.contains(card)) {
                bestHand.add(card);
            }
        }

    }

    public void findBestStrength(List<Card> allCards, Player player) {
        //before evaluating next rule check if the strength was already set by one of the child rules
        if (testRoyalFlush(allCards, player))
            return;

        if (testStraightFlush(allCards, player))
            return;

        if (testFourOfAKind(allCards, player))
            return;

        if (testFullHouse(allCards, player))
            return;

        if (testFlush(allCards, player))
            return;

        if (testStraight(allCards, player))
            return;

        if (testThreeOfAKind(allCards, player))
            return;

        if (testTwoPairs(allCards, player))
            return;

        if (testPair(allCards, player))
            return;

        getHighCard(allCards, player);
    }

    private void getHighCard(List<Card> allCards, Player player) {
        player.setStrength(Strength.HIGH_CARD);
        player.setBestHand(allCards.subList(0, 5));
    }

    public static boolean testRoyalFlush(List<Card> allCards, Player player) {
        if (testStraightFlush(allCards, player)) {
            if (player.getBestHand().get(0).getRank() == Rank.A) {
                player.setStrength(Strength.ROYAL_FLUSH);
                return true;
            }
        }
        return false;
    }

    public static boolean testStraightFlush(List<Card> allCards, Player player) {
        if (testFlush(allCards, player)) {
            if (testStraight(player.getBestHand(), player)) {
                player.setStrength(Strength.STRAIGHT_FLUSH);
                return true;
            }
        }
        return false;
    }

    public static boolean testStraight(List<Card> cards, Player player) {
        TreeSet<Card> uniqueCards = new TreeSet<>(cards);

        boolean isStraight = false;
        List<Card> sequence = new ArrayList<>(5);

        Iterator<Card> iterator = uniqueCards.iterator();
        Card prevItem = iterator.next();

        while (iterator.hasNext()) {
            Card currItem = iterator.next();

            if (prevItem.getRank().ordinal() - currItem.getRank().ordinal() == 1) {
                sequence.add(prevItem);

                if (sequence.size() == 4 || !iterator.hasNext()) {
                    sequence.add(currItem);
                    break;
                }
            } else {
                sequence.clear();
            }

            prevItem = currItem;
        }

        if (sequence.size() == 5) isStraight = true;

        // handle special straight case when TWO is followed by A
        if (sequence.size() == 4 && sequence.get(3).getRank().equals(Rank.TWO)) {
            //check if there's ACE
            if (cards.get(0).getRank().equals(Rank.A)) {
                sequence.add(cards.get(0));
                isStraight = true;
            }
        }

        if (isStraight) {
            player.setStrength(Strength.STRAIGHT);
            player.setBestHand(sequence);
            return true;
        }

        return false;
    }

    public static boolean testFlush(List<Card> allCards, Player player) {
        Map<Suit, List<Card>> cardsMap = allCards.stream().collect(Collectors.groupingBy(Card::getSuit));

        List<Card> bestHand = cardsMap.values().stream().filter(val -> val.size() >= 5).findFirst().orElse(null);

        if (bestHand != null) {
            player.setStrength(Strength.FLUSH);
            player.setBestHand(bestHand.subList(0,5));
            return true;
        }
        return false;
    }

    public static boolean testFourOfAKind(List<Card> allCards, Player player) {
        Map<Rank, List<Card>> cardsMap = allCards.stream().collect(Collectors.groupingBy(Card::getRank));
        List<Card> bestHand = cardsMap.values().stream().filter(val -> val.size() == 4).findFirst().orElse(null);

        if (bestHand != null) {
            player.setStrength(Strength.FOUR_OF_A_KIND);
            player.setBestHand(bestHand);
            return true;
        }

        return false;
    }

    public static boolean testFullHouse(List<Card> allCards, Player player) {
        if (testThreeOfAKind(allCards, player)) {
            List<Card> bestHand = player.getBestHand();

            if (testPair(allCards, player)) {
                bestHand.addAll(player.getBestHand());

                player.setStrength(Strength.FULL_HOUSE);
                player.setBestHand(bestHand);
                return true;
            }
        }
        return false;
    }

    public static boolean testPair(List<Card> allCards, Player player) {
        Map<Rank, List<Card>> cardsMap = allCards.stream().collect(Collectors.groupingBy(Card::getRank));
        List<Card> bestHand = cardsMap.values().stream()
                .filter(val -> val.size() == 2)
                .min((o1, o2) -> o1.get(0).compareTo(o2.get(0)))
                .orElse(null);

        if (bestHand != null) {
            player.setStrength(Strength.ONE_PAIR);
            player.setBestHand(bestHand);
            return true;
        }
        return false;
    }

    public static boolean testThreeOfAKind(List<Card> allCards, Player player) {
        Map<Rank, List<Card>> cardsMap = allCards.stream().collect(Collectors.groupingBy(Card::getRank));
        List<Card> bestHand = cardsMap.values().stream()
                .filter(val -> val.size() == 3)
                .min((o1, o2) -> o1.get(0).compareTo(o2.get(0)))
                .orElse(null);

        if (bestHand != null) {
            player.setStrength(Strength.THREE_OF_A_KIND);
            player.setBestHand(bestHand);
            return true;
        }

        return false;
    }

    public static boolean testTwoPairs(List<Card> allCards, Player player) {
        Map<Rank, List<Card>> cardsMap = allCards.stream().collect(Collectors.groupingBy(Card::getRank));
        List<Card> bestHand = new ArrayList<>(4);

        cardsMap.values().stream()
                .filter(val -> val.size() == 2)
                .sorted((o1, o2) -> o1.get(0).compareTo(o2.get(0)))
                .limit(2)
                .forEach(bestHand::addAll);

        if (bestHand.size() == 4) {
            player.setStrength(Strength.TWO_PAIR);
            player.setBestHand(bestHand);
            return true;
        }
        return false;
    }

}
