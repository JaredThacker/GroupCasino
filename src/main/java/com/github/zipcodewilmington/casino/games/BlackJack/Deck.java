package com.github.zipcodewilmington.casino.games.BlackJack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    public void createFullDeck() {
        for (Suit suit : Suit.values()) {
            for (Value value : Value.values()) {
                cards.add(new Card(value, suit));
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("No cards left in the deck");
        }
        return cards.remove(cards.size() - 1);
    }

    public void addCard(Card card) {
        cards.add(0, card);  // Add to the top of the deck
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public int remainingCards() {
        return cards.size();
    }

    public void reset() {
        cards.clear();
        createFullDeck();
        shuffleDeck();
    }
}