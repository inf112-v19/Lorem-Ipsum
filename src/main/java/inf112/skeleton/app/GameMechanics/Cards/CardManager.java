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

    public void newRound() {
        playerPtr = 0;
        sendCardsBackToDeck();
        cardDeck.shuffleDeck();
        for (int i = 0; i < players.length; i++) {
            players[i].setCardHand(cardDeck.drawCards(9));
        }
    }

    public Player getPlayer() {
        return players[playerPtr];
    }

    public boolean hasNotReadyPlayers() {
        for (Player player : players) {
            if (player.isReady() || player.isDead()) {
                continue;
            } else {
                return true;
            }
        }
        return false;
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
}

