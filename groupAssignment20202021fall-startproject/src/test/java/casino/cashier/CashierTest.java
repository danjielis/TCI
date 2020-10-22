package casino.cashier;

import casino.bet.Bet;
import casino.bet.MoneyAmount;
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
    public void CashierCanValidateBet() throws BetNotExceptedException {
        Cashier testCashier = new Cashier(loggingAuthority);
        GamblerCard tempCard = mock(GamblerCard.class);
        Bet testBet = mock(Bet.class);
        when (tempCard.getBalance()).thenReturn(new MoneyAmount(150));
        when(testBet.getMoneyAmount()).thenReturn(new MoneyAmount(100));

        Assert.assertTrue(testCashier.checkIfBetIsValid(tempCard, testBet));

    }
}