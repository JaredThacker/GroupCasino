package com.github.zipcodewilmington.casino.games.numberguess;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
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
    Integer guessCount;
    CasinoAccountManager cam;
    CasinoAccount casinoAccount;
    String username;
    String password;

    public Integer generateNew(){
        return numGen.nextInt(20) + 1;
    }

    public void gameLogic(){
        guessCount = 1;
        randomNum = generateNew();

        System.out.println("\n" +
                " __   __   __  __   __    __   ______   ______   ______       ______   __  __   ______   ______   ______    \n" +
                "/\\ \"-.\\ \\ /\\ \\/\\ \\ /\\ \"-./  \\ /\\  == \\ /\\  ___\\ /\\  == \\     /\\  ___\\ /\\ \\/\\ \\ /\\  ___\\ /\\  ___\\ /\\  ___\\   \n" +
                "\\ \\ \\-.  \\\\ \\ \\_\\ \\\\ \\ \\-./\\ \\\\ \\  __< \\ \\  __\\ \\ \\  __<     \\ \\ \\__ \\\\ \\ \\_\\ \\\\ \\  __\\ \\ \\___  \\\\ \\___  \\  \n" +
                " \\ \\_\\\\\"\\_\\\\ \\_____\\\\ \\_\\ \\ \\_\\\\ \\_____\\\\ \\_____\\\\ \\_\\ \\_\\    \\ \\_____\\\\ \\_____\\\\ \\_____\\\\/\\_____\\\\/\\_____\\ \n" +
                "  \\/_/ \\/_/ \\/_____/ \\/_/  \\/_/ \\/_____/ \\/_____/ \\/_/ /_/     \\/_____/ \\/_____/ \\/_____/ \\/_____/ \\/_____/ \n" +
                "                                                                                                            \n");

        while(true){

            System.out.println("\n------- Pick a number between 1 and 20 -------");

            while(!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please enter a number.");
            }

            userGuess = scanner.nextInt();

            if(userGuess == randomNum){
                System.out.println("You win");
                break;
            }
            if(guessCount == 5){
                System.out.println("Sorry you lost.......\n");
                break;
            }
            if(userGuess < randomNum && userGuess > 0){
                System.out.println("Your guess is too low");
                guessCount++;
            }else if(userGuess > randomNum && userGuess < 20){
                System.out.println("Your guess is too high");
                guessCount++;
            } else if (userGuess > 20 || userGuess < 1) {
                System.out.println("Number from 1 to 20, please.");
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
            playAgain = scan.nextLine().trim().toLowerCase();
        } while (playAgain.equals("y"));
        System.out.println("\n" +
                " ______   ______   __    __   ______       ______   __   __ ______   ______    \n" +
                "/\\  ___\\ /\\  __ \\ /\\ \"-./  \\ /\\  ___\\     /\\  __ \\ /\\ \\ / //\\  ___\\ /\\  == \\   \n" +
                "\\ \\ \\__ \\\\ \\  __ \\\\ \\ \\-./\\ \\\\ \\  __\\     \\ \\ \\/\\ \\\\ \\ \\'/ \\ \\  __\\ \\ \\  __<   \n" +
                " \\ \\_____\\\\ \\_\\ \\_\\\\ \\_\\ \\ \\_\\\\ \\_____\\    \\ \\_____\\\\ \\__|  \\ \\_____\\\\ \\_\\ \\_\\ \n" +
                "  \\/_____/ \\/_/\\/_/ \\/_/  \\/_/ \\/_____/     \\/_____/ \\/_/    \\/_____/ \\/_/ /_/ \n" +
                "                                                                               \n");
    }

    @Override
    public void addCasinoAccount(CasinoAccount casinoAccount) {

    }

    @Override
    public void addUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public void addCAM(CasinoAccountManager casinoAccountManager) {
        this.cam = casinoAccountManager;
        casinoAccount = casinoAccountManager.getAccount(username, password);
    }

    @Override
    public void play(Scanner scanner) {
        GameInterface.super.play(scanner);
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