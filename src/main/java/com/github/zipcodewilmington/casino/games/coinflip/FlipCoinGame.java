package com.github.zipcodewilmington.casino.games.coinflip;

import java.util.Random;

public class FlipCoinGame {
    int heads = 1;
    int tails = 1;
    static Random random = new Random();
    public static int flipCoin(int heads, int tails) {

        int randNumber = random.nextInt(2)+1;
        if(randNumber >1){
            System.out.println("The coin landed on Heads!");
        return heads;

        }
        System.out.println("The coin landed on Tails!");
      return tails;

    }




    public static void main(String[] args) {


    }

    public boolean playerWinsOrLose() {


        return false;
    }
}
