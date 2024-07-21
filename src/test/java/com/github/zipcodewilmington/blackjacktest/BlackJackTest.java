package com.github.zipcodewilmington.blackjacktest;

import com.github.zipcodewilmington.casino.games.BlackJack.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class BlackJackTest {
    private BlackJack game;

    @Before
    public void setUp() {
        Scanner scanner = new Scanner(System.in);
        game = new BlackJack(scanner);
    }

    @Test
    public void testInitialPlayerMoney() {
        assertEquals("Initial player money should be 1000", 1000, game.getPlayer().getMoney());
    }

    @Test
    public void testDealInitialCards() {
        game.dealInitialCards();
        assertEquals("Player should have 2 cards", 2, game.getPlayer().getHand().size());
        assertEquals("Dealer should have 2 cards", 2, game.getDealer().getHand().size());
        assertNotEquals("Player's cards should be different", game.getPlayer().getHand().get(0), game.getPlayer().getHand().get(1));
        assertNotEquals("Dealer's cards should be different", game.getDealer().getHand().get(0), game.getDealer().getHand().get(1));
    }

    @Test
    public void testPlayerBet() {
        BJPlayer player = game.getPlayer();
        int initialMoney = player.getMoney();
        player.addToPot(100);
        assertEquals("Current bet should be 100", 100, player.getCurrentBet());
        assertEquals("Player money should decrease by 100", initialMoney - 100, player.getMoney());
    }

    @Test
    public void testPlayerWinBet() {
        BJPlayer player = game.getPlayer();
        int initialMoney = player.getMoney();
        player.addToPot(100);
        player.winBet(2);
        assertEquals("Player should win 200", initialMoney + 100, player.getMoney());
        assertEquals("Current bet should be reset to 0", 0, player.getCurrentBet());
    }

    @Test
    public void testPlayerLoseBet() {
        BJPlayer player = game.getPlayer();
        int initialMoney = player.getMoney();
        player.addToPot(100);
        player.loseBet();
        assertEquals("Player should lose 100", initialMoney - 100, player.getMoney());
        assertEquals("Current bet should be reset to 0", 0, player.getCurrentBet());
    }

    @Test
    public void testIsBlackjack() {
        BJPlayer player = game.getPlayer();
        player.clearHand();
        player.addCard(new Card(Value.ACE, Suit.HEARTS));
        player.addCard(new Card(Value.KING, Suit.SPADES));
        assertTrue("Ace and King should be blackjack", player.hasBlackjack());

        player.clearHand();
        player.addCard(new Card(Value.KING, Suit.HEARTS));
        player.addCard(new Card(Value.QUEEN, Suit.SPADES));
        assertFalse("Two face cards should not be blackjack", player.hasBlackjack());
    }

    @Test
    public void testGetHandValue() {
        BJPlayer player = game.getPlayer();
        player.clearHand();
        player.addCard(new Card(Value.ACE, Suit.HEARTS));
        player.addCard(new Card(Value.KING, Suit.SPADES));
        assertEquals("Ace and King should be 21", 21, player.getHandValue());

        player.addCard(new Card(Value.FIVE, Suit.DIAMONDS));
        assertEquals("Ace should be counted as 1 when over 21", 16, player.getHandValue());
    }

    @Test
    public void testDoubleDown() {
        BJPlayer player = game.getPlayer();
        int initialMoney = player.getMoney();
        player.addToPot(100);
        player.addCard(new Card(Value.TEN, Suit.HEARTS));
        player.addCard(new Card(Value.FIVE, Suit.SPADES));

        assertTrue("Double down should be successful", game.doubleDown());
        assertEquals("Current bet should be 200", 200, player.getCurrentBet());
        assertEquals("Player money should decrease by 200", initialMoney - 200, player.getMoney());
        assertEquals("Player should have 3 cards after double down", 3, player.getHand().size());
    }

    @Test
    public void testGetBet() {
        BJPlayer player = game.getPlayer();
        assertEquals("Valid bet should be accepted", 100, player.getBet("100"));
        assertEquals("Zero bet should be accepted", 0, player.getBet("0"));
        assertEquals("Invalid input should return -1", -1, player.getBet("invalid"));
        assertEquals("Bet larger than player money should return -1", -1, player.getBet("2000"));
    }

    @Test
    public void testPlayerTurn() {
        String input = "s\n";
        Scanner scanner = new Scanner(input);
        game = new BlackJack(scanner);  // Reinitialize game with mocked input scanner
        game.dealInitialCards();

        game.playerTurn();  // No arguments
        assertTrue("Player's hand should have at least 2 cards after turn",
                game.getPlayer().getHand().size() >= 2);
    }

    @Test
    public void testDealerTurn() {
        game.dealInitialCards();
        game.dealerTurn();
        assertTrue("Dealer's hand value should be at least 17 after turn",
                game.getDealer().getHandValue() >= 17);
    }

    @Test
    public void testDetermineWinner() {
        BJPlayer player = game.getPlayer();
        Dealer dealer = game.getDealer();

        // Player wins
        player.clearHand();
        dealer.clearHand();
        player.addCard(new Card(Value.TEN, Suit.HEARTS));
        player.addCard(new Card(Value.NINE, Suit.SPADES));
        dealer.addCard(new Card(Value.TEN, Suit.DIAMONDS));
        dealer.addCard(new Card(Value.SEVEN, Suit.CLUBS));
        player.addToPot(100);
        game.determineWinner();
        assertEquals("Player should win 200", 1100, player.getMoney());

        // Dealer wins
        player.clearHand();
        dealer.clearHand();
        player.addCard(new Card(Value.TEN, Suit.HEARTS));
        player.addCard(new Card(Value.SEVEN, Suit.SPADES));
        dealer.addCard(new Card(Value.TEN, Suit.DIAMONDS));
        dealer.addCard(new Card(Value.NINE, Suit.CLUBS));
        player.addToPot(100);
        game.determineWinner();
        assertEquals("Player should lose 100", 1000, player.getMoney());
    }

    @Test
    public void testBlackjackPayout() {
        BJPlayer player = game.getPlayer();
        Dealer dealer = game.getDealer();

        // Set up the deck to ensure a blackjack for the player
        Deck rigged = new Deck();
        rigged.addCard(new Card(Value.KING, Suit.SPADES));
        rigged.addCard(new Card(Value.SEVEN, Suit.CLUBS));
        rigged.addCard(new Card(Value.ACE, Suit.HEARTS));
        rigged.addCard(new Card(Value.TEN, Suit.DIAMONDS));
        game.setDeck(rigged);

        int initialMoney = player.getMoney();  // Should be 1000
        int bet = 100;
        player.addToPot(bet);

        boolean continueGame = game.dealInitialCards();

        assertFalse("Game should end on blackjack", continueGame);
        int expectedPayout = initialMoney + (int)(bet * 1.5);  // Blackjack pays 3:2
        assertEquals("Player should win 150 for blackjack", expectedPayout, player.getMoney());
    }

    @Test
    public void testPlayerBust() {
        BJPlayer player = game.getPlayer();
        player.clearHand();
        player.addCard(new Card(Value.TEN, Suit.HEARTS));
        player.addCard(new Card(Value.KING, Suit.SPADES));
        player.addCard(new Card(Value.FIVE, Suit.DIAMONDS));
        player.addToPot(100);
        game.determineWinner();
        assertEquals("Player should lose 100 on bust", 900, player.getMoney());
    }

    @Test
    public void testDealerBust() {
        BJPlayer player = game.getPlayer();
        Dealer dealer = game.getDealer();
        player.clearHand();
        dealer.clearHand();
        player.addCard(new Card(Value.TEN, Suit.HEARTS));
        player.addCard(new Card(Value.EIGHT, Suit.SPADES));
        dealer.addCard(new Card(Value.TEN, Suit.DIAMONDS));
        dealer.addCard(new Card(Value.NINE, Suit.CLUBS));
        dealer.addCard(new Card(Value.FIVE, Suit.HEARTS));
        player.addToPot(100);
        game.determineWinner();
        assertEquals("Player should win 200 when dealer busts", 1100, player.getMoney());
    }

    @Test
    public void testTie() {
        BJPlayer player = game.getPlayer();
        Dealer dealer = game.getDealer();
        int initialMoney = player.getMoney();
        int betAmount = 100;

        player.clearHand();
        dealer.clearHand();
        player.addCard(new Card(Value.TEN, Suit.HEARTS));
        player.addCard(new Card(Value.EIGHT, Suit.SPADES));
        dealer.addCard(new Card(Value.JACK, Suit.DIAMONDS));
        dealer.addCard(new Card(Value.EIGHT, Suit.CLUBS));

        player.addToPot(betAmount);
        game.determineWinner();

        assertEquals("Player should not lose their bet on a tie", initialMoney, player.getMoney());
        assertEquals("Current bet should be reset to 0 after a tie", 0, player.getCurrentBet());
    }
}