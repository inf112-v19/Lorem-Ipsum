package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.Visuals.RoboRally;

public class MenuState extends State {
	private TextureRegionDrawable background;
	private Image startButton;
	private Table table;

	private boolean start;

	public MenuState(GameStateManager gsm) {
		super(gsm);

		this.table = new Table();
		this.table.setFillParent(true);
		this.table.center();

		this.start = false;

		this.background = new TextureRegionDrawable(super.assetHandler.getTexture("StateImages/menuBackground.jpg"));
		this.table.setBackground(this.background);

		setStartButton();

		super.stage.addActor(table);
	}

	private void setStartButton() {
		this.startButton = new Image(assetHandler.getTextureRegion("StateImages/start.png"));
		this.table.add(startButton);

		clickable(this.startButton);
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
