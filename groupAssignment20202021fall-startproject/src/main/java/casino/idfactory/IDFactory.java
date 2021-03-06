package casino.idfactory;

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






}
