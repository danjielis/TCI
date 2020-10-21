package casino.bet;

import casino.idfactory.GeneralID;
import casino.idfactory.IDFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Mockito.*;

public class BetIDTest {

    @Test
    public void IDFactoryCanCreateBetID()
    {
        IDFactory testFactory = new IDFactory();

        GeneralID temp = testFactory.generateID("BetID");

        Assert.assertThat(temp, instanceOf(BetID.class));
    }

}