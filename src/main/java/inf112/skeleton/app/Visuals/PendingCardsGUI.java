package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Player;

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
    private Player currentPlayer;
    private SpriteType currentPlayerSprite;

    public PendingCardsGUI(OrthographicCamera camera, SpriteBatch batch, Board board) {
        this.camera = camera;
        this.batch = batch;
        this.board = board;
        this.spriteSheet = new SpriteSheet();
        this.screenViewport = new ScreenViewport(camera);
        this.stage = new Stage(screenViewport);

        playingCard = new BitmapFont(true);
        pendingCard = new BitmapFont(true);

    }

    public void update() {
        currentCard = board.getCurCard();
        currentPlayer = board.getCurPlayer();
        if (currentPlayer != null) {
            currentPlayerSprite = currentPlayer.getSpriteType();
        }
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
    }

    private void drawCurrentCard() {
        //TextureRegion card = new TextureRegion(spriteSheet.getTexture(currentCard), 40, 0, 100, 140);
        TextureRegion card = new TextureRegion(spriteSheet.getTexture(currentCard));
        card.flip(false, true);
        Image image = new Image(card);
        image.setSize(97, 135);
        image.setPosition(10, 25);
        stage.addActor(image);
        stage.draw();
    }

}
