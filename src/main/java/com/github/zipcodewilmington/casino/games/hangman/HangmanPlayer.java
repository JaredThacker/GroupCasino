package com.github.zipcodewilmington.casino.games.hangman;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Scanner;

public class HangmanPlayer implements PlayerInterface {


    public HangmanPlayer(HangmanGame hangmanGame) {
    }

    public HangmanPlayer() {
    }

    @Override
    public CasinoAccount getArcadeAccount() {
        return null;
    }

    @Override
    public void playGame() {
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        playGame();
        return null;
    }
}
