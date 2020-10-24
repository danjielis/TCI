package casino.idfactory;

import casino.bet.BetID;
import casino.cashier.CardID;
<<<<<<< HEAD
=======
import casino.game.BettingRoundID;
import casino.gamingmachine.GamingMachineID;
>>>>>>> 4902ac4408fe5d983c2107556504e354481a3b4d

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
<<<<<<< HEAD
        switch(idType)
        {
            case "CardID":
                return new CardID();
            case "BetID":
                return new BetID();
        }
        return null;
    };
=======
        switch (idType) {
            case "IDBet":
                return new BetID();
            case "IDBettingRound":
                return new BettingRoundID();
            case "IDCard":
                return new CardID();
            case "IDGamingMachine":
                return new GamingMachineID();
        }
        return null;
    }
>>>>>>> 4902ac4408fe5d983c2107556504e354481a3b4d





}
