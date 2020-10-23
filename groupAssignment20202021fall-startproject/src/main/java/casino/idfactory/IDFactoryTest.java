package casino.idfactory;

import casino.bet.BetID;
import casino.cashier.CardID;
import casino.game.BettingRoundID;
import casino.gamingmachine.GamingMachineID;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import javax.smartcardio.Card;

import static org.junit.Assert.*;

public class IDFactoryTest {

    private IDFactory IDfactory;

    @Test
    public void ShouldCreateTypeBetID() {
        BetID betID = (BetID) IDfactory.generateID("betID");

        assertThat(betID, instanceOf(GeneralID.class));
    }

    @Test
    public void ShouldCreateTypeBettingRoundID() {
        BettingRoundID bettingRoundID = (BettingRoundID) IDfactory.generateID("bettingRoundID");

        assertThat(bettingRoundID, instanceOf(GeneralID.class));
    }

    @Test
    public void ShouldCreateTypeCardID() {
        CardID cardID = (CardID) IDfactory.generateID("cardID");

        assertThat(cardID, instanceOf(GeneralID.class));
    }

    @Test
    public void ShouldCreateTypeGamingMachineID() {
        GamingMachineID gamingMachineID = (GamingMachineID) IDfactory.generateID("gamingMachineID");

        assertThat(gamingMachineID, instanceOf(GeneralID.class));
    }

}