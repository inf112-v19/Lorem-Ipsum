package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import inf112.skeleton.app.Visuals.BoardGUI;
import inf112.skeleton.app.Visuals.SpriteSheet;

import java.util.Stack;

public class SpawnPointState extends State {
	private Stack<Player> players;
	private SpriteSheet spriteSheet;
	private CardManager cardManager;


	public SpawnPointState(GameStateManager gsm, Board board, CardManager cardManager, Stack<Player> players){
		super(gsm);
		this.players = players;
		this.cardManager = cardManager;

		this.spriteSheet = new SpriteSheet();


		System.out.println(players.peek().getPlayerID());
	}

	@Override
	protected void handleInput() {

	}

	@Override
	public void update(float dt) {
		if (players.empty()){
			gsm.set(new PlaceFlagState(this.gsm, this.board, this.cardManager));
		}
	}

	@Override
	public void render() {
		super.render();
	}


	public void handleBordInput(Tile tile, int tilesize) {
		System.out.println("du trykket!!");
		if (!players.empty()){
			int x = (int) tile.getX()/tilesize;
			int y = (int) (tile.getY()/tilesize);
			Position playerPos = new Position(x,y);

			Player player = players.pop();

			player.setDrawable(new TextureRegionDrawable(spriteSheet.getTexture(player)));
			player.setSize(tilesize,tilesize);
			player.setPosition(tile.getX(), tile.getY());

			board.placePlayerOnPos(player,playerPos);
			stage.addActor(player);

			System.out.println("placing player");
		}

	}
}
