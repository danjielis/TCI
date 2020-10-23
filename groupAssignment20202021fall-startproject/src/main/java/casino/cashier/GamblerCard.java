package casino.cashier;


import casino.bet.BetID;

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
        return null;
    }

    @Override
    public int getNumberOfBetIDs() {
        return 0;
    }

    @Override
    public CardID getCardID() {
        return null;
    }
}
