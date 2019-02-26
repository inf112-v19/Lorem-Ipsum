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

	public TextureRegion getTexure(SpriteType spriteType){
		return spriteSheet[spriteType.getY()][spriteType.getX()];
	}

}
