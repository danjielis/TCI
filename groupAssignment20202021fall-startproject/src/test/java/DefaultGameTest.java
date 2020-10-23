import casino.bet.Bet;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import casino.cashier.ICashier;
import casino.cashier.IGamblerCard;
import casino.game.*;
import casino.gamingmachine.IGamingMachine;
import casino.gamingmachine.NoPlayerCardException;
import casino.idfactory.BetID;
import casino.idfactory.BettingRoundID;
import casino.idfactory.IDFactory;
import gamblingauthoritiy.*;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class DefaultGameTest {
    private GameRule gameRule = mock(GameRule.class);
    private IBetTokenAuthority betTokenAuthority = mock(IBetTokenAuthority.class);
    private IBetLoggingAuthority betLoggingAuthority= mock(gamblingauthoritiy.IBetLoggingAuthority.class);
    DefaultGame game=new DefaultGame(gameRule,betLoggingAuthority,betTokenAuthority);
    private IGamingMachine gamingMachine = mock(IGamingMachine.class);
    MoneyAmount moneyAmount = mock(MoneyAmount.class);


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
        game.startBettingRound();
        MoneyAmount moneyAmount = mock(MoneyAmount.class);
        Bet bet=new Bet(new BetID(),moneyAmount);
        assertTrue(game.acceptBet(bet, gamingMachine));
    }

    @Test(expected = NoCurrentRoundException.class)
    public void acceptBetUnSuccessfully() throws NoCurrentRoundException {
        Bet bet= mock(Bet.class);
        game.acceptBet(bet,gamingMachine);
    }

    @Test
    public void determineWinnerSuccessfully(){
        BettingRoundID bettingRoundID = (BettingRoundID) IDFactory.generateID("BettingRoundID");
        Set<Bet> bets = new HashSet<>();
        BetToken token= mock(BetToken.class);
        Cashier cashier=mock(Cashier.class);
        BetResult betResult=mock(BetResult.class);
        Bet bet1 = new Bet(new BetID(), new MoneyAmount(50));
        Bet bet2 = new Bet(new BetID(), new MoneyAmount(50));
        bets.add(bet1);
        bets.add(bet2);
        IBettingRound bettingRound=new BettingRound(bettingRoundID,token,cashier);
        game.determineWinner();
        verify(betLoggingAuthority).logEndBettingRound(bettingRound,betResult);
    }

    @Test(expected = NoBetsMadeException.class)
    public void determineWinnerUnSuccessfully(){
        game.determineWinner();

    }

    @Test
    public void CheckIfBettingRoundFinished(){

    }





    }
