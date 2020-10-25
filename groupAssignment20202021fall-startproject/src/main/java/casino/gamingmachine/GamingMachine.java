package casino.gamingmachine;


import casino.bet.Bet;
import casino.bet.BetID;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;
import casino.cashier.BetNotExceptedException;
import casino.cashier.Cashier;
import casino.cashier.IGamblerCard;
import casino.cashier.InvalidAmountException;
import casino.game.BettingRound;
import casino.idfactory.IDFactory;

import java.util.ArrayList;
import java.util.List;

public class GamingMachine implements IGamingMachine {

    private IGamblerCard connectedCard;
    private Cashier cashier;
    private BettingRound bettingRound;

    private GamingMachineID gamingMachineID;
    private List<Bet> bets;

    public GamingMachine(Cashier cashier_local, BettingRound bettingRound) {
        this.cashier = cashier_local;
        this.bettingRound = bettingRound;
        this.gamingMachineID = (GamingMachineID) IDFactory.generateID("gamingMachineID");
        this.bets = new ArrayList<Bet>();
    }

    /**
     * try to place bet with given amount and connected card.
     * amount needs to be checked with the cashier
     * if accepted: generate a bet using the card and add it to the game.
     *
     * @param amountInCents amount in cents to gamble
     * @return true if bet is valid, excepted and added to betting round.
     * @throws NoPlayerCardException when no card is connected to this machine.
     */
    @Override
    public boolean placeBet(long amountInCents) throws NoPlayerCardException, BetNotExceptedException {
        if (this.getConnectedCard() == null) { throw new NoPlayerCardException(); }
        if (amountInCents < 1) { throw new BetNotExceptedException(); }

        Bet bet = new Bet((BetID) this.connectedCard.generateNewBetID(), new MoneyAmount(amountInCents));

        if (this.cashier.checkIfBetIsValid(this.connectedCard, bet)) {
            if (this.bettingRound.placeBet(bet)) {
                bets.add(bet);
                return true;
            }
        }

        return false;
    }

    /**
     * Accept the BetResult from the winner. Clear all open bets on this machine.
     * When the winner has made his bet on this machine: let the Cashier update
     * the amount of the winner.
     *
     * @param winResult result of a betting round. can be null when there is no winner.
     */
    @Override
    public void acceptWinner(BetResult winResult) throws InvalidAmountException {
        for(Bet b:bets) {
            if (winResult.getWinningBet().getBetID() == b.getBetID())
                this.cashier.addAmount(this.connectedCard, winResult.getAmountWon());
        }
        bets.clear();
    }

    /**
     * getter
     *
     * @return gamingmachineID
     */
    @Override
    public GamingMachineID getGamingMachineID() {

        return this.gamingMachineID;
    }

    /**
     * connect card to this gaming machine
     *
     * @param card card to connect
     */
    @Override
    public void connectCard(IGamblerCard card) {

        this.connectedCard = card;
    }

    /**
     * disconnects/removes card from this gaming machine.
     *
     * @throws CurrentBetMadeException when open bets made with this card
     *                                 are still present in the current betting round and
     */
    @Override
    public void disconnectCard() throws CurrentBetMadeException {
        if (!bets.isEmpty()) { throw new CurrentBetMadeException(); }

        this.connectedCard = null;
    }

    public IGamblerCard getConnectedCard() {
        return this.connectedCard;
    }

    public List<Bet> getBets() { return this.bets; }
}
