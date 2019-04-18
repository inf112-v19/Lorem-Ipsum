package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuState extends State {
	private TextureRegionDrawable background;
	private Image startButton;
	private TextButton hostGameButton;
	private TextButton joinGameButton;
	private Table table;

	private boolean start;
	private boolean isHostingGame;
	private boolean isJoiningGame;

	public MenuState(GameStateManager gsm) {
		super(gsm);

		this.start = false;
		this.isHostingGame = false;
		this.isJoiningGame = false;

		this.table = new Table();
		this.table.setFillParent(true);
		this.table.defaults().uniform();
		this.table.center();

		this.background = new TextureRegionDrawable(super.assetHandler.getTexture("StateImages/menuBackground.jpg"));
		this.table.setBackground(this.background);

		setStartButton();
		setHostGameButton();
		setJoinGameButton();

		super.stage.addActor(table);
	}

	private void setStartButton() {
		this.startButton = new Image(assetHandler.getTextureRegion("StateImages/start.png"));
		this.table.add(startButton);
		this.table.row();

		clickable(this.startButton);
	}

	private void setHostGameButton(){
		this.hostGameButton = new TextButton("Host a game", assetHandler.getSkin());
		this.hostGameButton.getLabel().setFontScale(1.5f);
		this.hostGameButton.getLabel().setColor(Color.WHITE);
		this.hostGameButton.setColor(Color.BROWN);
		this.table.add(hostGameButton);
		this.table.row();

		this.hostGameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Host game!");
				isHostingGame = true;
				return true;
			}
		});
	}

	private void setJoinGameButton(){
		this.joinGameButton = new TextButton("Join a game", assetHandler.getSkin());
		this.joinGameButton.getLabel().setFontScale(1.5f);
		this.joinGameButton.getLabel().setColor(Color.WHITE);
		this.joinGameButton.setColor(Color.BROWN);
		this.table.add(joinGameButton);
		this.table.row();

		this.joinGameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Join Game!");
				isJoiningGame = true;
				return true;
			}
		});
	}

	private void clickable(Image button) {
		button.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("Start game!");
				start = true;
				return true;
			}
		});
	}

	@Override
	public void handleInput() {
		if (this.start) {
			gsm.set(new ChooseBoardState(gsm));
		}else if(this.isHostingGame){
			System.out.println("Hosting a game!");
			gsm.set(new LobbyState(gsm));
		}else if(this.isJoiningGame){
			System.out.println("Joining a game");
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
