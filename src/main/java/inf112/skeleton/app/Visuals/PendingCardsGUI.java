package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;

public class PendingCardsGUI {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Board board;
    private SpriteSheet spriteSheet;
    private Stage stage;
    private ScreenViewport screenViewport;

    private BitmapFont font;
    private String stringFont;

    private Card currentCard;

    public PendingCardsGUI(OrthographicCamera camera, SpriteBatch batch, Board board) {
        this.camera = camera;
        this.batch = batch;
        this.board = board;
        this.spriteSheet = new SpriteSheet();
        this.screenViewport = new ScreenViewport(camera);
        this.stage = new Stage(screenViewport);

        font = new BitmapFont(true);

    }

    public void update() {
        currentCard = board.getCurCard();
    }

    public void render() {
        batch.begin();
        font.draw(batch, "card being played:", 10, 10);
        batch.end();

        if (currentCard != null) {
            drawCurrentCard();
        }
    }

    private void drawCurrentCard() {
        TextureRegion card = new TextureRegion(spriteSheet.getTexture(currentCard));
        card.flip(false, true);
        Image image = new Image(card);
        image.setSize(97, 135);
        image.setPosition(10, 20);
        stage.addActor(image);
        stage.draw();
    }

}
