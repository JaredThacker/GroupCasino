package com.github.zipcodewilmington.casino.games.hangman;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Scanner;

public class HangmanPlayer implements PlayerInterface {

    private HangmanGame hangmanGame;
    private Scanner scanner;
    private CasinoAccount arcadeAccount;

    public HangmanPlayer(HangmanGame hangmanGame) {
        this.hangmanGame = hangmanGame;
        this.scanner = new Scanner(System.in);
        this.arcadeAccount = new CasinoAccount();
    }

    public HangmanPlayer() {
        this.hangmanGame = hangmanGame;
        this.scanner = new Scanner(System.in);
        this.arcadeAccount = new CasinoAccount();
    }

    @Override
    public CasinoAccount getArcadeAccount() {

        return arcadeAccount;
    }

    @Override
    public void playGame() {
        while (!hangmanGame.isGameOver()) {
            System.out.println("Current state: " + hangmanGame.getCurrentState());
            System.out.println("You have " + hangmanGame.getTriesLeft() + " tries left.");

            System.out.println("Enter '1' to guess a character, '2' to guess the whole word:");
            int userChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (userChoice == 1) {
                System.out.println("Enter a character to guess: ");
                char guess = scanner.nextLine().charAt(0);
                if (hangmanGame.guessCharacter(guess)) {
                    System.out.println("Correct guess: " + guess);
                } else {
                    System.out.println("Incorrect guess: " + guess);
                }
            } else if (userChoice == 2) {
                System.out.println("Enter a word to guess: ");
                String guess = scanner.nextLine();
                if (hangmanGame.guessWord(guess)) {
                    System.out.println("Congratulations, You guessed the word: " + hangmanGame.getSolution());
                } else {
                    System.out.println("Incorrect guess: " + guess);
                }
            } else {
                System.out.println("Invalid choice. Please enter '1' or '2'.");
            }
        }

        if (hangmanGame.isWordGuessed()) {
            System.out.println("Congratulations! You guessed the word: " + hangmanGame.getSolution());
        } else {
            System.out.println("You lost! The word was: " + hangmanGame.getSolution());
        }
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        playGame();
        return null;
    }
}
