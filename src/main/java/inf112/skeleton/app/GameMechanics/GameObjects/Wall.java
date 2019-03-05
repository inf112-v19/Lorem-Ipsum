package inf112.skeleton.app.GameMechanics.GameObjects;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.Visuals.SpriteType;

public class Wall extends GameObject {

	public Wall(Direction dir) {
		super(dir);
		switch (dir){
			case NORTH:
				spriteType = SpriteType.WALL_NORTH;
				break;
			case SOUTH:
				spriteType = SpriteType.WALL_SOUTH;
				break;
			case EAST:
				spriteType = SpriteType.WALL_EAST;
				break;
			case WEST:
				spriteType = SpriteType.WALL_WEST;
				break;
			default:
				System.err.println("No valid Direction in Wall!");
				break;
		}

	}

	public SpriteType getSpritetype(){
		return spriteType;
	}

}
