package com.github.zipcodewilmington.flipcointests;

import com.github.zipcodewilmington.casino.games.coinflip.FlipCoinGame;
import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class FlipCoinTest {
    @Test
    public void flipCoinTest(){
        //given
        int heads =1;
        int tails =2;
        Random random = new Random();
        int randNumber = random.nextInt(2)+1;

        FlipCoinGame flipCoin = new FlipCoinGame();
        //When
       int actual = flipCoin.flipCoin(heads,tails);


        //Then
        Assert.assertTrue(actual ==1 || actual ==2);
    }

    @Test

    public void playerGuessedRightTest(){}




}
