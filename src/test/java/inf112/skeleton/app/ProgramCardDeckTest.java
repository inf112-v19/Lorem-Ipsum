package inf112.skeleton.app;

import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardType;
import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.Interfaces.ICardDeck;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

    @Test
    public void deckShouldNotContainSameCardTypeWithSamePriority() {
        ICardDeck deck = new ProgramCardDeck();
        deck.createNewDeck();
        List<Card> cards = deck.drawCards(84);

        boolean doesNotContainDuplicates = true;
        HashMap<CardType, HashSet<Integer>> map = new HashMap<>();

        for (Card c: cards) {
            if (!map.containsKey(c.getCardType())) {
                map.put(c.getCardType(), new HashSet<Integer>());
                map.get(c.getCardType()).add(c.getPriority());
                continue;
            }
            if (!map.get(c.getCardType()).contains(c.getPriority())) {
                map.get(c.getCardType()).add(c.getPriority());
                continue;
            }
            doesNotContainDuplicates = false;
            break;
        }
        assertTrue(doesNotContainDuplicates);
    }




}
