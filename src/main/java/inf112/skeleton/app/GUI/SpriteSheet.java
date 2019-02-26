package inf112.skeleton.app.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class SpriteSheet {
	/**
	 * RoboRallyTiles.png is a 7x13 spritesheet
	 */

	private enum SpriteType{
		NORMAL_TILE(4, 0),
		HOLE_TILE(5,0),
		REPAIR_TILE(6,0),

		//laser shooting to the given direction
		LASERSOURCE_NORTH(4,4),
		LASERSOURCE_SOUTH(4, 5),
		LASERSOURCE_EAST(5, 4),
		LASERSOURCE_WEST(5,5),

		LASER_VERTICAL(7,5),
		LASER_HORIZONTAL(7,4),

		//wall blocking the given direction
		WALL_NORTH(),
		WALL_SOUTH(),
		WALL_EAST(),
		WALL_WEST(),

		FLAG(),
		PLAYER();

		private int x;
		private int y;
		Texture texture;

		SpriteType (int x, int y){
			this.x = x;
			this.y = y;
		}

		SpriteType(Texture texture){
			this.texture = texture;
		}

		SpriteType(){
			//constructor to avoid errors (#hack)
		}

	}

	private final Texture sheet = new Texture("RoboRallyTiles.png");
	private final TextureRegion[][] spriteSheet = new TextureRegion(sheet,336,624).split(336/7, 624/13);

	public TextureRegion getTexture(int x, int y){
		return spriteSheet[y][x];
	}

}
