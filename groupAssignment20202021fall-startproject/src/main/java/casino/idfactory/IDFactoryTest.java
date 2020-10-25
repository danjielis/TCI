package casino.idfactory;

import casino.bet.Bet;
import casino.bet.BetID;
import casino.cashier.CardID;
import casino.cashier.Cashier;
import casino.game.BettingRound;
import casino.game.BettingRoundID;
import casino.gamingmachine.GamingMachine;
import casino.gamingmachine.GamingMachineID;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class IDFactoryTest {

    // STUDENT C - DANAS JUSYS

    private IDFactory IDfactory;
    private Cashier cashier;
    private BettingRound bettingRound;

    @Before
    public void setUp() {
        this.IDfactory = new IDFactory();
        this.cashier = mock(Cashier.class);
        this.bettingRound = mock(BettingRound.class);
    }
    //------------------------------------------------------------------


    /**
     * Should be capable of generating type BetID
     */
    @Test
    public void ShouldCreateTypeBetID() {
        // Arrange - preconditions and inputs
        // Act - action on SUT
        BetID betID = (BetID) IDfactory.generateID("IDBet");

        // Assert - expecting desired outcome
        assertThat(betID, instanceOf(GeneralID.class));
    }


    /**
     * Should be capable of generating type BettingRoundID
     */
    @Test
    public void ShouldCreateTypeBettingRoundID() {
        // Arrange - preconditions and inputs
        // Act - action on SUT
        BettingRoundID bettingRoundID = (BettingRoundID) IDfactory.generateID("IDBettingRound");

        // Assert - expecting desired outcome
        assertThat(bettingRoundID, instanceOf(GeneralID.class));
    }


    /**
     * Should be capable of generating type CardID
     */
    @Test
    public void ShouldCreateTypeCardID() {
        // Arrange - preconditions and inputs
        // Act - action on SUT
        CardID cardID = (CardID) IDfactory.generateID("IDCard");

        // Assert - expecting desired outcome
        assertThat(cardID, instanceOf(GeneralID.class));
    }


    /**
     * Should be capable of generating type GamingMachineID
     */
    @Test
    public void ShouldCreateTypeGamingMachineID() {
        // Arrange - preconditions and inputs
        // Act - action on SUT
        GamingMachineID gamingMachineID = (GamingMachineID) IDfactory.generateID("IDGamingMachine");

        // Assert - expecting desired outcome
        assertThat(gamingMachineID, instanceOf(GeneralID.class));
    }

}
