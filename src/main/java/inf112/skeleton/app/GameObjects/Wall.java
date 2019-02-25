package inf112.skeleton.app.GameObjects;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.Board.Board;
import inf112.skeleton.app.GUI.states.SpriteSheet;

public class Wall extends GameObject {
	TextureRegion texture;
	SpriteSheet spriteSheet;


	public Wall(Board board) {
		super(board);
		spriteSheet = new SpriteSheet();
		texture = spriteSheet.getTexture(3, 4);
	}

	@Override
	public TextureRegion getTexture() {
		return texture;
	}
}
