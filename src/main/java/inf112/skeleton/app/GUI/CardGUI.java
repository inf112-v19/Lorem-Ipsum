package inf112.skeleton.app.GUI;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GameMechanics.Cards.Card;

import java.util.List;

public class CardGUI {
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private List<Card> cards;

    public CardGUI(OrthographicCamera camera, SpriteBatch batch, List<Card> cards) {
        this.camera = camera;
        this.batch = batch;
        this.cards = cards;
    }
}
