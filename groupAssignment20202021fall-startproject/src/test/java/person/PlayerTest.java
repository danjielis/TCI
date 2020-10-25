package person;

import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import casino.gamingmachine.GamingMachine;
import casino.idfactory.GamingMachineID;
import gamblingauthoritiy.IBetLoggingAuthority;
import gamblingauthoritiy.IBetTokenAuthority;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

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

    @Test
    public void ShouldConnectToAGamingMachine() {
        Player player = new Player();
        GamblerCard card = mock(GamblerCard.class);
        GamingMachineID gamingMachineID = mock(GamingMachineID.class);
        GamingMachine gamingMachine = mock(GamingMachine.class);
        when(gamingMachine.getGamingMachineID()).thenReturn(gamingMachineID);

        player.obtainGamblerCard(card);
        player.selectGamingMachine(gamingMachine);

        assertEquals(player.selectedGamingMachineID(), gamingMachine.getGamingMachineID());
    }

}