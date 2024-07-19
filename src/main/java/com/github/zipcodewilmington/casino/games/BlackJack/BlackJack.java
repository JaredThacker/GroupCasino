package com.github.zipcodewilmington.casino.games.BlackJack;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BlackJack implements GameInterface {
    public int playerMoney;
    public int sizeOfPot;
    public Scanner scanner;
    public List<Card> deck;
    public List<Card> playerHand;
    public List<Card> dealerHand;
    public PlayerInterface player;


    public BlackJack(int initialMoney) {
        this.playerMoney = initialMoney;
        this.scanner = new Scanner(System.in);
        this.sizeOfPot = 0;
        this.deck = initializeDeck();
        this.playerHand = new ArrayList<>();
        this.dealerHand = new ArrayList<>();
    }

    // Constructor for testing
    public BlackJack(int initialMoney, List<Card> deck) {
        this.playerMoney = initialMoney;
        this.scanner = new Scanner(System.in);
        this.sizeOfPot = 0;
        this.deck = new ArrayList<>(deck);
        this.playerHand = new ArrayList<>();
        this.dealerHand = new ArrayList<>();
    }

    public BlackJack() {

    }

    public void playBlackJack() {
        boolean playAgain = true;

        while (playAgain && playerMoney > 0) {
            System.out.println("\n♠︎ Welcome to Blackjack! ♠︎");
            System.out.println("Your current bankroll: $" + playerMoney + " 💵");
            System.out.println("\nPlease enter the amount of money you wish to gamble: ");
            System.out.println("Enter 0 to exit");

            int bet = getBet(scanner.nextLine());
            if (bet == 0) break;

            addToPot(bet);
            dealInitialCards();
            displayInitialHands();

            playerTurn();
            if (getHandValue(playerHand) <= 21) {
                dealerTurn();
            }
            determineWinner();

            playAgain = askToPlayAgain();

            if (deck.size() < 20) {
                System.out.println("Reshuffling the deck...");
                deck = initializeDeck();
            }
        }

        System.out.println("\nThank you for playing Blackjack!");
        System.out.println("Your final bankroll: $" + playerMoney);
        scanner.close();
    }

    public int getBet(String input) {
        try {
            int bet = Integer.parseInt(input);
            if (bet == 0) return 0;
            if (bet > 0 && bet <= playerMoney) {
                return bet;
            } else {
                System.out.println("Invalid bet. Please enter a value between 1 and " + playerMoney);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return -1;
    }

    public void addToPot(int amount) {
        sizeOfPot += amount;
        playerMoney -= amount;
    }

    public void setPot(int amount) {
        this.sizeOfPot = amount;
    }

    public boolean isBlackjack(List<Card> hand) {
        return hand.size() == 2 && getHandValue(hand) == 21;
    }

    public void playerTurn() {
        while (true) {
            System.out.println("\nYour hand: ");
            displayHand(playerHand, false);

            if (playerHand.size() == 2) {
                System.out.print("Do you want to hit (h), stand (s), or double down (d)? ");
            } else {
                System.out.print("Do you want to hit (h) or stand (s)? ");
            }

            String choice = scanner.nextLine().toLowerCase();
            if (playerAction(choice)) {
                break;
            }
        }
    }

    public boolean playerAction(String choice) {
        if ("h".equals(choice)) {
            hit(playerHand);
            if (getHandValue(playerHand) > 21) {
                System.out.println("You bust!");
                return true;
            }
        } else if ("s".equals(choice)) {
            return true;
        } else if ("d".equals(choice) && playerHand.size() == 2) {
            if (playerMoney >= sizeOfPot) {
                addToPot(sizeOfPot);
                hit(playerHand);
                return true;
            } else {
                System.out.println("Not enough money to double down.");
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
        return false;
    }

    public void dealerTurn() {
        System.out.println("\nDealer's turn: ");
        displayHand(dealerHand, false);

        while (getHandValue(dealerHand) < 17) {
            hit(dealerHand);
            displayHand(dealerHand, false);
        }

        if (getHandValue(dealerHand) > 21) {
            System.out.println("Dealer busts!");
        }
    }

    public void determineWinner() {
        int playerScore = getHandValue(playerHand);
        int dealerScore = getHandValue(dealerHand);

        System.out.println("\nYour hand: ");
        displayHand(playerHand, false);
        System.out.println("\nDealer's hand: ");
        displayHand(dealerHand, false);

        if (playerScore > 21) {
            System.out.println("You bust. Dealer wins.");
        } else if (dealerScore > 21) {
            System.out.println("Dealer busts. You win!");
            playerMoney += sizeOfPot * 2;
        } else if (playerScore > dealerScore) {
            System.out.println("You win!");
            playerMoney += sizeOfPot * 2;
        } else if (playerScore < dealerScore) {
            System.out.println("Dealer wins.");
        } else {
            System.out.println("It's a tie.");
            playerMoney += sizeOfPot;
        }
        sizeOfPot = 0;
    }

    public List<Card> initializeDeck() {
        List<Card> newDeck = new ArrayList<>();
        for (Suit suit : Suit.values()) {
            for (Rank rank : Rank.values()) {
                newDeck.add(new Card(rank, suit));
            }
        }
        Collections.shuffle(newDeck);
        return newDeck;
    }

    public void displayHand(List<Card> hand, boolean hideFirstCard) {
        for (int i = 0; i < hand.size(); i++) {
            if (i == 0 && hideFirstCard) {
                System.out.println("[Hidden]");
            } else {
                System.out.println(hand.get(i).toString());
            }
        }
        if (!hideFirstCard) {
            System.out.println("Total value: " + getHandValue(hand));
        }
    }

    public int getHandValue(List<Card> hand) {
        int value = 0;
        int aceCount = 0;
        for (Card card : hand) {
            if (card.getRank() == Rank.ACE) {
                aceCount++;
            }
            value += card.getRank().getValue();
        }
        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }
        return value;
    }

    public void dealInitialCards() {
        playerHand.clear();
        dealerHand.clear();
        hit(playerHand);
        hit(dealerHand);
        hit(playerHand);
        hit(dealerHand);
    }

    public void displayInitialHands() {
        // System.out.println("\nYour hand: ");
        // displayHand(playerHand, false);
        System.out.println("\nDealer's hand: ");
        displayHand(dealerHand, true);
        //System.out.println("Total value of dealers hand: " + getHandValue(dealerHand));
    }

    public boolean askToPlayAgain() {
        System.out.print("\nDo you want to play again? (y/n): ");
        String playChoice = scanner.nextLine().toLowerCase();
        return "y".equals(playChoice);
    }

    public void hit(List<Card> hand) {
        if (!deck.isEmpty()) {
            hand.add(deck.remove(0));
        }
    }

    public void doubleDown() {
        if (playerMoney >= sizeOfPot) {
            addToPot(sizeOfPot / 2);
            hit(playerHand);
            System.out.println("\nYour hand after doubling down:");
            displayHand(playerHand, false);
        } else {
            System.out.println("Not enough money to double down.");
            playerTurn();
        }
    }

    @Override
    public void add(PlayerInterface player) {
        this.player = player;

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {
        BlackJack blackjack = new  BlackJack(playerMoney, deck);

    }

    @Override
    public void addCasinoAccount(CasinoAccount casinoAccount) {

    }

    public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }

    public enum Rank {
        TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
        JACK(10), QUEEN(10), KING(10), ACE(11);

        public final int value;
        Rank(int value) { this.value = value; }
        public int getValue() { return value; }
    }

    public static class Card {
        public final Rank rank;
        public final Suit suit;

        public Card(Rank rank, Suit suit) {
            this.rank = rank;
            this.suit = suit;
        }

        public Rank getRank() { return rank; }

        @Override
        public String toString() { return rank + " of " + suit; }
    }

    // Getter methods for testing
    public List<Card> getPlayerHand() { return playerHand; }
    public List<Card> getDealerHand() { return dealerHand; }
    public int getPlayerMoney() { return playerMoney; }
    public int getPot() { return sizeOfPot; }

    public static void main(String[] args) {
        BlackJack game = new BlackJack(1000);
        game.playBlackJack();
    }
}