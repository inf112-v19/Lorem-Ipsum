package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.Visuals.*;
import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Interfaces.ICardDeck;

public class CardState extends State {

    private Board board;
    private BoardGUI boardGUI;
    private SpriteBatch batch;
    private Player[] players;

    private CardHandGUI cardHandGUI;

    private PlayerInfoGUI infoGUI;

    private CardManager cardManager;

    public CardState(GameStateManager gsm, Board board, CardManager cardManager) {
        super(gsm);
        this.board = board;
        this.batch = new SpriteBatch();
        this.batch.setProjectionMatrix(camera.combined);
        this.boardGUI = new BoardGUI(board, this.camera, this.stage, this.gsm);

        this.players = board.getAllPlayers();

        this.infoGUI = new PlayerInfoGUI(board, batch, stage);
        this.cardManager = cardManager;

        this.cardHandGUI = new CardHandGUI(cardManager, batch, stage);
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
        super.dispose();
        System.out.println("BUMP CARD");
        cardHandGUI.dispose();
        infoGUI.dispose();
        batch.dispose();
        boardGUI.dispose();
    }

    @Override
    public void resize() {
        super.resize();
        boardGUI.resize();
        infoGUI.resize();
    }
}
