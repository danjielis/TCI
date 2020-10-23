package casino.game;

import casino.cashier.ICashier;
import casino.idfactory.BettingRoundID;
import gamblingauthoritiy.BetToken;
import casino.bet.Bet;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class BettingRound implements IBettingRound {
    private BettingRoundID bettingRoundID; //Casino
    private BetToken betToken; //Gambling athority
    private ICashier cashier;
    private Set<Bet> SetOfBets;

    @Override
    public BettingRoundID getBettingRoundID() {
        return null;
    }

    public BettingRound(BettingRoundID bettingRoundID, BetToken betToken, ICashier cashier) {
        this.bettingRoundID = bettingRoundID;
        this.betToken = betToken;
        this.cashier = cashier;
        SetOfBets = new HashSet<Bet>();
    }

    @Override
    public boolean placeBet(Bet bet) throws IllegalArgumentException {
        return false;
    }

    @Override
    public Set<Bet> getAllBetsMade() {
        return null;
    }

    @Override
    public BetToken getBetToken() {
        return null;
    }

    @Override
    public int numberOFBetsMade() {
        return 0;
    }
}
