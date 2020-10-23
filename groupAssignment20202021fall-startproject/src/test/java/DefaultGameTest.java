import casino.cashier.GamblerCard;
import casino.cashier.IGamblerCard;
import casino.game.BettingRound;
import casino.game.DefaultGame;
import casino.game.GameRule;
import gamblingauthoritiy.*;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class DefaultGameTest {
    private GameRule gameRule = mock(GameRule.class);
    private IBetTokenAuthority betTokenAuthority = mock(IBetTokenAuthority.class);
    private IBetLoggingAuthority betLoggingAuthority= mock(gamblingauthoritiy.IBetLoggingAuthority.class);
    DefaultGame game=new DefaultGame(gameRule,betLoggingAuthority,betTokenAuthority);

    /**
     * Check if the new bettingRound starts
     * startBettingRound method
     */
    @Test
    public void CheckIfNewBettingRoundStart(){
        game=new DefaultGame(gameRule,betLoggingAuthority,betTokenAuthority);
        game.startBettingRound();
        verify(betLoggingAuthority).logStartBettingRound(any(BettingRound.class));
    }

    /**
     * Check
     */

}
