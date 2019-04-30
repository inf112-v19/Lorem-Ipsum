package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.Flag;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import inf112.skeleton.app.Visuals.BoardGUI;
import inf112.skeleton.app.Visuals.Text;

public class PlaceFlagState extends State {

	private Board board;
	private CardManager cardManager;
	private BoardGUI boardGUI;
	private int flagCount;
	private Player[] players;
	private Text text;

	public PlaceFlagState(GameStateManager gsm, Board board, CardManager cardManager) {
		super(gsm);
		this.board = board;
		this.cardManager = cardManager;
		this.boardGUI = new BoardGUI(board, super.camera, super.stage, gsm, super.assetHandler);
		this.boardGUI.create();
		this.boardGUI.addListenersToStage();
		this.players = board.getAllPlayers();
		this.text = new Text("'s turn to place flag", assetHandler.getSkin(), Text.TextPosition.TOP_LEFT);
		this.text.prependDynamicsText(players[0].getPlayerName());
		super.stage.addActor(text);
	}

	@Override
	public void render() {
		super.render();
		if (Gdx.input.isKeyPressed(Input.Keys.P)) {
			System.out.println("PAUSE!");
			this.gsm.push(new PauseState(this.gsm));
		}
	}

	@Override
	public void update(float dt) {
		if (flagCount >= players.length) {
			boardGUI.removeAllListeners();
			gsm.set(new CardState(gsm, board, cardManager));
		}
	}


	@Override
	public void tileEventHandle(Tile tile) {
		this.text.prependDynamicsText(players[flagCount].getPlayerName());
		Flag flag = new Flag(Direction.NORTH, flagCount);
		if (tile.placeFlagOnTile(flag)) {
			flag.setDrawable(new TextureRegionDrawable(assetHandler.getTexture(flag)));
			flag.setSize(tile.getWidth(), tile.getHeight());
			flag.setPosition(tile.getX(), tile.getY());

			stage.addActor(flag);
			flagCount++;
		}
	}
}
