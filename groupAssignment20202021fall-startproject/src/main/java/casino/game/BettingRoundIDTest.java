package casino.game;

import casino.idfactory.GeneralID;
import casino.idfactory.IDFactory;
import org.junit.Test;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class BettingRoundIDTest {
    @Test
    public void FactoryCreateBettingRoundID(){
        GeneralID bettingRoundIDInstance= IDFactory.generateID("bettingRoundID");
        assertThat(bettingRoundIDInstance, instanceOf(BettingRoundID.class));
    }

    @Test
    public void FactoryShouldCreateUniqueBettingRoundID(){
        GeneralID id1= IDFactory.generateID("bettingRoundID");
        GeneralID id2= IDFactory.generateID("bettingRoundID");
        assertNotEquals(id1.getUniqueID(),id2.getUniqueID());
    }

    @Test
    public void FactoryShouldCreateUniqueTimeStamp() throws InterruptedException {
        GeneralID id1= IDFactory.generateID("bettingRoundID");
        Thread.sleep(100);
        GeneralID id2= IDFactory.generateID("bettingRoundID");
        assertNotEquals(id1.getTimeStamp(),id2.getTimeStamp());
    }
}
