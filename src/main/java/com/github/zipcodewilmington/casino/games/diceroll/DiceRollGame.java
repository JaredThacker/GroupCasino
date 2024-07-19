package com.github.zipcodewilmington.casino.games.diceroll;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Random;
import java.util.Scanner;

public class DiceRollGame implements GameInterface {

    private static int numberOfDie;
    static Random random = new Random();


//    public DiceRollGame(int numberOfDie){
//        this.numberOfDie = 2;
//    }

    public static int rollDie(int numberOfDie) {

        int Die = random.nextInt(12)+2;
//        int rollSum = 0;
//
//        for (int i = 0; i < numberOfDie; i++) {
//            rollSum += (int) (Math.random() * 6) + 1;
//        }
//        System.out.println(rollSum);
//        return rollSum;
        System.out.println(Die);
        return Die;
    }

    public static void playDiceGame(){
    Scanner scanner = new Scanner(System.in);
        System.out.println("\n" +
                "▓█████▄  ██▓ ▄████▄  ▓█████ ▓█████ ▓█████      ▄████  ▄▄▄       ███▄ ▄███▓▓█████ ▓█████ ▓█████ \n" +
                "▒██▀ ██▌▓██▒▒██▀ ▀█  ▓█   ▀ ▓█   ▀ ▓█   ▀     ██▒ ▀█▒▒████▄    ▓██▒▀█▀ ██▒▓█   ▀ ▓█   ▀ ▓█   ▀ \n" +
                "░██   █▌▒██▒▒▓█    ▄ ▒███   ▒███   ▒███      ▒██░▄▄▄░▒██  ▀█▄  ▓██    ▓██░▒███   ▒███   ▒███   \n" +
                "░▓█▄   ▌░██░▒▓▓▄ ▄██▒▒▓█  ▄ ▒▓█  ▄ ▒▓█  ▄    ░▓█  ██▓░██▄▄▄▄██ ▒██    ▒██ ▒▓█  ▄ ▒▓█  ▄ ▒▓█  ▄ \n" +
                "░▒████▓ ░██░▒ ▓███▀ ░░▒████▒░▒████▒░▒████▒   ░▒▓███▀▒ ▓█   ▓██▒▒██▒   ░██▒░▒████▒░▒████▒░▒████▒\n" +
                " ▒▒▓  ▒ ░▓  ░ ░▒ ▒  ░░░ ▒░ ░░░ ▒░ ░░░ ▒░ ░    ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ░  ░░░ ▒░ ░░░ ▒░ ░░░ ▒░ ░\n" +
                " ░ ▒  ▒  ▒ ░  ░  ▒    ░ ░  ░ ░ ░  ░ ░ ░  ░     ░   ░   ▒   ▒▒ ░░  ░      ░ ░ ░  ░ ░ ░  ░ ░ ░  ░\n" +
                " ░ ░  ░  ▒ ░░           ░      ░      ░      ░ ░   ░   ░   ▒   ░      ░      ░      ░      ░   \n" +
                "   ░     ░  ░ ░         ░  ░   ░  ░   ░  ░         ░       ░  ░       ░      ░  ░   ░  ░   ░  ░\n" +
                " ░          ░                                                                                  \n");
        System.out.println("Howdy! Thanks for playing Dice Game. If you have a gambling addiction please call 1-800-GAMBLER");
        System.out.println("This game is you versus the house. You roll first, the house rolls second. Whoever rolls the highest number wins. Good Luck!");
        System.out.println("How Much would you like to bet?");
        int userInput = scanner.nextInt();
        System.out.println("Time to roll some dice dude/dudette...");
        System.out.println("You rolled.. " );
        int player1 = rollDie(2);
        System.out.println("The casino rolled..");
        int casino = rollDie(2);

        if (player1>casino){
            System.out.println("Winner! Winner! Chicken Dinner!! You won " + "$" + userInput*2);
        }
        else System.out.println("Sorry, you'll have to take the L this round");











    }

    public static void main(String[] args){
//        DiceRollGame die = new DiceRollGame(2);
//     Integer  toss = die.rollDie();
//        System.out.println(toss);
        playDiceGame();

    }

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {
        Scanner scanner2 = new Scanner(System.in);
        String playAgain = "y";
        do {

            playDiceGame();
            System.out.println("Would you like to play again? y/n");
            playAgain = scanner2.nextLine().toLowerCase();
        } while (playAgain.equals("y"));
        System.out.println("\n" +
                "▓█████▄  ██▓ ▄████▄  ▓█████ ▓█████ ▓█████      ▄████  ▄▄▄       ███▄ ▄███▓▓█████ ▓█████ ▓█████ \n" +
                "▒██▀ ██▌▓██▒▒██▀ ▀█  ▓█   ▀ ▓█   ▀ ▓█   ▀     ██▒ ▀█▒▒████▄    ▓██▒▀█▀ ██▒▓█   ▀ ▓█   ▀ ▓█   ▀ \n" +
                "░██   █▌▒██▒▒▓█    ▄ ▒███   ▒███   ▒███      ▒██░▄▄▄░▒██  ▀█▄  ▓██    ▓██░▒███   ▒███   ▒███   \n" +
                "░▓█▄   ▌░██░▒▓▓▄ ▄██▒▒▓█  ▄ ▒▓█  ▄ ▒▓█  ▄    ░▓█  ██▓░██▄▄▄▄██ ▒██    ▒██ ▒▓█  ▄ ▒▓█  ▄ ▒▓█  ▄ \n" +
                "░▒████▓ ░██░▒ ▓███▀ ░░▒████▒░▒████▒░▒████▒   ░▒▓███▀▒ ▓█   ▓██▒▒██▒   ░██▒░▒████▒░▒████▒░▒████▒\n" +
                " ▒▒▓  ▒ ░▓  ░ ░▒ ▒  ░░░ ▒░ ░░░ ▒░ ░░░ ▒░ ░    ░▒   ▒  ▒▒   ▓▒█░░ ▒░   ░  ░░░ ▒░ ░░░ ▒░ ░░░ ▒░ ░\n" +
                " ░ ▒  ▒  ▒ ░  ░  ▒    ░ ░  ░ ░ ░  ░ ░ ░  ░     ░   ░   ▒   ▒▒ ░░  ░      ░ ░ ░  ░ ░ ░  ░ ░ ░  ░\n" +
                " ░ ░  ░  ▒ ░░           ░      ░      ░      ░ ░   ░   ░   ▒   ░      ░      ░      ░      ░   \n" +
                "   ░     ░  ░ ░         ░  ░   ░  ░   ░  ░         ░       ░  ░       ░      ░  ░   ░  ░   ░  ░\n" +
                " ░          ░                                                                                  \n");

    }
}
