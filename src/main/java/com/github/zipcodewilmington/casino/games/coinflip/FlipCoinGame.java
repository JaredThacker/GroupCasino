package com.github.zipcodewilmington.casino.games.coinflip;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Random;

public class FlipCoinGame implements GameInterface {
    int heads = 2;
    int tails = 1;
    PlayerInterface player;
    static Random random = new Random();

    //        System.out.println("Howdy! Thanks for playing Coin Flip. If you have a gambling addiction please call 1-800-GAMBLER");
//        System.out.println("How Much would you like to bet?");
//        System.out.println("Type 2 for Heads or 1 for Tails. Choose wisely...");

    public static int flipCoin(int heads, int tails) {

        int randNumber = random.nextInt(2)+1;
        if(randNumber >1){
            System.out.println("The coin landed on Heads!");
        return heads;

        }
        System.out.println("The coin landed on Tails!");
      return tails;

    }


    public boolean playerWinsOrLose() {

//        CoinFlipPlayer coinFlipPlayer = new CoinFlipPlayer;
//
//    if(flipCoin(2,1) )
        return false;
    }







    public static void main(String[] args) {
        System.out.println("\n" +
                " _____  _  _          ____          _         \n" +
                "|___  || |(_)  __ _  |___ \\   ___  (_)  __ _  \n" +
                "   _| || || | / _` |     | | / _ \\ | | / _` | \n" +
                "  |_  || || || (_| |  ___| || (_) || || | | | \n" +
                "    |_||_||_| \\__, | |____/  \\___/ |_||_| |_| \n" +
                "                 |_|                          \n");

                System.out.println("Howdy! Thanks for playing Coin Flip. If you have a gambling addiction please call 1-800-GAMBLER");
        System.out.println("How Much would you like to bet?");
        System.out.println("Type 2 for Heads or 1 for Tails. Choose wisely...");

    }


    @Override
    public void add(PlayerInterface player) {
        this.player = player;

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {
        flipCoin(1,2);

    }
}
