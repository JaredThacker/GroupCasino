package com.github.zipcodewilmington.casino.games.diceroll;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Random;
import java.util.Scanner;

public class DiceRollGame implements GameInterface {

    private static int numberOfDie;
    static Random random = new Random();
    String username;
    String password;
    CasinoAccount casinoAccount;
    CasinoAccountManager cam;
    static int balance;
    static int betAmount;
    String playAgain = "y";

//    public DiceRollGame(int numberOfDie){
//        this.numberOfDie = 2;
//    }

    public static int rollDie(int numberOfDie) {

        int Die = random.nextInt(11)+2;
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

    public void playDiceGame(){

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
        balance = casinoAccount.getBalance();

        if (balance == 0){
            this.playAgain = "n";
            run();
        }
        System.out.println("Howdy! Thanks for playing Dice Game. If you have a gambling addiction please call 1-800-GAMBLER");
        System.out.println("This game is you versus the house. You roll first, the house rolls second. Whoever rolls the highest number wins. Good Luck!");
        System.out.println("How Much would you like to bet?");
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.println("Please enter a number");
        }
         betAmount = scanner.nextInt();

        if (betAmount > balance){
            while (betAmount > balance) {
                System.out.println("Please enter a lower amount you only have $" + balance);
                while (!scanner.hasNextInt()) {
                    scanner.next();
                    System.out.println("Please enter a number.");
                }
                betAmount = scanner.nextInt();
            }
        }


        balance -= betAmount;
        System.out.println("Time to roll some dice dude/dudette...");
        System.out.println("You rolled.. " );
        int player1 = rollDie(2);
        System.out.println("The casino rolled..");
        int casino = rollDie(2);

        if (player1>casino){

            System.out.println("Winner! Winner! Chicken Dinner!! You won " + "$" + betAmount*2);
            balance += betAmount*2;
//            return balance;
        } else if (player1 == casino) {
            System.out.println("It's a tie! Time to roll again. Please type y and restart the game.");


        } else
        {balance -= betAmount;
            System.out.println("Sorry, you'll have to take the L this round");}











    }

    public static void main(String[] args){
//        DiceRollGame die = new DiceRollGame(2);
//     Integer  toss = die.rollDie();
//        System.out.println(toss);
//        playDiceGame();
    }

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {
        balance = casinoAccount.getBalance();
        System.out.println("Initial Balance is $" + casinoAccount.getBalance());
        Scanner scanner2 = new Scanner(System.in);
//        String playAgain = "y";
        do {

            playDiceGame();
            System.out.println("Would you like to play again? y/n");
            playAgain = (balance == 0) ? "n" : scanner2.nextLine().trim().toLowerCase();
            System.out.println("new balance is " + "$" + balance);
        } while (playAgain.equals("y"));

//        System.out.println("new balance is " + "$" + balance);
        casinoAccount.setBalance(balance);
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
        casinoAccount = cam.getAccount(username, password);
    }

    @Override
    public void play(Scanner scanner) {
        GameInterface.super.play(scanner);
    }
}
