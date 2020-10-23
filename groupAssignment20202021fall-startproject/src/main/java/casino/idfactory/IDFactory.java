package casino.idfactory;

import casino.bet.BetID;
import casino.cashier.CardID;
import casino.game.BettingRoundID;
import casino.gamingmachine.GamingMachineID;

/**
 * Factory for creation of GeneralID objects.
 * creation of the right object is done by specifying the type to create as a string
 * the specified type is case insensitive
 *
 * possible types:
 * betID
 * bettingroundID
 * cardID
 * gamingMachineID
 *
 * when the type is not present, null is returned.
 */
public class IDFactory {
  //

    /**
     * generate the right generalID instance by specifying the type as a string
     * @param idType is name of the type in capitals.
     * @return an instance of the correct GeneralID object type, or null otherwise.
     */
    public static GeneralID generateID(String idType){
        switch (idType) {
            case "betID":
                return new BetID();
            case "bettingRoundID":
                return new BettingRoundID();
            case "cardID":
                return new CardID();
            case "gamingMachineID":
                return new GamingMachineID();
        }
        return null;
    };





}
