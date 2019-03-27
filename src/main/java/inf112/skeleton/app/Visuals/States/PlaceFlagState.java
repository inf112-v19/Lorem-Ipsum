package inf112.skeleton.app.Visuals.States;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.Flag;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import inf112.skeleton.app.Visuals.SpriteSheet;
import inf112.skeleton.app.Visuals.SpriteType;

public class PlaceFlagState extends State {

	protected PlaceFlagState(GameStateManager gsm, Board board, CardManager cardManager) {
		super(gsm);
	}

	@Override
	protected void handleInput() {

	}

	@Override
	public void update(float dt) {

	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void resize() {
		super.resize();
	}

	@Override
	public void addToStage(Actor actor) {
		super.addToStage(actor);
	}

	public void handleBordInput(Tile tile, int tilesize) {
		Flag flag = new Flag(Direction.NORTH, 100);
		if (tile.placeFlagOnTile(flag)){
			flag.setDrawable(new TextureRegionDrawable(new SpriteSheet().getTexture(flag)));
			flag.setSize(tilesize, tilesize);
			flag.setPosition(tile.getX(), tile.getY());
			System.out.println("flag added");
		}
		System.out.println(tile.spriteType);
	}
}
