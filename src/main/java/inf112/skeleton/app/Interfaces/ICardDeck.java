package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Card;

import java.util.List;

public interface ICardDeck {

    void createNewDeck();

    void shuffleDeck();

    List<Card> drawCards(int numberOfCards);
}
