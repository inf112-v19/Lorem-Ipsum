package inf112.skeleton.app.Visuals.States;

import inf112.skeleton.app.GameMechanics.Tiles.Tile;

import java.util.EmptyStackException;
import java.util.Stack;

public class GameStateManager {

	private Stack<State> states;

	public GameStateManager() {
		states = new Stack<>();
	}

	public void push(State state) {
		states.push(state);
	}

	public State pop() {
		return states.pop();
	}

	public State peek() throws EmptyStackException {
		return states.peek();
	}

	public void set(State state) {
		states.peek().dispose();
		states.pop();
		states.push(state);
	}

	public void dispose() {
		states.peek().dispose();
	}

	public void update(float dt) {
		states.peek().update(dt);
		/*
		states.get(0).update(dt);
		if(states.size() > 1){
			states.get(1).update(dt);
		}
		*/

	}

	public void render() {
		states.peek().render();
		/*states.get(0).render();
		if(states.size() > 1){
			states.get(1).render();
		}
		 */

	}

	public int size() {
		return states.size();
	}

	public void resize() {
		states.peek().resize();
	}

	public void tileEventHandle(Tile tile) {
		states.peek().tileEventHandle(tile);
	}
}
