package com.github.zipcodewilmington.slots;

import org.junit.Assert;
import org.junit.Test;
import java.util.Random;

public class SlotsTest {

    @Test
    public void calculatePayoutTest(){
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
}
