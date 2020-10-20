package casino.cashier;

import gamblingauthoritiy.IBetLoggingAuthority;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.*;


public class CashierTest {

    @Test
    public void CashierCanHandGamblerCardAndInformBetLoggingAuthority()
    {
        IBetLoggingAuthority bla = mock(IBetLoggingAuthority.class);
        Cashier testCashier = new Cashier(bla);

        IGamblerCard tempCard = testCashier.distributeGamblerCard();
        List<IGamblerCard> tempCardList = testCashier.getListOfGamblerCards();

        Assert.assertTrue(tempCardList.contains(tempCard));
        verify(bla).logHandOutGamblingCard(tempCard.getCardID());
    }

}