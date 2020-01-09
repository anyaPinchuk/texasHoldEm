package poker;

import org.junit.After;
import poker.model.Player;

public class RuleTest {
    protected Player player = new Player("1", null);

    @After
    public void tearDown() {
        player.setBestHand(null);
    }
}
