package casino.game;
import casino.bet.Bet;
import casino.bet.BetResult;
import casino.cashier.BetNotExceptedException;
import casino.cashier.Cashier;
import casino.cashier.ICashier;
import casino.gamingmachine.IGamingMachine;
import casino.game.*;
import casino.idfactory.IDFactory;
import gamblingauthoritiy.BetToken;
import gamblingauthoritiy.BetTokenAuthority;
import gamblingauthoritiy.IBetLoggingAuthority;
import gamblingauthoritiy.IBetTokenAuthority;

public class DefaultGame extends AbstractGame {

<<<<<<< HEAD
import casino.bet.Bet;
import casino.gamingmachine.IGamingMachine;
import gamblingauthoritiy.IBetLoggingAuthority;
import gamblingauthoritiy.IBetTokenAuthority;
=======
    private IBettingRound BettingRound;
    private IBetLoggingAuthority loggingAuthority;
    private IBetTokenAuthority tokenAuthority;
    private BetToken betToken;
    private GameRule gameRule;
    private ICashier cashier;
>>>>>>> 4902ac4408fe5d983c2107556504e354481a3b4d

    public DefaultGame(GameRule gameRule,IBetLoggingAuthority loggingAuthority, IBetTokenAuthority tokenAuthority) {
        this.loggingAuthority=loggingAuthority;
        this.tokenAuthority=tokenAuthority;
        this.gameRule=gameRule;
        cashier=new Cashier(loggingAuthority);
    }

    @Override
    public void startBettingRound() {
        BettingRoundID bettingRoundID = (BettingRoundID) IDFactory.generateID("bettingRoundID");
        tokenAuthority=new BetTokenAuthority();
        betToken=new BetToken(bettingRoundID);
        BettingRound=new BettingRound(bettingRoundID,betToken,cashier);

        loggingAuthority.logStartBettingRound(BettingRound);

    }

    @Override
    public boolean acceptBet(Bet bet, IGamingMachine gamingMachine) throws NoCurrentRoundException {
        if (BettingRound==null){
            throw new NoCurrentRoundException();
        }

        return true;
    }



    @Override
    public void determineWinner()throws NoBetsMadeException {
        BettingRoundID bettingRoundID = (BettingRoundID) IDFactory.generateID("bettingRoundID");
        BettingRound=new BettingRound(bettingRoundID,betToken,cashier);
        BetToken token = this.BettingRound.getBetToken();
        Integer random = this.tokenAuthority.getRandomInteger(token);
        BetResult result = this.gameRule.determineWinner(random, this.BettingRound.getAllBetsMade());
        this.loggingAuthority.logEndBettingRound(this.BettingRound, result);
        this.BettingRound = null;
    }

    @Override
    public boolean isBettingRoundFinished() throws NoBetsMadeException, BetNotExceptedException {
        //int random=tokenAuthority.getRandomInteger(betToken);
        if (this.BettingRound.numberOFBetsMade() == this.gameRule.getMaxBetsPerRound()) {
            //loggingAuthority.logEndBettingRound(BettingRound,gameRule.determineWinner(random,BettingRound.getAllBetsMade()));
            return true;
        }

<<<<<<< HEAD
    public DefaultGame(IBetLoggingAuthority loggingAuthority, IBetTokenAuthority tokenAuthority) {
        super(loggingAuthority, tokenAuthority);
    }

    /**
     * create and start a new BettingRound.
     * when called when a current bettinground is active: the current bettinground ends and a new
     * bettinground is created, which becomes the current bettinground.
     * <p>
     * Note: also use the appropiate required methods from the gambling authority API
     */
    @Override
    public void startBettingRound() {

    }

    /**
     * Accept a bet on the current betting round.
     * determine if the betting round is finished, if so: determine the winner,
     * notify the connected gaming machines and start a new betting round.
     * <p>
     * Note: also use the appropiate required methods from the gambling authority API
     *
     * @param bet           the bet to be made on the betting round
     * @param gamingMachine gamingmachine which places bet on this game.
     * @return true when bet is accepted by the game, otherwise false.
     * @throws NoCurrentRoundException when no BettingRound is currently active.
     */
    @Override
    public boolean acceptBet(Bet bet, IGamingMachine gamingMachine) throws NoCurrentRoundException {
        return false;
    }

    /**
     * End the current bettinground & calculate the winner using the gamerules.
     * notifiy all connected game machines of the BetResult.
     * When no bets have been made yet, no winner can be determined. In this case, only log to the betlogging authority,
     * and end the current bettinground.
     * <p>
     * Note: also use the appropiate required methods from the gambling authority API
     */
    @Override
    public void determineWinner() {

    }

    /**
     * determine if the right number of bets are done (determined by gamerules) to be able to
     * calculate a winner.
     * for calculation a winner, a true random value needs to be received from the gambling authority API.
     * Note: also use the appropiate required methods from the gambling authority API
     *
     * @return true if all necessary bets are made in the betting round, otherwise false
     */
    @Override
    public boolean isBettingRoundFinished() {
        return false;
    }
}
=======
        return false;
    }
    public void setBettingRound(IBettingRound bettingRound) {
        BettingRound = bettingRound;
    }
    public IBettingRound getBettingRound() {
        return BettingRound;
    }
}
>>>>>>> 4902ac4408fe5d983c2107556504e354481a3b4d
