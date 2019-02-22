package inf112.skeleton.app.GUI;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DemoGame extends ApplicationAdapter {

    public static final int WIDTH = 576;
    public static final int HEIHGT = 576;

    public static final String TITLE = "Robo Rally";

    OrthographicCamera camera;
    SpriteBatch batch;
    GameMap gameMap;

    @Override
    public void create () {
        batch = new SpriteBatch();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.update();

        gameMap = new TiledGameMap();
    }

    @Override
    public void render () {
        gameMap.update();
        gameMap.render(camera, batch);
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
