package casino.game;


import gamblingauthoritiy.IBetLoggingAuthority;
import gamblingauthoritiy.IBetTokenAuthority;

abstract class AbstractGame implements IGame{

    protected IBetTokenAuthority betTokenAuthority;
    protected IBetLoggingAuthority betLoggingAuthority;

    public AbstractGame(IBetLoggingAuthority loggingAuthority, IBetTokenAuthority tokenAuthority)
    {
        betLoggingAuthority = loggingAuthority;
        betTokenAuthority = tokenAuthority;
    }
}
