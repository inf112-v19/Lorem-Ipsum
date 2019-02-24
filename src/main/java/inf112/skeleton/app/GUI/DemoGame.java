package inf112.skeleton.app.GUI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GUI.states.GameStateManager;
import inf112.skeleton.app.GUI.states.MenuState;

public class DemoGame extends ApplicationAdapter {

    //em
    public static final int WIDTH = 576;
    public static final int HEIGHT = 576;
    public static final String TITLE = "Robo Rally";
    private GameStateManager gsm;
    private SpriteBatch batch;

    OrthographicCamera camera;
    GameMap gameMap;

    @Override
    public void create () {
        batch = new SpriteBatch();

        //em
        gsm = new GameStateManager();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        gameMap = new TiledGameMap();

        //em
        Gdx.gl.glClearColor(1,0,0,1);
        gsm.push(new MenuState(gsm));
    }

    @Override
    public void render () {
        gameMap.update();
        gameMap.render(camera, batch);

        //em
        gsm.update(Gdx.graphics.getDeltaTime());
        gsm.render(batch);
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
