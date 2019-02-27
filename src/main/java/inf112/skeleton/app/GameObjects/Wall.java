package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GUI.SpriteSheet;
import inf112.skeleton.app.GUI.SpriteType;

public class Wall extends GameObject {

	public Wall(Direction dir) {
		super(dir);
		switch (dir){
			case NORTH:
				spriteType = SpriteType.WALL_NORTH;
				//texture = spriteSheet.getTexture(4, 3);
				break;
			case SOUTH:
				spriteType = SpriteType.WALL_SOUTH;
				//texture = spriteSheet.getTexture(6, 3);
				break;
			case EAST:
				spriteType = SpriteType.WALL_EAST;
				//texture = spriteSheet.getTexture(6, 2);
				break;
			case WEST:
				spriteType = SpriteType.WALL_WEST;
				//texture = spriteSheet.getTexture(5, 3);
				break;
			default:
				System.err.println("No valid Direction in Wall!");
				break;
		}

	}

}
