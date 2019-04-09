package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Visuals.RoboRally;


public class ChoosePlayerState extends State {
	private Board board;
	private TextureRegion background;

	//booleans
	private boolean start;

	//text bar
	private Image textBar;

	//player
	private int spaceOverButtons;
	private int halfButtonWidth;
	private int halfButtonWidth1;
	private int bigButtonWidth;
	private int playerAmount;

	public ChoosePlayerState(GameStateManager gsm, Board board) {
		super(gsm);
		this.board = board;
		this.background = super.assetHandler.getTextureRegion("StateImages/secondBackground.png");
		this.start = false;

		//text bar
		this.textBar = new Image(assetHandler.getTextureRegion("StateImages/choosePlayerAmount.png"));

		//player buttons
		this.spaceOverButtons = 49 * 2;
		this.halfButtonWidth = 193 / 2;
		this.halfButtonWidth1 = 193 / 2;
		this.bigButtonWidth = this.halfButtonWidth + 193;

		setTextbar();
		setSixButtons();
	}

	/**
	 * set the textbar "Choose player amount"
	 */
	private void setTextbar() {
		this.textBar.setSize(1273 / 3, 102 / 3);
		this.textBar.setPosition((RoboRally.WIDTH / 2) - ((1273 / 3) / 2), 102);
		this.stage.addActor(this.textBar);
	}

	/**
	 * set up six players that you can choose
	 */
	private void setSixButtons() {
		int nPlayers = 6;
		Image nplayers;
		for (int i = 1; i < nPlayers + 1; i++) {
			String filename = "no" + i;
			nplayers = new Image(assetHandler.getTextureRegion("StateImages/" + filename + ".png"));
			nplayers.setSize(191, 49);
			this.stage.addActor(nplayers);
			if (i >= 1 && i <= 3) {
				nplayers.setPosition(this.halfButtonWidth1, (RoboRally.HEIGHT / 2) - (49));
				this.halfButtonWidth1 += bigButtonWidth;
			} else {

				nplayers.setPosition(this.halfButtonWidth, (RoboRally.HEIGHT / 2) + (49));
				this.halfButtonWidth += bigButtonWidth;
			}
			this.playerAmount = i;
			clickable(nplayers, this.playerAmount);
		}
	}


	private void clickable(Image button, final int playerNumber) {
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.print(playerNumber);
				if (playerNumber == 1) {
					System.out.println(" player was chosen!");
					playerAmount = playerNumber;
				} else {
					System.out.println(" players was chosen!");
					playerAmount = playerNumber;
				}
				start = true;
				return true;
			}
		});
	}

	@Override
	public void handleInput() {
		if (this.start) {
			gsm.set(new PlayerNameState(gsm, board, playerAmount));
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render() {
		super.render();
		stage.getBatch().begin();
		stage.getBatch().draw(this.background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
		stage.getBatch().end();
		stage.draw();
	}

	@Override
	public void resize() {
		super.resize();
	}

}