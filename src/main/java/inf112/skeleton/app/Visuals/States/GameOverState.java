package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import inf112.skeleton.app.GameMechanics.Board.Board;

public class GameOverState extends State {
	//image Game Over
	private Image gameOverImage;
	private Board board;

	//text
	private Table table;
	private Label.LabelStyle font;
	private Label playAgainLabel;

	public GameOverState(GameStateManager gsm, Board board) {
		super(gsm);
		this.board = board;

		this.table = new Table();
		this.table.center();
		this.table.setFillParent(true);
		this.table.defaults().space(0, 40, 40, 40);
		this.font = new Label.LabelStyle(new BitmapFont(false), Color.WHITE);

		gameOverImage();
		getWinnerStatus();
		playAgainLabel();

		super.stage.addActor(this.table);
	}

	private void getWinnerStatus() {
		if (board.getWinningPlayer() != null) {
			Label label = new Label(board.getWinningPlayer().getPlayerID() + " is the winner", this.font);
			label.setFontScale(2);
			this.table.add(label);
			this.table.row();
		} else {
			Label label = new Label("No winning player", this.font);
			label.setFontScale(2);
			this.table.add(label);
			this.table.row();
		}
	}

	private void gameOverImage() {
		this.gameOverImage = new Image(assetHandler.getTextureRegion("StateImages/gameOver.png"));
		this.table.add(this.gameOverImage);
		this.table.row();
		System.out.println("Game Over!");
	}

	private void playAgainLabel() {
		this.playAgainLabel = new Label("Click To Play Again", this.font);
		this.table.add(this.playAgainLabel).expandX().padTop(10f);
		this.table.row();
	}

	@Override
	public void handleInput() {
		if (Gdx.input.justTouched()) {
			System.out.println("New game!");
			this.gsm.set(new MenuState(this.gsm));
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

}
