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

    //private CardHandGUI cardGUI;
    private NewCardHandGUI newCardHandGUI;

    private CardHandGUI cardHandGUI;

    private PlayerInfoGUI infoGUI;

    private CardManager cardManager;

    public CardState(GameStateManager gsm, Board board, CardManager cardManager) {
        super(gsm, board);
        this.batch = new SpriteBatch();
        this.batch.setProjectionMatrix(camera.combined);
        this.boardGUI = new BoardGUI(board, this.camera, this.stage);

        this.players = board.getAllPlayers();
        this.cardDeck = new ProgramCardDeck();
        this.cardDeck.createNewDeck();
        this.infoGUI = new PlayerInfoGUI(board, batch, camera);
        this.cardManager = cardManager;
        /**
         * code for testing CardHandGUI. Does not use players stored in board
         */

        Player[] testarr = board.getAllPlayers();
        ICardDeck testDeck = new ProgramCardDeck();
        testDeck.createNewDeck();
        testarr[0].setCardHand(testDeck.drawCards(9));
        testarr[1].setCardHand(testDeck.drawCards(9));
        this.cardHandGUI = new CardHandGUI(camera, batch, testarr, this.stage);

        this.newCardHandGUI = new NewCardHandGUI(cardManager, camera, batch, stage);

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
		board.initRound();
        gsm.set(new ActionState(gsm, board, cardManager));
        dispose();

    }

    @Override
    public void render() {
        //boardGUI.render();
        super.render();
        //cardHandGUI.render();
        infoGUI.render();
    }

    @Override
    public void dispose() {
        //cardHandGUI.dispose();
        infoGUI.dispose();
        batch.dispose();
    }

    @Override
    public void resize() {
        super.resize();
        boardGUI.resize();
        //cardHandGUI.resize();
        infoGUI.resize();
    }

}
