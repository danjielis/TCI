package casino.cashier;


import casino.idfactory.BetID;
import casino.idfactory.CardID;
import casino.idfactory.IDFactory;

import java.util.HashSet;
import java.util.Set;


public class GamblerCard implements IGamblerCard {

    private CardID cardID;
    private Set<BetID> betIDSet=new HashSet<BetID>();

    public GamblerCard(CardID cardID) {
        this.cardID=cardID;
    }

    @Override
    public Set<BetID> returnBetIDs() {
        return betIDSet;
    }

    @Override
    public Set<BetID> returnBetIDsAndClearCard() {
        Set<BetID> temp=returnBetIDs();
        betIDSet.clear();
        return temp;
    }

    @Override
    public BetID generateNewBetID() {
        BetID newBeltID= (BetID) IDFactory.generateID("BeltID");
        betIDSet.add(newBeltID);
        return newBeltID;
    }

    @Override
    public int getNumberOfBetIDs() {
        return betIDSet.size();
    }

    @Override
    public CardID getCardID() {
        return cardID;
    }
}
