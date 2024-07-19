package com.github.zipcodewilmington.walletTest;

import com.github.zipcodewilmington.casino.games.wallet.Wallet;
import org.junit.Assert;
import org.junit.Test;

public class WalletTest {

    Wallet wallet = new Wallet();

    @Test
    public void withdrawalTest(){

        wallet.addFunds(1000.00);
        wallet.withdrawl(10.00);

        Double expected = 990.00;

        Assert.assertEquals(expected,wallet.emptyPockets());
    }



//    @Test
//    public void addFunds(){
//
//     wallet.addFunds(3000.0);
//
//     Double expected = 3000.0;
//
//
//        Assert.assertEquals(expected,wallet.getBalance());
//    }

    @Test
    public void getbalanceTest(){
        wallet.addFunds(100.00);

        Double expected = 100.00;

        Assert.assertEquals(expected,wallet.emptyPockets());
    }
}
