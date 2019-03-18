package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Visuals.BoardGUI;
import inf112.skeleton.app.Visuals.PendingCardsGUI;
import inf112.skeleton.app.Visuals.PlayerInfoGUI;


public class ActionState extends State {

	private boolean boardCanPlayCards;
	private float updateCount;
	private static final float UPDATE_LIMIT = 1;
	private SpriteBatch batch;
	private BoardGUI boardGUI;
	private PlayerInfoGUI infoGUI;
	private PendingCardsGUI pendingCardsGUI;

	public ActionState(GameStateManager gsm, Board board) {
		super(gsm, board);
		this.batch = new SpriteBatch();
		this.batch.setProjectionMatrix(camera.combined);
		this.boardGUI = new BoardGUI(board, camera);
		this.updateCount = 0;
		this.boardCanPlayCards = true;
		this.infoGUI = new PlayerInfoGUI(board, batch, camera);
		this.pendingCardsGUI = new PendingCardsGUI(camera, batch, board);
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
			infoGUI.update();
			pendingCardsGUI.update();

			if(boardCanPlayCards){
				boardCanPlayCards = board.doNextAction();
				boardGUI.update();
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
		infoGUI.render();
		pendingCardsGUI.render();
	}

	@Override
	public void dispose() {
		boardGUI.dispose();
		batch.dispose();
		pendingCardsGUI.dispose();

	}

	@Override
	public void resize() {
		super.resize();
		boardGUI.resize();
		infoGUI.resize();
	}
}
