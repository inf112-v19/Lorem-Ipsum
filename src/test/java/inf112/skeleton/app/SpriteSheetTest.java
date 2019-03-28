package inf112.skeleton.app;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import inf112.skeleton.app.GameMechanics.Cards.Card;
import inf112.skeleton.app.GameMechanics.Cards.CardType;
import inf112.skeleton.app.GameMechanics.Tiles.*;
import inf112.skeleton.app.Visuals.SpriteSheet;
import inf112.skeleton.app.Visuals.SpriteType;


import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.Laser;
import inf112.skeleton.app.GameMechanics.GameObjects.Wall;
import inf112.skeleton.app.GameMechanics.Player;
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
	public void initConveyorBeltTileCorrectTextureTest() {
		ConveyorBeltTile conveyorBeltTileNorth = new ConveyorBeltTile(null, Direction.NORTH);
		ConveyorBeltTile conveyorBeltTileEast = new ConveyorBeltTile(null, Direction.EAST);
		ConveyorBeltTile conveyorBeltTileSouth = new ConveyorBeltTile(null, Direction.SOUTH);
		ConveyorBeltTile conveyorBeltTileWest = new ConveyorBeltTile(null, Direction.WEST);

		assertEquals(conveyorBeltTileNorth.getSpriteType(), SpriteType.CONVEYOR_BELT_TILE_NORTH);
		assertEquals(conveyorBeltTileEast.getSpriteType(), SpriteType.CONVEYOR_BELT_TILE_EAST);
		assertEquals(conveyorBeltTileSouth.getSpriteType(), SpriteType.CONVEYOR_BELT_TILE_SOUTH);
		assertEquals(conveyorBeltTileWest.getSpriteType(), SpriteType.CONVEYOR_BELT_TILE_WEST);
	}

	@Test
	public void initDoubleConveyorBeltTileCorrectTextureTest() {
		DoubleConveyorBeltTile DoubleconveyorBeltTileNorth = new DoubleConveyorBeltTile(null, Direction.NORTH);
		DoubleConveyorBeltTile DoubleconveyorBeltTileEast = new DoubleConveyorBeltTile(null, Direction.EAST);
		DoubleConveyorBeltTile DoubleconveyorBeltTileSouth = new DoubleConveyorBeltTile(null, Direction.SOUTH);
		DoubleConveyorBeltTile DoubleconveyorBeltTileWest = new DoubleConveyorBeltTile(null, Direction.WEST);

		assertEquals(DoubleconveyorBeltTileNorth.getSpriteType(), SpriteType.DOUBLE_CONVEYOR_BELT_TILE_NORTH);
		assertEquals(DoubleconveyorBeltTileEast.getSpriteType(), SpriteType.DOUBLE_CONVEYOR_BELT_TILE_EAST);
		assertEquals(DoubleconveyorBeltTileSouth.getSpriteType(), SpriteType.DOUBLE_CONVEYOR_BELT_TILE_SOUTH);
		assertEquals(DoubleconveyorBeltTileWest.getSpriteType(), SpriteType.DOUBLE_CONVEYOR_BELT_TILE_WEST);
	}

	@Test
	public void initRotationRightTileCorrectTextureTest() {
		RotationRightTile RotationRightTile = new RotationRightTile(null, Direction.NORTH);
		assertEquals(RotationRightTile.getSpriteType(), SpriteType.ROTATION_RIGHT_TILE);
	}

	@Test
	public void initRepairTileCorrectTextureTest() {
		RepairTile repairTile = new RepairTile(null, Direction.NORTH);
		assertEquals(repairTile.getSpriteType(), SpriteType.REPAIR_TILE);
	}

	@Test
	public void initRotationLeftTileTileCorrectTextureTest() {
		RotationLeftTile RotationLeftTile = new RotationLeftTile(null, Direction.NORTH);
		assertEquals(RotationLeftTile.getSpriteType(), SpriteType.ROTATION_LEFT_TILE);

	}

	@Test
	public void initOptionsTileCorrectTextureTest(){
		OptionsTile optionsTile = new OptionsTile(null, Direction.NORTH);
		assertEquals(SpriteType.OPTIONS_TILE, optionsTile.getSpriteType());
	}

	@Test
	public void initSpawnTileCorrectTextureTest(){
		SpawnTile spawnTile = new SpawnTile(null , Direction.NORTH);
		assertEquals(SpriteType.SPAWN_TILE, spawnTile.getSpriteType());
	}

	@Test
	public void initHoleTileTextureTest() {
		HoleTile holeTile = new HoleTile(null, Direction.NORTH);
		TextureRegion textureRegion = spriteSheet.getTexture(holeTile.getSpriteType());
		assertNotNull(textureRegion);
	}

	@Test
	public void normalTileTextureIsEqualToNormalTileTextureTest() {
		NormalTile nTile = new NormalTile(null, Direction.NORTH);
		TextureRegion tile1 = spriteSheet.getTexture(nTile.getSpriteType());
		TextureRegion tile2 = spriteSheet.getTexture(SpriteType.NORMAL_TILE);
		assertEquals(tile1,tile2);
	}

	@Test
	public void holeTileIsNotNormalTileTextureTest() {
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
	public void wallTextureCorrectTest() {
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

	@Test
	public void playerHasTextureTest(){
		Player player = new Player("Player1", Direction.NORTH);
		assertNotEquals(player.getSpriteType(), null);
	}

	@Test
	public void playerTextureCorrectTest(){
		Player player = new Player("Player1", Direction.NORTH);
		SpriteType playerSprite = SpriteType.PLAYER1;
		assertEquals(player.getSpriteType(), playerSprite);
	}

	@Test
	public void differentPlayersHasDifferentSpritesTest(){
		Player player1 = new Player(0, "Player1", Direction.NORTH);
		Player player2 = new Player(1, "Player2", Direction.NORTH);

		assertNotEquals(player1.getSpriteType(), player2.getSpriteType());
	}

	@Test
	public void backwardCardTextureTest() {
		Card card = new Card(CardType.BACKWARD_1, 1);
		assertEquals(spriteSheet.getTexture(card), spriteSheet.getTexture(SpriteType.BACKWARD_1));
	}

	@Test
	public void forward1CardTextureTest(){
		Card card = new Card(CardType.FORWARD_1, 1);
		assertEquals(SpriteType.FORWARD_1, card.getSprite());
	}

	@Test
	public void forward2CardTextureTest(){
		Card card = new Card(CardType.FORWARD_2, 1);
		assertEquals(SpriteType.FORWARD_2, card.getSprite());
	}

	@Test
	public void forward3CardTextureTest(){
		Card card = new Card(CardType.FORWARD_3, 1);
		assertEquals(SpriteType.FORWARD_3, card.getSprite());
	}

	@Test
	public void rotateRightCardTextureTest(){
		Card card = new Card(CardType.ROTATE_90_R, 1);
		assertEquals(SpriteType.ROTATE_90_R, card.getSprite());
	}

	@Test
	public void rotateLeftCardTextureTest(){
		Card card = new Card(CardType.ROTATE_90_L, 1);
		assertEquals(SpriteType.ROTATE_90_L, card.getSprite());
	}

	@Test
	public void uTurnCardTextureTest(){
		Card card = new Card(CardType.ROTATE_180, 1);
		assertEquals(SpriteType.ROTATE_180, card.getSprite());
	}
	
}