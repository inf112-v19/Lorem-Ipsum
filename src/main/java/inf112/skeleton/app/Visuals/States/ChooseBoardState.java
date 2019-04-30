package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Netcode.Host;
import inf112.skeleton.app.Visuals.RoboRally;


public class ChooseBoardState extends State {
	private boolean start;

	private Host host;

	private Image textBar;

	private Board board;
	private String boardName;

	private Skin skin;
	private Table table;
	private Table tableButton;
	private TextureRegionDrawable background;

	public ChooseBoardState(GameStateManager gsm, Host host) {

		super(gsm);

		//Only hosts should ba able to choose board. can be null.
		this.host = host;

		this.start = false;

		this.skin = assetHandler.getSkin();
		this.table = new Table(this.skin);
		this.table.setFillParent(true);
		this.tableButton = new Table(this.skin);
		//this.table.setDebug(true);
		//this.tablebutton.setDebug(true);

		setBackground();
		this.table.defaults().padBottom(170F);
		this.table.add(getTopLabel());
		this.table.row();
		this.table.add(getButtons());

		super.stage.addActor(this.table);
	}

	private void setBackground() {
		this.background = new TextureRegionDrawable(super.assetHandler.getTexture("StateImages/secondBackground.png"));
		this.table.setBackground(this.background);
	}

	private Label getTopLabel() {
		Label topLabel = new Label("CHOOSE BOARD TYPE", this.skin);
		topLabel.setFontScale(2);
		topLabel.setAlignment(Align.center);
		return topLabel;
	}

	private Table getButtons() {
		this.tableButton.defaults().pad(0,80,0,80).width(150).height(50);
		for (int i = 0; i < 3; i++) {
			TextButton button = new TextButton("BOARD " + (i+1), this.skin);

			button.addListener(new ChangeListener() {
				@Override
				public void  changed(ChangeEvent event, Actor actor) {
					start = true;
					boardName = "Boards/BigBoard.txt";
				}
			});
			this.tableButton.add(button);
		}
		return this.tableButton;
	}

	@Override
	public void handleInput() {
		if (this.start) {
			this.board = new Board(this.boardName);
			if (this.host != null){
				System.out.println("hello??????????");
				this.host.send("BOARD!" + boardName);
				gsm.set(new PlayerNameState(gsm, this.board, 1, this.host));
			}else{
				gsm.set(new ChoosePlayerState(this.gsm, this.board));
			}
		}
	}

}