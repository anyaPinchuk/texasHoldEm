package poker;

import poker.model.Card;
import poker.model.Player;
import poker.model.PlayerRule;
import poker.model.Strength;
import poker.rule.StrengthRule;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static poker.CardUtil.sortCards;

public class StrengthCalculator {
    private List<StrengthRule> strengthRules;
    private Map<PlayerRule, Boolean> cachedRules;

    public StrengthCalculator(List<StrengthRule> strengthRules, Map<PlayerRule, Boolean> cachedRules) {
        this.strengthRules = strengthRules;
        this.cachedRules = cachedRules;
    }

    public void calculate(Map<Strength, List<Player>> strengthMap, List<Card> boardCards, Player player) {
        List<Card> allCards = sortCards(boardCards, player.getCards());

        for (StrengthRule rule : strengthRules) {
            //before evaluating next rule check if the strength was already set by one of the child rules
            if (rule.test(allCards, player)) {
                List<Player> players = new ArrayList<>();
                players.add(player);

                if (strengthMap.containsKey(player.getStrength())) {
                    players.addAll(strengthMap.get(player.getStrength()));
                    StrengthComparator.sort(players);
                }

                strengthMap.put(player.getStrength(), players);
                break;
            }
        }

    }

}
