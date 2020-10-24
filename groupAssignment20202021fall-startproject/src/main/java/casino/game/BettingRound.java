package casino.game;

import casino.cashier.ICashier;
import gamblingauthoritiy.BetToken;
import gamblingauthoritiy.IBetLoggingAuthority;
import casino.bet.Bet;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class BettingRound implements IBettingRound {

    private BettingRoundID bettingRoundID;
    private BetToken betToken;
    private ICashier cashier;
    private Set<Bet> SetOfBets;

    public BettingRound(BettingRoundID bettingRoundID, BetToken betToken, ICashier cashier) {
        this.bettingRoundID = bettingRoundID;
        this.betToken = betToken;
        this.cashier = cashier;
        SetOfBets = new HashSet<Bet>();
    }

    public void setListOfBetsMadeByTheRound(Set<Bet> listOfBetsMadeByTheRound) {
        this.SetOfBets = listOfBetsMadeByTheRound;
    }

    @Override
    public BettingRoundID getBettingRoundID() {
        return bettingRoundID;
    }

    @Override
    public boolean placeBet(Bet bet) {
        if (bet.getMoneyAmount().getAmountInCents() > 0) {
            SetOfBets.add(bet);
            return true;
        }
        return false;
    }

    @Override
    public Set<Bet> getAllBetsMade() {
        return SetOfBets;
    }

    @Override
    public BetToken getBetToken() {
        return betToken;
    }

    @Override
    public int numberOFBetsMade() {
        return SetOfBets.size();
    }
}
