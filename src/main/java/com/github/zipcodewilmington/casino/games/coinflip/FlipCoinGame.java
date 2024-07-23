package com.github.zipcodewilmington.casino.games.coinflip;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Random;
import java.util.Scanner;

public class FlipCoinGame implements GameInterface {
    int heads = 2;
    int tails = 1;
    PlayerInterface player;
    static Random random = new Random();
    String username;
    String password;
    CasinoAccount casinoAccount;
    CasinoAccountManager cam;
    static int balance;
    static int betAmount;
    String playAgain = "y";
    int guess;

    //        System.out.println("Howdy! Thanks for playing Coin Flip. If you have a gambling addiction please call 1-800-GAMBLER");
//        System.out.println("How Much would you like to bet?");
//        System.out.println("Type 2 for Heads or 1 for Tails. Choose wisely...");

    public static int flipCoin(int heads, int tails) {

        int flipCoin = random.nextInt(2)+1;

        if(flipCoin == 2){
            return heads;
        }
        else return tails;
//        Scanner scanner = new Scanner(System.in);
//
//        System.out.println("\n" +
//                " _____  _  _          ____          _         \n" +
//                "|___  || |(_)  __ _  |___ \\   ___  (_)  __ _  \n" +
//                "   _| || || | / _` |     | | / _ \\ | | / _` | \n" +
//                "  |_  || || || (_| |  ___| || (_) || || | | | \n" +
//                "    |_||_||_| \\__, | |____/  \\___/ |_||_| |_| \n" +
//                "                 |_|                          \n");
//
//        System.out.println("Howdy! Thanks for playing Coin Flip. If you have a gambling addiction please call 1-800-GAMBLER");
//        System.out.println("How Much would you like to bet?");
//        int userInput = scanner.nextInt();
//        System.out.println("Type 2 for Heads or 1 for Tails. Choose wisely...");
//
//        int userInput2 = scanner.nextInt();
//        int randNumber = random.nextInt(2)+1;
//        if(userInput2 == 1){
//            System.out.println("You guessed Tails, I hope you ain't fail.");
////            return userInput;
//        }
//        else if(userInput2 ==2) {
//            System.out.println("You guessed Heads for the bread.");
////            return userInput;
//        }
//        else return tails;
//
//
//
//////        if(randNumber >1){
//////            System.out.println("The coin landed on Heads!");
////        return heads;
////
////        }
////        System.out.println("The coin landed on Tails!");
//
//
//        if(randNumber == 1) {
//            System.out.println("The coin landed on Tails!");
//
//            }
//        else{ System.out.println("The coin landed on Heads.");
//        }
//        if (userInput2 == randNumber){
//            System.out.println("Bingo! Your guess was correct! You won " + "$"+ (userInput * 2));
//            return userInput;
//        }
//        System.out.println("Sorry wrong guess :(");
//        return tails;
//
    }


    public int playFlipCoin(int heads, int tails){
        Scanner scanner = new Scanner(System.in);


        System.out.println("\n" +
                " _____  _  _          ____          _         \n" +
                "|___  || |(_)  __ _  |___ \\   ___  (_)  __ _  \n" +
                "   _| || || | / _` |     | | / _ \\ | | / _` | \n" +
                "  |_  || || || (_| |  ___| || (_) || || | | | \n" +
                "    |_||_||_| \\__, | |____/  \\___/ |_||_| |_| \n" +
                "                 |_|                          \n");
        balance = casinoAccount.getBalance();

        if (balance == 0){
            this.playAgain = "n";
            run();
        }

        System.out.println("Howdy! Thanks for playing Coin Flip. If you have a gambling addiction please call 1-800-GAMBLER");
        System.out.println("How much would you like to bet?");
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
        System.out.println("Type 2 for Heads or 1 for Tails. Choose wisely...");
        while (!scanner.hasNextInt()){
            scanner.next();
            System.out.println("Please enter a number");
        }
        int userInput2 = scanner.nextInt();
        int randNumber = random.nextInt(2)+1;
        if(userInput2 == 1){
            System.out.println("You guessed Tails, I hope you ain't fail.");
//            return betAmount;
        }
        else if(userInput2 ==2) {
            System.out.println("You guessed Heads for the bread.");
//            return betAmount;
        }
        else return tails;



////        if(randNumber >1){
////            System.out.println("The coin landed on Heads!");
//        return heads;
//
//        }
//        System.out.println("The coin landed on Tails!");


        if(randNumber == 1) {
            System.out.println("The coin landed on Tails!");

        }
        else{ System.out.println("The coin landed on Heads.");
        }
        if (userInput2 == flipCoin(2,1)){
            System.out.println("Bingo! Your guess was correct! You won " + "$"+ (betAmount * 2));
            balance += betAmount*2;
            return balance;
        }
        System.out.println("Sorry wrong guess :(");
        balance -= betAmount;
        return balance;


    }


    public boolean playerWinsOrLose() {

        CoinFlipPlayer player = new CoinFlipPlayer();
        if (player.coinFlipPlayerGuess(guess,1,2) == flipCoin(2,1)){
            System.out.println("Bingo! Your guess was correct! You won " + "$"+ (betAmount * 2));
            balance += betAmount*2;
            return true;
        }
        System.out.println("Sorry wrong guess :(");
        balance -= betAmount;
        return false;

//        CoinFlipPlayer coinFlipPlayer = new CoinFlipPlayer;
//
//    if(flipCoin(2,1) )
//        return false;
    }







    public static void main(String[] args) {




//        playFlipCoin(2,1);
//        System.out.println("\n" +
//                " _____  _  _          ____          _         \n" +
//                "|___  || |(_)  __ _  |___ \\   ___  (_)  __ _  \n" +
//                "   _| || || | / _` |     | | / _ \\ | | / _` | \n" +
//                "  |_  || || || (_| |  ___| || (_) || || | | | \n" +
//                "    |_||_||_| \\__, | |____/  \\___/ |_||_| |_| \n" +
//                "                 |_|                          \n");
//
//                System.out.println("Howdy! Thanks for playing Coin Flip. If you have a gambling addiction please call 1-800-GAMBLER");
//        System.out.println("How Much would you like to bet?");
//        System.out.println("Type 2 for Heads or 1 for Tails. Choose wisely...");

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
//        flipCoin(1,2);
        balance = casinoAccount.getBalance();
        System.out.println("Initial Balance is $" + casinoAccount.getBalance());
        Scanner scanner = new Scanner(System.in);
        String playAgain = "y";
        do {

            playFlipCoin(2, 1);
            System.out.println("Would you like to play again? y/n");
            playAgain = scanner.nextLine().toLowerCase();
        } while (playAgain.equals("y"));
        System.out.println("new balance is " + "$" + balance);
        casinoAccount.setBalance(balance);
        System.out.println("\n" +
                " _____  _  _          ____          _         \n" +
                "|___  || |(_)  __ _  |___ \\   ___  (_)  __ _  \n" +
                "   _| || || | / _` |     | | / _ \\ | | / _` | \n" +
                "  |_  || || || (_| |  ___| || (_) || || | | | \n" +
                "    |_||_||_| \\__, | |____/  \\___/ |_||_| |_| \n" +
                "                 |_|                          \n");

//        System.out.println( System.out.println("\n" +
//                " _____  _  _          ____          _         \n" +
//                "|___  || |(_)  __ _  |___ \\   ___  (_)  __ _  \n" +
//                "   _| || || | / _` |     | | / _ \\ | | / _` | \n" +
//                "  |_  || || || (_| |  ___| || (_) || || | | | \n" +
//                "    |_||_||_| \\__, | |____/  \\___/ |_||_| |_| \n" +
//                "                 |_|                          \n");
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
