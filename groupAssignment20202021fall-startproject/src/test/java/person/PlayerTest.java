package person;

import casino.bet.MoneyAmount;
import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import casino.cashier.InvalidAmountException;
import casino.gamingmachine.GamingMachine;
import casino.idfactory.GamingMachineID;
import gamblingauthoritiy.IBetLoggingAuthority;
import gamblingauthoritiy.IBetTokenAuthority;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PlayerTest {

    private IBetLoggingAuthority loggingAuthority;

    @Before
    public void setUp() {
        this.loggingAuthority = mock(IBetLoggingAuthority.class);
    }


    /**
     * Checks if player can obtain gambler card from the cashier. No authorization
     * is assumed for now.
     */
    @Test
    public void ShouldReceiveGamblerCard() {
        Player player = new Player();
        Cashier cashier = new Cashier(loggingAuthority);

        GamblerCard card = (GamblerCard) cashier.distributeGamblerCard();
        player.obtainGamblerCard(card);

        assertEquals("cards must be equal", player.getGamblerCard(), card);
        verify(loggingAuthority).logHandOutGamblingCard(card.getCardID());
    }


    /**
     * Player should be connected to one machine at a time. That is who call for bet is made.
     * Assume that it is a precaution.
     */
    @Test
    public void ShouldConnectToAGamingMachine() {
        Player player = new Player();
        GamblerCard card = mock(GamblerCard.class);
        GamingMachineID gamingMachineID = mock(GamingMachineID.class);
        GamingMachine gamingMachine = mock(GamingMachine.class);
        when(gamingMachine.getGamingMachineID()).thenReturn(gamingMachineID);

        player.obtainGamblerCard(card);
        player.selectGamingMachine(gamingMachine);

        assertEquals(player.selectedGamingMachineID(), gamingMachine.getGamingMachineID());
    }


    /**
     * Player should be able to update balance through cashier, and that
     * balance should reflect with the held gambler card.
     * @throws InvalidAmountException
     */
    @Test
    public void ShouldBeAbleToUpdateCardBalance() throws InvalidAmountException {
        Player player = new Player();
        // approaches cashier to get a card
        Cashier cashier = new Cashier(loggingAuthority);
        // hands out card
        GamblerCard gamblerCard = (GamblerCard) cashier.distributeGamblerCard();
        // obtain card
        player.obtainGamblerCard(gamblerCard);
        // PLAYER GIVES CASH TO THE CASHIER IN REAL LIFE
        cashier.addAmount(gamblerCard, new MoneyAmount(100));

        assertEquals(player.getAvailableAmount(), 100);
    }

}