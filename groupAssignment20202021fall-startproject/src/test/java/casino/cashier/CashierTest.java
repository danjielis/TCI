package casino.cashier;

import casino.bet.Bet;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import casino.game.BettingRound;
import casino.idfactory.BettingRoundID;
import casino.gamingmachine.GamingMachine;
import casino.gamingmachine.NoPlayerCardException;
import gamblingauthoritiy.BetToken;
import gamblingauthoritiy.IBetLoggingAuthority;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;


public class CashierTest {
    private IBetLoggingAuthority loggingAuthority;

    @Before
    public void setUp()
    {
        this.loggingAuthority = mock(IBetLoggingAuthority.class);
    }

    /**
     * Test if Player has received a card and if gambling authority is informed when a card is being given out
     * Created by Student A: Yoanna Borisova
     */
    @Test
    public void CashierCanHandGamblerCardAndTrackGamblerCardsAndInformBetLoggingAuthority()
    {
        Cashier testCashier = new Cashier(loggingAuthority);
        IGamblerCard tempCard = testCashier.distributeGamblerCard();

        Assert.assertTrue(testCashier.checkCardIsValid(tempCard));
        verify(loggingAuthority).logHandOutGamblingCard(tempCard.getCardID());
    }

    /**
     * Test if cashier has retrieved the gambler card, logged all BetIDs to bet logging authority,
     * clears all the bets from the card, and sets the money amount to 0
     * Created by Student A: Yoanna Borisova
     */
    @Test
    public void CashierCanRetrieveGamblerCardAndInformBetLoggingAuthority()
    {
        Cashier testCashier = new Cashier(loggingAuthority);
        IGamblerCard tempCard = testCashier.distributeGamblerCard();
        testCashier.returnGamblerCard(tempCard);

        Assert.assertFalse(testCashier.checkCardIsValid(tempCard));
        verify(loggingAuthority).logHandInGamblingCard(tempCard.getCardID(), tempCard.returnBetIDsAndClearCard());
    }

    /**
     * Test if the cashier is able to add stored money balance to administration
     * Created by Student A: Yoanna Borisova
     */
    @Test
    public void CashierCanAddMoneyBalance() throws InvalidAmountException
    {
        Cashier testCashier = new Cashier(loggingAuthority);
        IGamblerCard tempCard = testCashier.distributeGamblerCard();
        testCashier.addAmount(tempCard, new MoneyAmount(100));

        Assert.assertEquals(tempCard.getBalance().getAmountInCents(), 100);
    }

    /**
     * Test if cashier tries to add invalid amount (negative amount or null amount) of money to balance
     * Created by Student A: Yoanna Borisova
     */
    @Test (expected = InvalidAmountException.class)
    public void CashierCannotAddInvalidAmount() throws InvalidAmountException
    {
        Cashier testCashier = new Cashier(loggingAuthority);
        IGamblerCard tempCard = testCashier.distributeGamblerCard();
        testCashier.addAmount(tempCard, new MoneyAmount(-100));
    }

    /**
     * ADDITIONAL TEST
     * Test if the cashier is able to add stored money balance to administration
     * Same as CashierCanAddMoneyBalance() but with mocked gambler card
     * Created by Student A: Yoanna Borisova
     */
    @Test
    public void CashierCanAddMoneyBalanceWithMockedGamblerCard() throws InvalidAmountException {
        Cashier testCashier = new Cashier(loggingAuthority);
        GamblerCard tempCard = mock(GamblerCard.class);
        when (tempCard.getBalance()).thenReturn(new MoneyAmount(100));

        testCashier.addAmount(tempCard, new MoneyAmount(100));

        Assert.assertEquals(tempCard.getBalance().getAmountInCents(), 100);
    }

    /**
     * Test if the cashier is able to check whether the gambler card contains enough balance for a bet to be placed
     * Created by Student A: Yoanna Borisova
     */
    @Test
    public void CashierCanValidateBet() throws BetNotExceptedException
    {
        Cashier testCashier = new Cashier(loggingAuthority);
        GamblerCard tempCard = mock(GamblerCard.class);
        Bet testBet = mock(Bet.class);
        when (tempCard.getBalance()).thenReturn(new MoneyAmount(150));
        when(testBet.getMoneyAmount()).thenReturn(new MoneyAmount(100));

        Assert.assertTrue(testCashier.checkIfBetIsValid(tempCard, testBet));
    }

    /**
     * Test if the cashier is able to not validate a gambler card that contains less balance than a bet amount
     * Created by Student A: Yoanna Borisova
     */
    @Test (expected = BetNotExceptedException.class)
    public void CashierCannotValidateInvalidBet() throws BetNotExceptedException
    {
        Cashier testCashier = new Cashier(loggingAuthority);
        GamblerCard tempCard = mock(GamblerCard.class);
        Bet testBet = mock(Bet.class);
        when (tempCard.getBalance()).thenReturn(new MoneyAmount(50));
        when(testBet.getMoneyAmount()).thenReturn(new MoneyAmount(100));

        testCashier.checkIfBetIsValid(tempCard, testBet);
    }

    /**
     * ADDITIONAL TEST
     * Test if the cashier is able to check whether the gambler card contains enough balance for a bet to be placed
     * but after it is validated the amount is substracted from the card
     * Created by Student A: Yoanna Borisova
     */
    @Test
    public void CashierCanValidateBetAndBetAmountIsSubstractedFromCard() throws BetNotExceptedException, InvalidAmountException {
        Cashier testCashier = new Cashier(loggingAuthority);
        GamblerCard tempCard = (GamblerCard) testCashier.distributeGamblerCard();
        testCashier.addAmount(tempCard, new MoneyAmount(150));
        Bet testBet = mock(Bet.class);
        when(testBet.getMoneyAmount()).thenReturn(new MoneyAmount(100));

       testCashier.checkIfBetIsValid(tempCard, testBet);

       Assert.assertEquals(tempCard.getBalance().getAmountInCents(), 50);
    }

    /**
     * Test if the cashier is able to add a winning amount of money to a winning gambling card
     * Created by Student A: Yoanna Borisova
     */
    @Test
    public void CashierCanAddWinnerMoneyAmount() throws InvalidAmountException, NoPlayerCardException, BetNotExceptedException {
        Cashier testCashier = new Cashier(loggingAuthority);
        BetToken testBetToken = mock(BetToken.class);
        BettingRound testBettingRound = new BettingRound(new BettingRoundID(), testBetToken, testCashier);
        GamblerCard card = new GamblerCard();
        testCashier.addAmount(card, new MoneyAmount(100));
        GamingMachine testGamingMachine = new GamingMachine(testCashier, testBettingRound);
        testGamingMachine.connectCard(card);
        testGamingMachine.placeBet(100);
        List<Bet> tempbetList = testGamingMachine.getBets();

        BetResult testBetResult = new BetResult(tempbetList.get(0), new MoneyAmount(100));
        testGamingMachine.acceptWinner(testBetResult);
        Assert.assertEquals(card.getBalance().getAmountInCents(), 100);
    }
}