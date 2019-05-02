package inf112.skeleton.app;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

public class CardManagerTest {
    private CardManager cardManager;
    private Board board;
    private Player[] players;

    /**
     * Set up method. creates a board containing two players
     * also creates card manager with this board
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        board = new Board("Boards/ExampleBoard.txt");
        Player player0 = new Player(0,"Player 0", Direction.EAST);
        Player player1 = new Player(1,"Player 1", Direction.EAST);
        player0.setBackup(new Position(1, 4));
        player1.setBackup(new Position(1, 11));
        board.placePlayerOnPos(player0, new Position(0, 0));
        board.placePlayerOnPos(player1, new Position(0, 1));

        players = board.getAllPlayers();
        cardManager = new CardManager(board, null);
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

    @Test
    public void getPlayerShouldReturnNullWhenAllPlayersAreDead() {
        for (int i = 0; i < 3; i++) {
            players[0].destroyPlayer();
            players[1].destroyPlayer();
        }
        assertEquals(null, cardManager.getPlayer());
    }

    @Test
    public void playerShouldGet8CardsWith9HP() {
        cardManager.newRound();
        players[0].increaseDamage();
        cardManager.newRound();
        assertEquals(8, players[0].getCardHand().size());
    }

    @Test
    public void cardDeckShouldNeverHaveMoreThan84Cards() {
        //simulates 3 rounds with no locked cards
        cardManager.newRound();
        cardManager.newRound();
        cardManager.newRound();
        assertTrue(cardManager.getCardDeckSize() <= 84);
    }

    @Test
    public void playerWith5HpShouldHaveOneLockedCard() {
        Player testPlayer = players[0];

        //simulates first round for the test player
        cardManager.newRound();
        List<Card> cardHand = testPlayer.getCardHand();
        Card[] validCardSeq = new Card[5];
        for (int i = 0; i < validCardSeq.length; i++) {
            validCardSeq[i] = cardHand.get(i);
        }
        assertTrue(cardManager.setCardSeq(testPlayer, validCardSeq));

        //Sets playerHP to 5
        for (int i = 0; i < 5; i++) {
            testPlayer.increaseDamage();
        }

        cardManager.newRound();

        assertTrue(cardManager.isLocked(validCardSeq[4]));
    }
}
