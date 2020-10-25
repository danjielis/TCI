package casino.cashier;

import casino.idfactory.GeneralID;
import casino.idfactory.IDFactory;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;


public class CardIDTest {

    // STUDENT C - DANAS JUSYS

    private IDFactory testFactory;

    @Before
    public void setUp() {
        this.testFactory = new IDFactory();
    }
    //------------------------------------------------------------------


    /**
     * IDFactory should produce General ID instance of type CardID
     */
    @Test
    public void IDFactoryCanCreateCardID() {
        // Arrange - preconditions and inputs
        // Act - action on SUT
        GeneralID temp = testFactory.generateID("IDCard");

        // Assert - expecting desired outcome
        assertThat(temp, instanceOf(CardID.class));
    }


    /**
     * Produced ID cards should have different UUIDs, as intended in base class
     */
    @Test
    public void ShouldHaveUniqueIdentification() {
        // Arrange - preconditions and inputs
        // Act - action on SUT
        GeneralID temp = testFactory.generateID("IDCard");
        GeneralID temp1 = testFactory.generateID("IDCard");

        // Assert - expecting desired outcome
        assertNotEquals(temp.getUniqueID(), temp1.getUniqueID());
    }


    /**
     * Produced ID cards should have different TimeStamps, as intended in base class
     * @throws InterruptedException
     */
    @Test
    public void ShouldHaveDifferentTimestamps() throws InterruptedException {
        // Arrange - preconditions and inputs
        // Act - action on SUT
        GeneralID temp = testFactory.generateID("IDCard");
        Thread.sleep(100);
        GeneralID temp1 = testFactory.generateID("IDCard");

        // Assert - expecting desired outcome
        assertNotEquals(temp.getTimeStamp(), temp1.getTimeStamp());
    }

}