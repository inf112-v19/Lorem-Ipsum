package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Visuals.BoardGUI;


public class ActionState extends State {

	private boolean boardCanPlayCards;
	private float updateCount;
	private final float UPDATE_LIMIT = 1;
	private SpriteBatch batch;
	private BoardGUI boardGUI;

	public ActionState(GameStateManager gsm, Board board) {
		super(gsm, board);
		this.batch = new SpriteBatch();
		this.boardGUI = new BoardGUI(board, camera);
		this.updateCount = 0;
		this.boardCanPlayCards = true;
	}

	@Override
	protected void handleInput() {

	}

	@Override
	public void update(float dt) {
		updateCount += dt;
		if (updateCount > UPDATE_LIMIT){
			updateCount = 0;
			System.out.println("update");

			boardGUI.update();

			if(boardCanPlayCards){
				boardCanPlayCards = board.playNextCard();
			}else{
				System.out.println("setting CardState");
				gsm.set(new CardState(gsm, board));
				dispose();
			}


		}
	}

	@Override
	public void render() {
		boardGUI.render();
	}

	@Override
	public void dispose() {
		batch.dispose();

	}

	@Override
	public void resize() {
		super.resize();
		boardGUI.resize();
	}
}
