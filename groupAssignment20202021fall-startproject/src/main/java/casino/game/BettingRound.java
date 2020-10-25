package casino.game;

import casino.cashier.ICashier;
import casino.idfactory.BettingRoundID;
import gamblingauthoritiy.BetToken;
import casino.bet.Bet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 *
 */
public class BettingRound implements IBettingRound {

    private BettingRoundID bettingRoundID;
    private BetToken betToken;
    private final Set<Bet> bets= new Set<Bet>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<Bet> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(Bet bet) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Bet> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }
    };
    public BettingRound()
    {
    }

    public BettingRound(BettingRoundID bettingRoundID, BetToken betToken) {
        this.bettingRoundID = bettingRoundID;
        this.betToken = betToken;
    }

    @Override
    public BettingRoundID getBettingRoundID() {
        return bettingRoundID;
    }

    /**
     * add a bet to the current bettinground.
     * <p>
     * <p>
     * Note: also use the appropiate required methods from the gambling authority API
     *
     * @param bet
     * @return true if bet is made, otherwise folse
     * @throws IllegalArgumentException when Bet is null
     */
    @Override
    public boolean placeBet(Bet bet) throws IllegalArgumentException {
        if (bet == null)
        {
            throw new IllegalArgumentException();
        }
        bets.add(bet);
        return true;
    }

    /**
     * @return set of all bets made in this betting round.
     */
    @Override
    public Set<Bet> getAllBetsMade() {
        return bets;
    }

    /**
     * @return betToken from this betting round.
     */
    @Override
    public BetToken getBetToken() {
        return betToken;
    }

    /**
     * @return number of bets made in the betting round
     */
    @Override
    public int numberOFBetsMade() {
        return 0;

    }
}
