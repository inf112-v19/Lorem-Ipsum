package inf112.skeleton.app;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.GameMechanics.Player;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class CardManagerTest {
    private CardManager cardManager;
    private Board board;
    private Player[] players;

    @Before
    public void setUp() throws Exception {
        board = new Board("Boards/ExampleBoard.txt");
        players = board.getAllPlayers();
        cardManager = new CardManager(board);
    }

    /**
     * Checks if card manager acts properly if a player tries to submit zero cards.
     * The function setCardSeq should return false and player should not be set to ready
     */
    @Test
    public void managerShouldNotAcceptZeroCardSubmit() {
        Player testPlayer = players[0];
        Card[] emptySeq = new Card[5];
        assertFalse(cardManager.setCardSeq(testPlayer, emptySeq));
    }

    @Test
    public void managerShouldNotAccept6CardSubmit() {
        Player testPlayer = players[0];
        ProgramCardDeck deck = new ProgramCardDeck();
        Card[] longCardSeq = deck.drawCards(6).toArray(new Card[0]);
        assertFalse(cardManager.setCardSeq(testPlayer, longCardSeq));
    }

    @Test
    public void managerShouldSetPlayerReadyOnValidSubmit() {
        Player testPlayer = players[0];
        ProgramCardDeck deck = new ProgramCardDeck();
        Card[] validCardSeq = deck.drawCards(5).toArray(new Card[5]);
        cardManager.setCardSeq(testPlayer, validCardSeq);
        assertTrue(testPlayer.isReady());
    }


}
