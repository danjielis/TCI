package person;

import casino.cashier.GamblerCard;
import casino.cashier.IGamblerCard;

public class Player {
    private IGamblerCard connectedCard;

    public void obtainGamblerCard(GamblerCard card) {
        this.connectedCard = card;
    }

    public IGamblerCard getGamblerCard() {
        return this.connectedCard;
    }
}
