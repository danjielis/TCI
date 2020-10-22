import casino.bet.BetID;
import casino.cashier.GamblerCard;
import casino.cashier.IGamblerCard;
import casino.idfactory.IDFactory;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import java.util.HashSet;
import java.util.Set;

public class GamblerCardTest {

    private CardID cardID= IDFactory.generateID("CardID");
    private IGamblerCard gamblerCard=new GamblerCard();
    private Set<BetID> setOfBetID=new HashSet<BetID>();


    /**
     * GamblerCard can return set of BetID
     */
    @Test
    public void GamblerCardCanReturnSetOfBetID(){
        BetID betID=gamblerCard.generateNewBetID();
        setOfBetID.add(betID);
        //assert
        assertThat(setOfBetID,is(gamblerCard.returnBetIDs()));
    }

    /**
     *GamblerCard can return all betIDs associated to the card and clears all betIDs
     */
    @Test
    public void GamblerReturnBetIDAndClearCard(){
        gamblerCard.returnBetIDsAndClearCard();
        //assert
        assertEquals(0,gamblerCard.getNumberOfBetIDs());
    }

    /**
     * GamblerCard will generate new bet ID and add it to set of BetID
     */
    @Test
    public void GenerateNewBetID(){
        BetID betID=gamblerCard.generateNewBetID();
        //assert
        assertTrue(gamblerCard.returnBetIDs().contains(betID));
    }

    /**
     *Return the number of Bet id in the gambler card
     */
    public void ReturnNumberOfBetID(){
        assertThat(gamblerCard.getNumberOfBetIDs(),equalTo(0));
    }

    /**
     *return the gambler card id
     */
    @Test
    public void GetGamblerCardID(){
        assertThat(gamblerCard.getCardID(),equalTo(cardID));
    }
}
