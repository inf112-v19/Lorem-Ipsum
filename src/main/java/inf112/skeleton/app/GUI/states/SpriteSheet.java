package inf112.skeleton.app.GUI.states;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public final class SpriteSheet {

	private final Texture sheet = new Texture("RoboRallyTiles.png");
	private final TextureRegion[][] spriteSheet = new TextureRegion(sheet,336,624).split(336/7, 624/13);


	public TextureRegion getTexture(int x, int y){
		return spriteSheet[y][x];
	}

}
