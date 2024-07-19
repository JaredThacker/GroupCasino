package com.github.zipcodewilmington.hangmantest;

import com.github.zipcodewilmington.casino.games.hangman.HangmanGame;
import com.github.zipcodewilmington.casino.games.hangman.HangmanPlayer;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HangManPlayerTest {

    private HangmanGame hangmanGame;
    private HangmanPlayer hangmanPlayer;
    private InputStream originalSystemIn;

    @BeforeEach
    public void setup() {
        hangmanGame = new HangmanGame();
        hangmanPlayer = new HangmanPlayer(hangmanGame);
        originalSystemIn = System.in; // Save original System.in
    }

    @AfterEach
    public void restoreSystemIn() {
        System.setIn(originalSystemIn);
    }

    @Test
    public void testGuessCharacter() {
        //Simulate the player input
        provideSimulatedInput("a\n");

        //Start game and make a guess
        hangmanGame.startNewGame("hangman");
        hangmanPlayer.playGame();

        //Check if the guessed letter 'a' is revealed
        assertTrue(String.valueOf(hangmanGame.getCurrentState()).contains("a"));
    }

    @Test
    public void testGuessWord() {
        //Simulate player input
        provideSimulatedInput("driftwood\n");

        //Start game and make a guess
        hangmanGame.startNewGame("driftwood");
        hangmanPlayer.playGame();

        //Check if the guessed word "driftwood" is revealed
        assertEquals("driftwood", String.valueOf(hangmanGame.getCurrentState()));
    }

    private void provideSimulatedInput(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }

//    //Restore original System.in after each test
//    @AfterEach
//    public void restoreSystemIn() {
//        System.getIn(originalSystemIn);
//    }
}
