package com.github.zipcodewilmington.casino.games.hangman;

import com.github.zipcodewilmington.casino.CasinoAccount;
import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.*;

public class HangmanGame implements  GameInterface {
    private String solution;
    private Set<Character> guessedCharacters;
    private Set<Character> incorrectGuesses;
    private int maxTries;
    private int triesLeft;
    private  PlayerInterface player;

    public HangmanGame() {
        String[] words = {"birds", "phils", "sixers", "flyers", "union", "heat", "astronaut", "thesaurus", "football", "palindrome", "granular"};
        Random random = new Random();
        solution = words[random.nextInt(words.length)];
        guessedCharacters = new HashSet<>();
        incorrectGuesses = new HashSet<>();
        maxTries = solution.length();
        triesLeft = maxTries;
    }

    public boolean guessCharacter(char guess) {
        guess = Character.toLowerCase(guess);

        if (guessedCharacters.contains(guess) || incorrectGuesses.contains(guess)) {
            return false; // Already guessed
        }

        if (solution.indexOf(guess) != -1) {
            guessedCharacters.add(guess);
            return true; // Correct Guess
        } else {
            incorrectGuesses.add(guess);
            triesLeft--;
            return false; // Incorrect Guess
        }
    }

    public boolean guessWord(String guess) {
        guess = guess.toLowerCase();
        if (guess.equals(solution)) {
            guessedCharacters.addAll(convertStringToCharacterSet(guess));
            return true; // Guessed Correctly
        } else {
            incorrectGuesses.addAll(convertStringToCharacterSet(guess));
            triesLeft--;
            return false; // Incorrect Guess
        }
    }

    public boolean isGameOver() {
        return triesLeft <= 0 || isWordGuessed();
    }

    public boolean isWordGuessed() {
        for (char c : solution.toCharArray()) {
            if (!guessedCharacters.contains(c)) {
                return false;
            }
        }
        return true;
    }

    public String getCurrentState() {
        StringBuilder currentState = new StringBuilder();

        for (int i = 0; i < solution.length(); i++) {
            char ch = solution.charAt(i);
            if (guessedCharacters.contains(ch)) {
                currentState.append(ch).append(" ");
            } else {
                currentState.append("_ ");
            }
        }
        return currentState.toString().trim();
    }

    private Set<Character> convertStringToCharacterSet(String str) {
        Set<Character> set = new HashSet<>();

        for (char c : str.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    public int getTriesLeft() {

        return triesLeft;
    }

    public String getSolution() {

        return solution;
    }

    public static void main(String[] args) {

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

        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;

        while(playAgain) {
        HangmanGame hangmanGame = new HangmanGame();

        System.out.println("\n" +
                "888    888        d8888 888b    888  .d8888b.  888b     d888        d8888 888b    888 \n" +
                "888    888       d88888 8888b   888 d88P  Y88b 8888b   d8888       d88888 8888b   888 \n" +
                "888    888      d88P888 88888b  888 888    888 88888b.d88888      d88P888 88888b  888 \n" +
                "8888888888     d88P 888 888Y88b 888 888        888Y88888P888     d88P 888 888Y88b 888 \n" +
                "888    888    d88P  888 888 Y88b888 888  88888 888 Y888P 888    d88P  888 888 Y88b888 \n" +
                "888    888   d88P   888 888  Y88888 888    888 888  Y8P  888   d88P   888 888  Y88888 \n" +
                "888    888  d8888888888 888   Y8888 Y88b  d88P 888   \"   888  d8888888888 888   Y8888 \n" +
                "888    888 d88P     888 888    Y888  \"Y8888P88 888       888 d88P     888 888    Y888 \n" +
                "                                                                                      \n" +
                "                                                                                      \n" +
                "                                                                                      \n");

        while (!hangmanGame.isGameOver()) {
            System.out.println("HERE YA GO: " + hangmanGame.getCurrentState());
            System.out.println("Better HURRY - TRIES LEFT: " + hangmanGame.getTriesLeft());
            System.out.print("ENTER A Character OR WORD: ");
            String input = scanner.nextLine();

            if (input.length() == 1) {
                char guess = input.toLowerCase().charAt(0);
                boolean isGuessCorrect = hangmanGame.guessCharacter(guess);
                if (isGuessCorrect) {
                    System.out.println("DING DING! NOT BAD KINDA ALRIGHT:");
                } else {
                    System.out.println("HA HA ! WRONG!!");
                }
            } else {
                boolean isGuessCorrect = hangmanGame.guessWord(input);
                if (isGuessCorrect) {
                    System.out.println("YOU GUESSED THE WORD: " + hangmanGame.getSolution());
                } else {
                    System.out.println("HAHAHAHA SO WRONG !! YOU'RE NOT VERY GOOD AT THIS GAME");
                }
            }
        }

        if (hangmanGame.isWordGuessed()) {
            System.out.println("\n" +
                    "Y88b   d88P  .d88888b.  888     888      888       888  .d88888b.  888b    888      888 888 888 \n" +
                    " Y88b d88P  d88P\" \"Y88b 888     888      888   o   888 d88P\" \"Y88b 8888b   888      888 888 888 \n" +
                    "  Y88o88P   888     888 888     888      888  d8b  888 888     888 88888b  888      888 888 888 \n" +
                    "   Y888P    888     888 888     888      888 d888b 888 888     888 888Y88b 888      888 888 888 \n" +
                    "    888     888     888 888     888      888d88888b888 888     888 888 Y88b888      888 888 888 \n" +
                    "    888     888     888 888     888      88888P Y88888 888     888 888  Y88888      Y8P Y8P Y8P \n" +
                    "    888     Y88b. .d88P Y88b. .d88P      8888P   Y8888 Y88b. .d88P 888   Y8888       \"   \"   \"  \n" +
                    "    888      \"Y88888P\"   \"Y88888P\"       888P     Y888  \"Y88888P\"  888    Y888      888 888 888 \n" +
                    "                                                                                                \n" +
                    "                                                                                                \n" +
                    "                                                                                                \n");
        } else {
            System.out.println("\n" +
                    " .d8888b.         d8888 888b     d888 8888888888       .d88888b.  888     888 8888888888 8888888b.  \n" +
                    "d88P  Y88b       d88888 8888b   d8888 888             d88P\" \"Y88b 888     888 888        888   Y88b \n" +
                    "888    888      d88P888 88888b.d88888 888             888     888 888     888 888        888    888 \n" +
                    "888            d88P 888 888Y88888P888 8888888         888     888 Y88b   d88P 8888888    888   d88P \n" +
                    "888  88888    d88P  888 888 Y888P 888 888             888     888  Y88b d88P  888        8888888P\"  \n" +
                    "888    888   d88P   888 888  Y8P  888 888             888     888   Y88o88P   888        888 T88b   \n" +
                    "Y88b  d88P  d8888888888 888   \"   888 888             Y88b. .d88P    Y888P    888        888  T88b  \n" +
                    " \"Y8888P88 d88P     888 888       888 8888888888       \"Y88888P\"      Y8P     8888888888 888   T88b \n" +
                    "                                                                                                    \n" +
                    "                                                                                                    \n" +
                    "                                                                                                    \nThe word was: " + hangmanGame.getSolution());
        }

        System.out.println("\n" +
                "8888888b.  888             d8888 Y88b   d88P             d8888  .d8888b.         d8888 8888888 888b    888  .d8888b.  \n" +
                "888   Y88b 888            d88888  Y88b d88P             d88888 d88P  Y88b       d88888   888   8888b   888 d88P  Y88b \n" +
                "888    888 888           d88P888   Y88o88P             d88P888 888    888      d88P888   888   88888b  888      .d88P \n" +
                "888   d88P 888          d88P 888    Y888P             d88P 888 888            d88P 888   888   888Y88b 888    .d88P\"  \n" +
                "8888888P\"  888         d88P  888     888             d88P  888 888  88888    d88P  888   888   888 Y88b888    888\"    \n" +
                "888        888        d88P   888     888            d88P   888 888    888   d88P   888   888   888  Y88888    888     \n" +
                "888        888       d8888888888     888           d8888888888 Y88b  d88P  d8888888888   888   888   Y8888            \n" +
                "888        88888888 d88P     888     888          d88P     888  \"Y8888P88 d88P     888 8888888 888    Y888    888     \n" +
                "                                                                                                                      \n" +
                "                                                                                                                      \n" +
                "                                                                                                                      \n");
        System.out.println("[ YES or NO ]");
        String playAgainInput = scanner.nextLine().toLowerCase();
        playAgain = playAgainInput.equals("yes") || playAgainInput.equals("y");
    }

        scanner.close();
    }

    @Override
    public void addCasinoAccount(CasinoAccount casinoAccount) {

    }

    public void startNewGame(String hangman) {
    }

}



//
//
//
//    public void startNewGame(String hangman) {
//        wordToGuess = hangman.toLowerCase(); //Convert word to lowercase for case
//        guessedCharacters = new HashSet<>();
//        incorrectGuesses = new HashSet<>();
//        maxIncorrectGuesses = wordToGuess.length();
//    }
//
//    public CharSequence getCurrentState() {
//        StringBuilder currentState = new StringBuilder();
//
//        for (int i = 0; i < wordToGuess.length(); i++) {
//            char ch = wordToGuess.charAt(i);
//            if (guessedCharacters.contains(ch)) {
//                currentState.append(ch).append(" ");
//            } else {
//                currentState.append("_ ");
//            }
//        }
//        return currentState.toString().trim(); // Trim to remove trailing space
//    }
//
//    public String guessCharacter(char guess) {
//        char guessLower = Character.toLowerCase(guess);
//
//        if (guessedCharacters.contains(guessLower) || incorrectGuesses.contains(guessLower)) {
//            return "Already guessed: " + guessLower;
//        }
//
//            guessedCharacters.add(guessLower);
//
//            if (wordToGuess.indexOf(guessLower) != -1) {
//                if (isWordGuessed()) {
//                    return "You guessed the word: " + wordToGuess;
//                }
//                return "Correct guess: " + guessLower;
//            } else {
//                incorrectGuesses.add(guessLower);
//                if (incorrectGuesses.size() >= maxIncorrectGuesses) {
//                    return "You lost! The word was: " + wordToGuess;
//                }
//                return "Incorrect guess: " + guessLower;
//            }
//        }
//
//    public String guessWord(String guess) {
//        String guessLower = guess.toLowerCase();
//
//        if (guessLower.equals(wordToGuess)) {
//            guessedCharacters.addAll(convertStringToCharacterSet(guessLower));
//            return "You guessed the word: " + wordToGuess;
//        } else {
//            incorrectGuesses.addAll(convertStringToCharacterSet(guessLower));
//            if (incorrectGuesses.size() >= maxIncorrectGuesses) {
//                return "You lost! The word was: " + wordToGuess;
//            }
//            return "Incorrect guess: " + guessLower;
//        }
//    }
//
//    private Set<Character> convertStringToCharacterSet(String str) {
//        Set<Character> set = new HashSet<>();
//        for (char c : str.toCharArray()) {
//            set.add(c);
//        }
//        return set;
//    }
//
//
//    private boolean isWordGuessed() {
//        for (char c : wordToGuess.toCharArray()) {
//            if (!guessedCharacters.contains(c)) {
//                return false;
//            }
//        }
//        return true;
//    }

