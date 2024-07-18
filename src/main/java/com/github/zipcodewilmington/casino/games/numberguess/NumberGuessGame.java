package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class NumberGuessGame implements GameInterface {

    Scanner scanner = new Scanner(System.in);
    Random numGen = new Random(System.currentTimeMillis());
    Integer userGuess;
    Integer randomNum;

    public Integer generateNew(){
        return numGen.nextInt(20) + 1;
    }

    public Boolean guessCheck(){
        return userGuess == randomNum;
    }


    public void gameLogic(){

        Integer guessCount = 0;

        randomNum = generateNew();

        System.out.println("\n" +
                " __   __   __  __   __    __   ______   ______   ______       ______   __  __   ______   ______   ______    \n" +
                "/\\ \"-.\\ \\ /\\ \\/\\ \\ /\\ \"-./  \\ /\\  == \\ /\\  ___\\ /\\  == \\     /\\  ___\\ /\\ \\/\\ \\ /\\  ___\\ /\\  ___\\ /\\  ___\\   \n" +
                "\\ \\ \\-.  \\\\ \\ \\_\\ \\\\ \\ \\-./\\ \\\\ \\  __< \\ \\  __\\ \\ \\  __<     \\ \\ \\__ \\\\ \\ \\_\\ \\\\ \\  __\\ \\ \\___  \\\\ \\___  \\  \n" +
                " \\ \\_\\\\\"\\_\\\\ \\_____\\\\ \\_\\ \\ \\_\\\\ \\_____\\\\ \\_____\\\\ \\_\\ \\_\\    \\ \\_____\\\\ \\_____\\\\ \\_____\\\\/\\_____\\\\/\\_____\\ \n" +
                "  \\/_/ \\/_/ \\/_____/ \\/_/  \\/_/ \\/_____/ \\/_____/ \\/_/ /_/     \\/_____/ \\/_____/ \\/_____/ \\/_____/ \\/_____/ \n" +
                "                                                                                                            \n");

        while(true){

            guessCount++;

            System.out.println("Pick a number between 1 and 20");

            userGuess = scanner.nextInt();

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

    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        String playAgain = "y";
        do {
            gameLogic();
            System.out.println("Would you like to play again? y/n");
            playAgain = scan.nextLine().toLowerCase();
        } while (playAgain.equals("y"));
    }

    public static void main(String[] args) {
        NumberGuessGame numberGuessGame = new NumberGuessGame();

        numberGuessGame.run();
    }

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }


}