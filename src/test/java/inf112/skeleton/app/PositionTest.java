package inf112.skeleton.app;

import inf112.skeleton.app.GameMechanics.Direction;
import inf112.skeleton.app.GameMechanics.Position;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class PositionTest {
	Position originPos = new Position(1,1);

	@Test
	public void getNorthNeighbourTest() {
		Position northPos = originPos.getNeighbour(Direction.NORTH);

		assertEquals(northPos, new Position(1,2));
	}

	@Test
	public void getSouthNeighbourTest() {
		Position northPos = originPos.getNeighbour(Direction.SOUTH);

		assertEquals(northPos, new Position(1,0));
	}

	@Test
	public void getEastNeighbourTest() {
		Position northPos = originPos.getNeighbour(Direction.EAST);

		assertEquals(northPos, new Position(2,1));
	}

	@Test
	public void getWestNeighbourTest() {
		Position northPos = originPos.getNeighbour(Direction.WEST);

		assertEquals(northPos, new Position(0,1));
	}
}
