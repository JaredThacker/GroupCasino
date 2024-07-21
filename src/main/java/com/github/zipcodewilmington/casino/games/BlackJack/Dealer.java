package com.github.zipcodewilmington.casino.games.BlackJack;

import java.util.List;

public class Dealer extends BJPlayer {
    private Card faceUpCard;

    public Dealer() {
        super("Dealer", 0);
    }

    @Override
    public void addCard(Card card) {
        super.addCard(card);
        if (getHand().size() == 1) {
            // This is the face-up card
            this.faceUpCard = card;
        }
    }

    public boolean hasFaceUpCardThatCouldMakeBlackjack() {
        if (faceUpCard == null) {
            return false;
        }
        // Assuming Card class has a getRank() method that returns a string
        String rank = String.valueOf(faceUpCard.getNumericValue());
        return rank.equals("10") || rank.equals("J") || rank.equals("Q") || rank.equals("K") || rank.equals("A");
    }

    public List<Card> getHand() {
        return super.getHand();
    }

    public void clearHand() {
        super.clearHand();
        faceUpCard = null;
    }

    public int getHandValue() {
        return super.getHandValue();
    }

    public boolean shouldHit() {
        return getHandValue() < 17;
    }

    public boolean hasBlackjack() {
        return super.hasBlackjack();
    }

    public void displayHand(boolean hideFirstCard) {
        System.out.println("\n🤵 Dealer's hand:");
        List<Card> cards = getHand();
        for (int i = 0; i < cards.size(); i++) {
            if (i == 0 && hideFirstCard) {
                System.out.println("[Hidden]");
            } else {
                System.out.println(cards.get(i));
            }
        }
        if (!hideFirstCard) {
            System.out.println("Dealers Total value: " + getHandValue());
        }
    }
}