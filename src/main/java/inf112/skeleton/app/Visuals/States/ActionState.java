package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Visuals.BoardGUI;

public class ActionState extends State {

	private SpriteBatch batch;
	private BoardGUI boardGUI;

	protected ActionState(GameStateManager gsm, Board board) {
		super(gsm, board);
		this.batch = new SpriteBatch();
		this.boardGUI = new BoardGUI(this.batch, board, camera);
	}

	@Override
	protected void handleInput() {

	}

	@Override
	public void update(float dt) {


	}

	@Override
	public void render() {
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
