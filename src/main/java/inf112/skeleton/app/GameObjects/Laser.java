package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.GUI.states.SpriteSheet;

public class Laser extends GameObject{
	TextureRegion texture = new TextureRegion();
	SpriteSheet spriteSheet = new SpriteSheet();

	public Laser(Board board) {
		super(board);
		//texture = spriteSheet.getTexture();
	}

	@Override
	public TextureRegion getTexture() {
		return texture;
	}
}
