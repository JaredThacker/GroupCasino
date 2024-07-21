package com.github.zipcodewilmington.casino.games.BlackJack;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return new ArrayList<>(cards);
    }

    public void clear() {
        cards.clear();
    }

    public int getValue() {
        int value = 0;
        int aceCount = 0;
        for (Card card : cards) {
            if (card.getValue() == Value.ACE) {
                aceCount++;
            }
            value += card.getNumericValue();
        }
        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }
        return value;
    }

    public boolean isBlackjack() {
        return cards.size() == 2 && getValue() == 21;
    }
}