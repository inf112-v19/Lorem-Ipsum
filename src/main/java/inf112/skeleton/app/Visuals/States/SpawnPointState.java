package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Queue;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import inf112.skeleton.app.Visuals.BoardGUI;
import inf112.skeleton.app.Visuals.SpriteSheet;


public class SpawnPointState extends State {
	private Board board;
	private Queue<Player> players;
	private SpriteSheet spriteSheet;
	private BoardGUI boardGUI;


	public SpawnPointState(GameStateManager gsm, Board board, Queue<Player> players){
		super(gsm);
		this.players = players;
		this.board = board;
		this.spriteSheet = new SpriteSheet();
		this.boardGUI = new BoardGUI(board, super.camera, super.stage, gsm);
		this.boardGUI.addListenersToStage();
	}

	@Override
	protected void handleInput() {

	}

	@Override
	public void update(float dt) {
		Gdx.input.setInputProcessor(stage);
		if (players.isEmpty()){
			System.out.println("setting PlaceFlagState");
			CardManager cardManager = new CardManager(board);
			boardGUI.removeAllListeners();
			gsm.set(new PlaceFlagState(this.gsm, this.board, cardManager));
		}
	}


	@Override
	public void tileEventHandle(Tile tile) {
		if (!players.isEmpty()) {
			float x = (tile.getX() - boardGUI.getxOffset())/(tile.getWidth());
			float y = (tile.getY() - boardGUI.getyOffset())/(tile.getHeight());
			Position playerPos = new Position((int)x, (int)y);

			Player player = players.first();
			if (board.spawnPlayer(playerPos, player)){
				players.removeFirst();
				player.setDrawable(new TextureRegionDrawable(spriteSheet.getTexture(player)));
				player.setSize(tile.getWidth(), tile.getHeight());
				player.setPosition(tile.getX(), tile.getY());
				stage.addActor(player);
				System.out.println("placing " + player.getPlayerID());
			}
		}
	}
}
