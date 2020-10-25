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

    // STUDENT C - DANAS JUSYS

    /**
     * Should return a number that is greater than 0, and represents maximum
     * number of bets for a betting round. In this project it is arbitrary
     * selected that 5 bets are allowed tops.
     */
    @Test
    public void ShouldReturnMaximumNumberOfBetsPerRound() {
        // Arrange - preconditions and inputs
        GameRule gameRule = new GameRule();

        // Act - action on SUT
        int count = gameRule.getMaxBetsPerRound();

        // Assert - expecting desired outcome
        assertTrue("Bet count must always exceed zero allowed bets", count > 0);
    }


    /**
     * Game rule method determineWinner, should return a betResult that must
     * specify the winner out of bet pool.
     * @throws NoBetsMadeException
     */
    @Test
    public void ShouldDetermineWinnerOutOfBetSet() throws NoBetsMadeException {
        // Arrange - preconditions and inputs
        GameRule gameRule = new GameRule();
        int random = (int) Math.random() * 100;
        List<Bet> bets = new ArrayList<>();
        BetID betID_1 = mock(BetID.class);
        Bet bet_1 = new Bet(betID_1, new MoneyAmount(100));
        bets.add(bet_1);
        BetID betID_2 = mock(BetID.class);
        Bet bet_2 = new Bet(betID_2, new MoneyAmount(100));
        bets.add(bet_2);
        Set<Bet> bet_set = new HashSet<>(bets);

        // Act - action on SUT
        BetResult result = gameRule.determineWinner((Integer) random, bet_set);

        // Assert - expecting desired outcome
        assertTrue("must equals one of the placed bets",
                ((result.getWinningBet().getBetID() == betID_1) || (result.getWinningBet().getBetID() == betID_2)));
    }


    /**
     * If no bets were made then determineWinner method should not attempt to calculate
     * and determine winner.
     * @throws NoBetsMadeException
     */
    // Assert - expecting desired outcome
    @Test(expected = NoBetsMadeException.class)
    public void ShouldThrowNoBetsMadeExceptionIfNoBetsMade() throws NoBetsMadeException {
        // Arrange - preconditions and inputs
        GameRule gameRule = new GameRule();
        List<Bet> bets = new ArrayList<>();
        Set<Bet> bet_set = new HashSet<>(bets);

        // Act - action on SUT
        BetResult result = gameRule.determineWinner(69, bet_set);
    }




}