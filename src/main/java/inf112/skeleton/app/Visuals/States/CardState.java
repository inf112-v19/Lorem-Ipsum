package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Visuals.BoardGUI;
import inf112.skeleton.app.Visuals.CardGUI;
import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Interfaces.ICardDeck;

public class CardState extends State{

    private BoardGUI boardGUI;
    private Player[] players;
    private ICardDeck cardDeck;
    private CardGUI cardGUI;

    public CardState(GameStateManager gsm, Board board) {
        super(gsm, board);

        this.batch = new SpriteBatch();
        this.batch.setProjectionMatrix(camera.combined);

        this.boardGUI = new BoardGUI(batch, board);
        this.players = board.getAllPlayers();

        this.cardDeck = new ProgramCardDeck();

        cardDeck.createNewDeck();
        this.cardGUI = new CardGUI(camera, batch, board, cardDeck.drawCards(9)); //test
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        // all players ready then set new ActionState
        int numPlayer = players.length;
        for(Player player : players){
            if (!player.isReady()){
                break;
            }else{
                numPlayer--;
                if (numPlayer == 0){
                    gsm.set(new ActionState(gsm, board));
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
        super.dispose();
    }

    @Override
    public void resize() {
        super.resize();
        batch.setProjectionMatrix(camera.combined);
        boardGUI.resize();
    }

    public void dealCards(){
        players[0].setCardHand(cardDeck.drawCards(9));
    }
}
