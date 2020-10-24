package casino.game;


import gamblingauthoritiy.IBetLoggingAuthority;
import gamblingauthoritiy.IBetTokenAuthority;

abstract class AbstractGame implements IGame{

    protected IBetTokenAuthority tokenAuthority;
    protected IBetLoggingAuthority loggingAuthority;

    public AbstractGame(IBetLoggingAuthority loggingAuthority, IBetTokenAuthority tokenAuthority)
    {
        this.loggingAuthority = loggingAuthority;
        this.tokenAuthority = tokenAuthority;
    }

    public AbstractGame() {

    }
}
