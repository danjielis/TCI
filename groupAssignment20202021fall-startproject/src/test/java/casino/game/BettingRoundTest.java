package casino.game;

import casino.bet.Bet;
import casino.bet.BetID;
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

    @Test
    public void BettingRoundCanPlaceABet()
    {
        BettingRound bettingRound = new BettingRound();

        Assert.assertTrue(bettingRound.placeBet(bet));

    }

    @Test (expected = IllegalArgumentException.class)
    public void BettingRoundCannotPlaceANullBet()
    {
        BettingRound bettingRound = new BettingRound();

        bettingRound.placeBet(null);
    }

}