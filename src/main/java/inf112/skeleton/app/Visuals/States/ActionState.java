package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.utils.Timer;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Visuals.BoardGUI;
import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.TimeUnit;

public class ActionState extends State {

	private float updateCount;
	private final float UPDATE_LIMIT = 1;
	private SpriteBatch batch;
	private BoardGUI boardGUI;

	public ActionState(GameStateManager gsm, Board board) {
		super(gsm, board);
		this.batch = new SpriteBatch();
		this.boardGUI = new BoardGUI(this.batch, board, camera);
		this.updateCount = 0;
	}

	@Override
	protected void handleInput() {

	}

	@Override
	public void update(float dt) {
		updateCount += dt;
		if (updateCount > UPDATE_LIMIT){
			boardGUI.update();
			updateCount = 0;
			System.out.println("----------------------------------------------------------------------");
		}
	}

	@Override
	public void render() {
		boardGUI.render();
		System.out.println("inside actionstate");
	}

	@Override
	public void dispose() {

	}

	@Override
	public void resize() {
		super.resize();
		batch.setProjectionMatrix(camera.combined);
		//boardGUI.resize();
	}
}
