package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Visuals.*;
import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Interfaces.ICardDeck;

public class CardState extends State {

    private BoardGUI boardGUI;
    private SpriteBatch batch;
    private Player[] players;
    private ICardDeck cardDeck;
    private CardHandGUI cardGUI;
    private NewCardHandGUI newCardHandGUI;
    private PlayerInfoGUI infoGUI;

    private CardManager cardManager;

    public CardState(GameStateManager gsm, Board board) {
        super(gsm, board);
        this.batch = new SpriteBatch();
        this.batch.setProjectionMatrix(camera.combined);
        this.boardGUI = new BoardGUI(board, camera);

        this.players = board.getAllPlayers();
        this.cardDeck = new ProgramCardDeck();
        this.cardDeck.createNewDeck();
        this.infoGUI = new PlayerInfoGUI(board, batch, camera);

        /**
         * code for testing CardHandGUI. Does not use players stored in board
         */

        Player[] testarr = board.getAllPlayers();
        ICardDeck testDeck = new ProgramCardDeck();
        testDeck.createNewDeck();
        testarr[0].setCardHand(testDeck.drawCards(9));
        testarr[1].setCardHand(testDeck.drawCards(9));
        this.cardGUI = new CardHandGUI(camera, batch, testarr);

        this.newCardHandGUI = new NewCardHandGUI(cardManager, camera, batch);

        //this.cardGUI = new CardHandGUI(camera, batch, board, cardManager); //this is how it should be
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        for (Player player : players) {
            if (!player.isReady()) {
                return;
            }
        }
		board.initPhase();
        gsm.set(new ActionState(gsm, board));
        dispose();

    }

    @Override
    public void render() {
        boardGUI.render();
        cardGUI.render();
        infoGUI.render();
    }

    @Override
    public void dispose() {
        cardGUI.dispose();
        infoGUI.dispose();
        batch.dispose();
    }

    @Override
    public void resize() {
        super.resize();
        boardGUI.resize();
        cardGUI.resize();
        infoGUI.resize();
    }

}
