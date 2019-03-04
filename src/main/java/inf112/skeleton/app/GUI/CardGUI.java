package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
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

    public CardGUI(OrthographicCamera camera, SpriteBatch batch, Board board, List<Card> cards) {
        this.camera = camera;
        this.batch = batch;
        this.cards = cards;
        this.board = board;
        this.buttonArr = new ImageButton[cards.size()];
        stage = new Stage(new ScreenViewport());
    }

    public void render() {
        drawCards();
    }

    private void drawCards() {


        Gdx.input.setInputProcessor(stage);
    }
}
