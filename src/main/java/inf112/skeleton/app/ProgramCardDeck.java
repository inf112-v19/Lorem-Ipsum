package inf112.skeleton.app;

import inf112.skeleton.app.Interfaces.ICardDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ProgramCardDeck implements ICardDeck {

    Stack<Card> deck = new Stack<>();

    @Override
    public void createNewDeck() {

    }

    @Override
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    @Override
    public List<Card> drawCards(int numberOfCards) {
        List<Card> cards = new ArrayList<>();

        for (int i = 0; i < numberOfCards; i++) {
            cards.add(deck.pop());
        }

        return cards;
    }

    @Override
    public int numbersOfCardsLeft() {
        return deck.size();
    }

    @Override
    public CardType getCardType(Card card) {
        return card.getCardType();
    }
}
