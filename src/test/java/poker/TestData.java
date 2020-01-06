package poker;

import poker.model.Card;
import poker.model.Player;
import poker.model.Rank;
import poker.model.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestData {
    public List<Player> players = new ArrayList<>(4);
    public List<Card> boardCards = new ArrayList<>(5);

    public TestData() {
        createBoardCards();
        players.add(new Player("1", Arrays.asList(new Card(Suit.c, Rank.A), new Card(Suit.d, Rank.FOUR))));
        players.add(new Player("2", Arrays.asList(new Card(Suit.s, Rank.A), new Card(Suit.s, Rank.NINE))));
        players.add(new Player("3", Arrays.asList(new Card(Suit.h, Rank.K), new Card(Suit.d, Rank.K))));
        players.add(new Player("4", Arrays.asList(new Card(Suit.d, Rank.FIVE), new Card(Suit.d, Rank.SIX))));
    }

    public void createBoardCards() {
        Card card = new Card(Suit.c, Rank.FOUR);
        boardCards.add(card);
        card = new Card(Suit.s, Rank.K);
        boardCards.add(card);
        card = new Card(Suit.h, Rank.FOUR);
        boardCards.add(card);
        card = new Card(Suit.s, Rank.EIGHT);
        boardCards.add(card);
        card = new Card(Suit.s, Rank.SEVEN);
        boardCards.add(card);
    }
}
