package com.github.zipcodewilmington.casino.games.BlackJack;

import java.util.List;

public class BJPlayer {
    private String name;
    private Hand hand;
    private int money;
    private int currentBet;

    public BJPlayer(String name, int initialMoney) {
        this.name = name;
        this.money = initialMoney;
        this.hand = new Hand();
        this.currentBet = 0;
    }

    public BJPlayer() {

    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public void clearHand() {
        hand.clear();
    }

    public int getHandValue() {
        return hand.getValue();
    }

    public boolean hasBlackjack() {
        return hand.isBlackjack();
    }

    public void displayHand() {
        System.out.println("\n🐲︎ " + name + "'s hand:");
        for (Card card : hand.getCards()) {
            System.out.println(card);
        }
        System.out.println("Your Total value: " + hand.getValue());
    }

    public int getBet(String input) {
        try {
            int bet = Integer.parseInt(input);
            if (bet == 0) {
                return 0;
            }
            if (bet > 0 && bet <= money) {
                return bet;
            } else {
                System.out.println("❌ Invalid bet. Please enter a value between 1 and " + money);
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid input. Please enter a number.");
        }
        return -1;
    }

    public void addToPot(int amount) {
        if (amount <= money) {
            currentBet += amount;
            money -= amount;
        } else {
            System.out.println("\nNot enough money to add to pot.");
        }
    }

//    public void winBet(double multiplier) {
//        int winnings = (int) (currentBet * multiplier);
//        money += winnings;
//        System.out.println("You won $" + winnings + "!");
//        currentBet = 0;
//    }

    public void winBet(double multiplier) {
        int winnings = (int) (currentBet * multiplier);
        money += winnings;
        System.out.println("\nYou won $" + winnings + "!");
        currentBet = 0;
    }

    public void pushBet() {
        money += currentBet;
        System.out.println("\nIt's a push. Your bet of $" + currentBet + " is returned.");
        currentBet = 0;
    }

    public void loseBet() {
        System.out.println("\nYou lost $" + currentBet + ".");
        currentBet = 0;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void subtractMoney(int amount) {
        money -= amount;
    }

    public int getMoney() {
        return money;
    }

    public String getName() {
        return name;
    }
}