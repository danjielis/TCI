import casino.cashier.GamblerCard;
import casino.cashier.IGamblerCard;
import casino.game.BettingRound;
import casino.game.DefaultGame;
import gamblingauthoritiy.*;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class DefaultGameTest {

    DefaultGame game=new DefaultGame();
    private IBetTokenAuthority iBetTokenAuthority = mock(IBetTokenAuthority.class);


    /**
     * Check if the new bettingRound starts
     * startBettingRound method
     */
    @Test
    public void CheckIfNewBettingRoundStart(){
        IBetLoggingAuthority IBetLoggingAuthority=new BetLoggingAuthority();
        game=new DefaultGame();
        game.startBettingRound();
        //Can't test since the gamblingauthority do not have implemented
        verify(IBetLoggingAuthority.logStartBettingRound(any(BettingRound.class)));
    }

}
