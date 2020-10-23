package casino.game;

import casino.bet.Bet;
import casino.bet.BetID;
import casino.bet.MoneyAmount;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BettingRoundTest {

    @Test
    public void BettingRoundCanPlaceABet()
    {
        BettingRound bettingRound = new BettingRound();
        Bet bet = mock(Bet.class);

        Assert.assertTrue(bettingRound.placeBet(bet));

    }

}