package com.github.zipcodewilmington.casino.games.numberguess;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessGame {

    Scanner scanner = new Scanner(System.in);
    Random numGen = new Random(System.currentTimeMillis());
    Integer guessCount;
    Integer userGuess;
    Integer randomNum;

    public Integer generateNew(){
        return numGen.nextInt(20) + 1;
    }

    public Boolean guessCheck(){
        return userGuess == randomNum;
    }

    public void playGame(){
        String playAgain = "y";
        do {
            gameLogic();
            System.out.println("Would you like to play again? y/n");
            playAgain = scanner.nextLine().toLowerCase();
        } while (playAgain.equals("y"));
    }

    public void gameLogic(){
        while(true){
            userGuess = scanner.nextInt();
            randomNum = generateNew();
            guessCount++;
            if(userGuess == randomNum){
                System.out.println("You win");
                break;
            }
            if(guessCount == 5){
                System.out.println("You lose");
                break;
            }
            if(userGuess < randomNum){
                System.out.println("Your guess is too low");

            }else if(userGuess > randomNum){
                System.out.println("Your guess is too high");
            }
        }
    }
}