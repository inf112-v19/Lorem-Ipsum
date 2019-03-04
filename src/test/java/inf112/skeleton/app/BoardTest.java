package inf112.skeleton.app;

import inf112.skeleton.app.Exceptions.PlayerNotFoundException;
import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Board.BoardBuilder;
import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.GameObjects.GameObject;
import inf112.skeleton.app.GameMechanics.GameObjects.Laser;
import inf112.skeleton.app.GameMechanics.Player;
import inf112.skeleton.app.GameMechanics.Position;
import inf112.skeleton.app.GameMechanics.Tiles.NormalTile;
import inf112.skeleton.app.GameMechanics.Tiles.Tile;
import junit.framework.AssertionFailedError;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertArrayEquals;
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
	public void boardMovePlayerPlayerNotFoundTest() throws PlayerNotFoundException {
		testBoard.movePlayer(new Player("test", Direction.NORTH), Direction.NORTH);
	}



}
