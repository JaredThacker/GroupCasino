package com.github.zipcodewilmington.casino.games.numberguess;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class NumberGuessGameTest {
    Random numGen = new Random(System.currentTimeMillis());

    @Test
    public void generateNew() {
        Integer ranNum = numGen.nextInt(20) + 1;
        Assert.assertTrue(ranNum >= 1 && ranNum <= 20);
    }

    @Test
    public void guessCheck() {
    }
}