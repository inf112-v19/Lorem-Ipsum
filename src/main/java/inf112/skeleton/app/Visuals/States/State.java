package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.viewport.FitViewport;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import inf112.skeleton.app.Visuals.AssetHandler;
import inf112.skeleton.app.Visuals.RoboRally;

public abstract class State {
	protected static final AssetHandler assetHandler = new AssetHandler();
	protected GameStateManager gsm;
	protected OrthographicCamera camera = new OrthographicCamera();
	protected Stage stage = new Stage(new FitViewport(RoboRally.WIDTH, RoboRally.HEIGHT, camera));


	protected State(GameStateManager gsm) {
		//this.camera.setToOrtho(true, RoboRally.WIDTH, RoboRally.HEIGHT);
		this.gsm = gsm;
		Gdx.input.setInputProcessor(this.stage);
	}

	protected void handleInput() {

	}

	public abstract void update(float dt);

	public void render() {
		this.stage.act();
		this.stage.draw();
	}

	public void dispose() {
		this.stage.dispose();
	}

	public void resize() {
		stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	/**
	 * Method that handles the clicking of tiles on the board.
	 * This method is GameStateManager when the current stage is active
	 */
	public void tileEventHandle(Tile tile) {

	}


	/**
	 * Method that handles the clicking of Stacks on the board.
	 * This method is GameStateManager when the current stage is active
	 */
	public void stackEventHandle(Stack stack) {

	}


}
