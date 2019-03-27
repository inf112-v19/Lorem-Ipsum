package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.Flag;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import inf112.skeleton.app.Visuals.BoardGUI;
import inf112.skeleton.app.Visuals.SpriteSheet;
import inf112.skeleton.app.Visuals.SpriteType;

public class PlaceFlagState extends State {

	private Board board;
	private CardManager cardManager;
	private BoardGUI boardGUI;
	private int flagCount;

	public PlaceFlagState(GameStateManager gsm, Board board, CardManager cardManager) {
		super(gsm);
		this.board = board;
		this.cardManager = cardManager;
		this.boardGUI = new BoardGUI(board, super.camera, super.stage, gsm);

	}


	@Override
	protected void handleInput() {

	}

	@Override
	public void update(float dt) {
		Gdx.input.setInputProcessor(stage);
		if (flagCount >= board.getAllPlayers().length){
			boardGUI.removeAllListeners();
			gsm.set(new CardState(gsm,board,cardManager));

		}

	}


	@Override
	public void tileEventHandle(Tile tile) {
		System.out.println("du trykket på en " + tile.spriteType);

		Flag flag = new Flag(Direction.NORTH, 100);
		if (tile.placeFlagOnTile(flag)){
			flag.setDrawable(new TextureRegionDrawable(new SpriteSheet().getTexture(flag)));
			flag.setSize(tile.getWidth(), tile.getHeight());
			flag.setPosition(tile.getX(), tile.getY());

			stage.addActor(flag);
			System.out.println("flag added to stage");
			flagCount++;
		}
		System.out.println(tile.spriteType);
	}
}
