package casino.cashier;

import casino.idfactory.GeneralID;
import casino.idfactory.IDFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

public class CardIDTest {

    private IDFactory testFactory;

    @Before
    public void setUp() {
        this.testFactory = new IDFactory();
    }


    @Test
    public void IDFactoryCanCreateCardID() {
        GeneralID temp = testFactory.generateID("IDCard");

        assertThat(temp, instanceOf(CardID.class));
    }

    @Test
    public void ShouldHaveUniqueIdentification() {
        GeneralID temp = testFactory.generateID("IDCard");
        GeneralID temp1 = testFactory.generateID("IDCard");

        assertNotEquals(temp.getUniqueID(), temp1.getUniqueID());
    }

    @Test
    public void ShouldHaveDifferentTimestamps() throws InterruptedException {
        GeneralID temp = testFactory.generateID("IDCard");
        Thread.sleep(1000);
        GeneralID temp1 = testFactory.generateID("IDCard");

        assertNotEquals(temp.getTimeStamp(), temp1.getTimeStamp());
    }

}