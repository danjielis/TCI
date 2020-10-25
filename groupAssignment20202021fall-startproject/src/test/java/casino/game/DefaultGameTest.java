package casino.game;

import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.cashier.*;
import casino.gamingmachine.IGamingMachine;
import casino.idfactory.BetID;
import gamblingauthoritiy.*;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DefaultGameTest {
    private GameRule gameRule = mock(GameRule.class);
    private IBetTokenAuthority betTokenAuthority = mock(IBetTokenAuthority.class);
    private IBetLoggingAuthority betLoggingAuthority= mock(gamblingauthoritiy.IBetLoggingAuthority.class);
    private IGamingMachine gamingMachine = mock(IGamingMachine.class);
    private DefaultGame game=new DefaultGame(gameRule,betLoggingAuthority,betTokenAuthority);
    private MoneyAmount moneyAmount ;

    /**
     * Check if the new bettingRound starts
     * startBettingRound method
     */
    @Test
    public void CheckIfNewBettingRoundStart(){
        game.startBettingRound();
        verify(betLoggingAuthority).logStartBettingRound(any(BettingRound.class));
    }

    /**
     * Check acceptBetSuccessfully
     */
    @Test
    public void acceptBetSuccessfully() throws NoCurrentRoundException {
        moneyAmount=new MoneyAmount(1);
        game.startBettingRound();
        Bet bet=new Bet(new BetID(),moneyAmount);
        assertTrue(game.acceptBet(bet, gamingMachine));
    }

    @Test(expected = NoCurrentRoundException.class)
    public void acceptBetUnSuccessfully() throws NoCurrentRoundException {
        Bet bet= mock(Bet.class);
        game.acceptBet(bet,gamingMachine);
    }


    @Test
    public void determineWinnerSuccessfully() throws NoBetsMadeException {
        game.determineWinner();
        //verify(betLoggingAuthority).logEndBettingRound(bettingRound,betResult);
        assertNull(game.getBettingRound());
    }

    @Test
    public void determineWinnerUnSuccessfullyAndSizeIsZero() throws NoBetsMadeException {
        game=new DefaultGame(gameRule,betLoggingAuthority,betTokenAuthority);
        game.setBettingRound(null);
        BettingRound bettingRound=new BettingRound();
        Set<Bet> set=bettingRound.getAllBetsMade();
        game.determineWinner();
        assertEquals(set.size(),0);
    }


    @Test
    public void CheckIfBettingRoundFinishedSuccessfully() throws NoCurrentRoundException, NoBetsMadeException, BetNotExceptedException {
        Bet bet=new Bet(new BetID(),new MoneyAmount(11));
        game.startBettingRound();
        game.acceptBet(bet,gamingMachine);
        boolean result= game.isBettingRoundFinished();
        assertTrue(result);
    }

    @Test
    public void CheckIfBettingRoundFinishedUnSuccessfully() throws NoCurrentRoundException, NoBetsMadeException, BetNotExceptedException {
        Bet bet=new Bet(new BetID(),new MoneyAmount(1));
        game.startBettingRound();
        game.acceptBet(bet,gamingMachine);
        boolean result=game.isBettingRoundFinished();
        assertTrue(result);

    }
}
