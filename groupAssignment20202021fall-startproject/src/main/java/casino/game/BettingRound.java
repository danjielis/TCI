package casino.game;

import casino.cashier.ICashier;
import gamblingauthoritiy.BetToken;
import gamblingauthoritiy.IBetLoggingAuthority;
import casino.bet.Bet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 */
public class BettingRound implements IBettingRound {

<<<<<<< HEAD
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

=======
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
>>>>>>> 4902ac4408fe5d983c2107556504e354481a3b4d
    }

    @Override
    public BettingRoundID getBettingRoundID() {
<<<<<<< HEAD
        return null;
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
        return null;
    }

    /**
     * @return betToken from this betting round.
     */
    @Override
    public BetToken getBetToken() {
        return null;
    }

    /**
     * @return number of bets made in the betting round
     */
    @Override
    public int numberOFBetsMade() {
        return 0;
=======
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
>>>>>>> 4902ac4408fe5d983c2107556504e354481a3b4d
    }
}
