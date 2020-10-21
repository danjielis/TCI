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

    private GamblerCard card;

    @Before
    public void setUp() {
        this.card = mock(GamblerCard.class);
    }

    @Test
    public void ShouldConnectCardToAGamingMachine() {
        GamingMachine machine = new GamingMachine();

        machine.connectCard(this.card);

        assertEquals(this.card, machine.getConnectedCard());
    }

    @Test
    public void ShouldDisconnectCardFromAGamingMachine() throws CurrentBetMadeException {
        GamingMachine machine = new GamingMachine();

        machine.connectCard(this.card);
        machine.disconnectCard();

        assertNull(machine.getConnectedCard());
    }


    @Test(expected = NoPlayerCardException.class)
    public void ShouldNotBeAbleToPlaceBetWithNoConnectedCard() throws NoPlayerCardException {
        GamingMachine machine = new GamingMachine();

        machine.placeBet(1000);
    }



}