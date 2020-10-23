package casino.game;

import casino.bet.Bet;
import casino.bet.BetResult;
import casino.bet.MoneyAmount;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GameRule implements IGameRule {
    /**
     * Determine the winner from a Set of Bets, using a given random win value;
     *
     * @param randomWinValue
     * @param bets
     * @return Betresult, containing the result for the winning bet.
     * @throws NoBetsMadeException when no bets have been made yet.
     */
    @Override
    public BetResult determineWinner(Integer randomWinValue, Set<Bet> bets) throws NoBetsMadeException {
        if (bets.isEmpty()) { throw new NoBetsMadeException(); }

        List<Bet> betList = new ArrayList<>();
        betList.addAll(bets);
        Integer sponge = randomWinValue;

        while (sponge > bets.size()) {
            sponge -= bets.size();
        }

        Bet bet = betList.get(sponge);

        return new BetResult(bet, new MoneyAmount(bet.getMoneyAmount().getAmountInCents() * 5));
    }

    /**
     * return the maximum number of bets which are used in the calculation of the winner.
     *
     * @return
     */
    @Override
    public int getMaxBetsPerRound() {
        return 5;
    }
}
