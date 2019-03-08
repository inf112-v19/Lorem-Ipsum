package inf112.skeleton.app;

import inf112.skeleton.app.Exceptions.PlayerNotFoundException;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class BoardTest {
	private Board testBoard;
	private Player[] players;

	/**
	 * Setup method for creating a board to test with containing two players
	 *
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		testBoard = new Board("Boards/ExampleBoard.txt");
		players = testBoard.getAllPlayers();
		testBoard.placePlayerOnPos(players[0], new Position(0, 0));
		testBoard.placePlayerOnPos(players[1], new Position(0, 1));
	}

	/**
	 * TearDown method that resets the position of the players
	 *
	 * @throws Exception
	 */
	@After
	public void tearDown() throws Exception {
		testBoard.placePlayerOnPos(players[0], new Position(0, 0));
		testBoard.placePlayerOnPos(players[1], new Position(0, 1));
	}


	/**
	 * Testing that the posToPlayer returns the correct player - should player[0] since we know that the position
	 * of this player is (0, 0)
	 */
	@Test
	public void posToPlayerPlayer0Test() {
		Player shouldBePlayer0 = testBoard.posToPlayer(new Position(0,0));
		assertEquals(shouldBePlayer0, players[0]);
	}

	/**
	 * Testing that the posToPlayer returns null when given a position with no player present
	 */
	@Test
	public void posToPlayerNullTest() {
		Player shouldBeNull = testBoard.posToPlayer(new Position(5,5));
		assertEquals(shouldBeNull, null);
	}



	/**
	 * Testing that the isValidPos works returns false when given a position outside the board
	 */
	@Test
	public void isValidPosFalseTest() {
		Position outsideBoard = new Position(-1, -1);
		assertFalse(testBoard.isValidPos(outsideBoard));
	}

	/**
	 * Testing that the isValidPos works returns true when given a valid position on the board
	 */
	@Test
	public void isValidPosTrueTest() {
		Position outsideBoard = new Position(0, 0);
		assertTrue(testBoard.isValidPos(outsideBoard));
	}


	/**
	 * Testing that it is possible to move player[0] east two times and then south - should move without
	 * any collision or exception
	 *
	 * @throws PlayerNotFoundException
	 */
	@Test
	public void movePlayer0SouthTest() throws PlayerNotFoundException {
		testBoard.movePlayer(players[0], Direction.EAST);
		testBoard.movePlayer(players[0], Direction.EAST);
		testBoard.movePlayer(players[0], Direction.SOUTH);
		Player shouldBePlayer0 = testBoard.posToPlayer(new Position(2,1));

		assertEquals(shouldBePlayer0, players[0]);
	}

	/**
	 * Testing that it is possible to move player[0] south - should move and collide with player[1] pushing
	 * it to tile (0,2)
	 *
	 * @throws PlayerNotFoundException
	 */
	@Test
	public void movePlayerCollisionTest() throws PlayerNotFoundException {
		testBoard.movePlayer(players[0], Direction.SOUTH);
		Player shouldBePlayer1 = testBoard.posToPlayer(new Position(0,2));

		assertEquals(shouldBePlayer1, players[1]);
	}

	/**
	 * Testing that it is not possible to move a player where there is a wall - player[0] should still be
	 * in its starting position (0,0)
	 *
	 * @throws PlayerNotFoundException
	 */
	@Test
	public void movePlayerWallTest() throws PlayerNotFoundException {
		testBoard.movePlayer(players[0], Direction.WEST);
		Player shouldBePlayer0 = testBoard.posToPlayer(new Position(0,0));

		assertEquals(shouldBePlayer0, players[0]);
	}

	/**
	 * Testing that the getAllPlayers return an array of correct size
	 */
	@Test
	public void getAllPlayers() {
		Player[] players = testBoard.getAllPlayers();
		assertEquals(players.length, 2);
	}

}
