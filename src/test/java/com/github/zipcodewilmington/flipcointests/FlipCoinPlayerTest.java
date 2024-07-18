package com.github.zipcodewilmington.flipcointests;

import com.github.zipcodewilmington.casino.games.coinflip.CoinFlipPlayer;
import org.junit.Assert;
import org.junit.Test;

public class FlipCoinPlayerTest {
//private final InputStream sysInBackup = System.in;
//private final PrintStream sysOutBack = System.out;
//private ByteArrayInputStream in;
//private ByteArrayOutputStream out;
//
//@BeforeEach
//public void setUpStreams(){
//    out = new ByteArrayOutputStream();
//    System.setOut(new PrintStream(out));
//}
//@AfterEach
//public void restoreStreams(){
//    System.setIn(sysInBackup);
//    System.setOut(sysOutBack);
//}

@Test
public void testUserGuessIsValid(){
//    //Given
//    String input = "2/n"; //User puts in 2
//    in = new ByteArrayInputStream(input.getBytes());
//    System.setIn(in);

    //Given
    CoinFlipPlayer player = new CoinFlipPlayer();

    //When

    int actual = player.coinFlipPlayerGuess(2,2,1);

    Assert.assertEquals(2,actual);


}

    @Test
    public void testUserGuessIsValid2() {
//    //Given
//    String input = "2/n"; //User puts in 2
//    in = new ByteArrayInputStream(input.getBytes());
//    System.setIn(in);

        //Given
        CoinFlipPlayer player = new CoinFlipPlayer();

        //When

        int actual = player.coinFlipPlayerGuess(1, 2, 1);

        Assert.assertEquals(1, actual);
    }

}
