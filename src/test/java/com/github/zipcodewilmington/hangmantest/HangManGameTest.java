package com.github.zipcodewilmington.hangmantest;

import com.github.zipcodewilmington.casino.games.hangman.HangmanGame;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class HangManGameTest {

    private HangmanGame hangmanGame;

    @BeforeEach
    public void setup() {
        hangmanGame = new HangmanGame();
    }

    @Test
    public void testStartNewGame() {
        hangmanGame.startNewGame("astronaut");
        assertEquals("_ _ _ _ _ _ _ _ _", hangmanGame.getCurrentState(), "Initial state should be word indexes for 'astronaut'");
    }

    @Test
    public void testGuessCharacterCorrect() {
        hangmanGame.startNewGame("railroad");
        assertEquals("_ a _ _ _ _ a _", hangmanGame.getCurrentState(), "Current word should reveal 'a' indexes");

    }

    @Test
    public void testGuessCharacterIncorrect() {
        hangmanGame.startNewGame("railroad");
        assertEquals("_ _ _ _ _ _ _ _", hangmanGame.getCurrentState(), "Current word should be unchanged");
    }

    @Test
    public void testGuessWordCorrect() {
        hangmanGame.startNewGame("railroad");
        assertEquals("r a i l r o a d", hangmanGame.getCurrentState(), "Current word should reveal the entire word");
    }

    @Test
    public void testGuessWordIncorrect() {
        hangmanGame.startNewGame("railroad");
        assertEquals("_ _ _ _ _ _ _ _", hangmanGame.getCurrentState(), "Current word should be unchanged");
    }

    @Test
    public void testMaxAttemptsReached() {
        hangmanGame.startNewGame("railroad");
        hangmanGame.guessCharacter('p');
        hangmanGame.guessCharacter('t');
        hangmanGame.guessCharacter('x');
        hangmanGame.guessCharacter('z');
        hangmanGame.guessCharacter('b');
        hangmanGame.guessCharacter('c');
        hangmanGame.guessCharacter('q');
        hangmanGame.guessCharacter('u');
        assertEquals("_ _ _ _ _ _ _ _", hangmanGame.getCurrentState(), "Current state should remain unchanged after max attempts");
    }
}
