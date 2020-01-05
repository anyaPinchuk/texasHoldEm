package poker;

import org.junit.Test;
import poker.model.Player;
import poker.rule.StraightRule;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StraightTest {

    @Test
    public void shouldCalculate() {
        TestData testData = new TestData();
        StraightRule straightRule = new StraightRule();
        StrengthCalculator strengthCalculator = new StrengthCalculator(Arrays.asList(straightRule), 4);
        Map<Integer, List<Player>> strengthMap = new TreeMap<>();

        strengthCalculator.calculate(strengthMap, testData.boardCards, testData.players.get(3));

        assert strengthMap.containsKey(3);
    }
}
