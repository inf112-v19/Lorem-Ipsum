package inf112.skeleton.app.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.Board.BoardBuilder;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;

public final class SpriteSheet extends Sprite {
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


	private ClassLoader classLoader;
	private File file;
	private FileHandle fileHandle;
	private Texture texture;
	private TextureRegion[][] spriteSheet;

	public SpriteSheet(){
		classLoader = SpriteSheet.class.getClassLoader();
		file = new File(classLoader.getResource("RoboRallyTiles.png").getFile());
		fileHandle = new FileHandle(file);
		texture = new Texture(fileHandle);
		spriteSheet = new TextureRegion(texture,336,624).split(336/7, 624/13);
	}

	public TextureRegion getTexture(int x, int y){
		return spriteSheet[y][x];
	}

	/*public void initializeTexture(){
		texture = new Texture(fileHandle);
		spriteSheet = new TextureRegion(texture,336,624).split(336/7, 624/13);
	}*/

}
