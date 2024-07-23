package com.github.zipcodewilmington.casino.games.BlackJack;

public class Card {
    private final Value value;
    private final Suit suit;

    public Card(Value value, Suit suit) {
        this.value = value;
        this.suit = suit;
    }

    public Value getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getNumericValue() {
        return value.getNumericValue();
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return value == card.value && suit == card.suit;
    }

    @Override
    public int hashCode() {
        return 31 * value.hashCode() + suit.hashCode();
    }

}
