package casino.game;

import casino.bet.Bet;
import casino.idfactory.BetID;
import casino.bet.MoneyAmount;
import casino.idfactory.BettingRoundID;
import gamblingauthoritiy.BetToken;
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
    private BettingRoundID testBettingRoundID;
    private BetToken testBetToken;

    //Arrange
    @Before
    public void setUp()
    {
        this.bet = mock(Bet.class);
        this.testBettingRoundID = mock(BettingRoundID.class);
        this.testBetToken = mock(BetToken.class);
    }

    /**
     * Test if the betting round can place a bet inside a betting round
     * Created by Student A: Yoanna Borisova
     */
    @Test
    public void BettingRoundCanPlaceABet()
    {
        //Arrange
        BettingRound bettingRound = new BettingRound(testBettingRoundID,testBetToken);

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
        BettingRound bettingRound = new BettingRound(testBettingRoundID,testBetToken);

        //Act
        bettingRound.placeBet(null);
    }

    /**
     * Test if the betting round can be constructed
     * Created by Student A: Yoanna Borisova
     */
    @Test
    public void BettingRoundCanBeConstructed()
    {
        //Arrange Act
        BettingRound testBettingRound = new BettingRound(testBettingRoundID,testBetToken);

        //Assert
        Assert.assertSame(testBettingRound.getBetToken(), testBetToken);
        Assert.assertSame(testBettingRound.getBettingRoundID(), testBettingRoundID);
    }

    /**
     * ADDITIONAL TEST
     * Test if the numberOFBetsMade() method can return the correct number of bets.
     * Created by Student A: Yoanna Borisova
     */
    @Test
    public void BettingRoundCanReturnCorrectNumberOfBets()
    {
        //Arrange
        BettingRound testBettingRound = new BettingRound(testBettingRoundID,testBetToken);

        //Act
        testBettingRound.placeBet(bet);

        //Assert
        Assert.assertEquals(testBettingRound.numberOFBetsMade(),1);
    }

}