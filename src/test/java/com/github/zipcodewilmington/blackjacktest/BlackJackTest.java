package com.github.zipcodewilmington.blackjacktest;

import com.github.zipcodewilmington.casino.games.BlackJack.BlackJack;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class BlackJackTest {

    private BlackJack game;
    private List<BlackJack.Card> testDeck;

    @Before
    public void setUp() {
        testDeck = new ArrayList<>();
        // Add some predetermined cards to the deck
        testDeck.add(new BlackJack.Card(BlackJack.Rank.ACE, BlackJack.Suit.HEARTS));
        testDeck.add(new BlackJack.Card(BlackJack.Rank.KING, BlackJack.Suit.SPADES));
        testDeck.add(new BlackJack.Card(BlackJack.Rank.QUEEN, BlackJack.Suit.DIAMONDS));
        testDeck.add(new BlackJack.Card(BlackJack.Rank.JACK, BlackJack.Suit.CLUBS));
        testDeck.add(new BlackJack.Card(BlackJack.Rank.TEN, BlackJack.Suit.HEARTS));
        testDeck.add(new BlackJack.Card(BlackJack.Rank.NINE, BlackJack.Suit.SPADES));
        testDeck.add(new BlackJack.Card(BlackJack.Rank.EIGHT, BlackJack.Suit.DIAMONDS));
        testDeck.add(new BlackJack.Card(BlackJack.Rank.SEVEN, BlackJack.Suit.CLUBS));

        game = new BlackJack(1000, new ArrayList<>(testDeck));
    }

    @Test
    public void testInitialDeal() {
        game.dealInitialCards();
        assertEquals(2, game.getPlayerHand().size());
        assertEquals(2, game.getDealerHand().size());
    }

    @Test
    public void testGetHandValue() {
        List<BlackJack.Card> hand = new ArrayList<>();
        hand.add(new BlackJack.Card(BlackJack.Rank.ACE, BlackJack.Suit.HEARTS));
        hand.add(new BlackJack.Card(BlackJack.Rank.KING, BlackJack.Suit.SPADES));
        assertEquals(21, game.getHandValue(hand));

        hand.add(new BlackJack.Card(BlackJack.Rank.FIVE, BlackJack.Suit.DIAMONDS));
        assertEquals(16, game.getHandValue(hand));
    }

    @Test
    public void testIsBlackjack() {
        List<BlackJack.Card> hand = new ArrayList<>();
        hand.add(new BlackJack.Card(BlackJack.Rank.ACE, BlackJack.Suit.HEARTS));
        hand.add(new BlackJack.Card(BlackJack.Rank.KING, BlackJack.Suit.SPADES));
        assertTrue(game.isBlackjack(hand));

        hand.add(new BlackJack.Card(BlackJack.Rank.TWO, BlackJack.Suit.DIAMONDS));
        assertFalse(game.isBlackjack(hand));
    }

    @Test
    public void testDealerTurn() {
        game.dealInitialCards();
        game.dealerTurn();
        assertTrue(game.getHandValue(game.getDealerHand()) >= 17);
    }

    @Test
    public void testAddToPot() {
        int initialMoney = game.getPlayerMoney();
        int initialPot = game.getPot();
        int betAmount = 100;

        game.addToPot(betAmount);

        assertEquals(initialMoney - betAmount, game.getPlayerMoney());
        assertEquals(initialPot + betAmount, game.getPot());
    }

    @Test
    public void testInitializeDeck() {
        List<BlackJack.Card> deck = game.initializeDeck();
        assertEquals(52, deck.size());
    }

    @Test
    public void testGetBet() {
        assertEquals(100, game.getBet("100"));
        assertEquals(0, game.getBet("0"));
        assertEquals(-1, game.getBet("invalid"));
        assertEquals(-1, game.getBet("2000")); // Assuming player money is 1000
    }

    @Test
    public void testPlayerAction() {
        game = new BlackJack(1000, new ArrayList<>(testDeck));
        game.dealInitialCards();

        // Test hit
        assertFalse(game.playerAction("h"));
        assertEquals(3, game.getPlayerHand().size());

        // Test stand
        assertTrue(game.playerAction("s"));

        // Test double down
        game = new BlackJack(1000, new ArrayList<>(testDeck)); // Reset the game
        game.dealInitialCards();
        int initialMoney = game.getPlayerMoney();
        int initialPot = 100;
        game.setPot(initialPot); // Set the pot directly for testing
        assertTrue(game.playerAction("d"));
        assertEquals(3, game.getPlayerHand().size());
        assertEquals(initialPot * 2, game.getPot());
        assertEquals(initialMoney - initialPot, game.getPlayerMoney());

        // Test invalid input
        assertFalse(game.playerAction("x"));
    }

    @Test
    public void testDetermineWinner() {
        // Player wins
        game.dealInitialCards();
        game.getPlayerHand().clear();
        game.getPlayerHand().add(new BlackJack.Card(BlackJack.Rank.KING, BlackJack.Suit.HEARTS));
        game.getPlayerHand().add(new BlackJack.Card(BlackJack.Rank.QUEEN, BlackJack.Suit.HEARTS));
        game.getDealerHand().clear();
        game.getDealerHand().add(new BlackJack.Card(BlackJack.Rank.TEN, BlackJack.Suit.HEARTS));
        game.getDealerHand().add(new BlackJack.Card(BlackJack.Rank.NINE, BlackJack.Suit.SPADES));
        int initialMoney = game.getPlayerMoney();
        int pot = 100;
        game.addToPot(pot);
        game.determineWinner();
        assertEquals(initialMoney + pot, game.getPlayerMoney());

        // Dealer wins
        game = new BlackJack(1000, new ArrayList<>(testDeck));
        game.dealInitialCards();
        game.getPlayerHand().clear();
        game.getPlayerHand().add(new BlackJack.Card(BlackJack.Rank.TEN, BlackJack.Suit.HEARTS));
        game.getPlayerHand().add(new BlackJack.Card(BlackJack.Rank.NINE, BlackJack.Suit.SPADES));
        game.getDealerHand().clear();
        game.getDealerHand().add(new BlackJack.Card(BlackJack.Rank.KING, BlackJack.Suit.HEARTS));
        game.getDealerHand().add(new BlackJack.Card(BlackJack.Rank.QUEEN, BlackJack.Suit.HEARTS));
        initialMoney = game.getPlayerMoney();
        pot = 100;
        game.addToPot(pot);
        game.determineWinner();
        assertEquals(initialMoney - pot, game.getPlayerMoney());

        // Tie
        game = new BlackJack(1000, new ArrayList<>(testDeck));
        game.dealInitialCards();
        game.getPlayerHand().clear();
        game.getPlayerHand().add(new BlackJack.Card(BlackJack.Rank.TEN, BlackJack.Suit.HEARTS));
        game.getPlayerHand().add(new BlackJack.Card(BlackJack.Rank.TEN, BlackJack.Suit.SPADES));
        game.getDealerHand().clear();
        game.getDealerHand().add(new BlackJack.Card(BlackJack.Rank.TEN, BlackJack.Suit.DIAMONDS));
        game.getDealerHand().add(new BlackJack.Card(BlackJack.Rank.TEN, BlackJack.Suit.CLUBS));
        initialMoney = game.getPlayerMoney();
        pot = 100;
        game.addToPot(pot);
        game.determineWinner();
        assertEquals(initialMoney, game.getPlayerMoney());
    }
}