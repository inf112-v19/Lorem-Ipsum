package inf112.skeleton.app.Interfaces;

import inf112.skeleton.app.Card;
import inf112.skeleton.app.CardType;

import java.util.List;

public interface ICardDeck {

    void createNewDeck();

    List<Card> drawCards(int numberOfCards);

    int numbersOfCardsLeft();

    CardType getCardType(Card card);

}
