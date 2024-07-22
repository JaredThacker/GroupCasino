package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Random;
import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame implements GameInterface {

    private static final String[] Symbols = {"Cherry", "Moneybag", "GoldBar", "7"};
    private static final int[] payouts = {2, 5, 10, 20};
    String[] reel;

    Random random = new Random(System.currentTimeMillis());
    Scanner scanner = new Scanner(System.in);
    int balance = 1000;
    int betAmount;

    public void play(){
        System.out.println("How much would you like to bet?: ");

        while(!scanner.hasNextInt()) {
            scanner.next();
            System.out.println("Please enter a number.");
        }

        betAmount = scanner.nextInt();
        balance -= betAmount;

        reel = spin();

        calculateWinnings();
    }

    public void calculateWinnings(){
        int payoutMultiplier = calculateMultiplier(reel);
        if (payoutMultiplier > 0) {
            int winnings = payoutMultiplier * betAmount;
            balance += winnings;
            System.out.println("\nYou won $" + winnings);
        } else {
            System.out.println("\nSorry you didn't win anything this time");
        }
    }

    private int calculateMultiplier(String[] reel) {
        if (reel[0].equals(reel[1]) && reel[1].equals(reel[2])) {
            for (int i = 0; i < Symbols.length; i++) {
                if (reel[0].equals(Symbols[i])) {
                    return payouts[i];
                }
            }
        }
        return 0;
    }

    private String[] spin(){
        System.out.println("---------Press Enter to spin the slot machine---------");
        scanner.nextLine();
        scanner.nextLine();

        String[] reel = new String[3];
        for (int i = 0; i < 3; i++){
            reel[i] = Symbols[random.nextInt(Symbols.length)];
        }

        System.out.println("---------Spinning---------");
        System.out.println("[" + reel[0] + "] [" + reel[1] + "] [" + reel[2] + "]");

        return reel;
    }

    public static void main(String[] args) {
        SlotsGame slot = new SlotsGame();
        slot.run();
    }

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {
        SlotsGame slot = new SlotsGame();

        System.out.println("\n" +
                "  ______   _____       ___    _________  ____    ____       _        ______  ____  ____  _____  ____  _____  ________  \n" +
                ".' ____ \\ |_   _|    .'   `. |  _   _  ||_   \\  /   _|     / \\     .' ___  ||_   ||   _||_   _||_   \\|_   _||_   __  | \n" +
                "| (___ \\_|  | |     /  .-.  \\|_/ | | \\_|  |   \\/   |      / _ \\   / .'   \\_|  | |__| |    | |    |   \\ | |    | |_ \\_| \n" +
                " _.____`.   | |   _ | |   | |    | |      | |\\  /| |     / ___ \\  | |         |  __  |    | |    | |\\ \\| |    |  _| _  \n" +
                "| \\____) | _| |__/ |\\  `-'  /   _| |_    _| |_\\/_| |_  _/ /   \\ \\_\\ `.___.'\\ _| |  | |_  _| |_  _| |_\\   |_  _| |__/ | \n" +
                " \\______.'|________| `.___.'   |_____|  |_____||_____||____| |____|`.____ .'|____||____||_____||_____|\\____||________| \n" +
                "                                                                                                                       \n");

        while (true) {
            slot.play();

            System.out.println("\nDo you want to spin again? (y/n)");
            String playAgain = slot.scanner.nextLine().trim().toLowerCase();

            if (playAgain.equals("n")) {
                System.out.println("\n" +
                        " _______ .-. .-.  .--.  .-. .-.,-. .-. .-.   .-. .---.  .-. .-.  ,---. .---.  ,---.     ,---.  ,-.      .--..-.   .-.,-..-. .-.  ,--,   \n" +
                        "|__   __|| | | | / /\\ \\ |  \\| || |/ /   \\ \\_/ )// .-. ) | | | |  | .-'/ .-. ) | .-.\\    | .-.\\ | |     / /\\ \\\\ \\_/ )/|(||  \\| |.' .'    \n" +
                        "  )| |   | `-' |/ /__\\ \\|   | || | /     \\   (_)| | |(_)| | | |  | `-.| | |(_)| `-'/    | |-' )| |    / /__\\ \\\\   (_)(_)|   | ||  |  __ \n" +
                        " (_) |   | .-. ||  __  || |\\  || | \\      ) (   | | | | | | | |  | .-'| | | | |   (     | |--' | |    |  __  | ) (   | || |\\  |\\  \\ ( _)\n" +
                        "   | |   | | |)|| |  |)|| | |)|| |) \\     | |   \\ `-' / | `-')|  | |  \\ `-' / | |\\ \\    | |    | `--. | |  |)| | |   | || | |)| \\  `-) )\n" +
                        "   `-'   /(  (_)|_|  (_)/(  (_)|((_)-'   /(_|    )---'  `---(_)  )\\|   )---'  |_| \\)\\   /(     |( __.'|_|  (_)/(_|   `-'/(  (_) )\\____/ \n" +
                        "        (__)           (__)    (_)      (__)    (_)             (__)  (_)         (__) (__)    (_)           (__)      (__)    (__)     \n");
                break;
            }
        }
    }

    @Override
    public void addCasinoAccount(CasinoAccount casinoAccount) {

    }
}
