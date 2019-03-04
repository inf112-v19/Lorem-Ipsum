package inf112.skeleton.app.GUI.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GUI.BoardGUI;

public class ActionState extends State {

	private SpriteBatch batch;
	private BoardGUI boardGUI;

	protected ActionState(GameStateManager gsm) {
		super(gsm);
		this.batch = new SpriteBatch();
		this.boardGUI = new BoardGUI(camera, this.batch);
	}

	@Override
	protected void handleInput() {

	}

	@Override
	public void update(float dt) {

	}

	@Override
	public void render() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void resize() {
		super.resize();
		batch.setProjectionMatrix(camera.combined);
		boardGUI.resize();
	}
}
