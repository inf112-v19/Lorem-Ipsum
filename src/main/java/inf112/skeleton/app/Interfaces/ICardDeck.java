package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Card;
import inf112.skeleton.app.CardType;

import java.util.List;

public interface ICardDeck {

    /**
     * initialize new deck. Shuffle deck
     */
    void createNewDeck();

    /**
     * returns list of cards and removes them from the deck
     *
     * @param numberOfCards number of cards to draw
     * @return list of Cards
     */
    List<Card> drawCards(int numberOfCards);

    /**
     *
     * @return number of cards left in deck
     */
    int numbersOfCardsLeft();

    /**
     *
     * @param card
     * @return CardType
     */
    CardType getCardType(Card card);

}
