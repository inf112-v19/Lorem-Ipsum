package inf112.skeleton.app.GUI.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class State {
    protected OrthographicCamera cam;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected State (GameStateManager gsm) {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        mouse = new Vector3();
    }

    //show you why later
    protected abstract void handleInput();

    //take in a deltic time, difference betqween one frame window and the next frame window
    public abstract void update(float dt);

    // spritebatch is contaonter for everyhting we need to render to the screen texture and all that, renders everything to the screen in a big blob
    public abstract void render(SpriteBatch sb);

    //dispose of our texture and other media when we are done using them, to prevent any kinds of memory links
    public abstract void dispose();

}
