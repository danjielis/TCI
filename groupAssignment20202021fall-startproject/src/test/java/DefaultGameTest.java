import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.cashier.GamblerCard;
import casino.cashier.IGamblerCard;
import casino.game.BettingRound;
import casino.game.DefaultGame;
import casino.game.GameRule;
import casino.game.NoCurrentRoundException;
import casino.gamingmachine.IGamingMachine;
import casino.idfactory.BetID;
import gamblingauthoritiy.*;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class DefaultGameTest {
    private GameRule gameRule = mock(GameRule.class);
    private IBetTokenAuthority betTokenAuthority = mock(IBetTokenAuthority.class);
    private IBetLoggingAuthority betLoggingAuthority= mock(gamblingauthoritiy.IBetLoggingAuthority.class);
    DefaultGame game=new DefaultGame(gameRule,betLoggingAuthority,betTokenAuthority);
    MoneyAmount moneyAmount = mock(MoneyAmount.class);
    private IGamingMachine gamingMachine = mock(IGamingMachine.class);


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
     * Check
     */
    @Test
    public void acceptBetSuccessfully() throws NoCurrentRoundException {
        game.startBettingRound();
        MoneyAmount moneyAmount = mock(MoneyAmount.class);
        Bet bet=new Bet(new BetID(),moneyAmount);
        assertTrue(game.acceptBet(bet, gamingMachine));
    }

}
