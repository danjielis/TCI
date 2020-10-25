package person;

import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import gamblingauthoritiy.IBetLoggingAuthority;
import gamblingauthoritiy.IBetTokenAuthority;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PlayerTest {

    private IBetLoggingAuthority loggingAuthority;

    @Before
    public void setUp() {
        this.loggingAuthority = mock(IBetLoggingAuthority.class);
    }


    /**
     * Checks if player can obtain gambler card from the cashier. No authorization
     * is assumed for now.
     */
    @Test
    public void ShouldReceiveGamblerCard() {
        Player player = new Player();
        Cashier cashier = new Cashier(loggingAuthority);

        GamblerCard card = (GamblerCard) cashier.distributeGamblerCard();
        player.obtainGamblerCard(card);

        assertEquals("cards must be equal", player.getGamblerCard(), card);
        verify(loggingAuthority).logHandOutGamblingCard(card.getCardID());
    }

}