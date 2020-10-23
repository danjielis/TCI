package casino.cashier;

import casino.idfactory.GeneralID;
import casino.idfactory.IDFactory;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.instanceOf;

public class CardIDTest {

    @Test
    public void IDFactoryCanCreateCardID() {
        IDFactory testFactory = new IDFactory();

        GeneralID temp = testFactory.generateID("IDCard");

        assertThat(temp, instanceOf(CardID.class));
    }

    @Test
    public void ShouldHaveUniqueIdentification() {
        IDFactory testFactory = new IDFactory();

        GeneralID temp = testFactory.generateID("IDCard");
        GeneralID temp1 = testFactory.generateID("IDCard");

        assertNotEquals(temp.getUniqueID(), temp1.getUniqueID());
    }

    @Test
    public void ShouldHaveDifferentTimestamps() throws InterruptedException {
        IDFactory testFactory = new IDFactory();

        GeneralID temp = testFactory.generateID("IDCard");
        Thread.sleep(1000);
        GeneralID temp1 = testFactory.generateID("IDCard");

        assertNotEquals(temp.getTimeStamp(), temp1.getTimeStamp());
    }

}