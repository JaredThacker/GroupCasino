package com.github.zipcodewilmington.hangmantest;

import com.github.zipcodewilmington.casino.games.hangman.HangmanGame;
import com.github.zipcodewilmington.casino.games.hangman.HangmanPlayer;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class HangManGameTest {

    private HangmanGame hangmanGame;
    private HangmanPlayer hangmanPlayer;
    private InputStream originalSystemIn;

    @BeforeEach
    public void setup() {
        hangmanGame = new HangmanGame();
        hangmanPlayer = new HangmanPlayer(hangmanGame);
        originalSystemIn = System.in; // Save original System.in
    }

    @Test
    public void testPlayerGuessCharacter() {
        provideSimulatedInput("1\na\n");
        hangmanGame.startNewGame("hangman");
        hangmanPlayer.playGame();
        assertEquals("Correct guess: a", hangmanGame.getCurrentState().toString().trim(),
                "Player should correctly guess character 'a'");
    }

    @Test
    public void testPlayerGuessWord() {
        provideSimulatedInput("2\ntestword\n");
        hangmanGame.startNewGame("testword");
        hangmanPlayer.playGame();
        assertEquals("You guessed the word: testword", hangmanGame.getCurrentState().toString().trim(),
                "Player should correctly guess word 'testword'");
    }

    private void provideSimulatedInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}