package casino.gamingmachine;

import casino.bet.MoneyAmount;
import casino.cashier.BetNotExceptedException;
import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import casino.cashier.InvalidAmountException;
import casino.game.BettingRound;
import gamblingauthoritiy.BetLoggingAuthority;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class GamingMachineTest {

    private GamblerCard card;
    private Cashier cashier;
    private BettingRound bettingRound;
    private BetLoggingAuthority logging;

    @Before
    public void setUp() {

        this.card = mock(GamblerCard.class);
        this.cashier = mock(Cashier.class);
        this.bettingRound = mock(BettingRound.class);
        this.logging = mock(BetLoggingAuthority.class);
    }

    @Test
    public void ShouldConnectCardToAGamingMachine() {
        GamingMachine machine = new GamingMachine(cashier, bettingRound);

        machine.connectCard(this.card);

        assertEquals(this.card, machine.getConnectedCard());
    }

    @Test
    public void ShouldDisconnectCardFromAGamingMachine() throws CurrentBetMadeException {
        GamingMachine machine = new GamingMachine(cashier, bettingRound);

        machine.connectCard(this.card);
        machine.disconnectCard();

        assertNull(machine.getConnectedCard());
    }


    @Test(expected = NoPlayerCardException.class)
    public void ShouldNotBeAbleToPlaceBetWithNoConnectedCard() throws NoPlayerCardException, BetNotExceptedException {
        GamingMachine machine = new GamingMachine(cashier, bettingRound);

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

    @Test
    public void ShouldBeAbleToPlaceABet() throws InvalidAmountException, NoPlayerCardException, BetNotExceptedException {
        Cashier cashier_local = new Cashier(logging);
        BettingRound bettingRound = new BettingRound();
        GamingMachine machine = new GamingMachine(cashier_local, bettingRound);

        GamblerCard card = (GamblerCard) cashier_local.distributeGamblerCard();
        cashier_local.addAmount(card, new MoneyAmount(200));
        machine.connectCard(card);
        boolean confirmation = machine.placeBet(50);

        assertTrue(confirmation);
    }


    @Test(expected = CurrentBetMadeException.class)
    public void ShouldNotDisconnectCardFromAGamingMachineWithActiveBet() throws CurrentBetMadeException, InvalidAmountException, NoPlayerCardException, BetNotExceptedException {
        Cashier cashier_local = new Cashier(logging);
        BettingRound bettingRound = new BettingRound();
        GamingMachine machine = new GamingMachine(cashier_local, bettingRound);

        GamblerCard card = (GamblerCard) cashier_local.distributeGamblerCard();
        cashier_local.addAmount(card, new MoneyAmount(200));
        machine.connectCard(card);
        machine.placeBet(100);
        machine.disconnectCard();
    }


    @Test(expected = NoPlayerCardException.class)
    public void ShouldNotBeAbleToPlaceBetWithNoCard() throws NoPlayerCardException, BetNotExceptedException {
        GamingMachine machine = new GamingMachine(cashier, bettingRound);
        machine.placeBet(100);
    }


    @Test(expected = BetNotExceptedException.class)
    public void ShouldNotBeAbleToMakeBetWithInsufficientAmount() throws InvalidAmountException, NoPlayerCardException, BetNotExceptedException {
        Cashier cashier_local = new Cashier(logging);
        BettingRound bettingRound = new BettingRound();
        GamingMachine machine = new GamingMachine(cashier_local, bettingRound);

        GamblerCard card = (GamblerCard) cashier_local.distributeGamblerCard();
        cashier_local.addAmount(card, new MoneyAmount(100));
        machine.connectCard(card);
        machine.placeBet(200);
    }


    @Test(expected = BetNotExceptedException.class)
    public void ShouldNotBeAbleToMakeBetWithInvalidAmount() throws BetNotExceptedException, InvalidAmountException, NoPlayerCardException {
        Cashier cashier_local = new Cashier(logging);
        BettingRound bettingRound = new BettingRound();
        GamingMachine machine = new GamingMachine(cashier_local, bettingRound);

        GamblerCard card = (GamblerCard) cashier_local.distributeGamblerCard();
        cashier_local.addAmount(card, new MoneyAmount(100));
        machine.connectCard(card);
        machine.placeBet(-200);
    }

}