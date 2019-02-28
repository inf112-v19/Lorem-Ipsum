package inf112.skeleton.app;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.GUI.SpriteSheet;
import inf112.skeleton.app.GUI.SpriteType;


import inf112.skeleton.app.Tiles.HoleTile;
import inf112.skeleton.app.Tiles.NormalTile;
import inf112.skeleton.app.Tiles.RepairTile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(GdxTestInit.class)
public class SpriteSheetTest {

	private SpriteSheet spriteSheet;

	@Before
	public void setUp() {
		spriteSheet = new SpriteSheet();
	}

	@Test
	public void initNormalTileTextureTest() {
		NormalTile normalTile = new NormalTile(null, Direction.NORTH);
		TextureRegion textureRegion = spriteSheet.getTexure(normalTile.getSpriteType());
			assertTrue(textureRegion != null );
	}

	@Test
	public void initRepairTileTextureTest() {
		RepairTile repairTile = new RepairTile(null, Direction.NORTH);
		TextureRegion textureRegion = spriteSheet.getTexure(repairTile.getSpriteType());
		assertTrue(textureRegion != null );
	}

	@Test
	public void initHoleTileTextureTest() {
		HoleTile holeTile = new HoleTile(null, Direction.NORTH);
		TextureRegion textureRegion = spriteSheet.getTexure(holeTile.getSpriteType());
		assertTrue(textureRegion != null );
	}

	@Test
	public void NormalTileTextureIsEqualToNormalTileTextureTest() {
		NormalTile nTile1 = new NormalTile(null, Direction.NORTH);
		NormalTile nTile2 = new NormalTile(null, Direction.NORTH);
		TextureRegion tile1 = spriteSheet.getTexure(nTile1.getSpriteType());
		TextureRegion tile2 = spriteSheet.getTexure(nTile2.getSpriteType());
		assertEquals(tile1,tile2);
	}

	@Test
	public void HoleTileIsNotNormalTileTextureTest() {
		HoleTile holeTile = new HoleTile(null, Direction.NORTH);
		NormalTile normalTile = new NormalTile(null, Direction.NORTH);
		TextureRegion holeTileText = spriteSheet.getTexure(holeTile.getSpriteType());
		TextureRegion normalTileText = spriteSheet.getTexure(normalTile.getSpriteType());
		assertNotEquals(holeTileText, normalTileText);
	}
}