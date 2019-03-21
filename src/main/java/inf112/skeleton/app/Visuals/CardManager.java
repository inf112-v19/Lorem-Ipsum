package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Interfaces.ICardDeck;

    public class CardManager {

        private ICardDeck cardDeck;

        private Board board;
        private OrthographicCamera camera;
        private Player[] players;

        public CardManager(Board board) {
            this.board = board;

            players = board.getAllPlayers();

            cardDeck = new ProgramCardDeck();
        }

        public void newRound() {
            sendCardsBackToDeck();
        }

        private void sendCardsBackToDeck() {

        }

        public boolean validSubmit() {
            return false;
        }

    }

