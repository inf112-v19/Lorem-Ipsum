package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GUI.SpriteSheet;
import inf112.skeleton.app.GUI.SpriteType;

public class Laser extends GameObject{

	public Laser(Direction dir) {
		super(dir);
		switch (dir){
			case NORTH:
			case SOUTH:
				spriteType = SpriteType.LASER_VERTICAL;
				break;
			case WEST:
			case EAST:
				spriteType = SpriteType.LASER_HORIZONTAL;
				break;
			default:
				System.err.println("Direction not valid in Laser");
				break;
		}
	}

}
