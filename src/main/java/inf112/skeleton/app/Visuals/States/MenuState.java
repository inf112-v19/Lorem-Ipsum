package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuState extends State {
	private boolean start;

	private Skin skin;
	private Table table;
	private TextureRegionDrawable background;

	public MenuState(GameStateManager gsm) {
		super(gsm);
		this.start = false;

		this.skin = assetHandler.getSkin();
		this.table = new Table(this.skin);
		this.table.setFillParent(true);
		this.table.center();

		setBackground();
		setStartButton();
		super.stage.addActor(this.table);
	}

	private void setStartButton() {
		TextButton startButton = new TextButton("START", this.skin);
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

	private void setBackground() {
		this.background = new TextureRegionDrawable(super.assetHandler.getTexture("StateImages/menuBackground.jpg"));
		this.table.setBackground(this.background);
	}

	@Override
	public void handleInput() {
		if (this.start) {
			this.gsm.set(new ChooseBoardState(this.gsm));
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
