package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import inf112.skeleton.app.GameMechanics.Board.Board;

public class ChooseBoardState extends State {
	private boolean start;
	private Board board;
	private String boardName;

	private Skin skin;
	private Table table;
	private Table tablebutton;
	private TextureRegionDrawable background;

	public ChooseBoardState(GameStateManager gsm) {
		super(gsm);
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
		Label topLabel = new Label("CHOOSE BOARD TYPE", skin);
		topLabel.setFontScale(2);
		topLabel.setAlignment(Align.center);

		//buttons
		TextButton button1 = new TextButton("BOARD 1", skin);
		TextButton button2 = new TextButton("BOARD 2", skin);
		TextButton button3 = new TextButton("BOARD 3", skin);

		//clickable
		//button1.getLabel().setFontScale(2);
		button1.addListener(new ChangeListener() {
			@Override
			public void  changed(ChangeEvent event, Actor actor) {
				start = true;
				saveBoardName("Boards/BigBoard.txt");
			}
		});
		button2.addListener(new ChangeListener() {
			@Override
			public void  changed(ChangeEvent event, Actor actor) {
				start = true;
				saveBoardName("Boards/BigBoard.txt");
			}
		});
		button3.addListener(new ChangeListener() {
			@Override
			public void  changed(ChangeEvent event, Actor actor) {
				start = true;
				saveBoardName("Boards/BigBoard.txt");
			}
		});

		//the visuals
		tablebutton.defaults().pad(0,80,0,80).width(150).height(50);
		tablebutton.add(button1);
		tablebutton.add(button2);
		tablebutton.add(button3);

		table.defaults().padBottom(170F);
		table.add(topLabel);
		table.row();
		table.add(tablebutton);

		super.stage.addActor(table);
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
			gsm.set(new ChoosePlayerState(this.gsm, this.board));
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