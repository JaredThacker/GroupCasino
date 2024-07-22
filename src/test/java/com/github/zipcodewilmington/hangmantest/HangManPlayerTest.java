package com.github.zipcodewilmington.hangmantest;

import com.github.zipcodewilmington.casino.games.hangman.HangmanGame;
import com.github.zipcodewilmington.casino.games.hangman.HangmanPlayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class HangManPlayerTest {

    private HangmanGame hangmanGame;
    private HangmanPlayer hangmanPlayer;
    private InputStream originalSystemIn;

    @Before
    public void setup() {
        hangmanGame = new HangmanGame();
        hangmanPlayer = new HangmanPlayer(hangmanGame);
        originalSystemIn = System.in;
    }

    @After
    public void restoreSystemIn() {
        System.setIn(originalSystemIn);
    }

    @Test
    public void testGuessCharacter() {
        provideSimulatedInput("a\n");
        hangmanGame.startNewGame("hangman");
        hangmanPlayer.playGame();
        assertTrue(hangmanGame.getCurrentState().contains("a"));
    }

    @Test
    public void testGuessWord() {
        provideSimulatedInput("driftwood\n");
        hangmanGame.startNewGame("driftwood");
        hangmanPlayer.playGame();
        assertEquals("driftwood", hangmanGame.getCurrentState().replace(" ", ""));
    }

    private void provideSimulatedInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }
}