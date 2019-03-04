package inf112.skeleton.app.Visuals;

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


	WALL_NORTH(6,3),
	WALL_SOUTH(4,3),
	WALL_EAST(6,2),
	WALL_WEST(5,3),

	FLAG(),
	PLAYER(),

	BACKWARD_1(),
	FORWARD_1(),
	FORWARD_2(),
	FORWARD_3(),
	ROTATE_180(),
	ROTATE_90_L(),
	ROTATE_90_R(),

	//menu
	MENU_BACKGROUND(),
	MENU_PLAY_BUTTON();


	private int x;
	private int y;
	private boolean usingCoordinates;

	SpriteType (int x, int y){
		this.x = x;
		this.y = y;
		this.usingCoordinates = true;
	}

	SpriteType(){
		this.usingCoordinates = false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isUsingCoordinates(){
		return usingCoordinates;
	}

}