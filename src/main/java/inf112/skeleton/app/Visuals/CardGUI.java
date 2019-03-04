package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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
    private Card[] cardSeq;
    private int cardPtr;
    private int selectedCardDrawPos;

    public CardGUI(OrthographicCamera camera, SpriteBatch batch, Board board, List<Card> cards) {
        this.camera = camera;
        this.batch = batch;
        this.cards = cards;
        this.board = board;
        this.buttonArr = new ImageButton[cards.size()];
        stage = new Stage(new ScreenViewport());
        spriteSheet  = new SpriteSheet();
        this.camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cardSeq = new Card[5];
        cardPtr = 0;
        selectedCardDrawPos = 0;

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
                    if (cardPtr < 5) {
                        cardSeq[cardPtr] = cards.get(finalI);
                        //int oldXpos = (int)buttonArr[finalI].getX();
                        cardPtr++;
                    }
                    System.out.println("card priority: " + cards.get(finalI).getPriority());
                    return true;
                }
            });
        }

        xpos += 10;

        ImageButton clear = new ImageButton(new TextureRegionDrawable(spriteSheet.getTexture(SpriteType.CARD_CLEAR)));
        ImageButton submit = new ImageButton(new TextureRegionDrawable(spriteSheet.getTexture(SpriteType.CARD_SUBMIT)));
        Image bar = new Image(new TextureRegionDrawable(spriteSheet.getTexture(SpriteType.CARD_BAR)));

        bar.setSize(480, 30);
        bar.setPosition(0,135);
        stage.addActor(bar);

        clear.setSize(80,30);
        clear.setPosition(xpos, 20);
        stage.addActor(clear);
        submit.setSize(80,30);
        submit.setPosition(xpos, 70);
        stage.addActor(submit);

        clear.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                cardPtr = 0;
                System.out.println("clear");
                return true;
            }
        });

        submit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.print("submit: ");
                for (int i = 0; i < cardPtr; i++) {
                    System.out.print(cardSeq[i].toString() + ", ");
                }
                return true;
            }
        });


        Gdx.input.setInputProcessor(stage);
    }
}
