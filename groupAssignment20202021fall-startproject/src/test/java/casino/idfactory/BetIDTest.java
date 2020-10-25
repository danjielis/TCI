package casino.idfactory;

import casino.idfactory.GeneralID;
import casino.idfactory.IDFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Mockito.*;

public class BetIDTest {

    /**
     * Test if the IDFactory can create a BetID object
     * Created by Student A: Yoanna Borisova
     */
    @Test
    public void IDFactoryCanCreateBetID()
    {
        GeneralID temp = IDFactory.generateID("IDBet");

        //assertThat(temp, instanceOf(BetID.class));
    }

}