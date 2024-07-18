package com.github.zipcodewilmington.casino.games.numberguess;

import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessPlayer {
    Scanner scanner = new Scanner(System.in);

    public Integer guessNum(){
        Integer userGuess = scanner.nextInt();
        return userGuess;
    }
}