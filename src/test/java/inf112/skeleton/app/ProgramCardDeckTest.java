package inf112.skeleton.app;

import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.Interfaces.ICardDeck;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProgramCardDeckTest {

    @Test
    public void newCardDeckShouldContain84CardsTest() {
        ICardDeck deck = new ProgramCardDeck();
        deck.createNewDeck();

        assertEquals(84, deck.numbersOfCardsLeft());
    }

    @Test
    public void deckShouldContain0cardsAfterClearDeckTest() {
        ICardDeck deck = new ProgramCardDeck();
        deck.createNewDeck();
        ((ProgramCardDeck) deck).clearDeck();

        assertEquals(0, deck.numbersOfCardsLeft());
    }

    @Test(expected = NullPointerException.class)
    public void deckShouldThrowErrorIfNotEnoughCardsInDeckTest() {
        ICardDeck deck = new ProgramCardDeck();
        deck.createNewDeck();
        ((ProgramCardDeck) deck).clearDeck();
        deck.drawCards(10);
    }

    @Test
    public void deckShouldContain83cardsAfterOneIsDrawnTest() {
        ICardDeck deck = new ProgramCardDeck();
        deck.createNewDeck();
        deck.drawCards(1);
        assertEquals(83, deck.numbersOfCardsLeft());
    }

    @Test
    public void deckShouldContain74cardsAfterOneIsDrawnTest() {
        ICardDeck deck = new ProgramCardDeck();
        deck.createNewDeck();
        deck.drawCards(10);
        assertEquals(74, deck.numbersOfCardsLeft());
    }

    //TODO: deckShouldNotContainSimilarCardsWithSamePriority


}
