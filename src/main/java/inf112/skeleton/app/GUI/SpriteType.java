package inf112.skeleton.app.GUI;

import com.badlogic.gdx.graphics.Texture;

/**
 * RoboRallyTiles.png is a 7x13 spritesheet
 */
public enum SpriteType{
	NORMAL_TILE(4, 0),
	HOLE_TILE(5,0),
	REPAIR_TILE(6,0),

	//laser shooting to the given direction
	LASERSOURCE_NORTH(4,4),
	LASERSOURCE_SOUTH(4, 5),
	LASERSOURCE_EAST(5, 4),
	LASERSOURCE_WEST(5,5),

	LASER_VERTICAL(6,5),
	LASER_HORIZONTAL(6,4),

	//wall blocking the given direction
	WALL_NORTH(6,3),
	WALL_SOUTH(4,3),
	WALL_EAST(6,2),
	WALL_WEST(5,3),

	//FLAG(),
	//PLAYER(),

	CARD_BACKUP("BackUp.png");



	private int x;
	private int y;
	String filename;

	SpriteType (int x, int y){
		this.x = x;
		this.y = y;
	}

	SpriteType(String filename){
		this.filename = filename;
		//constructor to avoid errors (#hack)
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getFilename(){
		return filename;
	}

}