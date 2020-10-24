package casino.cashier;

import casino.bet.Bet;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import casino.game.BettingRound;
import casino.game.BettingRoundID;
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


    @Test
    public void CashierCanHandGamblerCardAndTrackGamblerCardsAndInformBetLoggingAuthority()
    {
        Cashier testCashier = new Cashier(loggingAuthority);
        IGamblerCard tempCard = testCashier.distributeGamblerCard();

        Assert.assertTrue(testCashier.checkCardIsValid(tempCard));
        verify(loggingAuthority).logHandOutGamblingCard(tempCard.getCardID());
    }

    @Test
    public void CashierCanRetrieveGamblerCardAndInformBetLoggingAuthority()
    {
        Cashier testCashier = new Cashier(loggingAuthority);
        IGamblerCard tempCard = testCashier.distributeGamblerCard();
        testCashier.returnGamblerCard(tempCard);

        Assert.assertFalse(testCashier.checkCardIsValid(tempCard));
        verify(loggingAuthority).logHandInGamblingCard(tempCard.getCardID(), tempCard.returnBetIDsAndClearCard());
    }

    @Test
    public void CashierCanAddMoneyBalance() throws InvalidAmountException
    {
        Cashier testCashier = new Cashier(loggingAuthority);
        IGamblerCard tempCard = testCashier.distributeGamblerCard();
        testCashier.addAmount(tempCard, new MoneyAmount(100));

        Assert.assertEquals(tempCard.getBalance().getAmountInCents(), 100);
    }

    @Test (expected = InvalidAmountException.class)
    public void CashierCannotAddInvalidAmount() throws InvalidAmountException
    {
        Cashier testCashier = new Cashier(loggingAuthority);
        IGamblerCard tempCard = testCashier.distributeGamblerCard();
        testCashier.addAmount(tempCard, new MoneyAmount(-100));
    }

    @Test
    public void CashierCanAddMoneyBalanceWithMockedGamblerCard() throws InvalidAmountException {
        Cashier testCashier = new Cashier(loggingAuthority);
        GamblerCard tempCard = mock(GamblerCard.class);
        when (tempCard.getBalance()).thenReturn(new MoneyAmount(100));

        testCashier.addAmount(tempCard, new MoneyAmount(100));

        Assert.assertEquals(tempCard.getBalance().getAmountInCents(), 100);
    }

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
        Assert.assertEquals(card.getBalance().getAmountInCents(), 200);
    }
}