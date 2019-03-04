package inf112.skeleton.app.GUI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import inf112.skeleton.app.GUI.states.GameStateManager;
import inf112.skeleton.app.GUI.states.MenuState;
import inf112.skeleton.app.GUI.states.PlayState;

public class RoboRally extends ApplicationAdapter {

    public static final int WIDTH = 576;
    public static final int HEIGHT = 576;
    public static final String TITLE = "Robo Rally";

    private GameStateManager gsm;

    public RoboRally() {
        super();
    }

    @Override
    public void create () {
        gsm = new GameStateManager();
        gsm.push(new PlayState(gsm));
        gsm.push(new MenuState(gsm));
    }

    @Override
    public void render () {
        //TODO - handle input here
        //cleaning screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render();
    }

    @Override
    public void dispose () {
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        gsm.resize();
    }

    @Override
    public void pause() {
        super.pause();
    }

    @Override
    public void resume() {
        super.resume();
    }
}
