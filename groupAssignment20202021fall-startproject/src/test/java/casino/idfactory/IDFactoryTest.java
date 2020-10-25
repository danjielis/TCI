package casino.idfactory;

import casino.cashier.Cashier;
import casino.game.BettingRound;
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


    /**
     * All created types must have a unique ID.
     */
    @Test
    public void ShouldCreateTypeInstancesWithUniqueIDs() {
        // Arrange - preconditions and inputs
        // Act - action on SUT
        BetID betID = (BetID) IDfactory.generateID("IDBet");
        BettingRoundID bettingRoundID = (BettingRoundID) IDfactory.generateID("IDBettingRound");
        CardID cardID = (CardID) IDfactory.generateID("IDCard");
        GamingMachineID gamingMachineID = (GamingMachineID) IDfactory.generateID("IDGamingMachine");

        // Assert - expecting desired outcome
        assertTrue("type IDs must be unique",
                (betID.getUniqueID() != bettingRoundID.getUniqueID()) &&
                        (betID.getUniqueID() != cardID.getUniqueID()) &&
                        (betID.getUniqueID() != gamingMachineID.getUniqueID()) &&
                        (bettingRoundID.getUniqueID() != cardID.getUniqueID()) &&
                        (bettingRoundID.getUniqueID() != gamingMachineID.getUniqueID()) &&
                        (cardID.getUniqueID() != gamingMachineID.getUniqueID())
                );
    }


    /**
     * All created types must have a different TimeStamp.
     */
    @Test
    public void ShouldCreateTypeInstancesWithDifferentTimeStamps() throws InterruptedException {
        // Arrange - preconditions and inputs
        // Act - action on SUT
        BetID betID = (BetID) IDfactory.generateID("IDBet");
        Thread.sleep(100);
        BettingRoundID bettingRoundID = (BettingRoundID) IDfactory.generateID("IDBettingRound");
        Thread.sleep(100);
        CardID cardID = (CardID) IDfactory.generateID("IDCard");
        Thread.sleep(100);
        GamingMachineID gamingMachineID = (GamingMachineID) IDfactory.generateID("IDGamingMachine");

        // Assert - expecting desired outcome
        assertTrue("type timeStamps must be different",
                (betID.getTimeStamp() != bettingRoundID.getTimeStamp()) &&
                        (betID.getTimeStamp() != cardID.getTimeStamp()) &&
                        (betID.getTimeStamp() != gamingMachineID.getTimeStamp()) &&
                        (bettingRoundID.getTimeStamp() != cardID.getTimeStamp()) &&
                        (bettingRoundID.getTimeStamp() != gamingMachineID.getTimeStamp()) &&
                        (cardID.getTimeStamp() != gamingMachineID.getTimeStamp())
        );
    }


    /**
     * As specified, if type is unsupported should return null
     */
    @Test
    public void ShouldReturnNullIfSpecifiedTypeIsInvalid() {
        // Arrange - preconditions and inputs
        // Act - action on SUT
        GeneralID invalid_id = IDFactory.generateID("invalid_id");

        // Assert - expecting desired outcome
        assertNull(invalid_id);
    }
}
