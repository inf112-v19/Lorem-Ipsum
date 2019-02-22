package inf112.skeleton.app;

import inf112.skeleton.app.Interfaces.ICardDeck;
import java.util.List;
import java.util.Stack;

public class CardDeck implements ICardDeck {

    Stack<Card> deck = new Stack<>();

    @Override
    public void createNewDeck() {

    }

    @Override
    public void shuffleDeck() {

    }

    @Override
    public List<Card> drawCards(int numberOfCards) {
        return null;
    }
}
