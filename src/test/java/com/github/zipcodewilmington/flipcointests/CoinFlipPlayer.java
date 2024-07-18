package com.github.zipcodewilmington.flipcointests;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Scanner;

public class CoinFlipPlayer implements PlayerInterface {

//    Scanner scanner = new Scanner(System.in);

    public int coinFlipPlayerGuess(int guess,int heads, int tails) {
        if(guess == 1){
            return tails;

        }
        return heads;


    }

    @Override
    public CasinoAccount getArcadeAccount() {
        return null;
    }

    @Override
    public <SomeReturnType> SomeReturnType play() {
        return null;
    }
//        System.out.println("Howdy! Thanks for playing Coin Flip. If you have a gambling addiction please call 1-800-GAMBLER");
//        System.out.println("How Much would you like to bet?");
//        System.out.println("Type 2 for Heads or 1 for Tails. Choose wisely...");

//        if(guess == 1){
//            System.out.println("Your guess is Tails never fails");
//            return 1;
//        }
//        System.out.println("Your guess is Heads wins me bread");
//        return 2;
//        boolean validGuess;
//        int userInput = scanner.nextInt();
//        do{ guess = userInput;
//            validGuess = guess > 0 && guess < 3;
//            if(!validGuess){
//                System.out.println("Not a valid guess. Please choose 2 for Heads or 1 for Tails");
//            }
//
//
//        }
//while (!validGuess);
//    return guess;
//    }
}
