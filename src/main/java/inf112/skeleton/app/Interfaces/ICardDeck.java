package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.GameMechanics.Cards.Card;

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
     * returns one card from deck
     *
     * @return card
     */
    Card drawCard();

    /**
     *
     * @return number of cards left in deck
     */
    int numbersOfCardsLeft();

    /**
     * adds a card back into the deck
     *
     * @param card
     */
    void addCard(Card card);

    /**
     * shuffles deck
     */
    void shuffleDeck();

}
