package inf112.skeleton.app;

import inf112.skeleton.app.Interfaces.ICardDeck;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProgramCardDeckTest {

    @Test
    public void NewCardDeckShouldContain84Cards() {
        ICardDeck deck = new ProgramCardDeck();
        deck.createNewDeck();

        assertEquals(84, deck.numbersOfCardsLeft());
    }

    @Test
    public void deckShouldContain0cardsAfterClearDeck() {
        ICardDeck deck = new ProgramCardDeck();
        deck.createNewDeck();
        ((ProgramCardDeck) deck).clearDeck();

        assertEquals(0, deck.numbersOfCardsLeft());
    }
}
