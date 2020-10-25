package casino.gamingmachine;

import casino.bet.Bet;
import casino.bet.BetID;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import casino.cashier.*;
import casino.game.BettingRound;
import casino.idfactory.IDFactory;
import gamblingauthoritiy.BetLoggingAuthority;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


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


    /**
     * A gambler card (card) can be stored at a gaming machine.
     */
    @Test
    public void ShouldConnectCardToAGamingMachine() {
        // Arrange - preconditions and inputs
        GamingMachine machine = new GamingMachine(cashier, bettingRound);

        // Act - action on SUT
        machine.connectCard(this.card);

        // Assert - expecting desired outcome
        assertEquals("Connected card equals provided card", this.card, machine.getConnectedCard());
    }


    /**
     * A gambler card (card) can be disconnected from a gaming machine.
     * @throws CurrentBetMadeException
     */
    @Test
    public void ShouldDisconnectCardFromAGamingMachine() throws CurrentBetMadeException {
        // Arrange - preconditions and inputs
        GamingMachine machine = new GamingMachine(cashier, bettingRound);

        // Act - action on SUT
        machine.connectCard(this.card);
        machine.disconnectCard();

        // Assert - expecting desired outcome
        assertNull("After disconnect card slot is empty - null", machine.getConnectedCard());
    }


    /**
     * Bet should not be placed without connecting a gambler card beforehand.
     * @throws NoPlayerCardException
     * @throws BetNotExceptedException
     */
    // Assert - expecting desired outcome
    @Test(expected = NoPlayerCardException.class)
    public void ShouldNotBeAbleToPlaceBetWithNoConnectedCard() throws NoPlayerCardException, BetNotExceptedException {
        // Arrange - preconditions and inputs
        GamingMachine machine = new GamingMachine(cashier, bettingRound);

        // Act - action on SUT
        machine.placeBet(1000);
    }


    /*
    Comment after first integration of students tests:

    Initial approach to testing was that since there is no implementation and TDD cycle should be followed,
    it is better to direct immutable objects, and attempt to mock those that are harder to get around with.

    First branch with at least three tests per SUTs, was merged, and anticipating every team member doing same,
    now is possible to direct the more difficult ones, ensuring that nothing is left behind.

    However convenient mocking is, it is applicable best to functionalities that lie outside of the container,
    and since here in casino we have only one container, it only seems reasonable to fully check with directs.
     */


    /**
     * A gaming machine should produce confirmation if bet was placed successfully
     * That includes internally checking whether cashier confirms that amount is
     * available, and betting round adding bet if it does not exceed allowed count.
     * @throws InvalidAmountException
     * @throws NoPlayerCardException
     * @throws BetNotExceptedException
     */
    @Test
    public void ShouldProduceConfirmationIfBetPlacedSuccessfully() throws InvalidAmountException, NoPlayerCardException, BetNotExceptedException {
        // Arrange - preconditions and inputs
        Cashier cashier_local = new Cashier(logging);
        BettingRound bettingRound = new BettingRound();
        GamingMachine machine = new GamingMachine(cashier_local, bettingRound);
        GamblerCard card = (GamblerCard) cashier_local.distributeGamblerCard();
        cashier_local.addAmount(card, new MoneyAmount(200));

        // Act - action on SUT
        machine.connectCard(card);
        boolean confirmation = machine.placeBet(50);

        // Assert - expecting desired outcome
        assertTrue("Confirmation was not produced for a placed bet", confirmation);
    }


    /**
     * Once a bet was placed, a gambling card should not be disconnected until round has finished,
     * this is because gambling machine has an acceptWinner method that should inform a cashier
     * on the amount to be added. This can not be done (in current setting) if after bet placement,
     * the card is disconnected. Then even if that bet is deemed winner, machine won't inform.
     * @throws CurrentBetMadeException
     * @throws InvalidAmountException
     * @throws NoPlayerCardException
     * @throws BetNotExceptedException
     */
    // Assert - expecting desired outcome
    @Test(expected = CurrentBetMadeException.class)
    public void ShouldNotDisconnectCardFromAGamingMachineWithActiveBet() throws CurrentBetMadeException, InvalidAmountException, NoPlayerCardException, BetNotExceptedException {
        // Arrange - preconditions and inputs
        Cashier cashier_local = new Cashier(logging);
        BettingRound bettingRound = new BettingRound();
        GamingMachine machine = new GamingMachine(cashier_local, bettingRound);
        GamblerCard card = (GamblerCard) cashier_local.distributeGamblerCard();
        cashier_local.addAmount(card, new MoneyAmount(200));

        // Act - action on SUT
        machine.connectCard(card);
        machine.placeBet(100);
        machine.disconnectCard();
    }


    /**
     * A gaming machine should even begin to process bet (inquire with cashier, and then check with round)
     * if there is no connected card at that moment.
     * @throws NoPlayerCardException
     * @throws BetNotExceptedException
     */
    // Assert - expecting desired outcome
    @Test(expected = NoPlayerCardException.class)
    public void ShouldNotBeAbleToPlaceBetWithNoCard() throws NoPlayerCardException, BetNotExceptedException {
        // Arrange - preconditions and inputs
        GamingMachine machine = new GamingMachine(cashier, bettingRound);

        // Act - action on SUT
        machine.placeBet(100);
    }

    /**
     * A response from cashier should be appropriate if a bet on a gaming machine is attempted to be
     * made, when money amount is insufficient (gambles more than actually has).
     * @throws InvalidAmountException
     * @throws NoPlayerCardException
     * @throws BetNotExceptedException
     */
    // Assert - expecting desired outcome
    @Test(expected = BetNotExceptedException.class)
    public void ShouldNotBeAbleToMakeBetWithInsufficientAmount() throws InvalidAmountException, NoPlayerCardException, BetNotExceptedException {
        // Arrange - preconditions and inputs
        Cashier cashier_local = new Cashier(logging);
        BettingRound bettingRound = new BettingRound();
        GamingMachine machine = new GamingMachine(cashier_local, bettingRound);
        GamblerCard card = (GamblerCard) cashier_local.distributeGamblerCard();
        cashier_local.addAmount(card, new MoneyAmount(100));

        // Act - action on SUT
        machine.connectCard(card);
        machine.placeBet(200);
    }


    /**
     * Due to balance addition logic, where [balance = balance + addition], it must be ensured
     * that no negative values are allowed.
     * @throws BetNotExceptedException
     * @throws InvalidAmountException
     * @throws NoPlayerCardException
     */
    // Assert - expecting desired outcome
    @Test(expected = BetNotExceptedException.class)
    public void ShouldNotBeAbleToMakeBetWithInvalidAmount() throws BetNotExceptedException, InvalidAmountException, NoPlayerCardException {
        // Arrange - preconditions and inputs
        Cashier cashier_local = new Cashier(logging);
        BettingRound bettingRound = new BettingRound();
        GamingMachine machine = new GamingMachine(cashier_local, bettingRound);
        GamblerCard card = (GamblerCard) cashier_local.distributeGamblerCard();
        cashier_local.addAmount(card, new MoneyAmount(100));

        // Act - action on SUT
        machine.connectCard(card);
        machine.placeBet(-200);
    }


    /**
     * Should be able to track all bets made on this machine, so that determineWinner method could
     * later erase them and inform if there is a match for a winner.
     * @throws NoPlayerCardException
     * @throws BetNotExceptedException
     * @throws InvalidAmountException
     */
    @Test
    public void ShouldInformCashierDuringBetPlacement() throws NoPlayerCardException, BetNotExceptedException, InvalidAmountException {
        // Arrange - preconditions and inputs
        Cashier cashier_local = new Cashier(logging);
        BettingRound bettingRound = new BettingRound();
        GamingMachine machine = new GamingMachine(cashier_local, bettingRound);
        GamblerCard card = (GamblerCard) cashier_local.distributeGamblerCard();
        cashier_local.addAmount(card, new MoneyAmount(1000));

        // Act - action on SUT
        machine.connectCard(card);
        machine.placeBet(50);
        machine.placeBet(50);
        machine.placeBet(50);

        List<Bet> bets = machine.getBets();
        assertEquals("Should be able to track bets made from this machine", 3, machine.getBets().size());
    }


    /**
     * A requirement is to have logic, where after a betting round has finished, all bets that were
     * recorded on this machine (person can make several bets), are erased.
     * @throws InvalidAmountException
     * @throws NoPlayerCardException
     * @throws BetNotExceptedException
     */
    @Test
    public void ShouldEmptyListAfterAround() throws InvalidAmountException, NoPlayerCardException, BetNotExceptedException {
        // Arrange - preconditions and inputs
        Cashier cashier_local = new Cashier(logging);
        BettingRound bettingRound = new BettingRound();
        GamingMachine machine = new GamingMachine(cashier_local, bettingRound);
        GamblerCard card = (GamblerCard) cashier_local.distributeGamblerCard();
        cashier_local.addAmount(card, new MoneyAmount(100));

        // Act - action on SUT
        machine.connectCard(card);
        machine.placeBet(50);
        Bet bet = new Bet((BetID) IDFactory.generateID("IDBet"), new MoneyAmount(100));
        BetResult result = new BetResult(bet, new MoneyAmount(1000));
        machine.acceptWinner(result);

        // Assert - expecting desired outcome
        assertTrue(machine.getBets().size() == 0);
    }

}