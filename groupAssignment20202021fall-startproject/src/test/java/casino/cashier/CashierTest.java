package casino.cashier;

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
    public void CashierCanAddMoneyBalance() throws InvalidAmountException {
        Cashier testCashier = new Cashier(loggingAuthority);
        IGamblerCard tempCard = testCashier.distributeGamblerCard();
        testCashier.addAmount(tempCard, new MoneyAmount(100));

        Assert.assertEquals(tempCard.getBalance().getAmountInCents(), 100);
    }

}