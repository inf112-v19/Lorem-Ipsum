package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Visuals.RoboRally;


public class ChoosePlayerState extends State {
	private Board board;
	private boolean start;
	private int playerAmount;

	private Table table;
	private Table tablebutton;
	private TextureRegionDrawable background;
	private Skin skin;

	public ChoosePlayerState(GameStateManager gsm, Board board) {
		super(gsm);
		this.board = board;
		this.start = false;

		this.skin = assetHandler.getSkin();
		this.table = new Table(skin);
		this.table.setFillParent(true);
		this.tablebutton = new Table(skin);
		//this.table.setDebug(true);
		//this.tablebutton.setDebug(true);

		//adds background
		this.background = new TextureRegionDrawable(super.assetHandler.getTextureRegion("StateImages/secondBackground.png"));
		this.table.setBackground(this.background);

		//label
		Label topLabel = new Label("CHOOSE PLAYER AMOUNT", skin);
		topLabel.setFontScale(2);
		topLabel.setAlignment(Align.center);

		//buttons
		TextButton button1 = new TextButton("1 PLAYER", skin);
		TextButton button2 = new TextButton("2 PLAYER", skin);
		TextButton button3 = new TextButton("3 PLAYER", skin);
		TextButton button4 = new TextButton("4 PLAYER", skin);
		TextButton button5 = new TextButton("5 PLAYER", skin);
		TextButton button6 = new TextButton("6 PLAYER", skin);

		//clickable
		button1.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				start = true;
				playerAmount = 1;
			}
		});
		button2.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				start = true;
				playerAmount = 2;
			}
		});
		button3.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				start = true;
				playerAmount = 3;
			}
		});
		button4.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				start = true;
				playerAmount = 4;
			}
		});
		button5.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				start = true;
				playerAmount = 5;
			}
		});
		button6.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				start = true;
				playerAmount = 6;
			}
		});

		//the visuals
		tablebutton.defaults().pad(20,80,30,80).width(150).height(50);
		tablebutton.add(button1);
		tablebutton.add(button2);
		tablebutton.add(button3);
		tablebutton.row();
		tablebutton.add(button4);
		tablebutton.add(button5);
		tablebutton.add(button6);

		table.defaults().padBottom(100F);
		table.add(topLabel);
		table.row();
		table.add(tablebutton);

		super.stage.addActor(table);
	}

	/**
	 * set the textbar "Choose player amount"
	 */
	/*
	private void setTextbar() {
		this.textBar.setSize(1273 / 3, 102 / 3);
		this.textBar.setPosition((RoboRally.WIDTH / 2) - ((1273 / 3) / 2), 102);
		this.stage.addActor(this.textBar);
	}
*/
	/**
	 * set up six players that you can choose
	 */
	/*
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
*

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
	}*/

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
	}

	@Override
	public void resize() {
		super.resize();
	}

}