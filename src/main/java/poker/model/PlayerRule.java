package poker.model;

import poker.rule.StraightRule;

import java.util.Objects;

public class PlayerRule {
    Player player;
    StraightRule rule;

    public PlayerRule(Player player, StraightRule rule) {
        this.player = player;
        this.rule = rule;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerRule key = (PlayerRule) o;
        return player.equals(key.player) &&
                rule.equals(key.rule);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, rule);
    }
}
