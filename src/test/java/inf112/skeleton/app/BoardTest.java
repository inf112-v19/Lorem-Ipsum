package inf112.skeleton.app;

import inf112.skeleton.app.Exceptions.PlayerNotFoundException;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
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
		testBoard = new Board("Boards/ExampleBoard.txt", 2);
		players = testBoard.getAllPlayers();
		testBoard.placePlayerOnPos(players[0], new Position(0, 0));
		testBoard.placePlayerOnPos(players[1], new Position(0, 1));
	}

	/**
	 * Testing that the MovePlayer method gives correct exception when Player is not found
	 */
	@Test(expected = PlayerNotFoundException.class)
	public void movePlayerPlayerNotFoundTest() throws PlayerNotFoundException {
		testBoard.movePlayer(new Player("test", Direction.NORTH), Direction.NORTH);
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
	 * Testing that it is possible to move player0 East - should move without any collision or exception
	 * @throws PlayerNotFoundException
	 */
	@Test
	public void movePlayer0SouthTest() throws PlayerNotFoundException {
		testBoard.movePlayer(players[0], Direction.EAST);
		Player shouldBePlayer0 = testBoard.posToPlayer(new Position(1,0));

		assertEquals(shouldBePlayer0, players[0]);
	}

}
