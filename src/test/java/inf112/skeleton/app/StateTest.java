package inf112.skeleton.app;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.Visuals.States.ActionState;
import inf112.skeleton.app.Visuals.States.GameStateManager;
import inf112.skeleton.app.Visuals.States.MenuState;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(GdxTestInit.class)
public class StateTest {

	private GameStateManager gsm;
	private Board board;
	@Before
	public void setUp() throws Exception {
		gsm = new GameStateManager();
		board = new Board("Boards/ExampleBoard.txt");

	}

	@Test
	public void pushedStateWillBeOnTopOfStack() {
		ActionState actionStaten = new ActionState(gsm,board);
		gsm.push(new MenuState(gsm, board));
		gsm.push(actionStaten);
		assertSame(gsm.pop(), actionStaten);
	}


}
