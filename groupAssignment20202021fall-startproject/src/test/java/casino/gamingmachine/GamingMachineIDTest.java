package casino.gamingmachine;

import casino.idfactory.GamingMachineID;
import casino.idfactory.GeneralID;
import casino.idfactory.IDFactory;
import org.junit.Test;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;

public class GamingMachineIDTest {

    @Test
    public void FactoryCreateBettingRoundID(){
        GeneralID gamingMachineIDIDInstance= IDFactory.generateID("IDGamingMachine");
        assertThat(gamingMachineIDIDInstance, instanceOf(GamingMachineID.class));
    }

    @Test
    public void FactoryShouldCreateUniqueBettingRoundID(){
        GeneralID id1= IDFactory.generateID("IDGamingMachine");
        GeneralID id2= IDFactory.generateID("IDGamingMachine");
        assertNotEquals(id1.getUniqueID(),id2.getUniqueID());
    }

    @Test
    public void FactoryShouldCreateUniqueTimeStamp() throws InterruptedException {
        GeneralID id1= IDFactory.generateID("IDGamingMachine");
        Thread.sleep(100);
        GeneralID id2= IDFactory.generateID("IDGamingMachine");
        assertNotEquals(id1.getTimeStamp(),id2.getTimeStamp());
    }
}
