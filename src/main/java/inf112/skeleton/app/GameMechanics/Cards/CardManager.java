package inf112.skeleton.app.GameMechanics.Cards;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Interfaces.ICardDeck;

import java.util.List;

public class CardManager {

    private ICardDeck cardDeck;
    private Player[] players;
    private int playerPtr;

    public CardManager(Board board) {
        players = board.getAllPlayers();
        cardDeck = new ProgramCardDeck();
    }

    /**
     * Initialize new round. Fills card deck with last round cards.
     * Draw new cards for each player.
     */
    public void newRound() {
        playerPtr = 0;
        sendCardsBackToDeck();
        cardDeck.shuffleDeck();
        for (int i = 0; i < players.length; i++) {
            players[i].setCardHand(cardDeck.drawCards(9));
        }
    }

    /**
     * @return current player for assigning play sequence
     */
    public Player getPlayer() {
        return players[playerPtr];
    }

    /**
     * Checks if all players has a valid play card sequence
     *
     * @return true if there is one or more player without a valid card sequence ready to execute
     */
    public boolean hasNotReadyPlayers() {
        for (Player player : players) {
            if (player.isReady()) {
                continue;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets a five card play sequence for a given player
     *
     * @param player
     * @param cards
     * @return true if its a valid 5 card sequence
     */
    public boolean setCardSeq(Player player, Card[] cards) {
        if (cards.length == 5) {
            for (Card card : cards) {
                if (card == null) {
                    return false;
                }
            }
            player.setCardSequence(cards);
            player.setReady();
            playerPtr++;
            return true;
        } else {
            return false;
        }
    }

    private void sendCardsBackToDeck() {
        for (int i = 0; i < players.length; i++) {
            List<Card> cards = players[i].getCardHand();
            if (cards != null) {
                for (int j = 0; j < cards.size(); j++) {
                    cardDeck.addCard(cards.get(j));
                }
            }
        }
    }
}

