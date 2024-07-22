package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.CasinoAccountManager;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;
import com.github.zipcodewilmington.casino.games.BlackJack.BJPlayer;
import com.github.zipcodewilmington.casino.games.BlackJack.BlackJack;
import com.github.zipcodewilmington.casino.games.coinflip.CoinFlipPlayer;
import com.github.zipcodewilmington.casino.games.coinflip.FlipCoinGame;
import com.github.zipcodewilmington.casino.games.diceroll.DiceRollGame;
import com.github.zipcodewilmington.casino.games.diceroll.DiceRollPlayer;
import com.github.zipcodewilmington.casino.games.hangman.HangmanGame;
import com.github.zipcodewilmington.casino.games.hangman.HangmanPlayer;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessGame;
import com.github.zipcodewilmington.casino.games.numberguess.NumberGuessPlayer;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

public class Casino implements Runnable {
    private final IOConsole console = new IOConsole(AnsiColor.BLUE);
    String accountName;
    String accountPassword;
    CasinoAccountManager casinoAccountManager;

    @Override
    public void run() {
        String arcadeDashBoardInput;
        casinoAccountManager = new CasinoAccountManager();
        do {
            arcadeDashBoardInput = getArcadeDashboardInput();
            if ("select-game".equals(arcadeDashBoardInput)) {
                accountName = console.getStringInput("Enter your account name:");
                accountPassword = console.getStringInput("Enter your account password:");
                CasinoAccount casinoAccount = casinoAccountManager.getAccount(accountName, accountPassword);
                boolean isValidLogin = casinoAccount != null;
                if (isValidLogin) {
                    String gameSelectionInput = getGameSelectionInput().toUpperCase().trim();
                    System.out.println("Debug: Game selection input = '" + gameSelectionInput + "'");
                    switch (gameSelectionInput) {
                        case "SLOTS":
                            playGame(new SlotsGame(), new SlotsPlayer());
                            break;
                        case "NUMBERGUESS":
                            playGame(new NumberGuessGame(), new NumberGuessPlayer());
                            break;
                        case "FLIPCOIN":
                            playGame(new FlipCoinGame(), new CoinFlipPlayer());
                            break;
                        case "HANGMAN":
                            playGame(new HangmanGame(), new HangmanPlayer());
                            break;
                        case "BLACKJACK":
                            playGame(new BlackJack(console.getScanner()), new BJPlayer());
                            break;
                        case "DICEGAME":
                            playGame(new DiceRollGame(), new DiceRollPlayer());
                            break;
                        default:
                            String errorMessage = "[ %s ] is an invalid game selection";
                            throw new RuntimeException(String.format(errorMessage, gameSelectionInput));
                    }
                } else {
                    String errorMessage = "No account found with name of [ %s ] and password of [ %s ]";
                    throw new RuntimeException(String.format(errorMessage, accountName, accountPassword));
                }
            } else if ("create-account".equals(arcadeDashBoardInput)) {
                console.println("Welcome to the account-creation screen.");
                String accountName = console.getStringInput("Enter your account name:");
                String accountPassword = console.getStringInput("Enter your account password:");
                CasinoAccount newAccount = casinoAccountManager.createAccount(accountName, accountPassword);
                casinoAccountManager.registerAccount(newAccount);
                int initDeposit = console.getIntegerInput("How much would you like to deposit to your account?");
                casinoAccountManager.getAccount(accountName,accountPassword).setBalance(initDeposit);
            }
        } while (!"logout".equals(arcadeDashBoardInput));
    }

    private String getArcadeDashboardInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Arcade Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ create-account ], [ select-game ], [ logout ]")
                .toString());
    }

    private String getGameSelectionInput() {
        return console.getStringInput(new StringBuilder()
                .append("Welcome to the Game Selection Dashboard!")
                .append("\nFrom here, you can select any of the following options:")
                .append("\n\t[ SLOTS ], [ NUMBERGUESS ], [ FLIPCOIN ], [ HANGMAN ], [ BLACKJACK ], [ DICEGAME ]")
                .toString());
    }

    private void playGame(Object gameObject, Object playerObject) {
        GameInterface game = (GameInterface)gameObject;
        PlayerInterface player = (PlayerInterface)playerObject;
        game.add(player);
        game.addUser(accountName, accountPassword);
        game.addCAM(casinoAccountManager);
        game.play(console.getScanner()); {
        }
    }
}