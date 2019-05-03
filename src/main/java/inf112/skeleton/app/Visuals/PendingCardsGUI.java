package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Player;

import java.util.HashMap;

public class PendingCardsGUI {

    private SpriteBatch batch;
    private Board board;
    private AssetHandler assetHandler;
    private Stage stage;

    private Card currentCard;
    private Card nextCard;
    private Player currentPlayer;
    private Player nextPlayer;
    private HashMap<Player, TextureRegion> playerTextures;

    private Table table;
    private Label playingCard;
    private Label pendingCard;

    public PendingCardsGUI(SpriteBatch batch, Board board, Stage stage, AssetHandler assetHandler) {
        table = new Table();
        table.top().left();
        table.setFillParent(true);

        this.batch = batch;
        this.board = board;
        this.assetHandler = assetHandler;
        this.stage = stage;

        playerTextures = new HashMap<>();
        Player[] players = board.getAllPlayers();
        for (int i = 0; i < players.length; i++) {
            playerTextures.put(players[i], assetHandler.getTexture(players[i]));
        }

        playingCard = new Label("card being played: ", assetHandler.getSkin());
        pendingCard = new Label("next up: ", assetHandler.getSkin());
    }

    /**
     * Draws current card and next card on screen
     */
    public void render() {
        table.clearChildren();

        if (currentCard != null) {
            table.add(playingCard);
            table.row();
            drawCurrentCard();
        }
        if (nextCard != null) {
            table.add(pendingCard).left();
            table.row();
            drawNextCard();
        }
        stage.addActor(table);
    }
    
    public void update() {
        currentCard = board.getCurCard();
        currentPlayer = board.getCurPlayer();
        nextCard = board.peekNextCard();
        nextPlayer = board.getNextPlayer();
    }

    private void drawCurrentCard() {
        TextureRegion card = new TextureRegion(assetHandler.getTexture(currentCard));
        addCardToStage(card);
        TextureRegion player = new TextureRegion(playerTextures.get(currentPlayer));
        addPlayerImageToStage(player);
    }

    private void drawNextCard() {
        TextureRegion card = new TextureRegion(assetHandler.getTexture(nextCard));
        addCardToStage(card);
        TextureRegion player = new TextureRegion(playerTextures.get(nextPlayer));
        addPlayerImageToStage(player);
    }

    private void addCardToStage(TextureRegion cardTexture) {
        Image image = new Image(cardTexture);
        table.add(image).width(97).height(135);
    }

    private void addPlayerImageToStage(TextureRegion playerTexture) {
        Image image = new Image(playerTexture);
        table.add(image).width(40).height(40);
        table.row();
    }

    public void dispose() {
        table.clearChildren();
    }
}
