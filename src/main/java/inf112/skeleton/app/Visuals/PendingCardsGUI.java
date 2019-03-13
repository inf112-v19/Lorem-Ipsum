package inf112.skeleton.app.Visuals;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GameMechanics.Board.Board;

public class PendingCardsGUI {

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Board board;

    private BitmapFont font;
    private String stringFont;

    public PendingCardsGUI(OrthographicCamera camera, SpriteBatch batch, Board board) {
        this.camera = camera;
        this.batch = batch;
        this.board = board;
    }
}
