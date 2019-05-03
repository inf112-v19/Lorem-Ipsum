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
import inf112.skeleton.app.Visuals.Text;

public abstract class State {
	protected static final AssetHandler assetHandler = new AssetHandler();
	protected GameStateManager gsm;
	protected OrthographicCamera camera = new OrthographicCamera();
	protected Stage stage = new Stage(new FitViewport(RoboRally.WIDTH, RoboRally.HEIGHT, camera));


	protected State(GameStateManager gsm) {
		this.gsm = gsm;
		Gdx.input.setInputProcessor(this.stage);
	}

	protected void handleInput() {

	}

	public void update(float dt){
		try{
			handleInput();
			this.stage.act(dt);
		}catch (Exception e){
			gsm.set(new MenuState(gsm, new Text("Something unexpected happend! Try again", assetHandler.getSkin())));
		}
	}

	public void render() {
		try{
			this.stage.draw();
		}catch (Exception e){
			gsm.set(new MenuState(gsm, new Text("Something unexpected happend! Try again", assetHandler.getSkin())));
		}
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


}
