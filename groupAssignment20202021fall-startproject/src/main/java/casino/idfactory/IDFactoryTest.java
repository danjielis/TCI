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
        BetID betID = (BetID) IDfactory.generateID("IDBet");

        assertThat(betID, instanceOf(GeneralID.class));
    }

    @Test
    public void ShouldCreateTypeBettingRoundID() {
        BettingRoundID bettingRoundID = (BettingRoundID) IDfactory.generateID("IDBettingRound");

        assertThat(bettingRoundID, instanceOf(GeneralID.class));
    }

    @Test
    public void ShouldCreateTypeCardID() {
        CardID cardID = (CardID) IDfactory.generateID("IDCard");

        assertThat(cardID, instanceOf(GeneralID.class));
    }

    @Test
    public void ShouldCreateTypeGamingMachineID() {
        GamingMachineID gamingMachineID = (GamingMachineID) IDfactory.generateID("IDGamingMachine");

        assertThat(gamingMachineID, instanceOf(GeneralID.class));
    }

}