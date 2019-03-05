package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    public CardState(GameStateManager gsm) {
        super(gsm);
        this.batch = new SpriteBatch();
        this.batch.setProjectionMatrix(camera.combined);
        this.boardGUI = new BoardGUI(camera, batch);

        this.players = board.getAllPlayers();
        this.cardDeck = new ProgramCardDeck();
        this.cardDeck.createNewDeck();

        this.cardGUI = new CardGUI(camera, batch, board); //only for GUI testing
        //this.cardGUI = new CardGUI(camera, batch, board, players); //this is how it should be
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        // all players ready then set new ActionState
        int numPlayer = players.length;
        for (Player player : players) {
            if (!player.isReady()) {
                break;
            } else {
                numPlayer--;
                if (numPlayer == 0) {
                    gsm.set(new ActionState(gsm));
                    dispose();
                }
            }
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
        batch.setProjectionMatrix(camera.combined);
        boardGUI.resize();
    }

    public void dealCards() {
        players[0].setCardHand(cardDeck.drawCards(9));
    }
}
