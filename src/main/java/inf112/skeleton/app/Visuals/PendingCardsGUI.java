package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Player;

import java.util.HashMap;

public class PendingCardsGUI {

    private SpriteBatch batch;
    private Board board;
    private AssetHandler assetHandler;
    private Stage stage;

    private BitmapFont playingCard;
    private BitmapFont pendingCard;

    private Card currentCard;
    private Card nextCard;
    private Player currentPlayer;
    private Player nextPlayer;
    private HashMap<Player, TextureRegion> playerTextures;

    public PendingCardsGUI(SpriteBatch batch, Board board, Stage stage, AssetHandler assetHandler) {
        this.batch = batch;
        this.board = board;
        this.assetHandler = assetHandler;
        this.stage = stage;

        playingCard = new BitmapFont();
        pendingCard = new BitmapFont();

        playerTextures = new HashMap<>();
        Player[] players = board.getAllPlayers();
        for (int i = 0; i < players.length; i++) {
            playerTextures.put(players[i], assetHandler.getTexture(players[i]));
        }
    }

    public void update() {
        currentCard = board.getCurCard();
        currentPlayer = board.getCurPlayer();
        nextCard = board.peekNextCard();
        nextPlayer = board.getNextPlayer();
    }

    public void render() {
        batch.begin();
        playingCard.draw(batch, "card being played:", 10, Gdx.graphics.getHeight()-10);
        pendingCard.draw(batch, "next up: ", 10, Gdx.graphics.getHeight()-145);
        batch.end();

        if (currentCard != null) {
            drawCurrentCard();
        }
        if (nextCard != null) {
            drawNextCard();
        }
    }

    private void drawCurrentCard() {
        TextureRegion card = new TextureRegion(assetHandler.getTexture(currentCard));
        addCardToStage(card, Gdx.graphics.getHeight()-145);
        TextureRegion player = new TextureRegion(playerTextures.get(currentPlayer));
        addPlayerImageToStage(player, Gdx.graphics.getHeight()-145);
    }

    private void drawNextCard() {
        TextureRegion card = new TextureRegion(assetHandler.getTexture(nextCard));
        addCardToStage(card, Gdx.graphics.getHeight()-300);
        TextureRegion player = new TextureRegion(playerTextures.get(nextPlayer));
        addPlayerImageToStage(player, Gdx.graphics.getHeight()-300);

    }

    private void addCardToStage(TextureRegion cardTexture, int yPos) {
        Image image = new Image(cardTexture);
        image.setSize(97, 135);
        image.setPosition(10, yPos);
        stage.addActor(image);
    }

    private void addPlayerImageToStage(TextureRegion playerTexture, int yPos) {
        Image image = new Image(playerTexture);
        image.setSize(40, 40);
        image.setPosition(107, yPos + 10);
        stage.addActor(image);
    }

    public void dispose() {
        playingCard.dispose();
        pendingCard.dispose();
    }

}
