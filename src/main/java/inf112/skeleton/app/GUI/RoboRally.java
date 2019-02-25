package inf112.skeleton.app.GUI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import inf112.skeleton.app.GUI.states.GameStateManager;
import inf112.skeleton.app.GUI.states.MenuState;

public class RoboRally extends ApplicationAdapter {
    //em
    public static final int WIDTH = 576;
    public static final int HEIGHT = 576;
    public static final String TITLE = "Robo Rally";

    private GameStateManager gsm;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private BoardGUI boardGUI;

    public RoboRally() {
        super();
    }

    @Override
    public void create () {
        batch = new SpriteBatch();

        //camera = new OrthographicCamera();
        //camera.setToOrtho(true);

        //em
        gsm = new GameStateManager();

        camera = new OrthographicCamera();
        camera.update();

        boardGUI = new BoardGUI(camera, batch);

        //em
        //Gdx.gl.glClearColor(1,0,0,1);
        gsm.push(new MenuState(gsm));
    }

    @Override
    public void render () {

        //TODO - handle input here

        //cleaning screen
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //redering BoardGUI
        boardGUI.render();

        //em
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);

    }

    @Override
    public void dispose () {
        batch.dispose();
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        boardGUI.resize();
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
