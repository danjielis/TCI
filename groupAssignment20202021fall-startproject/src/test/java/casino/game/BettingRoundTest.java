package casino.game;

import casino.bet.Bet;
import casino.idfactory.BetID;
import casino.bet.MoneyAmount;
import gamblingauthoritiy.IBetLoggingAuthority;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BettingRoundTest {

    private Bet bet;
    @Before
    public void setUp()
    {
        this.bet = mock(Bet.class);
    }

    /**
     * Test if the betting round can place a bet inside a betting round
     * Created by Student A: Yoanna Borisova
     */
    @Test
    public void BettingRoundCanPlaceABet()
    {
        //Arrange
        BettingRound bettingRound = new BettingRound();

        //Act & Assert
        Assert.assertTrue(bettingRound.placeBet(bet));

    }

    /**
     * Test if the betting round cannot place a bet if the bet is null
     * Created by Student A: Yoanna Borisova
     * @throws IllegalArgumentException
     */
    //Assert
    @Test (expected = IllegalArgumentException.class)
    public void BettingRoundCannotPlaceANullBet()
    {
        //Arrange
        BettingRound bettingRound = new BettingRound();

        //Act
        bettingRound.placeBet(null);
    }

}