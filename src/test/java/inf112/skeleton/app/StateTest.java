package inf112.skeleton.app;

import inf112.skeleton.app.GameMechanics.Board.Board;
import inf112.skeleton.app.GameMechanics.Cards.CardManager;
import inf112.skeleton.app.Visuals.States.ActionState;
import inf112.skeleton.app.Visuals.States.CardState;
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
	private CardManager cardManager;
	@Before
	public void setUp() {
		gsm = new GameStateManager();
		board = new Board("Boards/ExampleBoard.txt");
		cardManager = new CardManager(board);
	}

	@Test
	public void pushStateTest() {
		ActionState actionState = new ActionState(gsm,board,cardManager);
		gsm.push(actionState);
		assertSame(gsm.peek(), actionState);
		assertEquals(gsm.size(), 1);

        CardState cardState = new CardState(gsm,board,cardManager);
        gsm.push(cardState);
        gsm.push(actionState);
        gsm.push(cardState);
        assertSame(gsm.peek(), cardState);
        assertEquals(gsm.size(), 4);
	}

	@Test
	public void popStateTest() {
		ActionState actionState = new ActionState(gsm,board,cardManager);
		gsm.push(new MenuState(gsm,board,cardManager));
		gsm.push(actionState);
		assertSame(gsm.pop(), actionState);
		assertEquals(gsm.size(), 1);
	}

    @Test
    public void peekStateTest() {
        ActionState actionState = new ActionState(gsm,board,cardManager);
        gsm.push(new MenuState(gsm,board,cardManager));
        gsm.push(actionState);
        assertSame(gsm.peek(), actionState);
		assertEquals(gsm.size(), 2);
    }

	@Test
	public void setStateTest() {
		ActionState actionState = new ActionState(gsm,board,cardManager);
		gsm.push(new MenuState(gsm,board,cardManager));
		gsm.set(actionState);
		assertSame(gsm.peek(), actionState);
		assertEquals(gsm.size(), 1);
	}

}
