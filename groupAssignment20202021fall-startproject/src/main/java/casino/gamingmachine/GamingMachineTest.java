package casino.gamingmachine;

import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import casino.game.BettingRound;
import gamblingauthoritiy.BetLoggingAuthority;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GamingMachineTest {

    @Test
    public void ShouldConnectCardToAGamingMachine() {
        GamingMachine machine = new GamingMachine();
        GamblerCard card = mock(GamblerCard.class);

        machine.connectCard(card);

        assertEquals(card, machine.getConnectedCard());
    }

    @Test
    public void ShouldDisconnectCardFromAGamingMachine() throws CurrentBetMadeException {
        GamingMachine machine = new GamingMachine();
        GamblerCard card = mock(GamblerCard.class);

        machine.connectCard(card);
        machine.disconnectCard();

        assertNull(machine.getConnectedCard());
    }

}