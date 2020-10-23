package casino.game;
import casino.bet.Bet;
import casino.bet.BetResult;
import casino.cashier.Cashier;
import casino.cashier.ICashier;
import casino.gamingmachine.IGamingMachine;
import casino.idfactory.BettingRoundID;
import casino.idfactory.IDFactory;
import gamblingauthoritiy.BetToken;
import gamblingauthoritiy.BetTokenAuthority;
import gamblingauthoritiy.IBetLoggingAuthority;
import gamblingauthoritiy.IBetTokenAuthority;

public class DefaultGame extends AbstractGame {

    private IBettingRound BettingRound;
    private IBetLoggingAuthority loggingAuthority;
    private IBetTokenAuthority tokenAuthority;
    private GameRule gameRule;
    private ICashier cashier;

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
        BetToken betToken=new BetToken(bettingRoundID);
        BettingRound=new BettingRound(bettingRoundID,betToken,cashier);

        loggingAuthority.logStartBettingRound(BettingRound);

    }

    @Override
    public boolean acceptBet(Bet bet, IGamingMachine gamingMachine) throws NoCurrentRoundException {
        if (BettingRound==null){
            throw  new NoCurrentRoundException();
        }
        return true;
    }

    @Override
    public void determineWinner()throws NoBetsMadeException {
        BetToken token = this.BettingRound.getBetToken();
        Integer random = this.tokenAuthority.getRandomInteger(token);
        BetResult result = this.gameRule.determineWinner(random, this.BettingRound.getAllBetsMade());
        this.loggingAuthority.logEndBettingRound(this.BettingRound, result);
        this.BettingRound = null;
    }

    @Override
    public boolean isBettingRoundFinished() {
        if (this.BettingRound.numberOFBetsMade() == this.gameRule.getMaxBetsPerRound()) {
            return true;
        }

        return false;
    }
}
