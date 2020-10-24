package casino.cashier;


import casino.bet.Bet;
import casino.bet.MoneyAmount;
<<<<<<< HEAD
import casino.idfactory.IDFactory;
import gamblingauthoritiy.IBetLoggingAuthority;

import java.util.ArrayList;
import java.util.List;

public class Cashier implements ICashier {

    private IBetLoggingAuthority loggingAuthority;
    private List<IGamblerCard> cardList = new ArrayList<IGamblerCard>();

    public Cashier(IBetLoggingAuthority loggingAuthority) {
        this.loggingAuthority=loggingAuthority;
=======
import gamblingauthoritiy.IBetLoggingAuthority;

public class Cashier implements ICashier {

    public Cashier(IBetLoggingAuthority loggingAuthority) {
>>>>>>> 4902ac4408fe5d983c2107556504e354481a3b4d
    }

    /**
     * Cards are distributed by a Cashier to a gambler
     * bankteller keeps track of which cards are handed out.
     * Note: also use the appropiate required methods from the gambling authority API
     *
     * @return
     */
    @Override
<<<<<<< HEAD
    public IGamblerCard distributeGamblerCard()
    {

        IGamblerCard card = new GamblerCard();
        cardList.add(card);
        loggingAuthority.logHandOutGamblingCard(card.getCardID());

        return card;

=======
    public IGamblerCard distributeGamblerCard() {
        return null;
>>>>>>> 4902ac4408fe5d983c2107556504e354481a3b4d
    }

    /**
     * When handing in the card at a Bank teller, all betID’s on it are logged.
     * The total amount of money credit is physically handed to the gambler,
     * and the amount stored on the card is changed to zero.
     * The stored betID’s on the card are also removed.
     * Note: also use the appropiate required methods from the gambling authority API
     *
     * @param card
     */
    @Override
<<<<<<< HEAD
    public void returnGamblerCard(IGamblerCard card)
    {
        cardList.remove(card);
        loggingAuthority.logHandInGamblingCard(card.getCardID(), card.returnBetIDsAndClearCard());
=======
    public void returnGamblerCard(IGamblerCard card) {

>>>>>>> 4902ac4408fe5d983c2107556504e354481a3b4d
    }

    /**
     * check if Bet made with the playercard is possible. this is based on the amount related
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
<<<<<<< HEAD
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
=======
    public boolean checkIfBetIsValid(IGamblerCard card, Bet betToCheck) throws BetNotExceptedException {
        return false;
>>>>>>> 4902ac4408fe5d983c2107556504e354481a3b4d
    }

    /**
     * add amount to the players card.
     *
     * @param card   card to add amount to
     * @param amount amount to add to card
     * @throws InvalidAmountException when MoneyAmount contains a negative value or is null
     */
    @Override
<<<<<<< HEAD
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
=======
    public void addAmount(IGamblerCard card, MoneyAmount amount) throws InvalidAmountException {

>>>>>>> 4902ac4408fe5d983c2107556504e354481a3b4d
    }
}
