package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.Card;

import java.util.List;

public class CardGUI {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private List<Card> cards;
    private Board board;
    private ImageButton[] buttonArr;
    Stage stage;
    SpriteSheet spriteSheet;

    public CardGUI(OrthographicCamera camera, SpriteBatch batch, Board board, List<Card> cards) {
        this.camera = camera;
        this.batch = batch;
        this.cards = cards;
        this.board = board;
        this.buttonArr = new ImageButton[cards.size()];
        stage = new Stage(new ScreenViewport());
        spriteSheet  = new SpriteSheet();

        create();
    }

    public void render() {

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();


    }

    private void create() {
        int xpos = 0;
        for (int i = 0; i < buttonArr.length; i++) {
            buttonArr[i] = new ImageButton(new TextureRegionDrawable(spriteSheet.getTexture(cards.get(i))));
            buttonArr[i].setSize(97, 135);
            buttonArr[i].setPosition(xpos, 0);
            xpos += 97;
            stage.addActor(buttonArr[i]);
        }

        for (int i = 0; i < buttonArr.length; i++) {
            final int finalI = i;
            buttonArr[i].addListener(new InputListener() {
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    System.out.println("card number " + (finalI+1) + " selected");
                    return true;
                }
            });
        }
        Gdx.input.setInputProcessor(stage);
    }
}
