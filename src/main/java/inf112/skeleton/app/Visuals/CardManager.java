package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.maps.tiled.renderers.OrthoCachedTiledMapRenderer;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.Interfaces.ICardDeck;

    public class CardManager {

        private ICardDeck cardDeck;

        private Board board;
        private OrthographicCamera camera;
        private Batch batch;

        public CardManager(Board board, OrthographicCamera camera, Batch batch) {
            this.board = board;
            this.camera = camera;
            this.batch = batch;

            cardDeck = new ProgramCardDeck();
        }

        public void pickCards() {

        }
    }

