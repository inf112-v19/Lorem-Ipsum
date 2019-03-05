package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.Visuals.BoardGUI;
import inf112.skeleton.app.Visuals.CardGUI;
import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Interfaces.ICardDeck;

public class CardState extends State {

    private BoardGUI boardGUI;
    private SpriteBatch batch;
    private Player[] players;
    private ICardDeck cardDeck;
    private CardGUI cardGUI;

    public CardState(GameStateManager gsm, Board board) {
        super(gsm, board);
        this.batch = new SpriteBatch();
        this.batch.setProjectionMatrix(camera.combined);
        this.boardGUI = new BoardGUI(batch, board, camera);

        this.players = board.getAllPlayers();
        this.cardDeck = new ProgramCardDeck();
        this.cardDeck.createNewDeck();

        /**
         * code for testing CardGUI. Does not use players stored in board
         */
        Player[] testarr = new Player[2];
        testarr[0] = new Player("Player1", Direction.NORTH);
        testarr[1] = new Player("Player2", Direction.NORTH);
        ICardDeck testDeck = new ProgramCardDeck();
        testDeck.createNewDeck();
        testarr[0].setCardHand(testDeck.drawCards(9));
        testarr[1].setCardHand(testDeck.drawCards(9));
        this.cardGUI = new CardGUI(camera, batch, board, testarr);

        //this.cardGUI = new CardGUI(camera, batch, board, players); //this is how it should be
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        // all players ready then set new ActionState
        boolean allReady = true;
        for (Player player : players) {
            if (!player.isReady()) {
            	allReady = false;
                break;
            }
        }
		if (allReady) {
			gsm.set(new ActionState(gsm, board));
			dispose();
		}

    }

    @Override
    public void render() {
        boardGUI.render();
        cardGUI.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    @Override
    public void resize() {
        super.resize();
        //batch.setProjectionMatrix(camera.combined);
        boardGUI.resize();
    }

    public void dealCards() {
        players[0].setCardHand(cardDeck.drawCards(9));
    }
}
