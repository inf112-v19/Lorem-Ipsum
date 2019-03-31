package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.viewport.FitViewport;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.Visuals.Text;


public class PlayerNameState extends State{

	private Skin uiSkin;
	private int numPlayers;
	private Board board;
	private Queue<Player> players;
	private TextArea[] textAreas;
	private Stage tempStage;
	private TextureRegionDrawable background;
	private Table table;
	private final int textAreaWidth = 150;

	public PlayerNameState(GameStateManager gsm, Board board, int numPlayers) {
		super(gsm);
		this.tempStage = new Stage();
		this.uiSkin = new Skin(Gdx.files.internal("uiskin.json"));
		this.numPlayers = numPlayers;
		this.board = board;
		this.players = new Queue<>();
		this.textAreas = new TextArea[numPlayers];
		this.background = new TextureRegionDrawable(new Texture("StateImages/secondBackground.png"));
		this.table = new Table(uiSkin);
		this.table.setSize(stage.getWidth(), stage.getHeight());
		this.table.setBackground(background);
		this.table.defaults().space(0,40,40,40);

		Gdx.input.setInputProcessor(tempStage);
		creatTextFields();
		creatSubmitButton();

		tempStage.addActor(table);
	}

	private void creatTextFields(){
		int offset = 20;
		System.out.println(offset);

		//table.columnDefaults(numPlayers).width(100);

		for(int i = 0; i < numPlayers; i++){
			textAreas[i] = new TextArea("",uiSkin);
			textAreas[i].setWidth(textAreaWidth);

			table.add(new Label("Player " +(i+1), uiSkin)).width(100);
			table.add(textAreas[i]).width(150);
			table.row();
		}
	}

	private void creatSubmitButton(){
		Image submit = new Image(new TextureRegionDrawable(new Texture("submit.png")));
		submit.setPosition((Gdx.graphics.getWidth()/(float)2) - (submit.getWidth()/(float)2), 50);
		submit.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				System.out.println("hello");
				for (int i = 0; i < textAreas.length; i++){
					System.out.println(textAreas[i].getText());
					Player player = new Player(i, textAreas[i].getText(), Direction.EAST);
					players.addLast(player);
				}
				gsm.set(new SpawnPointState(gsm, board, players));
				stage.dispose();
				return true;
			}
		});
		table.add(submit);
	}

	@Override
	public void render(){
		tempStage.act();
		tempStage.draw();
	}


	@Override
	protected void handleInput() {

	}

	@Override
	public void update(float dt) {

	}
}
