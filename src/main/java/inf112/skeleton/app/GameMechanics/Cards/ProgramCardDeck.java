package inf112.skeleton.app.GameMechanics.Cards;

import inf112.skeleton.app.Interfaces.ICardDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class ProgramCardDeck implements ICardDeck {

    Stack<Card> deck = new Stack<>();

    public ProgramCardDeck() {
        createNewDeck();
    }

    /**
     * Creates new program card deck containing 84 cards AND also shuffles deck
     */
    @Override
    public void createNewDeck() {
        clearDeck();
        int priority;

        // rotate R, number: 18, priority 80-420, step 20
        priority = 80;
        for (int i = 0; i < 18; i++) {
            deck.add(new Card(CardType.ROTATE_90_R, priority));
            priority += 20;
        }
        // rotate L, number: 18, priority 70-410, step 20
        priority = 70;
        for (int i = 0; i < 18; i++) {
            deck.add(new Card(CardType.ROTATE_90_L, priority));
            priority += 20;
        }
        // rotate 180, number: 6, priority 10-60, step 10
        priority = 10;
        for (int i = 0; i < 6; i++) {
            deck.add(new Card(CardType.ROTATE_180, priority));
            priority += 10;
        }
        // move 1, number: 18, priority 490-650, step 10
        priority = 490;
        for (int i = 0; i < 18; i++) {
            deck.add(new Card(CardType.FORWARD_1, priority));
            priority += 10;
        }
        // move 2, number: 12, priority 670-780, step 10
        priority = 670;
        for (int i = 0; i < 12; i++) {
            deck.add(new Card(CardType.FORWARD_2, priority));
            priority += 10;
        }
        // move 3, number: 6, priority 790-840, step 10
        priority = 790;
        for (int i = 0; i < 6; i++) {
            deck.add(new Card(CardType.FORWARD_3, priority));
            priority += 10;
        }
        // move -1, number: 6, priority 430-480, step 10
        priority = 430;
        for (int i = 0; i < 6; i++) {
            deck.add(new Card(CardType.BACKWARD_1, priority));
            priority += 10;
        }

        shuffleDeck();
    }

    @Override
    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    @Override
    public List<Card> drawCards(int numberOfCards) {
        if (numbersOfCardsLeft() < numberOfCards) {
            throw new NullPointerException("not enough cards in deck");
        } else {
            List<Card> cards = new ArrayList<>();

            for (int i = 0; i < numberOfCards; i++) {
                cards.add(deck.pop());
            }

            return cards;
        }
    }

    @Override
    public int numbersOfCardsLeft() {
        return deck.size();
    }

    @Override
    public CardType getCardType(Card card) {
        return card.getCardType();
    }

    @Override
    public void addCard(Card card) {
        deck.add(card);
    }

    public void clearDeck() {
        deck.removeAllElements();
    }

}
