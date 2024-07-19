package com.github.zipcodewilmington.hangmantest;

import com.github.zipcodewilmington.casino.games.hangman.HangmanGame;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HangManGameTest {

    private HangmanGame hangmanGame;

    @Before
    public void setup() {
        hangmanGame = new HangmanGame();
    }

    @Test
    public void testGuessCharacterCorrect() {
        char correctChar = getValidCharacter(); // Fetch a valid character from solution
        boolean isGuessCorrect = hangmanGame.guessCharacter(correctChar);
        Assert.assertTrue(isGuessCorrect);
    }

    @Test
    public void testGuessCharacterIncorrect() {
        char incorrectChar = getInvalidCharacter(); // Fetch an invalid character not in solution
        boolean isGuessIncorrect = hangmanGame.guessCharacter(incorrectChar);
        Assert.assertFalse(isGuessIncorrect);
    }

    @Test
    public void testGuessWordCorrect() {
        String correctWord = hangmanGame.getSolution();
        boolean isGuessCorrect = hangmanGame.guessWord(correctWord);
        Assert.assertTrue(isGuessCorrect);
    }

    @Test
    public void testGuessWordIncorrect() {
        String incorrectWord = "incorrect"; // Assuming "incorrect" is not the solution
        boolean isGuessCorrect = hangmanGame.guessWord(incorrectWord);
        Assert.assertFalse(isGuessCorrect);
    }

    @Test
    public void testIsGameOverCorrectWordGuessed() {
        for (char c : hangmanGame.getSolution().toCharArray()) {
            hangmanGame.guessCharacter(c);
        }
        Assert.assertTrue(hangmanGame.isGameOver());
    }

    @Test
    public void testIsWordGuessedCorrect() {
        for (char c : hangmanGame.getSolution().toCharArray()) {
            hangmanGame.guessCharacter(c);
        }
        Assert.assertTrue(hangmanGame.isWordGuessed());
    }

    @Test
    public void testIsWordGuessedIncorrect() {
        hangmanGame.guessCharacter('z'); // Making an incorrect guess
        Assert.assertFalse(hangmanGame.isWordGuessed());
    }

    @Test
    public void testGetCurrentState() {
        String currentState = hangmanGame.getCurrentState();
        Assert.assertNotNull(currentState);
        Assert.assertEquals(hangmanGame.getSolution().length() * 2 - 1, currentState.length());
    }

    private char getValidCharacter() {
        String solution = hangmanGame.getSolution();
        return solution.charAt(0); // Assuming first character is always a valid guess
    }

    private char getInvalidCharacter() {
        // Find a character that is not in the solution
        String solution = hangmanGame.getSolution();
        char invalidChar = 'z';
        while (solution.indexOf(invalidChar) >= 0) {
            invalidChar++;
        }
        return invalidChar;
    }
}
