package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import inf112.skeleton.app.GameMechanics.Board.Board;

public class ChoosePlayerState extends State {
	private Board board;
	private boolean start;
	private int playerAmount;

	private Table table;
	private Table tableButton;
	private TextureRegionDrawable background;
	private Skin skin;

	private boolean row;

	public ChoosePlayerState(GameStateManager gsm, Board board) {
		super(gsm);
		this.board = board;
		this.start = false;
		this.row = false;

		this.skin = assetHandler.getSkin();
		this.table = new Table(this.skin);
		this.table.setFillParent(true);
		this.tableButton = new Table(skin);
		//this.table.setDebug(true);
		//this.tablebutton.setDebug(true);

		setBackground();
		this.table.defaults().padBottom(100F);
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
		Label topLabel = new Label("CHOOSE PLAYER AMOUNT", this.skin);
		topLabel.setFontScale(2);
		topLabel.setAlignment(Align.center);
		return topLabel;
	}

	private Table getButtons() {
		this.tableButton.defaults().pad(20, 80, 30, 80).width(150).height(50);
		for (int i = 0; i < 6; i++) {
			TextButton button = new TextButton((i + 1) + " PLAYER", this.skin);
			final int tempPlayerAmount = (i + 1);

			button.addListener(new ChangeListener() {
				@Override
				public void changed(ChangeEvent event, Actor actor) {
					start = true;
					playerAmount = tempPlayerAmount;
				}
			});

			if (i <= 2) {
				this.tableButton.add(button);
			} else {
				if (!this.row) {
					this.tableButton.row();
					this.row = true;
				}
				this.tableButton.add(button);
			}
		}
		return this.tableButton;
	}

	@Override
	public void handleInput() {
		if (this.start) {
			this.gsm.set(new PlayerNameState(this.gsm, this.board, this.playerAmount, null));
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render() {
		super.render();
	}


}