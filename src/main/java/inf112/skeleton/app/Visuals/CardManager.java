package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Interfaces.ICardDeck;

import java.util.List;

public class CardManager {

    private ICardDeck cardDeck;

    private Board board;
    private OrthographicCamera camera;
    private Player[] players;

    private int playerPtr;

    public CardManager(Board board) {
        this.board = board;

        players = board.getAllPlayers();

        cardDeck = new ProgramCardDeck();
    }

    public void newRound() {
        playerPtr = 0;
        sendCardsBackToDeck();
        cardDeck.shuffleDeck();
        System.out.println(players.length);
        for (int i = 0; i < players.length; i++) {
            players[i].setCardHand(cardDeck.drawCards(9));
        }


    }

    public Player getPlayer() {
        return players[playerPtr];
    }

    public boolean hasNotReadyPlayers() {
        for (Player player: players) {
            if (player.isReady()) {
                break;
            } else {
                return true;
            }
        }
        return false;
    }

    private void sendCardsBackToDeck() {
        for (int i = 0; i < players.length; i++) {
            List<Card> cards = players[i].getCardHand();
            for (int j = 0; j < cards.size(); j++) {
                cardDeck.addCard(cards.get(j));
            }
        }
    }

    public boolean setCardSeq(Player player, List<Card> cards) {
        if (cards.size() == 5) {
            player.setCardSequence((Card[]) cards.toArray());
            player.setReady();
            return true;
        } else {
            return false;
        }
    }
}

