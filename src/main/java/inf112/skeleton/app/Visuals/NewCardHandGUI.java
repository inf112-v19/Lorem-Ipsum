package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class NewCardHandGUI {
    private CardManager cardManager;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Stage stage;

    private SpriteSheet spriteSheet;
    private ImageButton clear;
    private ImageButton submit;

    public NewCardHandGUI(CardManager cardManager, OrthographicCamera camera, SpriteBatch batch, Stage stage) {
        this.cardManager = cardManager;
        this.camera = camera;
        this.batch = batch;
        this.stage = stage;

        spriteSheet = new SpriteSheet();

        clear = new ImageButton(new TextureRegionDrawable(spriteSheet.getTexture(SpriteType.CARD_CLEAR)));
        submit = new ImageButton(new TextureRegionDrawable(spriteSheet.getTexture(SpriteType.CARD_SUBMIT)));

    }




    private void createSubmitButton() {
        submit.setSize(80, 30);
        submit.setPosition(cardXPos, 70);
        stage.addActor(submit);

        submit.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (validSubmit()) {
                    System.out.print("submit: ");
                    for (int i = 0; i < cardPtr; i++) {
                        System.out.print(cardSeq[i].toString() + ", ");
                    }
                    cardPtr = 0;
                    stage.clear();
                    setPlayerDone();
                }
                return true;
            }
        });
    }

    private void createClearButton() {
        clear.setSize(80, 30);
        clear.setPosition(cardXPos, 20);
        stage.addActor(clear);

        clear.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                cardPtr = 0;
                stage.clear();
                draw(players[currentPlayer].getCardHand());
                System.out.println("clear");
                return true;
            }
        });
    }

}
