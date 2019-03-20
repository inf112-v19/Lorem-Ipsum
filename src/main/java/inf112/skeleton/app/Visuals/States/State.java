package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import inf112.skeleton.app.GameMechanics.Board.Board;

public abstract class State {
    protected OrthographicCamera camera;
    protected SpriteBatch batch;
    protected Vector3 mouse;
    protected GameStateManager gsm;
    protected Board board;


    protected State (GameStateManager gsm, Board board) {
        this.gsm = gsm;
        this.camera = new OrthographicCamera();
        this.mouse = new Vector3();
        this.camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this.board = board;
    }

    protected abstract void handleInput();

    //take in a delta time, difference between one frame window and the next frame window
    public abstract void update(float dt);

    // spritebatch is contaonter for everyhting we need to render to the screen texture and all that, renders everything to the screen in a big blob
    public abstract void render();

    //dispose of our texture and other media when we are done using them, to prevent any kinds of memory links
    public void dispose(){
        this.batch.dispose();
    }

    public void resize() {
    }

}
