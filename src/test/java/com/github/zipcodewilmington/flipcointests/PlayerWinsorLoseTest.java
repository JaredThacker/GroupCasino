package com.github.zipcodewilmington.flipcointests;

import com.github.zipcodewilmington.casino.games.coinflip.FlipCoinGame;
import org.junit.Test;

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


}
