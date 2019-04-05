package inf112.skeleton.app.Visuals.States;

import inf112.skeleton.app.GameMechanics.Tiles.Tile;

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

	public State peek() {
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
	}

	public void render() {
		states.peek().render();
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

	public void stackEventHandle(com.badlogic.gdx.scenes.scene2d.ui.Stack stack) {
		states.peek().stackEventHandle(stack);
	}
}
