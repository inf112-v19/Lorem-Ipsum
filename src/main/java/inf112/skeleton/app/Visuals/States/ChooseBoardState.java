package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Netcode.Host;
import inf112.skeleton.app.Visuals.RoboRally;


public class ChooseBoardState extends State {
	private boolean start;

	private Host host;

	private Image textBar;
	private TextureRegion background;

	private Board board;

	//board types
	private int halfButtonWidth;
	private int bigButtonWidth;
	private String boardName;

	public ChooseBoardState(GameStateManager gsm, Host host) {
		super(gsm);

		//Only hosts should ba able to choose board. can be null.
		this.host = host;

		this.start = false;
		this.halfButtonWidth = 193 / 2;
		this.bigButtonWidth = this.halfButtonWidth + 193;

		this.background = super.assetHandler.getTextureRegion("StateImages/secondBackground.png");
		this.textBar = new Image(assetHandler.getTextureRegion("StateImages/chooseBoardType.png"));

		setTextBar();
		setBoardTypes();
	}

	/**
	 * set the textbar "Choose board type"
	 */
	private void setTextBar() {
		this.textBar.setSize(1070 / 3, 102 / 3);
		this.textBar.setPosition((RoboRally.WIDTH / 2) - ((1070 / 3) / 2), 102);
		stage.addActor(this.textBar);
	}

	private void setBoardTypes() {
		Image board;
		for (int i = 1; i < 4; i++) {
			String filename = "board" + i;
			board = new Image(assetHandler.getTextureRegion("StateImages/" + filename + ".png"));
			board.setSize(191, 49);
			if (i == 1) {
				board.setPosition(this.halfButtonWidth, (RoboRally.HEIGHT / 2) - (49 / 2));
			} else if (i == 2) {
				board.setPosition((this.halfButtonWidth + this.bigButtonWidth), (RoboRally.HEIGHT / 2) - (49 / 2));
			} else if (i == 3) {
				board.setPosition((this.halfButtonWidth + ((this.bigButtonWidth) * 2)), (RoboRally.HEIGHT / 2) - (49 / 2));
			}
			stage.addActor(board);
			clickable(board, "Boards/BigBoard.txt");
		}
	}

	private void clickable(Image buttonType, final String buttonName) {
		buttonType.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println(buttonName + " was chosen!");
				saveBoardName(buttonName);
				if (buttonName.equals("Options")) {
					gsm.push(new PauseState(gsm));
				} else {
					start = true;
					return true;
				}
				return false;
			}
		});
	}

	private String saveBoardName(String boardName) {
		this.boardName = boardName;
		return this.boardName;
	}

	public String getBoardName() {
		return this.boardName;
	}

	@Override
	public void handleInput() {
		if (this.start) {
			this.board = new Board(getBoardName());
			if (this.host != null){
				System.out.println("hello??????????");
				this.host.send("BOARD!" + boardName);
				gsm.set(new PlayerNameState(gsm, this.board, 1, this.host));
			}else{
				gsm.set(new ChoosePlayerState(this.gsm, this.board));
			}
		}
	}


	@Override
	public void render() {
		super.render();
		this.stage.act();
		this.stage.getBatch().begin();
		this.stage.getBatch().draw(this.background, 0, 0, RoboRally.WIDTH, RoboRally.HEIGHT);
		this.stage.getBatch().end();
		this.stage.draw();

	}

	@Override
	public void resize() {
		super.resize();
	}
}