package com.github.zipcodewilmington.casino.games.diceroll;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Scanner;

public class DiceRollGame implements GameInterface {

    private static int numberOfDie;

    public DiceRollGame(int numberOfDie){
        this.numberOfDie = 2;
    }

    public static int rollDie() {
        int rollSum = 0;

        for (int i = 0; i < numberOfDie; i++) {
            rollSum += (int) (Math.random() * 6) + 1;
        }
        System.out.println(rollSum);
        return rollSum;
    }

    public static int playDiceGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Howdy! Thanks for playing Dice Game. If you have a gambling addiction please call 1-800-GAMBLER");
        System.out.println("This game is you versus the house. You roll first, the house rolls second. Whoever rolls the highest number wins. Good Luck!");
        System.out.println("How Much would you like to bet?");
        int userInput = scanner.nextInt();
        System.out.println("Time to roll some dice dude/dudette...");
        Integer player1 = rollDie();
        System.out.println("You rolled.." + player1);
        Integer casino = rollDie();
        System.out.println("The casino rolled.." + casino);


        return rollDie();

    }

    public static void main(String[] args){
//        DiceRollGame die = new DiceRollGame(2);
//     Integer  toss = die.rollDie();
//        System.out.println(toss);
        rollDie();
    }

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {

    }
}
