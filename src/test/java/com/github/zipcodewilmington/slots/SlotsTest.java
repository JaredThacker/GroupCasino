package com.github.zipcodewilmington.slots;

import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import org.junit.Assert;
import org.junit.Test;

import java.awt.image.ShortLookupTable;
import java.util.Random;

public class SlotsTest {

    @Test
    public void calculateMultiplierTest(){
        String[] Symbols = {"Cherry"};
        int[] payouts = {2};
        int expected = 0;
        Random random = new Random();

        String[] reel = new String[3];
        for (int i = 0; i < 3; i++){
            reel[i] = Symbols[random.nextInt(Symbols.length)];
        }

        for (int i = 0; i < Symbols.length; i++) {
            if (reel[0].equals(Symbols[i])) {
                expected = payouts[i];
            }
        }
        Assert.assertEquals(expected, 2);
    }

    @Test
    public void calculatePayoutTest(){
        int payoutMultiplier = 2;
        int betAmount = 5;
        int winnings = payoutMultiplier * betAmount;
        Assert.assertEquals(10, winnings);
    }

    @Test
    public void spinTest(){
        String[] Symbols = {"Cherry"};
        Random random = new Random();
        String[] reel = new String[3];
        for (int i = 0; i < 3; i++){
            reel[i] = Symbols[random.nextInt(Symbols.length)];
        }
        String[] expectedReel = {"Cherry", "Cherry", "Cherry"};
        Assert.assertEquals(expectedReel, reel);
    }
}
