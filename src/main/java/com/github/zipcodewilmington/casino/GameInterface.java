package com.github.zipcodewilmington.casino;

import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public interface GameInterface extends Runnable {
    /**
     * adds a player to the game
     * @param player the player to be removed from the game
     */
    void add(PlayerInterface player);

    /**
     * removes a player from the game
     * @param player the player to be removed from the game
     */
    void remove(PlayerInterface player);

    /**
     * specifies how the game will run
     */
    void run();


    void addCasinoAccount(CasinoAccount casinoAccount);

    void addUser(String username, String password);

    void addCAM(CasinoAccountManager casinoAccountManager);

    /**
     * Plays the game using the provided scanner
     * This method provides a default implementation that calls run()
     * Games can override this method to provide custom play logic
     * @param scanner the scanner to be used for input
     */
    default void play(Scanner scanner) {
        run();
    }


}
