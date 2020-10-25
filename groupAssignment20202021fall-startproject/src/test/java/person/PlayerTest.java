package person;

import casino.bet.MoneyAmount;
import casino.cashier.BetNotExceptedException;
import casino.cashier.Cashier;
import casino.cashier.GamblerCard;
import casino.cashier.InvalidAmountException;
import casino.game.BettingRound;
import casino.game.DefaultGame;
import casino.game.GameRule;
import casino.gamingmachine.CurrentBetMadeException;
import casino.gamingmachine.GamingMachine;
import casino.gamingmachine.NoPlayerCardException;
import casino.idfactory.BettingRoundID;
import casino.gamingmachine.GamingMachineID;
import casino.idfactory.IDFactory;
import gamblingauthoritiy.BetToken;
import gamblingauthoritiy.IBetLoggingAuthority;
import gamblingauthoritiy.IBetTokenAuthority;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class PlayerTest {

    private IBetLoggingAuthority loggingAuthority;
    private IBetTokenAuthority tokenAuthority;

    @Before
    public void setUp() {
        this.loggingAuthority = mock(IBetLoggingAuthority.class);
        this.tokenAuthority = mock(IBetTokenAuthority.class);
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



    @Test
    public void ShouldBeAbleToPlaceABet() throws InvalidAmountException, NoPlayerCardException, BetNotExceptedException {
        Player player = new Player();
        // approaches cashier to get a card
        Cashier cashier = new Cashier(loggingAuthority);
        // hands out card
        GamblerCard gamblerCard = (GamblerCard) cashier.distributeGamblerCard();
        // obtain card
        player.obtainGamblerCard(gamblerCard);
        // PLAYER GIVES CASH TO THE CASHIER IN REAL LIFE
        cashier.addAmount(gamblerCard, new MoneyAmount(100));
        // player approaches gaming machine and connects

        // quite few things must happen
        GameRule gameRule = new GameRule();
        DefaultGame defaultGame = new DefaultGame(gameRule, loggingAuthority, tokenAuthority);
        BettingRoundID bettingRoundID = (BettingRoundID) IDFactory.generateID("IDBettingRound");
        BetToken betToken = mock(BetToken.class);
        BettingRound bettingRound = new BettingRound(bettingRoundID, betToken);
        GamingMachine gamingMachine = new GamingMachine(cashier, bettingRound);

        // player connects to a machine
        gamingMachine.connectCard(gamblerCard);

        boolean confirmation = gamingMachine.placeBet(50);

        assertTrue("response must be true", confirmation);
    }


    @Test(expected = CurrentBetMadeException.class)
    public void ShouldNotDisconnectCardWithActiveBet() throws InvalidAmountException, NoPlayerCardException, BetNotExceptedException, CurrentBetMadeException {
        Player player = new Player();
        // approaches cashier to get a card
        Cashier cashier = new Cashier(loggingAuthority);
        // hands out card
        GamblerCard gamblerCard = (GamblerCard) cashier.distributeGamblerCard();
        // obtain card
        player.obtainGamblerCard(gamblerCard);
        // PLAYER GIVES CASH TO THE CASHIER IN REAL LIFE
        cashier.addAmount(gamblerCard, new MoneyAmount(100));
        // player approaches gaming machine and connects

        // quite few things must happen
        GameRule gameRule = new GameRule();
        DefaultGame defaultGame = new DefaultGame(gameRule, loggingAuthority, tokenAuthority);
        BettingRoundID bettingRoundID = (BettingRoundID) IDFactory.generateID("IDBettingRound");
        BetToken betToken = mock(BetToken.class);
        BettingRound bettingRound = new BettingRound(bettingRoundID, betToken);
        GamingMachine gamingMachine = new GamingMachine(cashier, bettingRound);

        // player connects to a machine
        gamingMachine.connectCard(gamblerCard);

        boolean confirmation = gamingMachine.placeBet(50);

        gamingMachine.disconnectCard();
    }

}