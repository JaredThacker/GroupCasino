package com.github.zipcodewilmington.casino.games.hangman;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.wallet.Wallet;

import java.util.Scanner;

public class HangmanPlayer implements PlayerInterface {
    private HangmanGame hangmanGame;

    public HangmanPlayer(HangmanGame hangmanGame) {
        this.hangmanGame = hangmanGame;
    }

    public HangmanPlayer() {
        this.hangmanGame = new HangmanGame();
    }

    @Override
    public CasinoAccount getArcadeAccount() {
        return null;
    }

//    @Override
//    public void playGame() {
//    }

//    @Override
//    public void playGame() {
//        Scanner scanner = new Scanner(System.in);
//        hangmanGame.play(scanner);
//    }

    @Override
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();

        if (input.length() == 1) {
            hangmanGame.guessCharacter(input.charAt(0));
        } else {
            hangmanGame.guessWord(input);
        }
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        playGame();
        return null;
    }

    @Override
    public void addWallet(Wallet wallet) {

    }
}
