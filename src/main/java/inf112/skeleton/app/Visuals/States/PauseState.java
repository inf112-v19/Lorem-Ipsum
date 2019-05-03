package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import inf112.skeleton.app.Netcode.INetCode;
import inf112.skeleton.app.Visuals.RoboRally;


public class PauseState extends State {
	//boolean
	private boolean mainMenu;
	private boolean resume;
	private boolean exit;
	private boolean start;

	//image
	private Image resumeButton;
	private Image mainMenuButton;
	private Image exitButton;

	private Skin skin;
	private Table table;
	private Table tablebutton;

	private INetCode net;


	public PauseState(GameStateManager gsm, INetCode net) {
		super(gsm);

		//boolean
		this.mainMenu = false;
		this.resume = false;
		this.exit = false;
		this.start = false;

		this.skin = assetHandler.getSkin();
		this.table = new Table(skin);
		this.table.setFillParent(true);
		this.tablebutton = new Table(skin);
		//this.tablebutton.setDebug(true);
		//this.table.setDebug(true);

		//label
		Label topLabel = new Label("PAUSE", skin);
		topLabel.setFontScale(2);
		topLabel.setAlignment(Align.center);
		
		table.defaults().padBottom(100F);
		table.add(topLabel);
		table.row();
		setResume();

		table.add(tablebutton);


		this.net = net;
		super.stage.addActor(table);

	}

	private void setResume() {
		TextButton resumeBut = new TextButton("RESUME", skin);
		TextButton mainMenuBut = new TextButton("MAIN MENU", skin);
		TextButton exitBut = new TextButton("EXIT", skin);

		resumeBut.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				start = true;
				resume = true;
			}
		});
		mainMenuBut.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				start = true;
				mainMenu = true;
			}
		});
		exitBut.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				start = true;
				exit = true;
			}
		});

		//the visuals
		tablebutton.defaults().pad(0,80,20,80).width(150).height(50);
		tablebutton.add(resumeBut);
		tablebutton.row();
		tablebutton.add(mainMenuBut);
		tablebutton.row();
		tablebutton.add(exitBut);
	}

/*
	private void setResume() {
		this.resumeButton.setSize(191, 49);
		this.resumeButton.setPosition((RoboRally.WIDTH / 2) - (191 / 2), 49 * 3);
		this.stage.addActor(this.resumeButton);
		clickable(this.resumeButton, "resume");
	}

	private void setMainMenu() {
		this.mainMenuButton.setSize(191, 49);
		this.mainMenuButton.setPosition((RoboRally.WIDTH / 2) - (191 / 2), 49 * 5);
		this.stage.addActor(this.mainMenuButton);
		clickable(this.mainMenuButton, "mainMenu");
	}

	private void setExit() {
		this.exitButton.setSize(191, 49);
		this.exitButton.setPosition((RoboRally.WIDTH / 2) - (191 / 2), 49 * 7);
		this.stage.addActor(this.exitButton);
		clickable(this.exitButton, "exit");
	}

	private void clickable(Image buttonType, final String buttonName) {
		buttonType.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if (buttonName.equals("resume")) {
					resume = true;
				} else if (buttonName.equals("mainMenu")) {
					mainMenu = true;
				} else if (buttonName.equals("exit")) {
					exit = true;
				}
				start = true;
				return true;
			}
		});
	}
	*/

	@Override
	protected void handleInput() {
		if (this.start) {
			if (this.resume) {
				System.out.println("Resume Game!");
				this.gsm.pop();

			} else if (this.mainMenu) {
				System.out.println("New Game!");
				this.gsm.pop();
				this.gsm.set(new MenuState(this.gsm));
			} else if (this.exit) {
				System.out.println("Exit Game!");
				this.net.disconnect();
				Gdx.app.exit();
			}

		}
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

}
