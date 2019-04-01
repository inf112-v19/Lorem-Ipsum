package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Queue;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;


public class PlayerNameState extends State {

	private Skin uiSkin;
	private int numPlayers;
	private Board board;
	private Queue<Player> players;
	private TextArea[] textAreas;
	private Texture texture;
	private TextureRegionDrawable background;
	private Table table;

	public PlayerNameState(GameStateManager gsm, Board board, int numPlayers) {
		super(gsm);
		super.camera.setToOrtho(false);

		this.uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		this.numPlayers = numPlayers;
		this.board = board;
		this.players = new Queue<>();
		this.textAreas = new TextArea[numPlayers];
		this.texture = super.assetHandler.getTexture("StateImages/secondBackground.png");
		this.background = new TextureRegionDrawable(texture);

		this.table = new Table(uiSkin);
		this.table.setFillParent(true);
		this.table.setBackground(background);
		this.table.defaults().space(0, 40, 40, 40);

		creatTextFields();
		creatSubmitButton();

		stage.addActor(table);
	}

	private void creatTextFields() {
		for (int i = 0; i < numPlayers; i++) {

			textAreas[i] = new TextArea("", uiSkin);

			Label label = new Label("Player " + (i + 1), uiSkin);
			label.setFontScale(1.5f);

			table.add(label).width(100);
			table.add(textAreas[i]).width(150);
			table.row();
		}
	}

	private void creatSubmitButton() {
		TextureRegion textureRegion = assetHandler.getTextureRegion("submit.png");
		textureRegion.flip(false, true);
		Image submit = new Image(textureRegion);
		submit.setPosition((Gdx.graphics.getWidth() / (float) 2) - (submit.getWidth() / (float) 2), 50);
		submit.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				for (int i = 0; i < textAreas.length; i++) {
					System.out.println(textAreas[i].getText());
					Player player = new Player(i, textAreas[i].getText(), Direction.EAST);
					players.addLast(player);
				}
				gsm.set(new SpawnPointState(gsm, board, players));
				return true;
			}
		});
		table.add(submit);
	}

	@Override
	public void update(float dt) {

	}
}
