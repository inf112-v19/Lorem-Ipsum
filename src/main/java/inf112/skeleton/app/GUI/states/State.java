package inf112.skeleton.app.GUI.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;


    protected State (GameStateManager gsm) {
        this.gsm = gsm;
        this.camera = new OrthographicCamera();
        this.mouse = new Vector3();
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    protected abstract void handleInput();

    //take in a delta time, difference between one frame window and the next frame window
    public abstract void update(float dt);

    // spritebatch is contaonter for everyhting we need to render to the screen texture and all that, renders everything to the screen in a big blob
    public abstract void render();

    //dispose of our texture and other media when we are done using them, to prevent any kinds of memory links
    public abstract void dispose();

    public void resize(){
        camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

}
