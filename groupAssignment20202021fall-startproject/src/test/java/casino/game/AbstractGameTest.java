package casino.game;

import gamblingauthoritiy.IBetLoggingAuthority;
import gamblingauthoritiy.IBetTokenAuthority;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

public class AbstractGameTest {

    /**
     * Test if the abstract game can be constructed using a BettingAuhtority,
     * initializes a list of gamingMachines, a GameRule and a BettingRound.
     * The BettingRound must also be started inside the constructor
     * Created by Student A: Yoanna Borisova
     */
    @Test
    public void AbstractGameCanBeConstructed()
    {
        IBetLoggingAuthority betLoggingAuthority = mock(IBetLoggingAuthority.class);
        IBetTokenAuthority betTokenAuthority = mock(IBetTokenAuthority.class);

        AbstractGame testAbstractGame = mock(AbstractGame.class, Mockito.withSettings().useConstructor(betLoggingAuthority, betTokenAuthority).defaultAnswer(CALLS_REAL_METHODS));
        Assert.assertSame(betLoggingAuthority, testAbstractGame.loggingAuthority);
        Assert.assertSame(betTokenAuthority, testAbstractGame.tokenAuthority);

    }

}