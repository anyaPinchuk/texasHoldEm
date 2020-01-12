package poker.model;

import java.util.Objects;

public class PlayerRule {
    Player player;
    Strength strength;

    public PlayerRule(Player player, Strength strength) {
        this.player = player;
        this.strength = strength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerRule key = (PlayerRule) o;
        return player.equals(key.player) &&
                strength.equals(key.strength);
    }

    @Override
    public int hashCode() {
        return Objects.hash(player, strength);
    }
}
