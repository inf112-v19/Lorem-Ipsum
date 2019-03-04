package inf112.skeleton.app.Visuals.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.Visuals.BoardGUI;
import inf112.skeleton.app.Visuals.CardGUI;
import inf112.skeleton.app.GameMechanics.Cards.ProgramCardDeck;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Interfaces.ICardDeck;

public class CardState extends State{

    private BoardGUI boardGUI;
    private SpriteBatch batch;
    private Player[] players;
    private ICardDeck cardDeck;
    private CardGUI cardGUI;

    public CardState(GameStateManager gsm) {
        super(gsm);
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
        boardGUI = new BoardGUI(camera, batch);

        this.players = board.getAllPlayers();
        this.cardDeck = new ProgramCardDeck();


        ICardDeck deck = new ProgramCardDeck();
        deck.createNewDeck();
        this.cardGUI = new CardGUI(camera, batch, board, deck.drawCards(9)); //test
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

    public void dealCards(){
        players[0].setCardHand(cardDeck.drawCards(9));
    }
}
