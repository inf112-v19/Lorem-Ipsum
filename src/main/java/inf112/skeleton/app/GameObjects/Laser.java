package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.Direction;
import inf112.skeleton.app.GUI.states.SpriteSheet;

public class Laser extends GameObject{
	TextureRegion texture;
	SpriteSheet spriteSheet;


	//TODO - need two lasers. one LaserBase and one Laser. This class now functions ass a Laser
	public Laser(Direction dir) {
		super(dir);
		spriteSheet = new SpriteSheet();
		//TODO - this texture point north/south. should maybe rotate according to Direction
		texture = spriteSheet.getTexture(6,5);
	}

	@Override
	public TextureRegion getTexture() {
		return texture;
	}
}
