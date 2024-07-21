package com.github.zipcodewilmington.dicegametest;

import com.github.zipcodewilmington.casino.games.diceroll.DiceRollGame;
import org.junit.Assert;
import org.junit.Test;

public class DiceRollTest {
    @Test
    public void diceRollTest(){
        //Given
//        DiceRollGame dice = new DiceRollGame(2);

        //When
        int actual = DiceRollGame.rollDie(2);

        //Then
        Assert.assertTrue(actual >= 2);
        Assert.assertTrue(actual <= 12);

    }






}
