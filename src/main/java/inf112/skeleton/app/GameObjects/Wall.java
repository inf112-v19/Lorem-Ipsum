package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GUI.SpriteSheet;

public class Wall extends GameObject {

	public Wall(Direction dir) {
		super(dir);

	}

	@Override
	public TextureRegion getTexture() {
		return texture;
	}

	@Override
	public void initializeTexture() {
		super.initializeTexture();
		switch (dir){
			case NORTH:
				texture = spriteSheet.getTexture(4, 3);
				break;
			case SOUTH:
				texture = spriteSheet.getTexture(6, 3);
				break;
			case EAST:
				texture = spriteSheet.getTexture(6, 2);
				break;
			case WEST:
				texture = spriteSheet.getTexture(5, 3);
				break;
			default:
				System.err.println("No valid Direction in Wall!");
				break;
		}
	}
}
