package com.github.zipcodewilmington.casino.games.wallet;

public class Wallet {


    private double dollars;
    private double balance;

    public void addFunds(Double dollars) {
       this.balance += dollars;

    }

    public void withdrawl(double dollars) {
        this.balance -= dollars;
    }

    public Double emptyPockets() {
    if(balance > 0){
        System.out.println(":YOU'RE NOT BROKE YET GAMBLE SOME MORE......hehehe");
        return balance ;
        }
         return balance;
    }
}
