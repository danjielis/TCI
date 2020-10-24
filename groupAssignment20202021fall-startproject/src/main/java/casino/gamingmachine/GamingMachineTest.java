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

    /*
    Initial approach to testing was that since there is no implementation and TDD cycle should be followed,
    it is better to direct immutable objects, and attempt to mock those that are harder to get around with.

    First branch with at least three tests per SUTs, was merged, and anticipating every team member doing same,
    now is possible to direct the more difficult ones, ensuring that nothing is left behind.

    However convenient mocking is, it is applicable best to functionalities that lie outside of the container,
    and since here in casino we have only one container, it only seems reasonable to fully check with directs.
     */

}