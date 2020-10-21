package casino.game;

import casino.bet.Bet;
import casino.bet.BetID;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GameRuleTest {

    @Test
    public void ShouldReturnMaximumNumberOfBetsPerRound() {
        GameRule gameRule = new GameRule();

        int count = gameRule.getMaxBetsPerRound();

        assertTrue("returning ceiling value", count > 0);
    }

    @Test
    public void ShouldDetermineWinnerOutOfBetSet() {
        GameRule gameRule = new GameRule();
        int random = (int) Math.random();
        List<Bet> bets = new ArrayList<>();

        BetID betID_1 = mock(BetID.class);
        Bet bet_1 = new Bet(betID_1, new MoneyAmount(100));
        bets.add(bet_1);

        BetID betID_2 = mock(BetID.class);
        Bet bet_2 = new Bet(betID_2, new MoneyAmount(100));
        bets.add(bet_2);

        Set<Bet> bet_set = new HashSet<>(bets);

        BetResult result = gameRule.determineWinner((Integer) random, bet_set);

        assertNotNull(result);
    }

}