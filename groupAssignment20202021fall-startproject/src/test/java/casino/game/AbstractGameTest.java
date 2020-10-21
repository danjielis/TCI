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

    @Test
    public void AbstractGameCanBeConstructed()
    {
        IBetLoggingAuthority betLoggingAuthority = mock(IBetLoggingAuthority.class);
        IBetTokenAuthority betTokenAuthority = mock(IBetTokenAuthority.class);

        AbstractGame testAbstractGame = mock(AbstractGame.class, Mockito.withSettings().useConstructor(betLoggingAuthority, betTokenAuthority).defaultAnswer(CALLS_REAL_METHODS));
        Assert.assertSame(betLoggingAuthority, testAbstractGame.betLoggingAuthority);
        Assert.assertSame(betTokenAuthority, testAbstractGame.betTokenAuthority);

    }

}