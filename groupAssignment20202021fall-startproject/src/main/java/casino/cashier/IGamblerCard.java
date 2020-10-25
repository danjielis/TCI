package casino.cashier;

import casino.idfactory.BetID;
import casino.bet.MoneyAmount;
import casino.idfactory.CardID;


import java.util.Set;

public interface IGamblerCard {
    /**
     * returns all generated betID's by this card
     * @return a copied set of betID's generated by this card.
     */
    Set<BetID> returnBetIDs();

    /**
     * returns all generated betID's by this card, and clears all betID's from the card.
     * @return a copied set of betID's generated by this card.
     */
    Set<BetID> returnBetIDsAndClearCard();

    /**
     * The card generates a unique betID for every bet made by the gambler on the machine.
     * A list of all generated betID’s is also stored on the card. BetID’s also contain a timestamp.
     *
     * @return
     */
    BetID generateNewBetID();

    /**
     * return number of betID's generated on this card.
     * @return
     */
    int getNumberOfBetIDs();

    CardID getCardID();

    void setBalance(MoneyAmount amount);
    MoneyAmount getBalance();
}
