package inf112.skeleton.app;

import inf112.skeleton.app.GameMechanics.Direction;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class DirectionTest {

	@Test
	public void getOppositeDirectionNorthTest() {
		Direction dir = Direction.NORTH;
		assertEquals(dir.oppositeDirection(), Direction.SOUTH);
	}

	@Test
	public void getOppositeDirectionSouthTest() {
		Direction dir = Direction.SOUTH;
		assertEquals(dir.oppositeDirection(), Direction.NORTH);
	}

	@Test
	public void getOppositeDirectionEastTest() {
		Direction dir = Direction.EAST;
		assertEquals(dir.oppositeDirection(), Direction.WEST);
	}

	@Test
	public void getOppositeDirectionWestTest() {
		Direction dir = Direction.WEST;
		assertEquals(dir.oppositeDirection(), Direction.EAST);
	}
}
