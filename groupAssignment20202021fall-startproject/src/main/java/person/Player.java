package person;

import casino.cashier.GamblerCard;
import casino.cashier.IGamblerCard;
import casino.gamingmachine.GamingMachine;
import casino.gamingmachine.IGamingMachine;
import casino.idfactory.GamingMachineID;

public class Player {
    private IGamblerCard connectedCard;
    private GamingMachine gamingMachine;

    public void obtainGamblerCard(GamblerCard card) {
        this.connectedCard = card;
    }

    public IGamblerCard getGamblerCard() {
        return this.connectedCard;
    }

    public void selectGamingMachine(GamingMachine gamingMachine) {
        this.gamingMachine = gamingMachine;
    }

    public GamingMachineID selectedGamingMachineID() {
        return this.gamingMachine.getGamingMachineID();
    }
}
