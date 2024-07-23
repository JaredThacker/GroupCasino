package com.github.zipcodewilmington.flipcointests;

import com.github.zipcodewilmington.casino.games.coinflip.CoinFlipPlayer;
import com.github.zipcodewilmington.casino.games.coinflip.FlipCoinGame;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class PlayerWinsorLoseTest {

    @Test
    public void testIfPlayerWins(){
    CoinFlipPlayer player = new CoinFlipPlayer();
    FlipCoinGame game = new FlipCoinGame();

    boolean actual = game.playerWinsOrLose();
    boolean expected = true;

    assertTrue(actual);

    }
    @Test
    public void testIfPlayerWins2() {
        CoinFlipPlayer player = new CoinFlipPlayer();
        FlipCoinGame game = new FlipCoinGame();

        game.add(player);

        // Simulate multiple games to ensure we cover both win and lose scenarios
        int wins = 0;
        int totalGames = 1000;

        for (int i = 0; i < totalGames; i++) {
            player.coinFlipPlayerGuess(1,1,2); // Player guesses randomly
            game.flipCoin(1,2); // Flip the coin
            if (game.playerWinsOrLose()) {
                wins++;
            }
        }

        // The win rate should be close to 50% for a fair coin
        double winRate = (double) wins / totalGames;
        assertEquals(0.5, winRate, 0.05); // Allow for 5% deviation due to randomness

    }


}
