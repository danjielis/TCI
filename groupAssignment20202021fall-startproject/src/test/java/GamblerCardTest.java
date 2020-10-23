import casino.idfactory.BetID;
import casino.idfactory.CardID;
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

    private CardID cardID= (CardID) IDFactory.generateID("cardID");
    private IGamblerCard gamblerCard=new GamblerCard(cardID);
    private Set<BetID> setOfBetID=new HashSet<BetID>();


    /**
     * GamblerCard can return set of BetID
     * returnBetIDs Method
     */
    @Test
    public void GamblerCardCanReturnSetOfBetID(){
        BetID betID= gamblerCard.generateNewBetID();
        setOfBetID.add(betID);
        //assert
        assertThat(setOfBetID,is(gamblerCard.returnBetIDs()));
    }

    /**
     *GamblerCard can return all betIDs associated to the card and clears all betIDs
     * getNumberOfBetIDs Method
     */
    @Test
    public void GamblerReturnBetIDAndClearCard(){
        gamblerCard.returnBetIDsAndClearCard();
        //assert
        assertEquals(0,gamblerCard.getNumberOfBetIDs());
    }

    /**
     * GamblerCard will generate new bet ID and add it to set of BetID
     * generateNewBetID Method
     */
    @Test
    public void GenerateNewBetID(){
        BetID betID=gamblerCard.generateNewBetID();
        //assert
        assertTrue(gamblerCard.returnBetIDs().contains(betID));
    }

    /**
     *Return the number of Bet id in the gambler card
     * getNumberOfBetIDs Method
     */
    public void ReturnNumberOfBetID(){
        assertThat(gamblerCard.getNumberOfBetIDs(),equalTo(0));
    }

    /**
     *return the gambler card id
     * getCardID Method
     */
    @Test
    public void GetGamblerCardID(){
        assertThat(gamblerCard.getCardID(),equalTo(cardID));
    }
}