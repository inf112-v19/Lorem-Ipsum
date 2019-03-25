package inf112.skeleton.app.GameMechanics.Tiles;

import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.Visuals.SpriteSheet;
import inf112.skeleton.app.Visuals.SpriteType;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;

public class HoleTile extends Tile {

	public HoleTile(GameObject[] gameObjects, Direction direction) {
		super(gameObjects, direction);
		super.spriteType = SpriteType.HOLE_TILE;
		this.setDrawable(new TextureRegionDrawable(new SpriteSheet().getTexture(this).getTexture()));
	}

}
