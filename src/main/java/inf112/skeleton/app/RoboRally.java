package inf112.skeleton.app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GUI.GameMap;
import inf112.skeleton.app.GUI.TiledGameMap;

public class RoboRally extends ApplicationAdapter {

	OrthographicCamera camera;
	SpriteBatch batch;
	GameMap gameMap;

	@Override
	public void create () {
		batch = new SpriteBatch();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.update();

		gameMap = new Board();
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

}
