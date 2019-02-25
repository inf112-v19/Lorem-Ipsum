package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GUI.SpriteSheet;

public class Laser extends GameObject{
	TextureRegion texture;
	SpriteSheet spriteSheet;


	//TODO - need two lasers. one LaserBase and one Laser. This class now functions ass a Laser
	public Laser(Direction dir) {
		super(dir);
		spriteSheet = new SpriteSheet();
		switch (dir){
			case NORTH:
			case SOUTH:
				texture = spriteSheet.getTexture(6,5);
				break;
			case WEST:
			case EAST:
				texture = spriteSheet.getTexture(6,4);
				break;
			default:
				System.err.println("Direction not valid in Laser");
				break;
		}

	}

	@Override
	public TextureRegion getTexture() {
		return texture;
	}
}
