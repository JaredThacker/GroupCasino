package com.github.zipcodewilmington.casino.games.BlackJack;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Scanner;

public class BlackJack implements GameInterface {
    private BJPlayer player;
    private Dealer dealer;
    private Scanner scanner;
    private Deck deck;

    public BlackJack(Scanner scanner) {
        if (scanner == null) {
            throw new IllegalArgumentException("Scanner cannot be null");
        }
        this.scanner = scanner;
        initializeGame();
    }

    private void initializeGame() {
        this.player = new BJPlayer("Player", 1000); // Initialize with default values
        this.dealer = new Dealer();
        this.deck = new Deck();
        this.deck.createFullDeck();
        this.deck.shuffleDeck();
    }

    private int getBet() {
        if (player == null) {
            System.out.println("Error: Player is not initialized.");
            return 0;
        }
        if (scanner == null) {
            System.out.println("Error: Scanner is not initialized.");
            return 0;
        }
        int bet;
        do {
            System.out.print("Enter your bet (or 0 to quit): $");
            String input = scanner.nextLine();
            bet = player.getBet(input);
        } while (bet == -1);
        return bet;
    }

    public void playBlackJack() {
        displayWelcomeMessage();
        boolean playAgain = true;

        while (playAgain && player.getMoney() > 0) {
            System.out.println("\n💵 Your current bankroll: $" + player.getMoney() + " 💵");

            int bet = getBet();
            if (bet == 0) break;

            player.addToPot(bet);
            boolean continueRound = dealInitialCards();

            if (continueRound) {
                displayInitialHands();
                playerTurn();
                if (player.getHandValue() <= 21) {
                    dealerTurn();
                }
                determineWinner();
            }

            playAgain = askToPlayAgain();
        }

        endGame();
    }

    private boolean dealInitialCards() {
        player.clearHand();
        dealer.clearHand();

        if (deck.remainingCards() < 4) {
            System.out.println("Reshuffling the deck...");
            deck.createFullDeck();
            deck.shuffleDeck();
        }

        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());

        if (player.hasBlackjack()) {
            System.out.println("\nPlayer has Blackjack!");
            dealer.displayHand(false);
            if (dealer.hasBlackjack()) {
                System.out.println("\nDealer also has Blackjack. It's a push.");
                player.pushBet();
            } else {
                System.out.println("\n🎉 You win with Blackjack!");
                player.winBet(2.5);
            }
            return false;
        }

        if (dealer.hasBlackjack()) {
            System.out.println("\nDealer has Blackjack!");
            dealer.displayHand(false);
            player.loseBet();
            return false;
        }

        return true;
    }

    private void displayInitialHands() {
        player.displayHand();
        dealer.displayHand(true);
    }

    private void playerTurn() {
        while (true) {
            System.out.print("\n➤ Do you want to hit (h), stand (s)" +
                    (player.getHand().size() == 2 ? ", or double down (d)? " : "? "));
            String choice = scanner.nextLine().toLowerCase();
            if (playerAction(choice)) {
                break;
            }
            player.displayHand();
        }
    }

    private boolean playerAction(String choice) {
        if ("h".equals(choice)) {
            player.addCard(deck.drawCard());
            if (player.getHandValue() > 21) {
                System.out.println("\nYou bust!");
                return true;
            }
        } else if ("s".equals(choice)) {
            return true;
        } else if ("d".equals(choice) && player.getHand().size() == 2) {
            if (doubleDown()) {
                return true;
            }
        } else {
            System.out.println("\n❌ Invalid choice. Please try again.");
        }
        return false;
    }

    private boolean doubleDown() {
        if (player.getMoney() >= player.getCurrentBet()) {
            player.addToPot(player.getCurrentBet());
            player.addCard(deck.drawCard());
            System.out.println("\nYour hand after doubling down:");
            player.displayHand();
            return true;
        } else {
            System.out.println("\nNot enough money to double down.");
            return false;
        }
    }

    private void dealerTurn() {
        dealer.displayHand(false);
        while (dealer.shouldHit()) {
            dealer.addCard(deck.drawCard());
            dealer.displayHand(false);
        }
    }

    private void determineWinner() {
        int playerScore = player.getHandValue();
        int dealerScore = dealer.getHandValue();

        player.displayHand();
        dealer.displayHand(false);

        if (playerScore > 21) {
            System.out.println("\n😖 You bust. Dealer wins.");
            player.loseBet();
        } else if (dealerScore > 21) {
            System.out.println("\n😂 Dealer busts. You win!");
            player.winBet(2);
        } else if (playerScore > dealerScore) {
            System.out.println("\n💰 You win!");
            player.winBet(2);
        } else if (playerScore < dealerScore) {
            System.out.println("\n😓 Dealer wins.");
            player.loseBet();
        } else {
            System.out.println("\n☹️ It's a tie!");
            player.pushBet();
        }
    }

    private boolean askToPlayAgain() {
        while (true) {
            System.out.print("\n⎆ Do you want to play again? (y/n): ");
            String playChoice = scanner.nextLine().toLowerCase();
            if ("y".equals(playChoice)) {
                return true;
            } else if ("n".equals(playChoice)) {
                return false;
            } else {
                System.out.println("\n❌ Invalid input. Please enter 'y' for yes or 'n' for no.");
            }
        }
    }

    private void displayWelcomeMessage() {
        System.out.println("♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦");
        System.out.println("                                                         ");
        System.out.println("   ██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗");
        System.out.println("   ██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝");
        System.out.println("   ██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗  ");
        System.out.println("   ██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝  ");
        System.out.println("   ╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗");
        System.out.println("    ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝");
        System.out.println("                                                         ");
        System.out.println("   ████████╗ ██████╗     ██████╗ ██╗      █████╗  ██████╗██╗  ██╗     ██╗ █████╗  ██████╗██╗  ██╗");
        System.out.println("   ╚══██╔══╝██╔═══██╗    ██╔══██╗██║     ██╔══██╗██╔════╝██║ ██╔╝     ██║██╔══██╗██╔════╝██║ ██╔╝");
        System.out.println("      ██║   ██║   ██║    ██████╔╝██║     ███████║██║     █████╔╝      ██║███████║██║     █████╔╝ ");
        System.out.println("      ██║   ██║   ██║    ██╔══██╗██║     ██╔══██║██║     ██╔═██╗ ██   ██║██╔══██║██║     ██╔═██╗ ");
        System.out.println("      ██║   ╚██████╔╝    ██████╔╝███████╗██║  ██║╚██████╗██║  ██╗╚█████╔╝██║  ██║╚██████╗██║  ██╗");
        System.out.println("      ╚═╝    ╚═════╝     ╚═════╝ ╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝ ╚════╝ ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝");
        System.out.println("                                                         ");
        System.out.println("♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦");
    }

    private void endGame() {
        System.out.println("\nThank you for playing Blackjack!");
        System.out.println("Your final bankroll: $" + player.getMoney());
        System.out.println("National Problem Gambling Helpline number: 1-800-GAMBLER\n\n\n\n");
    }

    @Override
    public void run() {
        playBlackJack();
    }

    @Override
    public void add(PlayerInterface player) {
        if (player instanceof BJPlayer) {
            this.player = (BJPlayer) player;
        }
    }

    @Override
    public void remove(PlayerInterface player) {
        // Do not set player to null, just remove it from the game if needed
    }

    @Override
    public void addCasinoAccount(CasinoAccount casinoAccount) {
        // Implementation if needed
    }
}
//package com.github.zipcodewilmington.casino.games.BlackJack;
//
//import com.github.zipcodewilmington.casino.CasinoAccount;
//import com.github.zipcodewilmington.casino.GameInterface;
//import com.github.zipcodewilmington.casino.PlayerInterface;
//
//import java.util.*;
//
//public class BlackJack implements GameInterface {
//    private BJPlayer player;
//    private Dealer dealer;
//    private Scanner scanner;
//    private Deck deck;
//
//    public BlackJack(){
//
//    }
//
//    public BlackJack(int initialPlayerMoney) {
//        this.player = new BJPlayer("Player", initialPlayerMoney);
//        this.dealer = new Dealer();
//        this.scanner = new Scanner(System.in);
//        this.deck = dealCards();
//    }
//
//    public void setDeck(Deck deck) {
//        this.deck = deck;
//    }
//
//    private void checkAndReshuffleDeckIfNeeded() {
//        if (deck.remainingCards() < 10) {  // Reshuffle when less than 10 cards left
//            System.out.println("Reshuffling the deck...");
//            deck = dealCards();
//        }
//    }
//
//    public void playBlackJack() {
//        displayWelcomeMessage();
//        boolean playAgain = true;
//
//        while (playAgain && player.getMoney() > 0) {
//            System.out.println("\n💵 Your current bankroll: $" + player.getMoney() + " 💵");
//
//            int bet = getBet();
//            if (bet == 0) break;
//
//            player.addToPot(bet);
//            boolean continueRound = dealInitialCards();
//
//            if (continueRound) {
//                displayInitialHands();
//                playerTurn();
//                if (player.getHandValue() <= 21) {
//                    dealerTurn();
//                }
//                determineWinner();
//            }
//
//            playAgain = askToPlayAgain();
//        }
//
//        endGame();
//    }
//
//    private int getBet() {
//        int bet;
//        do {
//            System.out.print("Enter your bet (or 0 to quit): $");
//            bet = player.getBet(scanner.nextLine());
//        } while (bet == -1);
//        return bet;
//    }
//
//    public boolean dealInitialCards() {
//        player.clearHand();
//        dealer.clearHand();
//
//        if (deck.remainingCards() < 4) {
//            System.out.println("Reshuffling the deck...");
//            deck = dealCards();
//        }
//
//        player.addCard(deck.drawCard());
//        dealer.addCard(deck.drawCard());
//        player.addCard(deck.drawCard());
//        dealer.addCard(deck.drawCard());
//
//        //displayInitialHands();
//
//        if (player.hasBlackjack()) {
//            System.out.println("\nPlayer has Blackjack!");
//            dealer.displayHand(false); // Show dealer's hand
//            if (dealer.hasBlackjack()) {
//                System.out.println("\nDealer also has Blackjack. It's a push.");
//                player.pushBet(); // Implement this method to return the bet to the player
//            } else {
//                System.out.println("\n🎉 You win with Blackjack!");
//                player.winBet(2.5); // Blackjack pays 3:2
//            }
//            return false; // End the round
//        }
//
//        if (dealer.hasBlackjack()) {
//            System.out.println("\nDealer has Blackjack!");
//            dealer.displayHand(false);
//            player.loseBet();
//            return false; // End the round
//        }
//
//        return true; // Continue the round if no blackjack
//    }
//
//    private void handlePlayerBlackjack() {
//        System.out.println("\n♠︎♠︎ Player has Blackjack! ♠︎♠︎");
//        dealer.displayHand(false); // Reveal dealer's hand to check for blackjack
//        if (dealer.hasBlackjack()) {
//            System.out.println("\n😩 Dealer also has Blackjack. You lose.");
//            // Handle push (tie) logic here
//        } else {
//            System.out.println("\n🎉 You win with Blackjack!");
//            player.winBet(2.5); // Blackjack pays 3:2
//        }
//    }
//
//    public void playerTurn() {
//        while (true) {
//            System.out.print("\n➤ Do you want to hit (h), stand (s)" +
//                    (player.getHand().size() == 2 ? ", or double down (d)? " : "? "));
//            String choice = scanner.nextLine().toLowerCase();
//            if (playerAction(choice)) {
//                break;
//            }
//            player.displayHand();
//        }
//    }
//
//    private boolean playerAction(String choice) {
//        if ("h".equals(choice)) {
//            checkAndReshuffleDeckIfNeeded();
//            player.addCard(deck.drawCard());
//            if (player.getHandValue() > 21) {
//                System.out.println("\nYou bust!");
//                return true;
//            }
//        } else if ("s".equals(choice)) {
//            return true;
//        } else if ("d".equals(choice) && player.getHand().size() == 2) {
//            if (doubleDown()) {
//                return true;
//            }
//        } else {
//            System.out.println("\n❌ Invalid choice. Please try again.");
//        }
//        return false;
//    }
//
//    public boolean doubleDown() {
//        if (player.getMoney() >= player.getCurrentBet()) {
//            player.addToPot(player.getCurrentBet());
//            checkAndReshuffleDeckIfNeeded();
//            player.addCard(deck.drawCard());
//            System.out.println("\nYour hand after doubling down:");
//            player.displayHand();
//            return true;
//        } else {
//            System.out.println("\nNot enough money to double down.");
//            return false;
//        }
//    }
//
//    public void dealerTurn() {
//        //System.out.println("\nDealer's turn: ");
//        // dealer.displayHand(false);
//
//        while (dealer.shouldHit()) {
//            checkAndReshuffleDeckIfNeeded();
//            dealer.addCard(deck.drawCard());
//            //dealer.displayHand(false);
//        }
//
//        if (dealer.getHandValue() > 21) {
//            // System.out.println("\n😂 Dealer busts!");
//        }
//    }
//
//    public void determineWinner() {
//        int playerScore = player.getHandValue();
//        int dealerScore = dealer.getHandValue();
//
//        player.displayHand();
//        dealer.displayHand(false);
//
//        if (playerScore > 21) {
//            System.out.println("\n😖 You bust. Dealer wins.");
//            player.loseBet();
//        } else if (dealerScore > 21) {
//            System.out.println("\n😂 Dealer busts. You win!");
//            player.winBet(2);
//        } else if (playerScore > dealerScore) {
//            System.out.println("\n💰 You win!");
//            player.winBet(2);
//        } else if (playerScore < dealerScore) {
//            System.out.println("\n😓 Dealer wins.");
//            player.loseBet();
//        } else {
//            System.out.println("\n☹️ It's a tie! Dealer wins.");
//            player.loseBet();
//        }
//    }
//
//    private boolean askToPlayAgain() {
//        while (true) {
//            System.out.print("\n⎆ Do you want to play again? (y/n): ");
//            String playChoice = scanner.nextLine().toLowerCase();
//            if ("y".equals(playChoice)) {
//                return true;
//            } else if ("n".equals(playChoice)) {
//                return false;
//            } else {
//                System.out.println("\n❌ Invalid input. Please enter 'y' for yes or 'n' for no.");
//            }
//        }
//    }
//
//    private void displayWelcomeMessage() {
//        System.out.println("♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦");
//        System.out.println("                                                         ");
//        System.out.println("   ██╗    ██╗███████╗██╗      ██████╗ ██████╗ ███╗   ███╗███████╗");
//        System.out.println("   ██║    ██║██╔════╝██║     ██╔════╝██╔═══██╗████╗ ████║██╔════╝");
//        System.out.println("   ██║ █╗ ██║█████╗  ██║     ██║     ██║   ██║██╔████╔██║█████╗  ");
//        System.out.println("   ██║███╗██║██╔══╝  ██║     ██║     ██║   ██║██║╚██╔╝██║██╔══╝  ");
//        System.out.println("   ╚███╔███╔╝███████╗███████╗╚██████╗╚██████╔╝██║ ╚═╝ ██║███████╗");
//        System.out.println("    ╚══╝╚══╝ ╚══════╝╚══════╝ ╚═════╝ ╚═════╝ ╚═╝     ╚═╝╚══════╝");
//        System.out.println("                                                         ");
//        System.out.println("   ████████╗ ██████╗     ██████╗ ██╗      █████╗  ██████╗██╗  ██╗     ██╗ █████╗  ██████╗██╗  ██╗");
//        System.out.println("   ╚══██╔══╝██╔═══██╗    ██╔══██╗██║     ██╔══██╗██╔════╝██║ ██╔╝     ██║██╔══██╗██╔════╝██║ ██╔╝");
//        System.out.println("      ██║   ██║   ██║    ██████╔╝██║     ███████║██║     █████╔╝      ██║███████║██║     █████╔╝ ");
//        System.out.println("      ██║   ██║   ██║    ██╔══██╗██║     ██╔══██║██║     ██╔═██╗ ██   ██║██╔══██║██║     ██╔═██╗ ");
//        System.out.println("      ██║   ╚██████╔╝    ██████╔╝███████╗██║  ██║╚██████╗██║  ██╗╚█████╔╝██║  ██║╚██████╗██║  ██╗");
//        System.out.println("      ╚═╝    ╚═════╝     ╚═════╝ ╚══════╝╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝ ╚════╝ ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝");
//        System.out.println("                                                         ");
//        System.out.println("♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦♠♥♣♦");
//    }
//
//    private void displayInitialHands() {
//        player.displayHand();
//        dealer.displayHand(true);
//    }
//
//    private void endGame() {
//        System.out.println("\nThank you for playing Blackjack!");
//        System.out.println("Your final bankroll: $" + player.getMoney());
//        System.out.println("National Problem Gambling Helpline number: 1-800-GAMBLER\n\n\n\n");
//        scanner.close();
//    }
//
//    private Deck dealCards() {
//        Deck newDeck = new Deck();
//        newDeck.createFullDeck();
//        newDeck.shuffleDeck();
//        return newDeck;
//    }
//
//    // Implement GameInterface methods
//    @Override
//    public void add(PlayerInterface player) {
//        // Implementation
//    }
//
//    @Override
//    public void remove(PlayerInterface player) {
//        // Implementation
//    }
//
//    @Override
//    public void run() {
//        playBlackJack();
//    }
//
//    @Override
//    public void addCasinoAccount(CasinoAccount casinoAccount) {
//
//    }
//
//    // Getters for testing
//    public BJPlayer getPlayer() {
//        return player;
//    }
//
//    public Dealer getDealer() {
//        return dealer;
//    }
//
////    public static void main(String[] args) {
////        BlackJack game = new BlackJack(1000);
////        game.playBlackJack();
////    }
//
//    // For testing purposes
//    public void playerTurn(Scanner scanner) {
//        // Implementation if needed
//    }
//
//    public void playerTurn(List<String> mockedInputs) {
//        Scanner mockScanner = new Scanner(String.join("\n", mockedInputs));
//        playerTurn(mockScanner);
//    }
//}
//
//
