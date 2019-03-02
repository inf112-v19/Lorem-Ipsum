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
	PLAYER("player.png"),

	BACKWARD_1("CardImages/BackUp.png"),
	FORWARD_1("CardImages/Move1.png"),
	FORWARD_2("CardImages/Move2.png"),
	FORWARD_3("CardImages/Move3.png"),
	ROTATE_180("CardImages/U-Turn.png"),
	ROTATE_90_L("CardImages/LeftTurn.png"),
	ROTATE_90_R("CardImages/RightTurn.png");


	private int x;
	private int y;
	private String filename;
	private boolean usingCoordinates;

	SpriteType (int x, int y){
		this.x = x;
		this.y = y;
		this.usingCoordinates = true;
	}

	SpriteType(String filename){
		this.filename = filename;
		this.usingCoordinates = false;
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

	public boolean isUsingCoordinates(){
		return usingCoordinates;
	}

}