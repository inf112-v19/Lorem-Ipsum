package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.GUI.SpriteSheet;
import inf112.skeleton.app.GUI.SpriteType;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(GdxTestInit.class)
public class SpriteSheetTest {

	private SpriteSheet spriteSheet;

	@Before
	public void setUp() {
		spriteSheet = new SpriteSheet();
	}

	@Test
	public void initNormalTileTextureTest() {
		TextureRegion textureRegion = spriteSheet.getTexure(SpriteType.NORMAL_TILE);
			assertTrue(textureRegion != null );
	}

	@Test
	public void initRepairTileTextureTest() {
		TextureRegion textureRegion = spriteSheet.getTexure(SpriteType.REPAIR_TILE);
		assertTrue(textureRegion != null );
	}

	@Test
	public void initHoleTileTextureTest() {
		TextureRegion textureRegion = spriteSheet.getTexure(SpriteType.HOLE_TILE);
		assertTrue(textureRegion != null );
	}





}