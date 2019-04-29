package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.Visuals.RoboRally;

public class MenuState extends State {
	private boolean start;

	private Skin skin;
	private Table table;
	private TextureRegionDrawable background;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		this.start = false;

		this.skin = assetHandler.getSkin();
		this.table = new Table(skin);
		this.table.setFillParent(true);
		this.table.center();

		this.background = new TextureRegionDrawable(super.assetHandler.getTexture("StateImages/menuBackground.jpg"));
		this.table.setBackground(this.background);

		setStartButton();
		super.stage.addActor(table);
	}

	private void setStartButton() {
		TextButton startButton = new TextButton("START", skin);
		this.table.defaults().padTop(50).width(150).height(50);
		startButton.getLabel().setFontScale(1.5F);
		this.table.add(startButton);

		startButton.addListener(new ChangeListener() {
			@Override
			public void  changed(ChangeEvent event, Actor actor) {
				System.out.println("Start game!");
				start = true;
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
