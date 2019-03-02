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

	private Texture texture;
	private TextureRegion[][] spriteSheet;


	public SpriteSheet(){
		texture = new Texture("RoboRallyTiles.png");
		spriteSheet = new TextureRegion(texture,336,624).split(336/7, 624/13);
		flip();
	}

	private void flip(){
		for (int i = 0; i < spriteSheet.length; i++){
			for(int j = 0; j < spriteSheet[i].length; j++){
				spriteSheet[i][j].flip(false, true);
			}
		}
	}


	public TextureRegion getTexure(SpriteType spriteType){
		if (spriteType.isUsingCoordinates()) {
			return spriteSheet[spriteType.getY()][spriteType.getX()];
		}
		//TODO - making new Texture every time this is called. should maybe just be made one time
		Texture tex = new Texture(spriteType.getFilename());
		return new TextureRegion(tex);

	}



}
