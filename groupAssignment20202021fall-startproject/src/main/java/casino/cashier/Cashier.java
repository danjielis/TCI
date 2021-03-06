package casino.cashier;

import casino.bet.Bet;
import casino.bet.MoneyAmount;
import casino.idfactory.IDFactory;
import gamblingauthoritiy.IBetLoggingAuthority;
import java.util.ArrayList;
import java.util.List;

public class Cashier implements ICashier {

    private IBetLoggingAuthority loggingAuthority;
    private List<IGamblerCard> cardList = new ArrayList<IGamblerCard>();

    public Cashier(IBetLoggingAuthority loggingAuthority) {
        this.loggingAuthority = loggingAuthority;
    }

    /**
     * Cards are distributed by a Cashier to a gambler
     * bank teller keeps track of which cards are handed out.
     * Note: also use the appropriate required methods from the gambling authority API
     *
     * @return
     */
    @Override
    public IGamblerCard distributeGamblerCard()
    {
        IGamblerCard card = new GamblerCard();
        cardList.add(card);
        loggingAuthority.logHandOutGamblingCard(card.getCardID());

        return card;

    }

    /**
     * When handing in the card at a Bank teller, all betID’s on it are logged.
     * The total amount of money credit is physically handed to the gambler,
     * and the amount stored on the card is changed to zero.
     * The stored betID’s on the card are also removed.
     * Note: also use the appropriate required methods from the gambling authority API
     *
     * @param card
     */
    @Override
    public void returnGamblerCard(IGamblerCard card)
    {
        cardList.remove(card);
        loggingAuthority.logHandInGamblingCard(card.getCardID(), card.returnBetIDsAndClearCard());
    }

    /**
     * check if Bet made with the player card is possible. this is based on the amount related
     * to the card, and the amount made in the bet.
     * <p>
     * if the bet is valid, the amount of the bet is subtracted from the amount belonging to
     * the card.
     *
     * @param card
     * @param betToCheck
     * @return
     * @throws BetNotExceptedException if bet amount is invalid
     */
    @Override
    public boolean checkIfBetIsValid(IGamblerCard card, Bet betToCheck) throws BetNotExceptedException
    {
        if (card.getBalance().getAmountInCents() < betToCheck.getMoneyAmount().getAmountInCents())
        {
            throw new BetNotExceptedException();
        }
        else
        {
            card.setBalance(new MoneyAmount(card.getBalance().getAmountInCents() - betToCheck.getMoneyAmount().getAmountInCents()));
            return true;
        }
    }

    /**
     * add amount to the players card.
     *
     * @param card   card to add amount to
     * @param amount amount to add to card
     * @throws InvalidAmountException when MoneyAmount contains a negative value or is null
     */
    @Override
    public void addAmount(IGamblerCard card, MoneyAmount amount) throws InvalidAmountException
    {
        if (amount.getAmountInCents() > 0)
        {
            card.setBalance(new MoneyAmount(card.getBalance().getAmountInCents() + amount.getAmountInCents()));
        }
        else
            throw new InvalidAmountException();
    }

    public boolean checkCardIsValid(IGamblerCard card)
    {
        for(IGamblerCard c:cardList)
        {
            if(card.getCardID().equals(c.getCardID()))
                return true;
        }
        return false;
    }
}
