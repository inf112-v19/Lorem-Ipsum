package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Player;

import java.util.HashMap;

public class PendingCardsGUI {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Board board;
    private SpriteSheet spriteSheet;
    private Stage stage;
    private ScreenViewport screenViewport;

    private BitmapFont playingCard;
    private BitmapFont pendingCard;

    private Card currentCard;
    private Card nextCard;
    private Player currentPlayer;
    private Player nextPlayer;
    private HashMap<Player, TextureRegion> playerTextures;

    public PendingCardsGUI(OrthographicCamera camera, SpriteBatch batch, Board board) {
        this.camera = camera;
        this.batch = batch;
        this.board = board;
        this.spriteSheet = new SpriteSheet();
        this.screenViewport = new ScreenViewport(camera);
        this.stage = new Stage(screenViewport);

        playingCard = new BitmapFont(true);
        pendingCard = new BitmapFont(true);

        playerTextures = new HashMap<>();
        Player[] players = board.getAllPlayers();
        for (int i = 0; i < players.length; i++) {
            playerTextures.put(players[i], spriteSheet.getTexture(players[i]));
        }
    }

    public void update() {
        currentCard = board.getCurCard();
        currentPlayer = board.getCurPlayer();
        nextCard = board.peekNextCard();
        nextPlayer = board.getNextPlayer();
    }

    public void render() {
        stage.clear();
        batch.begin();
        playingCard.draw(batch, "card being played:", 10, 10);
        pendingCard.draw(batch, "next up: ", 10, 165);
        batch.end();

        if (currentCard != null) {
            drawCurrentCard();
        }
        if (nextCard != null) {
            drawNextCard();
        }

        stage.draw();
    }

    private void drawCurrentCard() {
        TextureRegion card = new TextureRegion(spriteSheet.getTexture(currentCard));
        addCardToStage(card, 25);
        TextureRegion player = new TextureRegion(playerTextures.get(currentPlayer));
        addPlayerImageToStage(player, 25);
    }

    private void drawNextCard() {
        //TextureRegion card = new TextureRegion(spriteSheet.getTexture(nextCard), 40, 65, 115, 115);
        TextureRegion card = new TextureRegion(spriteSheet.getTexture(nextCard));
        addCardToStage(card, 190);
        TextureRegion player = new TextureRegion(playerTextures.get(nextPlayer));
        addPlayerImageToStage(player, 190);

    }

    private void addCardToStage(TextureRegion cardTexture, int yPos) {
        cardTexture.flip(false, true);
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
        stage.dispose();
        playingCard.dispose();
        pendingCard.dispose();
    }

}
