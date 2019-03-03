package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.Cards.Card;
import inf112.skeleton.app.Cards.CardType;
import inf112.skeleton.app.GUI.SpriteSheet;
import inf112.skeleton.app.GUI.SpriteType;


import inf112.skeleton.app.GameObjects.Laser;
import inf112.skeleton.app.GameObjects.Wall;
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
		TextureRegion textureRegion = spriteSheet.getTexture(normalTile.getSpriteType());
			assertTrue(textureRegion != null );
	}

	@Test
	public void initRepairTileTextureTest() {
		RepairTile repairTile = new RepairTile(null, Direction.NORTH);
		TextureRegion textureRegion = spriteSheet.getTexture(repairTile.getSpriteType());
		assertTrue(textureRegion != null );
	}

	@Test
	public void initHoleTileTextureTest() {
		HoleTile holeTile = new HoleTile(null, Direction.NORTH);
		TextureRegion textureRegion = spriteSheet.getTexture(holeTile.getSpriteType());
		assertTrue(textureRegion != null );
	}

	@Test
	public void NormalTileTextureIsEqualToNormalTileTextureTest() {
		NormalTile nTile = new NormalTile(null, Direction.NORTH);
		TextureRegion tile1 = spriteSheet.getTexture(nTile.getSpriteType());
		TextureRegion tile2 = spriteSheet.getTexture(SpriteType.NORMAL_TILE);
		assertEquals(tile1,tile2);
	}

	@Test
	public void HoleTileIsNotNormalTileTextureTest() {
		HoleTile holeTile = new HoleTile(null, Direction.NORTH);
		NormalTile normalTile = new NormalTile(null, Direction.NORTH);
		TextureRegion holeTileText = spriteSheet.getTexture(holeTile.getSpriteType());
		TextureRegion normalTileText = spriteSheet.getTexture(normalTile.getSpriteType());
		assertNotEquals(holeTileText, normalTileText);
	}

	@Test
	public void laserTextureCorrectTest() {
		Laser laserNorth = new Laser(Direction.NORTH);
		TextureRegion laserTexNorth = spriteSheet.getTexture(laserNorth.getSpriteType());

		Laser laserSouth = new Laser(Direction.SOUTH);
		TextureRegion laserTexSouth = spriteSheet.getTexture(laserSouth.getSpriteType());

		Laser laserEast = new Laser(Direction.EAST);
		TextureRegion laserTexEast = spriteSheet.getTexture(laserEast.getSpriteType());

		Laser laserWest = new Laser(Direction.WEST);
		TextureRegion laserTexWest = spriteSheet.getTexture(laserWest.getSpriteType());

		boolean laserNorthIsVertical = laserTexNorth.equals(spriteSheet.getTexture(SpriteType.LASER_VERTICAL));
		boolean laserSouthIsVertical = laserTexSouth.equals(spriteSheet.getTexture(SpriteType.LASER_VERTICAL));
		boolean laserEastIsHorizontal = laserTexEast.equals(spriteSheet.getTexture(SpriteType.LASER_HORIZONTAL));
		boolean laserWestIsHorizontal = laserTexWest.equals(spriteSheet.getTexture(SpriteType.LASER_HORIZONTAL));

		assertTrue(laserNorthIsVertical && laserSouthIsVertical && laserEastIsHorizontal && laserWestIsHorizontal);
	}

	@Test
	public void WallTextureCorrectTest() {
		Wall wallNorth = new Wall(Direction.NORTH);
		TextureRegion WallTexNorth = spriteSheet.getTexture(wallNorth.getSpriteType());

		Wall wallSouth = new Wall(Direction.SOUTH);
		TextureRegion WallTexSouth = spriteSheet.getTexture(wallSouth.getSpriteType());

		Wall wallEast = new Wall(Direction.EAST);
		TextureRegion WallTexEast = spriteSheet.getTexture(wallEast.getSpriteType());

		Wall wallWest = new Wall(Direction.WEST);
		TextureRegion WallTexWest = spriteSheet.getTexture(wallWest.getSpriteType());

		boolean WallNorthIsVertical = WallTexNorth.equals(spriteSheet.getTexture(SpriteType.WALL_NORTH));
		boolean WallSouthIsVertical = WallTexSouth.equals(spriteSheet.getTexture(SpriteType.WALL_SOUTH));
		boolean WallEastIsHorizontal = WallTexEast.equals(spriteSheet.getTexture(SpriteType.WALL_EAST));
		boolean WallWestIsHorizontal = WallTexWest.equals(spriteSheet.getTexture(SpriteType.WALL_WEST));

		assertTrue(WallNorthIsVertical && WallSouthIsVertical && WallEastIsHorizontal && WallWestIsHorizontal);
	}

}